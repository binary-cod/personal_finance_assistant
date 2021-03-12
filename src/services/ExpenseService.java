package services;

import domain.Expense;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

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
    
    public List<Expense> filterListByDate(LocalDateTime givenDate){
        List<Expense> filteredList = expensesList
                .stream()  // create stream
                .filter(expense -> expense.getExpenseDate().getMonth() == givenDate.getMonth()) // apply filter
                .sorted() // sorting
                .collect(Collectors.toList());  // terminal operation
        return filteredList;
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
