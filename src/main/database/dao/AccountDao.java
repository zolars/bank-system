package database.dao;

import java.io.IOException;

import database.entity.*;

/**
 * AccountDao
 */
public interface AccountDao {

    public Account findAccount(int id) throws IOException;

    public int addAccount(Account account) throws IOException;

    public boolean deleteAccount(Account account) throws IOException;

    public boolean addDeposit(Account account, double num, String depositType) throws IOException;

    // public boolean addWithdral(Account account, double num) throws IOException;

    public boolean clearFundsByAccount(Account account) throws IOException;

    public boolean clearFundsAll() throws IOException;

}