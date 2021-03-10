package services;

import domain.Expense;

import java.time.LocalDateTime;
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

    public Float calculateSum(ArrayList<Expense> list){
        Float result = 0.0f;
        for (Expense expense: list)
            result += expense.getExpenseValue();

        return result;
    }

    public ArrayList<Expense> getExpensesOfGivenDate(LocalDateTime givenDate){
        ArrayList<Expense> resultList = new ArrayList<>();
        for (int i = 0; i < expensesList.size(); i++){
            if (expensesList.get(i).getExpenseDate().getMonth().equals(givenDate.getMonth()))
                resultList.add(expensesList.get(i));
        }
        return resultList;
    }

    @Override
    public String toString() {
        return "expensesList=" + expensesList ;
    }
}
