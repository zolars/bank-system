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
            if (jtDateOfBirth.getText().equals("") || jtUsername.getText().equals("")
                    || jtAddress.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Invalid Input! Please try again.", "Sorry",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                try {
                    AccountDao dao = new AccountDaoImpl();
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(sf.parse(jtDateOfBirth.getText()));
                    Customer customer = new Customer(jtUsername.getText(), jtAddress.getText(), calendar);

                    int result = dao.addAccount(customer);
                    if (result == 1) {
                        JOptionPane.showMessageDialog(null, "Congratulations! Register Successfully!",
                                "Congratulations!", JOptionPane.WARNING_MESSAGE);
                    } else if (result == 0) {
                        JOptionPane.showMessageDialog(null, "The user ID or user name has been used. Please try again.",
                                "Sorry", JOptionPane.WARNING_MESSAGE);
                    } else if (result == -1) {
                        JOptionPane.showMessageDialog(null,
                                "Your credit record is illegal. Please connect the administer.", "Sorry",
                                JOptionPane.WARNING_MESSAGE);
                    }

                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }

            jtUsername.setText("");
            jtAddress.setText("");
        }
    }

    public static void main(String[] args) {
        Main.setup();
    }
}