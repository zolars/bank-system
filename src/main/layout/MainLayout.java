package layout;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

import application.*;
import database.dao.AccountDao;
import database.dao.impl.AccountDaoImpl;

/**
 * MainLayout
 * 
 * @author Xin Yifei
 * @version 0.9
 */
public class MainLayout extends JFrame {

    private static final long serialVersionUID = 1L;
    private LinkedList<JPanel> funcSet;
    private JPanel controlPanel = new JPanel();
    private JPanel displayPanel = new JPanel();
    private CardLayout cardLayout = new CardLayout();

    public MainLayout(LinkedList<JPanel> funcSet) {
        this.funcSet = funcSet;
        init();
    }

    private void init() {
        setLayout(new BorderLayout());

        add(displayPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
        displayPanel.setLayout(cardLayout);
        controlPanel.setPreferredSize(new Dimension(0, 65));
        controlPanel.setLayout(new GridLayout());
        controlPanel.setBackground(Color.BLACK);

        setSize(700, 450);
        setMinimumSize(new Dimension(300, 200));
        setTitle("Bank System " + Main.version);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        for (int i = 0; i < this.funcSet.size(); i++) {
            addFunc(this, this.funcSet.get(i).getName(), this.funcSet.get(i));
        }

        sync();
    }

    /**
     * Refresh and Sync job
     */
    private void sync() {
        while (true)
            try {

                // Refresh all Layout
                if (Main.restart) {
                    Thread.sleep(Main.freshInterval);
                    Main.restart = false;
                    dispose();
                    Main.setup();
                }

                // Update UI. Especially the data on JLabel
                for (int i = 0; i < this.funcSet.size(); i++) {
                    this.funcSet.get(i).updateUI();
                }

                // clear the cheque
                if (Calendar.getInstance().getTime().getTime() % 5 == 0) {
                    AccountDao dao = new AccountDaoImpl();
                    dao.clearFundsAll();
                }

                // Set time interval
                Thread.sleep(Main.freshInterval);

            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    // A new function label page in order to increase new functions easily.
    public void addFunc(MainLayout mainLayout, String labelPageName, JPanel funcPanel) {
        JPanel controlPanel = mainLayout.getControlPanel();
        JPanel displayPanel = mainLayout.getDisplayPanel();
        CardLayout cardLayout = mainLayout.getCardLayout();

        displayPanel.add(labelPageName, funcPanel);
        JButton btn = new JButton(labelPageName);
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(displayPanel, labelPageName);
                setTitle("Bank System " + Main.version + " " + labelPageName);
            }
        });

        controlPanel.add(btn);

        mainLayout.setControlPanel(controlPanel);
        mainLayout.setDisplayPanel(displayPanel);
        mainLayout.setCardLayout(cardLayout);
    }

    /**
     * @param controlPanel the controlPanel to set
     */
    public void setControlPanel(JPanel controlPanel) {
        this.controlPanel = controlPanel;
    }

    /**
     * @return the controlPanel
     */
    public JPanel getControlPanel() {
        return controlPanel;
    }

    /**
     * @param displayPanel the displayPanel to set
     */
    public void setDisplayPanel(JPanel displayPanel) {
        this.displayPanel = displayPanel;
    }

    /**
     * @return the displayPanel
     */
    public JPanel getDisplayPanel() {
        return displayPanel;
    }

    /**
     * @param cardLayout the cardLayout to set
     */
    public void setCardLayout(CardLayout cardLayout) {
        this.cardLayout = cardLayout;
    }

    /**
     * @return the cardLayout
     */
    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public static void main(String[] args) {
        Main.setup();
    }
}