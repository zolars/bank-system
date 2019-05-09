package database.dao;

import java.io.IOException;

import database.entity.*;

/**
 * AccountDao
 */
public interface AccountDao {

    public Account findAccount(int id) throws IOException;

    public int addAccount(Customer customer) throws IOException;

    public boolean deleteAccount(int id, int pin) throws IOException;

    public boolean addDeposit(int id, double num, String depositType) throws IOException;

    public int addWithdral(int id, int pin, double num) throws IOException;

    public boolean clearFundsByAccount(int id) throws IOException;

    public boolean clearFundsAll() throws IOException;

}