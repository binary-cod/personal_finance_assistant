package ui;

public class UI {

    public String mainMenu(){
        String menu = "1.Create Income\n2.Create Expense\n3.List Income\n4.List Expense\n5.Print Balance\n-1.Exit";
        return menu;
    }

    public String newIncomeMenu(){
        String menu= "Please enter name and value, (-1 to go to main menu)";
        return menu;
    }

    public String newExpenseMenu() {
        String menu= "Please enter name and value, (-1 to go to main menu)";
        return menu;
    }

    public String loginMenu(){
        return "enter your email and password to login!";
    }

    public String welcomeMessage () {
        return "Welcome. Enjoy your stay!";
    }

    public String noSucchUser(){
        return "Sorry! User with such credentials not found.";
    }
}
