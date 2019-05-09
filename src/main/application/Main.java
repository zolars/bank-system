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
        CurrentAccountDao cdao = new CurrentAccountDaoImpl();

        try {
            Calendar dateOfBirth = Calendar.getInstance();
            dateOfBirth.set(2011, 3 - 1, 9);
            Customer customer = new Customer("aa", "aa", dateOfBirth);
            customer.setCreditStatus(true);

            dao.addAccount(new JuniorAccount(customer));

            JuniorAccount readAccount = dao.findAccount(1);
            readAccount.setOverdraftLimit(200);

            dao.addDeposit(readAccount, 10.0, "cheque");
            dao.addDeposit(readAccount, 20.0, "cash");
            if (dao.addWithdral(readAccount, 9.0) == -1)
                System.out.println("overrun");
            if (dao.addWithdral(readAccount, 9.0) == -1)
                System.out.println("overrun");
            if (dao.addWithdral(readAccount, 9.0) == -1)
                System.out.println("overrun");
            if (dao.addWithdral(readAccount, 9.0) == -1)
                System.out.println("overrun");
            if (dao.addWithdral(readAccount, 9.0) == -1)
                System.out.println("overrun");
            if (dao.addWithdral(readAccount, 9.0) == -1)
                System.out.println("overrun");

            dao.clearFundsAll();

            CurrentAccount account = new CurrentAccount(customer);

            account.setOverdraftLimit(200);

            System.out.println(cdao.addAccount(account));

            CurrentAccount currentAccount = cdao.findAccount(88);

            if (currentAccount != null) {

                cdao.addWithdral(currentAccount, 100);
                cdao.addWithdral(currentAccount, 100);
                cdao.addWithdral(currentAccount, 100);
                cdao.addWithdral(currentAccount, 100);
                cdao.addWithdral(currentAccount, 100);

                System.out.println(dao.findAccount(2).getBalance());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        test();
        System.out.println("done");
    }
}