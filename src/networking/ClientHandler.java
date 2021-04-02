package networking;

import domain.Expense;
import domain.Income;
import domain.User;
import services.ExpenseService;
import services.IncomeService;
import services.Service;
import services.UserService;
import ui.UI;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Scanner;

public class ClientHandler implements Runnable {
    private Socket incomingSocket;

    public ClientHandler(Socket socket) {
        this.incomingSocket = socket;

    }

    @Override
    public void run() {
        UI ui = new UI();
        IncomeService incomeService = new IncomeService();
        ExpenseService expenseService = new ExpenseService();

        try {
            InputStream inputStream = incomingSocket.getInputStream();
            Scanner in = new Scanner(inputStream, "UTF-8");

            //write data to connected socket
            OutputStream outputStream = incomingSocket.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
            BufferedWriter out = new BufferedWriter(outputStreamWriter);

            UserService userService = new UserService();

            writeToWriterAndFlush(ui.loginMenu(), out);

            String username = in.next();
            String password = in.next();

            Optional<User> optionalUser = userService.getUserByEmailAndPassword(username, password);

            if (optionalUser.isPresent()) {


                User user = optionalUser.get();

                Integer input;

                do {
                    writeToWriterAndFlush(ui.mainMenu(), out);
                    input = in.nextInt();

                    switch (input) {
                        case 1:
                            writeToWriterAndFlush(ui.newIncomeMenu(), out);
                            String incomeName = in.next();
                            float incomeValue = in.nextFloat();

                            if (incomeValue == -1 || incomeName.equals("-1"))
                                break;

                            Income income = new Income(incomeName, incomeValue, LocalDateTime.now(), user);

                            if (incomeService.insert(income))
                                writeToWriterAndFlush("Your income recorded as "+incomeService.numberOfIncomes(),   out);
                            break;
                        case 2:
                            writeToWriterAndFlush(ui.newExpenseMenu(), out);

                            String expenseName = in.next();
                            float expenseValue = in.nextFloat();

                            if (expenseValue == -1 || expenseName.equals("-1"))
                                break;

                            Expense expense =  new Expense(expenseName, expenseValue, LocalDateTime.now(), user);

                            if (expenseService.insert(expense))
                                writeToWriterAndFlush("Your expense recorded as "+expenseService.numberOfExpense()  , out);

                            break;
                        case 3:
                            writeToWriterAndFlush(incomeService.toString(), out);
                            break;
                        case 4:
                            writeToWriterAndFlush(expenseService.toString(), out);
                            break;
                        case 5:
                            Float incomeSum = incomeService.calculateSum(incomeService.getIncomesOfGivenDate(LocalDateTime.now(), user));
                            Float expenseSum = expenseService.calculateSum(expenseService.getExpensesOfGivenDate(LocalDateTime.now(), user));

                            writeToWriterAndFlush(incomeService.getIncomesOfGivenDate(LocalDateTime.now(), user).toString(), out);
                            writeToWriterAndFlush(expenseService.filterListByDate(LocalDateTime.now()).toString(), out);

                            writeToWriterAndFlush("sum of your incomes for month: "+LocalDateTime.now().getMonth()  +" : "
                                    + incomeSum, out);

                            writeToWriterAndFlush("sum of expenses for month: "+LocalDateTime.now().getMonth()+ "   : "
                                    + expenseSum, out);
                            writeToWriterAndFlush("Your balance is : "+ (incomeSum - expenseSum), out);
                            break;
                        default:
                            break;
                    }
                } while (input != -1);
            } else {
                writeToWriterAndFlush(ui.noSucchUser(), out);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void writeToWriterAndFlush(String text, BufferedWriter writer) throws IOException {
        writer.write(text + "\n");
        writer.flush();
    }
}
