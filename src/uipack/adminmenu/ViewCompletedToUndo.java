package uipack.adminmenu;

import controllerpresenterpack.ControllerPresenterGrouper;
import controllerpresenterpack.UseCaseGrouper;
import entitypack.Frame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A UI class that displays the details of a completed trade and allows the administrative user to undo it
 */
public class ViewCompletedToUndo {
    private JTextPane tradeToString;
    private JPanel mainPanel;
    private JButton undoButton;
    private JButton backButton;
    private JLabel tradeLabel;

    /**
     * Constructs the interface to view the completed trade with tradeID and to undo it
     * @param useCases the use case grouper
     * @param cpg the controller presenter grouper
     * @param tradeID the ID of the trade
     * @param window the main window displayed to the administrative user of the program
     */
    public ViewCompletedToUndo(UseCaseGrouper useCases, ControllerPresenterGrouper cpg, int tradeID, JFrame window) {
        window.setContentPane(mainPanel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);

        tradeLabel.setText(cpg.menuPresenter.getText(Frame.ADMINVIEWTRADE, 5));
        tradeToString.setText(cpg.menuPresenter.printTradeToString(useCases.itemManager, useCases.tradeCreator.searchTrades(tradeID)));
        undoButton.setText(cpg.menuPresenter.getText(Frame.ADMINVIEWTRADE, 6));
        backButton.setText(cpg.menuPresenter.getText(Frame.ADMINVIEWTRADE, 7));

        undoButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cpg.adminActions.editTrade(tradeID, "Completed", useCases.adminUser, useCases.tradeCreator,
                        useCases.itemManager, useCases.userManager);
                JOptionPane.showMessageDialog(window, cpg.menuPresenter.getText(Frame.ADMINVIEWTRADE, 8));
                new AdminActionsMenu(useCases, cpg, window);
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewCompletedTrades(useCases, cpg, window);
            }
        });
    }
}
