package database.entity;

import java.util.Calendar;

/**
 * Customer
 */
public class Customer {
    private String name;
    private String address;
    private Calendar dateOfBirth;
    private boolean creditStatus;

    public Customer() {
    }

    public Customer(String name, String address, Calendar dateOfBirth) {
        this.name = name;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        creditStatus = false;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param dateOfBirth the dateOfBirth to set
     */
    public void setDateOfBirth(Calendar dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * @return the dateOfBirth
     */
    public Calendar getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @param creditStatus the creditStatus to set
     */
    public void setCreditStatus(boolean creditStatus) {
        this.creditStatus = creditStatus;
    }

    /**
     * @return the creditStatus
     */
    public boolean isCreditStatus() {
        return creditStatus;
    }

}