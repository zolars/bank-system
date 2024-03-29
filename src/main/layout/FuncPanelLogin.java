package layout;

import application.Main;
import database.dao.AccountDao;
import database.dao.impl.AccountDaoImpl;
import database.entity.Account;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * FuncPanelLogin
 *
 * @author Xin Yifei
 * @version 0.9
 */
public class FuncPanelLogin extends FuncPanelDefault implements ActionListener {

    private static final long serialVersionUID = 1L;

    private JTextField jtId;
    private JPasswordField jtPin;
    private JButton jbLogin = new JButton("Login !");

    public FuncPanelLogin() {
        super();
        if (Main.loginStatus != 0) {
        } else {
            setName("Login");

            setLayout(new GridLayout(8, 1, 0, 0));

            JPanel panel01 = new JPanel();
            JPanel panel02 = new JPanel();
            JPanel panel03 = new JPanel();
            JPanel panel04 = new JPanel();
            JPanel panel05 = new JPanel();
            JPanel panel06 = new JPanel();
            // JPanel panel07 = new JPanel();
            // JPanel panel08 = new JPanel();

            JLabel jlLogin = new JLabel("Please Login :");
            jlLogin.setFont(new java.awt.Font("Dialog", 1, 25));
            panel03.add(jlLogin);

            // Userid input
            JLabel jlUserid = new JLabel("User ID : ");
            jlUserid.setFont(new java.awt.Font("Dialog", 1, 25));
            panel04.add(jlUserid);
            jtId = new JTextField(15);
            panel04.add(jtId);

            // Password input
            JLabel jlPassword = new JLabel("Password : ");
            jlPassword.setFont(new java.awt.Font("Dialog", 1, 25));
            panel05.add(jlPassword);
            jtPin = new JPasswordField(15);
            panel05.add(jtPin);

            panel06 = new JPanel(new FlowLayout(FlowLayout.CENTER));
            // add Action Listener
            jbLogin.addActionListener(this);
            panel06.add(jbLogin);

            panel01.setOpaque(false);
            panel02.setOpaque(false);
            panel03.setOpaque(false);
            panel04.setOpaque(false);
            panel05.setOpaque(false);
            panel06.setOpaque(false);
            add(panel01);
            add(panel02);
            add(panel03);
            add(panel04);
            add(panel05);
            add(panel06);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbLogin) {
            AccountDao dao = new AccountDaoImpl();
            try {
                Account account = dao.findAccount(Integer.parseInt(jtId.getText()));
                if (!Pattern.compile("[0-9]+").matcher(jtId.getText()).matches() || account == null
                        || account.isActive() == false) {
                    JOptionPane.showMessageDialog(this,
                            "This account doesn't exist. Please try again.", "Sorry",
                            JOptionPane.WARNING_MESSAGE);
                } else if (!Pattern.compile("[0-9]+").matcher(String.valueOf(jtPin.getPassword()))
                        .matches()
                        || account.getPin() != Integer
                        .parseInt(String.valueOf(jtPin.getPassword()))) {
                    JOptionPane.showMessageDialog(this, "Your pin is invalid. Please try again.",
                            "Sorry",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    Main.loginStatus = Integer.parseInt(jtId.getText());
                    Main.restart = true;
                    JOptionPane.showMessageDialog(this, "Successfully Login!", "Congratulations",
                            JOptionPane.INFORMATION_MESSAGE);
                }

                jtId.setText("");
                jtPin.setText("");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Main.setup();
    }
}
