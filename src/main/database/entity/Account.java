package database.entity;

import java.util.Random;

/**
 * Account
 */
public class Account {

    private int id;
    private int pin;
    private Customer customer;
    private double balance;
    private double overdraftLimit;
    private boolean isSuspended;
    private boolean isActive;

    public Account() {
    }

    public Account(Customer customer) {
        generatePin();
        this.customer = customer;
        this.isSuspended = false;
        this.isActive = true;
    }

    public Account(Account account) {
        this.id = account.id;
        this.pin = account.pin;
        this.balance = account.balance;
        this.overdraftLimit = account.overdraftLimit;
        this.isSuspended = account.isSuspended;
        this.isActive = account.isActive;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * generate pin
     */
    public void generatePin() {
        Random r = new Random();
        this.pin = (100000 + r.nextInt(900000));
    }

    /**
     * @param pin the pin to set
     */
    public void setPin(int pin) {
        this.pin = pin;
    }

    /**
     * @return the pin
     */
    public int getPin() {
        return pin;
    }

    /**
     * @param customer the customer to set
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * @return the balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * @param overdraftLimit the overdraftLimit to set
     */
    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }

    /**
     * @return the overdraftLimit
     */
    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    /**
     * @param isSuspended the isSuspended to set
     */
    public void setSuspended(boolean isSuspended) {
        this.isSuspended = isSuspended;
    }

    /**
     * @return the isSuspended
     */
    public boolean isSuspended() {
        return isSuspended;
    }

    /**
     * @param isActive the isActive to set
     */
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * @return the isActive
     */
    public boolean isActive() {
        return isActive;
    }

    @Override
    public String toString() {
        return "0\t|\t" + pin + "\t|\t" + balance + "\t|\t" + overdraftLimit + "\t|\t" + isSuspended + "\t|\t"
                + isActive;
    }

    public String toFileName() {
        return id + ".txt";
    }

    public static void main(String[] args) {

    }

}