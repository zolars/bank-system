package database.entity;

/**
 * JuniorAccount
 */
public class JuniorAccount extends Account {
    public JuniorAccount() {
        setOverdraftLimit(0);
    }

    public JuniorAccount(Customer customer) {
        super(customer);
        setOverdraftLimit(0);
    }

    public JuniorAccount(Account account) {
        super(account);
    }

    @Override
    public void setOverdraftLimit(double overdraftLimit) {
        System.out.println(
                "Error: The JuniorAccount's overdraftLimit must be zero.\n\tat database.entity.JuniorAccount.setOverdraftLimit(JuniorAccount.java:21)");
        overdraftLimit = 0;
    }

}