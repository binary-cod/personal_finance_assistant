import services.ExpenseService;
import services.IncomeService;
import ui.UI;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        UI ui = new UI();
        IncomeService incomeService = new IncomeService(3);
        ExpenseService expenseService = new ExpenseService(3);

        do {
            System.out.println(ui.mainMenu());
            int input = scanner.nextInt();

            if (input == -1)
                break;

            switch (input) {
                case 1:
                    System.out.println(ui.newIncomeMenu());
                    float income = scanner.nextFloat();
                    if (income == -1)
                        break;
                    if (incomeService.insertIncome(income))
                        System.out.println("Your income recorded as "+incomeService.numberOfIncomes());
                    else
                        System.out.println("Sorry Incomes are full!");
                    break;
                case 2:
                    System.out.println(ui.newExpenseMenu());
                    float expense = scanner.nextFloat();
                    if (expense == -1)
                        break;
                    if (expenseService.insertExpense(expense))
                        System.out.println("Your expense recorded as "+ expenseService.numberOfExpense());
                    else
                        System.out.println("Sorry, Expenses is full!");
                    break;
                case 3:
                    System.out.println("List income is selected");
                    System.out.println(Arrays.toString(incomeService.getAllIncomes()));
                    break;
                case 4:
                    System.out.println("List expense is selected");
                    System.out.println(Arrays.toString(expenseService.getAllExpenses()));
                    break;
                default:
                    System.out.println("please use numbers between 1 and 4");
                    break;
            }
        } while (true);
    }
}
