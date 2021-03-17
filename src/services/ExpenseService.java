package services;

import domain.Expense;
import domain.Income;
import repo.FileRepo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ExpenseService implements Service<Expense> {

    private FileRepo fileRepo = new FileRepo();

    private ArrayList<Expense> expensesList = new ArrayList<>();

    public Boolean insert(Expense value) {
        return expensesList.add(value);
    }

    public ArrayList<Expense> getData() {
        return expensesList;
    }

    public int numberOfExpense() {
        return expensesList.size();
    }

    public Float calculateSum(ArrayList<Expense> list) {
        Float result = 0.0f;
        for (Expense expense : list)
            result += expense.getExpenseValue();

        return result;
    }

    public Double calculateSumByStreams(ArrayList<Expense> list) {
        return list
                .stream()  //create stream
                //.map(expense -> expense.getExpenseName().toUpperCase(Locale.ROOT))
                .mapToDouble(e -> e.getExpenseValue().doubleValue()) // apply operations
                .sum(); // terminal operation, to terminate stream
    }

    public List<Expense> filterListByDate(LocalDateTime givenDate) {
        Predicate<Expense> expensePredicate = expense ->
                expense.getExpenseDate().getMonth() == givenDate.getMonth();

        List<Expense> filteredList = expensesList
                .stream()  // create stream
                .filter(expensePredicate) // apply filter
                .sorted((o1, o2) -> o1.getExpenseDate().compareTo(o2.getExpenseDate())) // sorting
                .collect(Collectors.toList());  // terminal operation
        return filteredList;
    }

    public ArrayList<Expense> getExpensesOfGivenDate(LocalDateTime givenDate) {
        ArrayList<Expense> resultList = new ArrayList<>();
        for (int i = 0; i < expensesList.size(); i++) {
            if (expensesList.get(i).getExpenseDate().getMonth().equals(givenDate.getMonth()))
                resultList.add(expensesList.get(i));
        }
        return resultList;
    }

    public void printData(){
        fileRepo.printDataFromFile();
    }

    @Override
    public String toString() {
        return "expensesList=" + expensesList;
    }
}
