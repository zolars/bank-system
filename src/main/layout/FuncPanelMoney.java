package layout;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.regex.Pattern;

import javax.swing.*;

import application.*;
import database.dao.*;
import database.dao.impl.*;
import database.entity.*;

/**
 * FuncPanelMoney
 * 
 * @author Xin Yifei
 * @version 0.9
 */
public class FuncPanelMoney extends FuncPanelDefault implements ActionListener {
    private static final long serialVersionUID = 1L;

    private JLabel jlBalance;
    private JButton jbDeposit = new JButton("Deposit");
    private JButton jbWithdraw = new JButton("Withdraw");
    private JButton jbSuspend = new JButton("Suspend or Unsuspend Account");
    private JButton jbDelete = new JButton("Delete Account");
    private JButton jbLogout = new JButton("Log out");

    public FuncPanelMoney() {
        super();
        if (Main.loginStatus == 0) {
        } else {
            setName("Money");

            setLayout(new GridLayout(10, 1, 0, 0));

            JPanel panel01 = new JPanel();
            JPanel panel02 = new JPanel();
            JPanel panel03 = new JPanel();
            JPanel panel04 = new JPanel();
            JPanel panel05 = new JPanel();
            JPanel panel06 = new JPanel();
            JPanel panel07 = new JPanel();

            jlBalance = new JLabel("", JLabel.CENTER);
            jlBalance.setFont(new java.awt.Font("Dialog", 1, 20));
            jlBalance.setOpaque(false);
            jlBalance.setForeground(Color.BLACK);
            jlBalance.setText("Loading...");
            add(jlBalance);

            panel03 = new JPanel(new FlowLayout(FlowLayout.CENTER));
            // add Action Listener
            jbDeposit.addActionListener(this);
            panel03.add(jbDeposit);

            panel04 = new JPanel(new FlowLayout(FlowLayout.CENTER));
            // add Action Listener
            jbWithdraw.addActionListener(this);
            panel04.add(jbWithdraw);

            panel05 = new JPanel(new FlowLayout(FlowLayout.CENTER));
            // add Action Listener
            jbSuspend.addActionListener(this);
            panel05.add(jbSuspend);

            panel06 = new JPanel(new FlowLayout(FlowLayout.CENTER));
            // add Action Listener
            jbDelete.addActionListener(this);
            panel06.add(jbDelete);

            panel07 = new JPanel(new FlowLayout(FlowLayout.CENTER));
            // add Action Listener
            jbLogout.addActionListener(this);
            panel07.add(jbLogout);

            panel01.setOpaque(false);
            panel02.setOpaque(false);
            panel03.setOpaque(false);
            panel04.setOpaque(false);
            panel05.setOpaque(false);
            panel06.setOpaque(false);
            panel07.setOpaque(false);
            add(panel01);
            add(panel02);
            add(jlBalance);
            add(panel03);
            add(panel04);
            add(panel05);
            add(panel06);
            add(panel07);
        }
    }

    public void actionPerformed(ActionEvent e) {
        int id = Main.loginStatus;
        AccountDao dao = new AccountDaoImpl();
        try {
            switch (dao.findAccountType(id)) {
            case 1:
                dao = new CurrentAccountDaoImpl();
                break;
            case 2:
                dao = new SaverAccountDaoImpl();
                break;
            case 3:
                dao = new JuniorAccountDaoImpl();
                break;
            }

            if (e.getSource() == jbDeposit) {
                String amountStr = JOptionPane.showInputDialog(null, "Please input your amount here...", "Input amount",
                        JOptionPane.PLAIN_MESSAGE);
                int amount = 0;

                if (!Pattern.compile("[0-9]+").matcher(amountStr).matches()) {
                    JOptionPane.showMessageDialog(null, "Your input is invalid. Please try again.", "Sorry",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    amount = Integer.parseInt(amountStr);
                    int result;
                    Object[] choices = { "Cash", "Cheque" };
                    if ((int) JOptionPane.showOptionDialog(null, "Please choose your type of deposit :", "Choose Type",
                            JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, choices,
                            choices[0]) == 0) {
                        result = dao.addDeposit(id, amount, "cash");
                    } else {
                        result = dao.addDeposit(id, amount, "cheque");
                    }

                    switch (result) {
                    case 1:
                        JOptionPane.showMessageDialog(null, "Successfully Deposit!", "Congratulations",
                                JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case 0:
                        JOptionPane.showMessageDialog(null, "This account doesn't exist. Please try again.", "Sorry",
                                JOptionPane.WARNING_MESSAGE);
                        break;
                    case -3:
                        JOptionPane.showMessageDialog(null,
                                "Your account is suspended. Please adjust the status first.", "Sorry",
                                JOptionPane.WARNING_MESSAGE);
                        break;
                    }
                }
            }

            if (e.getSource() == jbWithdraw) {

                String pinStr = JOptionPane.showInputDialog(null, "Please input your pin here...", "Input pin",
                        JOptionPane.PLAIN_MESSAGE);

                int pin = 0;

                if (!Pattern.compile("[0-9]+").matcher(pinStr).matches()) {
                    JOptionPane.showMessageDialog(null, "Your input is invalid. Please try again.", "Sorry",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    pin = Integer.parseInt(pinStr);
                }

                String amountStr = JOptionPane.showInputDialog(null, "Please input your amount here...", "Input amount",
                        JOptionPane.PLAIN_MESSAGE);
                double amount = 0;

                if (!Pattern.compile("[0-9]+").matcher(amountStr).matches()) {
                    JOptionPane.showMessageDialog(null, "Your input is invalid. Please try again.", "Sorry",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    amount = Double.parseDouble(amountStr);
                }

                switch (dao.addWithdral(id, pin, amount)) {
                case 1:
                    JOptionPane.showMessageDialog(null, "Successfully Withdraw!", "Congratulations",
                            JOptionPane.INFORMATION_MESSAGE);
                    break;
                case 0:
                    JOptionPane.showMessageDialog(null, "This account doesn't exist. Please try again.", "Sorry",
                            JOptionPane.WARNING_MESSAGE);
                    break;
                case -1:
                    JOptionPane.showMessageDialog(null, "Your operation caused an overrun. Please check your balance.",
                            "Sorry", JOptionPane.WARNING_MESSAGE);
                    break;
                case -2:
                    JOptionPane.showMessageDialog(null, "Your pin is invalid. Please try again.", "Sorry",
                            JOptionPane.WARNING_MESSAGE);
                    break;
                case -3:
                    JOptionPane.showMessageDialog(null, "Your account is suspended. Please adjust the status first.",
                            "Sorry", JOptionPane.WARNING_MESSAGE);
                    break;
                case -4:
                    if (JOptionPane.showConfirmDialog(null,
                            "You haven't done a order for withdrawal.\nWould you want to order a withdrawal for "
                                    + amount + " dollars on tommorrow?",
                            "Order", JOptionPane.WARNING_MESSAGE) == 0) {
                        if (dao.addWithdral(id, -1, amount) == 1)
                            JOptionPane.showMessageDialog(null, "Successfully Appointment!", "Congratulations",
                                    JOptionPane.INFORMATION_MESSAGE);
                    }
                    break;
                case -5:
                    if (JOptionPane.showConfirmDialog(null,
                            "You have done a order for withdrawal.\nWould you want to withdraw the amount you last ordered in current?",
                            "Ordered", JOptionPane.WARNING_MESSAGE) == 0) {
                        if (dao.addWithdral(id, pin, -1) == 1)
                            JOptionPane.showMessageDialog(null, "Successfully withdrawal!", "Congratulations",
                                    JOptionPane.INFORMATION_MESSAGE);
                    }
                    break;
                case -6:
                    JOptionPane.showMessageDialog(null,
                            "You have done a order for withdrawal. But not yet agreed time in present.", "Sorry",
                            JOptionPane.WARNING_MESSAGE);
                    break;
                }

            }

            if (e.getSource() == jbSuspend) {
                String pinStr = JOptionPane.showInputDialog(null, "Please input your pin here...", "Input pin",
                        JOptionPane.PLAIN_MESSAGE);

                int pin = 0;

                if (!Pattern.compile("[0-9]+").matcher(pinStr).matches()) {
                    JOptionPane.showMessageDialog(null, "Your pin is invalid. Please try again.", "Sorry",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    pin = Integer.parseInt(pinStr);
                    switch (dao.adjustSuspendedAccount(id, pin)) {
                    case 1:
                        JOptionPane.showMessageDialog(null, "Successfully Operation!", "Congratulations",
                                JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case 0:
                        JOptionPane.showMessageDialog(null, "This account doesn't exist. Please try again.", "Sorry",
                                JOptionPane.WARNING_MESSAGE);
                        break;
                    case -2:
                        JOptionPane.showMessageDialog(null, "Your pin is invalid. Please try again.", "Sorry",
                                JOptionPane.WARNING_MESSAGE);
                        break;
                    }
                }
            }

            if (e.getSource() == jbDelete) {
                String pinStr = JOptionPane.showInputDialog(null, "Please input your pin here...", "Input pin",
                        JOptionPane.PLAIN_MESSAGE);

                int pin = 0;

                if (!Pattern.compile("[0-9]+").matcher(pinStr).matches()) {
                    JOptionPane.showMessageDialog(null, "Your pin is invalid. Please try again.", "Sorry",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    pin = Integer.parseInt(pinStr);
                    int result = dao.deleteAccount(id, pin);
                    if (result == -2) {
                        JOptionPane.showMessageDialog(null, "Your pin is invalid. Please try again.", "Sorry",
                                JOptionPane.WARNING_MESSAGE);
                    } else if (result == -1) {
                        JOptionPane.showMessageDialog(null, "Your balance is not null. Please try again.", "Sorry",
                                JOptionPane.WARNING_MESSAGE);
                    } else if (result == 0) {
                        JOptionPane.showMessageDialog(null, "This account doesn't exist. Please try again.", "Sorry",
                                JOptionPane.WARNING_MESSAGE);
                    } else if (result == 1) {
                        Main.loginStatus = 0;
                        Main.restart = true;
                        JOptionPane.showMessageDialog(null, "Successfully Delete! You are logging out.",
                                "Congratulations", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }

            if (e.getSource() == jbLogout) {
                Main.loginStatus = 0;
                Main.restart = true;
                JOptionPane.showMessageDialog(null, "Successfully Log out!", "Congratulations",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void updateUI() {
        super.updateUI();
        AccountDao dao = new AccountDaoImpl();
        try {
            int id = Main.loginStatus;
            Account account = dao.findAccount(id);
            if (account.isSuspended())
                jlBalance.setText("Your account is suspended!");
            else
                jlBalance.setText("Your balance is : " + String.valueOf(account.getBalance()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Main.setup();
    }
}