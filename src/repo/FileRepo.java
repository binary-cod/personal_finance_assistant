package repo;

import domain.Expense;
import domain.Income;
import domain.User;
import services.UserService;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class FileRepo {

    String fileName;
    List<Income> incomeList = new ArrayList<>();
    List<Expense> expenseList = new ArrayList<>();
    UserService userService;

    public FileRepo(){
        this.fileName = "/home/wild/projects/binarycod/fin_app_cl/per_fin_assistant.txt";
        userService = new UserService();
        readAndparseData();

    }

    public void readAndparseData(){

        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
               if (line.startsWith("I")) {
                   incomeList.add(parseIncome(line));
               }
               if (line.startsWith("E")) {
                   expenseList.add(parseExpense(line));
               }
            }
        } catch (NoSuchFileException ex ){
          System.out.println("I can not find the file : " + fileName
                  + " please contact administrator! ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Income parseIncome(String line){
        // I ; salary ; 2000 ; 2021-03-15:14:56:00
        // Step 1 : Tokenize, split
        // Step 2 : Go through each token and create Income object
        // Step 3 : get email and get User object according to email, set to Income
        // Step 4 : return Income

        // ["I", "salary", "2000", "2021-03-15:14:56:00"] -->

        String[] tokens = line.split(";");
        Income income = new Income();
        income.setID(tokens[1]);
        income.setName(tokens[2]);
        income.setValue(Float.parseFloat(tokens[3]));
        income.setIncomeDate(LocalDateTime.parse(tokens[4]));

        User user = userService.getUserByEmail(tokens[5]).get();
        income.setOwner(user);

        return income;
    }

    public Expense parseExpense(String line){
        // I ; salary ; 2000 ; 2021-03-15:14:56:00
        // Step 1 : Tokenize, split
        // Step 2 : Go through each token and create Income object
        // Step 3 : get email and get User object according to email, set to Expense
        // Step 4 : return Income

        // ["I", "salary", "2000", "2021-03-15:14:56:00"] -->

        String[] tokens = line.split(";");
        Expense expense = new Expense();
        expense.setID(tokens[1]);
        expense.setExpenseName(tokens[2]);
        expense.setExpenseValue(Float.parseFloat(tokens[3]));
        expense.setExpenseDate(LocalDateTime.parse(tokens[4]));

        User user = userService.getUserByEmail(tokens[5]).get();
        expense.setOwner(user);

        return expense;
    }

    public void writeEverythingToFile(){
        // 1. get list of incomes and expenses
        // 2. go through each of the objects in lists and get String representation of it
        // 3. build single content out of lines
        // 4. truncate the file and write the content to the file

        StringBuilder content = new StringBuilder();
        for (Expense e : expenseList) {
            content.append(e +"\n");
        }

        for (Income i: incomeList) {
            content.append(i+"\n");
        }

        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(fileName))) {
            bw.write(content.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeData(String data){
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(fileName),
                StandardOpenOption.APPEND)) {
            bw.write(data);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    public List<Income> getIncomeList() {
        return incomeList;
    }

    public List<Expense> getExpenseList() {
        return expenseList;
    }
}
