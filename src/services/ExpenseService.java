package services;

import domain.Expense;
import domain.User;
import repo.FileRepo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ExpenseService implements Service<Expense> {

    private List<Expense> expensesList;
    private FileRepo fileRepo;

    public ExpenseService(){
        fileRepo = new FileRepo();
        expensesList = fileRepo.getExpenseList();
    }

    public Boolean insert(Expense value) {
        expensesList.add(value);
        fileRepo.writeData(value.toString() +"\n");
        return true;
    }

    public List<Expense> getData(User user) {
        return expensesList
                .stream()
                .filter(expense -> expense.getOwner().getEmail().equals(user.getEmail()))
                .collect(Collectors.toList());
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

    public ArrayList<Expense> getExpensesOfGivenDate(LocalDateTime givenDate, User user) {
        ArrayList<Expense> resultList = new ArrayList<>();
        for (int i = 0; i < expensesList.size(); i++) {
            if (expensesList.get(i).getExpenseDate().getMonth().equals(givenDate.getMonth())
            && expensesList.get(i).getOwner().getEmail().equals(user.getEmail()))
                resultList.add(expensesList.get(i));
        }
        return resultList;
    }

    @Override
    public String toString() {
        return "expensesList=" + expensesList;
    }
}
