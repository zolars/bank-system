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

            dao.addAccount(customer);

            dao.addDeposit(1, 10.0, "cheque");
            dao.addDeposit(1, 20.0, "cash");
            if (dao.addWithdral(1, 12, 9.0) == -1)
                System.out.println("overrun");
            if (dao.addWithdral(1, 12, 9.0) == -1)
                System.out.println("overrun");
            if (dao.addWithdral(1, 12, 9.0) == -1)
                System.out.println("overrun");
            if (dao.addWithdral(1, 12, 9.0) == -1)
                System.out.println("overrun");
            if (dao.addWithdral(1, 12, 9.0) == -1)
                System.out.println("overrun");
            if (dao.addWithdral(1, 12, 9.0) == -1)
                System.out.println("overrun");

            dao.clearFundsAll();

            System.out.println(cdao.addAccount(customer, 250));

            cdao.addWithdral(2, 565582, 100);
            cdao.addWithdral(2, 565582, 100);
            cdao.addWithdral(2, 565582, 100);
            cdao.addWithdral(2, 565582, 100);
            cdao.addWithdral(2, 565582, 100);

            cdao.adjustSuspendedAccount(2, 565582);

            // System.out.println(dao.findAccount(2).getBalance());

        } catch (

        IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        test();
        System.out.println("done");
    }
}