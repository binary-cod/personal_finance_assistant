package ui;

public class UI {

    public String mainMenu(){
        String menu = "1.Create Income\n2.Create Expense\n3.List Income\n4.List Expense\n-1.Exit";
        return menu;
    }

    public String newIncomeMenu(){
        String menu= "Please enter value, (-1 to go to main menu)";
        return menu;
    }

    public String newExpenseMenu() {
        String menu= "Please enter value, (-1 to go to main menu)";
        return menu;
    }
}
