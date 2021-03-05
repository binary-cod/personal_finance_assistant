package domain;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Income {
    private String name;
    private float value;
    private long incomeDate;

    private SimpleDateFormat dtf = new SimpleDateFormat("yyyy-MM-dd:HH.mm.ss");

    public Income(String name, float value, long incomeDate) {
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

    public long getIncomeDate() {
        return incomeDate;
    }

    public void setIncomeDate(long incomeDate) {
        this.incomeDate = incomeDate;
    }

    @Override
    public String toString() {

        return "Income{" +
                "name='" + name + '\'' +
                ", value=" + value +
                ", incomeDate=" + dtf.format(new Timestamp(incomeDate)) +
                '}';
    }
}
