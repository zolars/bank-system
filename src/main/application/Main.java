package application;

import java.io.IOException;

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
        CurrentAccountDao dao = new CurrentAccountDaoImpl();

        try {
            CurrentAccount account = new CurrentAccount();
            account.setId(111);
            account.setBalance(200);
            account.setOverdraftLimit(100);

            dao.addAccount(account);

            System.out.println(account.getPin());

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