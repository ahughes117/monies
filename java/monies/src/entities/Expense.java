package entities;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * The Expense Entity File
 * 
 * @author ahughes
 */
public class Expense extends Entity {
    
    private int expenseID = NIL;
    private double amount;
    private String description;
    private Date date;
    private Timestamp dateCreated;
    private Timestamp dateModified;

    /**
     * Constructor for fetching expenses
     * 
     * @param expenseID
     * @param amount
     * @param description
     * @param date
     * @param dateCreated
     * @param dateModified 
     */
    public Expense(int expenseID, double amount, String description, Date date, Timestamp dateCreated, Timestamp dateModified) {
        this.expenseID = expenseID;
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
    }

    /**
     * Constructor for inserting / updating expenses
     * 
     * @param expenseID
     * @param amount
     * @param description
     * @param date 
     */
    public Expense(int expenseID, double amount, String description, Date date) {
        this.expenseID = expenseID;
        this.amount = amount;
        this.description = description;
        this.date = date;
    }

    public int getExpenseID() {
        return expenseID;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public Timestamp getDateModified() {
        return dateModified;
    }
}
