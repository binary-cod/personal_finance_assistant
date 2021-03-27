import domain.Expense;
import domain.Income;
import domain.User;
import services.ExpenseService;
import services.IncomeService;
import services.UserService;
import ui.UI;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        UI ui = new UI();
        IncomeService incomeService = new IncomeService();
        ExpenseService expenseService = new ExpenseService();
        UserService userService = new UserService();

        System.out.println(ui.loginMenu());

        String username = scanner.next();
        String password = scanner.next();

        Optional<User>  optionalUser = userService.getUserByEmailAndPassword(username, password);

        if (!optionalUser.isPresent()) {
            System.out.println(ui.noSucchUser());
            System.exit(0);
        }

        User user = optionalUser.get();

       /* Income temp1 = new Income("Salary", 2000, LocalDateTime.of(2021, 3, 7, 12, 20, 30));
        Income temp2 = new Income("Freelancing", 1000, LocalDateTime.of(2021, 3, 10, 12, 20, 30));
        Income temp3 = new Income("Trading", 500, LocalDateTime.of(2021, 1, 21, 12, 20, 30));
        Expense tempExpense = new Expense("taxi bill", 100.0f, LocalDateTime.of(2021, 1, 21, 12, 20, 30));

        incomeService.insert(temp1);
        incomeService.insert(temp2);
        incomeService.insert(temp3);
        expenseService.insert(tempExpense);
*/
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

                    Income income = new Income(incomeName, incomeValue, LocalDateTime.now(), user);

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

                    Expense expense = new Expense(expenseName, expenseValue, LocalDateTime.now(), user);
                    if (expenseService.insert(expense))
                        System.out.println("Your expense recorded as "+ expenseService.numberOfExpense());
                    else
                        System.out.println("Sorry, Expenses is full!");
                    break;
                case 3:
                    System.out.println("List income is selected");
                    System.out.println(incomeService.getData(user));
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
                    System.out.println("stream example "+incomeService.getData(user));

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
