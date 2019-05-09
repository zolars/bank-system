package database.dao;

import java.io.IOException;

import database.entity.*;

/**
 * CurrentAccountDao
 */
public interface CurrentAccountDao extends AccountDao {

    public CurrentAccount findAccount(int id) throws IOException;

    public int addAccount(Customer customer, double overdraftLimit) throws IOException;

}