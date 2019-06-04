package database.entity;

import application.Main;

/**
 * CurrentAccount
 */
public class CurrentAccount extends Account {

    public CurrentAccount() {
        setOverdraftLimit(Main.defaultOverdraftLimit);
    }

    public CurrentAccount(Customer customer) {
        super(customer);
        setOverdraftLimit(Main.defaultOverdraftLimit);
    }

    public CurrentAccount(Account account) {
        super(account);
        setOverdraftLimit(Main.defaultOverdraftLimit);
    }
}
