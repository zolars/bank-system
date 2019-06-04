package database.dao;

import database.entity.Customer;
import database.entity.JuniorAccount;
import java.io.IOException;

/**
 * JuniorAccountDao
 */
public interface JuniorAccountDao extends AccountDao {

    public JuniorAccount findAccount(int id) throws IOException;

    public int addAccount(Customer customer) throws IOException;

}
