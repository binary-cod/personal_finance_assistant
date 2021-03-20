package domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Income {

    private String ID;
    private String name;
    private float value;
    private LocalDateTime incomeDate;

    public Income(){}

    public Income(String name, float value, LocalDateTime incomeDate) {
        this.ID = UUID.randomUUID().toString();
        this.name = name;
        this.value = value;
        this.incomeDate = incomeDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public LocalDateTime getIncomeDate() {
        return incomeDate;
    }

    public void setIncomeDate(LocalDateTime incomeDate) {
        this.incomeDate = incomeDate;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {

        return "I;"
                + ID + ';'
                + name + ';'
                + value + ';'
                + incomeDate;
    }
}
