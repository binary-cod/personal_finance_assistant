package services;

public class ExpenseService {
    private float[] expenses;
    private int expenseLevel;

    public ExpenseService(int size){
        expenses = new float[size];
        expenseLevel = 0;
    }

    public boolean insertExpense(float value) {
        if (expenseLevel < expenses.length) {
            expenses[expenseLevel] = value;
            expenseLevel++;
            return true;
        }
        return false;
    }

    public float[] getAllExpenses(){
        return expenses;
    }

    public int numberOfExpense(){
        return expenseLevel;
    }
}
