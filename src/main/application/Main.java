package application;

import java.io.IOException;
import java.util.Calendar;

import database.dao.*;
import database.dao.impl.*;
import database.entity.*;

/**
 * Main
 */
public class Main {
    public static final double version = 0.1;
    public static final String filePath = "resource/";

    private static void test() {
        JuniorAccountDao dao = new JuniorAccountDaoImpl();

        try {

            Calendar dateOfBirth = Calendar.getInstance();
            dateOfBirth.set(2011, 3 - 1, 9);
            Customer customer = new Customer("aa", "aa", dateOfBirth);
            customer.setCreditStatus(true);

            JuniorAccount account = new JuniorAccount(customer);

            System.out.println(dao.addAccount(account));

            JuniorAccount readAccount = dao.findAccount(2);

            dao.addDeposit(readAccount, 10.0, "cheque");
            dao.addDeposit(readAccount, 20.0, "cash");
            dao.addDeposit(readAccount, 10.0, "cheque");
            dao.addDeposit(readAccount, 10.0, "cash");

            dao.clearFundsAll();

            System.out.println(dao.findAccount(2).getBalance());

            dao.deleteAccount(account);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        test();
        System.out.println("done");
    }
}