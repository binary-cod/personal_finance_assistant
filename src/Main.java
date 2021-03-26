import domain.Expense;
import domain.Income;
import services.ExpenseService;
import services.IncomeService;
import ui.UI;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        UI ui = new UI();
        IncomeService incomeService = new IncomeService();
        ExpenseService expenseService = new ExpenseService();

        do {
            System.out.println(ui.mainMenu());
            int input = scanner.nextInt();

            if (input == -1)
                break;

            switch (input) {
                case 1:
                    System.out.println(ui.newIncomeMenu());
                    String incomeName = scanner.next();
                    float incomeValue = scanner.nextFloat();

                    if (incomeValue == -1 || incomeName.equals("-1"))
                        break;

                    Income income = new Income(incomeName, incomeValue, LocalDateTime.now());

                    if (incomeService.insert(income))
                        System.out.println("Your income recorded as "+incomeService.numberOfIncomes());
                    else
                        System.out.println("Sorry Incomes are full!");
                    break;
                case 2:
                    System.out.println(ui.newExpenseMenu());
                    String expenseName = scanner.next();
                    Float expenseValue = scanner.nextFloat();

                    if (expenseName.equals("-1") || expenseValue == -1)
                        break;

                    Expense expense = new Expense(expenseName, expenseValue, LocalDateTime.now());
                    if (expenseService.insert(expense))
                        System.out.println("Your expense recorded as "+ expenseService.numberOfExpense());
                    else
                        System.out.println("Sorry, Expenses is full!");
                    break;
                case 3:
                    System.out.println("List income is selected");
                    System.out.println(incomeService);
                    break;
                case 4:
                    System.out.println("List expense is selected");
                    System.out.println(expenseService);
                    break;
                case 5:
                    Float incomeSum = incomeService.calculateSum(incomeService.getIncomesOfGivenDate(LocalDateTime.now()));
                    Float expenseSum = expenseService.calculateSum(expenseService.getExpensesOfGivenDate(LocalDateTime.now()));
                    System.out.println(incomeService.getIncomesOfGivenDate(LocalDateTime.now()));
                    System.out.println(expenseService.filterListByDate(LocalDateTime.now()));
                    incomeService.deleteIncomeGivenDaysOld(5l);
                    System.out.println("stream example "+incomeService.getData());

                    System.out.println("sum of your incomes for month: "+LocalDateTime.now().getMonth()+" : "
                            + incomeSum);
                    System.out.println("sum of expenses for month: "+LocalDateTime.now().getMonth()+ " : "
                            + expenseSum);
                    System.out.println("Your balance is : "+ (incomeSum - expenseSum));
                    System.out.println("----Data from File ---");
                default:
                    System.out.println("please use numbers between 1 and 4");
                    break;
            }
        } while (true);
    }
}
