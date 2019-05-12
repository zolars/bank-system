package database.entity;

/**
 * SaverAccount
 */
public class SaverAccount extends Account {

    private double availableAmount;

    public SaverAccount() {
        setOverdraftLimit(0);
    }

    public SaverAccount(Customer customer) {
        super(customer);
        setOverdraftLimit(0);
    }

    public SaverAccount(Account account) {
        super(account);
        setOverdraftLimit(0);
    }

    /**
     * @param availableAmount the availableAmount to set
     */
    public void setAvailableAmount(double availableAmount) {
        this.availableAmount = availableAmount;
    }

    /**
     * @return the availableAmount
     */
    public double getAvailableAmount() {
        return availableAmount;
    }

    @Override
    public String toString() {
        return "0\t|\t" + getPin() + "\t|\t" + getBalance() + "\t|\t" + getOverdraftLimit() + "\t|\t" + isSuspended()
                + "\t|\t" + isActive() + "\t|\t" + getAvailableAmount();
    }

}