package ui;

import domain.Expense;
import domain.Income;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import services.ExpenseService;
import services.IncomeService;

import java.time.LocalDateTime;


public class Gui_Controller {

    IncomeService incomeService = new IncomeService();
    ExpenseService expenseService = new ExpenseService();
    private Boolean drawChart= true;
    private Boolean drawIncomeTable= true;
    private Boolean drawExpenseTable= true;

    @FXML
    private Label total_income=new Label();

    @FXML
    private Label total_Expences= new Label();

    @FXML
    private PieChart pieChart= new PieChart();

    @FXML
    private Label net_Profit= new Label();

    @FXML
    private TableView<Income> incomeListTableView= new TableView();

    @FXML
    private TextField income_Type = new TextField();

    @FXML
    private TextField income_Amount= new TextField();

    @FXML
    private Label addIncomeStatus= new Label();

    @FXML
    private TableView<Expense>  expenseListTableView= new TableView();

    @FXML
    private TextField expense_Type = new TextField();

    @FXML
    private TextField expense_Amount= new TextField();

    @FXML
    private Label addExpenseStatus= new Label();

    //balance tab selected
    public void balance_onSelect(Event event) {
        Float incomeSum = incomeService.calculateSum(incomeService.getIncomesOfGivenDate(LocalDateTime.now()));
        Float expenseSum = expenseService.calculateSum(expenseService.getExpensesOfGivenDate(LocalDateTime.now()));

        total_income.setText(incomeSum.toString());
        total_Expences.setText(expenseSum.toString());
        net_Profit.setText((incomeSum - expenseSum)+"");

        //drawing chart
        if(drawChart){
        ObservableList<PieChart.Data> data = FXCollections. observableArrayList();
        data.add(new PieChart.Data("Income", incomeSum));
        data.add(new PieChart.Data("Expense", expenseSum));
        pieChart.setData(data);
        drawChart=false;
        }
    }
    //income tab selected
    private void drawIncomeTableView(){


        ObservableList<Income> tableData = FXCollections.observableArrayList();
        for (Income i : incomeService.getData()) {
            tableData.add(i);
            //System.out.println(i);
        }
        incomeListTableView.setItems(tableData);
        
    }


    public void listOfIncome_onSelect(Event event) {

        if(drawIncomeTable) {
            TableColumn incomeId = new TableColumn("id");
            incomeId.setCellValueFactory(new PropertyValueFactory<>("ID"));

            TableColumn incomeType = new TableColumn("type");
            incomeType.setCellValueFactory(new PropertyValueFactory<>("name"));

            TableColumn incomeValue = new TableColumn("value");
            incomeValue.setCellValueFactory(new PropertyValueFactory<>("value"));

            TableColumn incomeTime = new TableColumn("date and time");
            incomeTime.setCellValueFactory(new PropertyValueFactory<>("incomeDate"));

            incomeListTableView.getColumns().addAll(incomeId, incomeType, incomeValue, incomeTime);
            drawIncomeTableView();
            drawIncomeTable=false;
        }
    }

    public void incometypefieldOnMouse(MouseEvent mouseEvent) {
        income_Type.setText("");
    }

    public void incomeamountfieldOnMouse(MouseEvent mouseEvent) {
        income_Amount.setText("");
    }

    public void add_income_Pushed(ActionEvent actionEvent) {
        Income income = new Income(income_Type.getText(), Float.parseFloat(income_Amount.getText()), LocalDateTime.now());
        if (incomeService.insert(income))
            addIncomeStatus.setText("Your income recorded!");
        else
            addIncomeStatus.setText("Sorry Incomes are full!");

        drawIncomeTableView();
    }



    //expense tab selected
    private void drawExpenseTableView(){


        ObservableList<Expense> expenseTableData = FXCollections.observableArrayList();
        for (Expense i : expenseService.getData()) {
            expenseTableData.add(i);
            //System.out.println(i);
        }
        expenseListTableView.setItems(expenseTableData);
    }

    public void listOfExpences_onSelect(Event event) {
        if(drawExpenseTable) {
            TableColumn ExpenseId = new TableColumn("id");
            ExpenseId.setCellValueFactory(new PropertyValueFactory<>("ID"));

            TableColumn expenseType = new TableColumn("type");
            expenseType.setCellValueFactory(new PropertyValueFactory<>("expenseName"));

            TableColumn expenseValue = new TableColumn("value");
            expenseValue.setCellValueFactory(new PropertyValueFactory<>("expenseValue"));

            TableColumn expenseTime = new TableColumn("date and time");
            expenseTime.setCellValueFactory(new PropertyValueFactory<>("expenseDate"));

            expenseListTableView.getColumns().addAll(ExpenseId, expenseType, expenseValue, expenseTime);
            drawExpenseTableView();
            drawExpenseTable=false;
        }
    }

    public void expensetypefieldOnMouse(MouseEvent mouseEvent) {
        expense_Type.setText("");
    }

    public void expeneamountfieldOnMouse(MouseEvent mouseEvent) {
        expense_Amount.setText("");
    }

    public void add_expense_Pushed(ActionEvent actionEvent) {
        Expense expense = new Expense(expense_Type.getText(), Float.parseFloat(expense_Amount.getText()), LocalDateTime.now());
        if (expenseService.insert(expense))
            addExpenseStatus.setText("Your expense recorded!");
        else
            addExpenseStatus.setText("Sorry Expenses are full!");

        drawExpenseTableView();
    }



}
