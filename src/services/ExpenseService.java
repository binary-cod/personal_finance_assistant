package services;

import domain.Expense;

import java.util.ArrayList;

public class ExpenseService implements Service<Expense> {

    private ArrayList<Expense> expensesList = new ArrayList<>();

    public Boolean insert(Expense value) {
        return expensesList.add(value);
    }

    public ArrayList<Expense> getData(){
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
