package domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Income {
    private String name;
    private float value;
    private LocalDateTime incomeDate;

    public Income(String name, float value, LocalDateTime incomeDate) {
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

    @Override
    public String toString() {

        return "Income{" +
                "name='" + name + '\'' +
                ", value=" + value +
                ", incomeDate=" + incomeDate.format(DateTimeFormatter.ofPattern("YYYY-MM-DD:hh:mm:ss")) +
                '}';
    }
}
