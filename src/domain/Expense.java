package domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Expense {

    private String ID;
    private String expenseName;
    private Float expenseValue;
    private LocalDateTime expenseDate;

    public Expense(String expenseName, Float expenseValue, LocalDateTime expenseDate) {
        this.ID = UUID.randomUUID().toString();
        this.expenseName = expenseName;
        this.expenseValue = expenseValue;
        this.expenseDate = expenseDate;
    }

    public Expense() {

    }

    public String getExpenseName() {
        return expenseName;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    public Float getExpenseValue() {
        return expenseValue;
    }

    public void setExpenseValue(Float expenseValue) {
        this.expenseValue = expenseValue;
    }

    public LocalDateTime getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(LocalDateTime expenseDate) {
        this.expenseDate = expenseDate;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "E;"
                +getID()+";"
                +getExpenseName()+";"
                +getExpenseValue()+";"
                +getExpenseDate();
    }

}
