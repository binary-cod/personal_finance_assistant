package networking;

import domain.Expense;
import domain.Income;
import services.ExpenseService;
import services.IncomeService;
import ui.UI;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Scanner;

public class PFAServer {
    public static void main(String[] args) {

        UI ui = new UI();
        IncomeService incomeService = new IncomeService();
        ExpenseService expenseService = new ExpenseService();

        try (ServerSocket serverSocket = new ServerSocket(8888)) {

            System.out.println("Server is up and running...");

            Socket incoming = serverSocket.accept();
            //read data from connected socket
            InputStream inputStream = incoming.getInputStream();
            Scanner in = new Scanner(inputStream, "UTF-8");

            //write data to connected socket
            OutputStream outputStream = incoming.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
            BufferedWriter out = new BufferedWriter(outputStreamWriter);

            if (incoming.isConnected()) {
                System.out.println("Somebody is just connected! "+ incoming.getInetAddress().toString());
            }

            Integer input ;
            do {
                writeToWriterAndFlush(ui.mainMenu(), out);
                input = in.nextInt();

                if (input == -1)
                    break;
                switch (input) {
                    case 1:
                        writeToWriterAndFlush(ui.newIncomeMenu(), out);
                        String incomeName = in.next();
                        float incomeValue = in.nextFloat();

                        if (incomeValue == -1 || incomeName.equals("-1"))
                            break;

                        Income income = new Income(incomeName, incomeValue, LocalDateTime.now(), null);

                        if (incomeService.insert(income))
                            writeToWriterAndFlush("Your income recorded as "+incomeService.numberOfIncomes(), out);
                        break;
                    case 2:
                        writeToWriterAndFlush(ui.newExpenseMenu(), out);

                        String expenseName = in.next();
                        float expenseValue = in.nextFloat();

                        if (expenseValue == -1 || expenseName.equals("-1"))
                            break;

                        Expense expense =  new Expense(expenseName, expenseValue, LocalDateTime.now(), null);

                        if (expenseService.insert(expense))
                            writeToWriterAndFlush("Your expense recorded as "+expenseService.numberOfExpense(), out);

                        break;
                    case 5:
                        Float incomeSum = incomeService.calculateSum(incomeService.getIncomesOfGivenDate(LocalDateTime.now(), null));
                        Float expenseSum = expenseService.calculateSum(expenseService.getExpensesOfGivenDate(LocalDateTime.now(), null));

                        writeToWriterAndFlush(incomeService.getIncomesOfGivenDate(LocalDateTime.now(), null).toString(), out);
                        writeToWriterAndFlush(expenseService.filterListByDate(LocalDateTime.now()).toString(), out);

                        writeToWriterAndFlush("sum of your incomes for month: "+LocalDateTime.now().getMonth()+" : "
                                + incomeSum, out);

                        writeToWriterAndFlush("sum of expenses for month: "+LocalDateTime.now().getMonth()+ " : "
                                + expenseSum, out);
                        writeToWriterAndFlush("Your balance is : "+ (incomeSum - expenseSum), out);
                        break;
                    default:
                        writeToWriterAndFlush("please use numbers between 1 and 4", out);
                        break;
                }

            } while (input != -1);

            out.close();
            in.close();

        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void writeToWriterAndFlush(String text, BufferedWriter writer) throws IOException {
        writer.write(text+"\n");
        writer.flush();
    }
}
