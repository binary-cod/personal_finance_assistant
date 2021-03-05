package services;

import java.util.ArrayList;

public class ExpenseService {

    private ArrayList<Float> expensesList = new ArrayList<>();

    public boolean insertExpense(float value) {
        return expensesList.add(value);
    }

    public ArrayList<Float> getAllExpenses(){
        return expensesList;
    }

    public int numberOfExpense(){
        return expensesList.size();
    }

    @Override
    public String toString() {
        return "expensesList=" + expensesList ;
    }
}
