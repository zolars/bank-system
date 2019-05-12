package layout;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.*;

import application.*;
import database.dao.*;
import database.dao.impl.*;
import database.entity.*;

/**
 * FuncPanelRegister
 * 
 * @author Xin Yifei
 * @version 0.9
 */
public class FuncPanelRegister extends FuncPanelDefault implements ActionListener {
    private static final long serialVersionUID = 1L;
    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
    private DateChooser dateChooser = DateChooser.getInstance("yyyy-MM-dd");

    private JTextField jtUsername, jtAddress, jtDateOfBirth;
    private JButton jbRegister = new JButton("Register !");

    public FuncPanelRegister() {
        super();
        setName("Register");
        setLayout(new GridLayout(8, 1, 0, 0));
        JPanel panel01 = new JPanel();
        JPanel panel02 = new JPanel();
        JPanel panel03 = new JPanel();
        JPanel panel04 = new JPanel();
        JPanel panel05 = new JPanel();
        JPanel panel07 = new JPanel();

        // Username input
        JLabel jlUsername = new JLabel("Username : ");
        jlUsername.setFont(new java.awt.Font("Dialog", 1, 25));
        panel03.add(jlUsername);
        jtUsername = new JTextField(15);
        panel03.add(jtUsername);

        // Address input
        JLabel jlAddress = new JLabel("Address : ");
        jlAddress.setFont(new java.awt.Font("Dialog", 1, 25));
        panel04.add(jlAddress);
        jtAddress = new JTextField(15);
        panel04.add(jtAddress);

        // UserID input
        JLabel jlDateOfBirth = new JLabel("Date of Birth : ");
        jlDateOfBirth.setFont(new java.awt.Font("Dialog", 1, 25));
        panel02.add(jlDateOfBirth);
        jtDateOfBirth = new JTextField(15);
        jtDateOfBirth.setText("Click to choose birth date");
        dateChooser.register(jtDateOfBirth);
        panel02.add(jtDateOfBirth);

        panel05 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        // add Action Listener
        jbRegister.addActionListener(this);
        panel05.add(jbRegister);

        panel01.setOpaque(false);
        panel02.setOpaque(false);
        panel03.setOpaque(false);
        panel04.setOpaque(false);
        panel05.setOpaque(false);

        panel07.setOpaque(false);
        add(panel07);
        add(panel01);
        add(panel03);
        add(panel04);
        add(panel02);
        add(panel05);

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == jbRegister) {
            try {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(sf.parse(jtDateOfBirth.getText()));

                if (jtDateOfBirth.getText().equals("Click to choose birth date") || jtUsername.getText().equals("")
                        || jtAddress.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Invalid Input! Please try again.", "Sorry",
                            JOptionPane.WARNING_MESSAGE);
                } else if (calendar.after(Calendar.getInstance())) {
                    JOptionPane.showMessageDialog(null, "Wrong input at date of birth! Please try again.", "Sorry",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    Customer customer = new Customer(jtUsername.getText(), jtAddress.getText(), calendar);

                    Object[] choices = { "Current Account", "Saver Account", "Junior Account", "Cancel" };
                    int choiceNum = (int) JOptionPane.showOptionDialog(null,
                            "Which is the type of account you want to register?", "Register",
                            JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, choices,
                            choices[0]);

                    AccountDao dao = new AccountDaoImpl();

                    switch (choiceNum) {
                    case 0:
                        dao = new CurrentAccountDaoImpl();
                        break;
                    case 1:
                        dao = new SaverAccountDaoImpl();
                        break;
                    case 2:
                        dao = new JuniorAccountDaoImpl();
                        break;
                    case 3:
                        break;
                    }

                    int result = dao.addAccount(customer);
                    if (choiceNum == 3) {

                    } else if (result >= 1) {
                        JOptionPane.showMessageDialog(null,
                                "Congratulations! Register Successfully!\nYour id is : " + result + "\nYour pin is : "
                                        + dao.findAccount(result).getPin(),
                                "Congratulations!", JOptionPane.INFORMATION_MESSAGE);
                    } else if (result == 0) {
                        JOptionPane.showMessageDialog(null, "The user ID or user name has been used. Please try again.",
                                "Sorry", JOptionPane.WARNING_MESSAGE);
                    } else if (result == -1) {
                        JOptionPane.showMessageDialog(null,
                                "Your credit record is illegal. Please connect the administer.", "Sorry",
                                JOptionPane.WARNING_MESSAGE);
                    } else if (result == -3) {
                        JOptionPane.showMessageDialog(null,
                                "Your age is illegal for this account. Please choose the other ones.", "Sorry",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }

            jtDateOfBirth.setText("Click to choose birth date");
            jtUsername.setText("");
            jtAddress.setText("");
        }
    }

    public static void main(String[] args) {
        Main.setup();
    }
}