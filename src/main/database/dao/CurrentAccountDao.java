package database.dao;

import database.entity.CurrentAccount;
import database.entity.Customer;
import java.io.IOException;

/**
 * CurrentAccountDao
 */
public interface CurrentAccountDao extends AccountDao {

    public CurrentAccount findAccount(int id) throws IOException;

    public int addAccount(Customer customer) throws IOException;

}
