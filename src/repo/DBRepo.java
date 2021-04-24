package repo;

import domain.Income;
import domain.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DBRepo {

    private Connection connection;

    private final String DB_URL ="jdbc:mysql://localhost:3306/fin_per_assistant";
    private final String DB_USER="fin_user";
    private final String DB_PASS="fin123";

    public DBRepo(){

    }

    public void connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    DB_URL,
                    DB_USER,
                    DB_PASS
            );
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Integer selectCount(){
        Integer count=0;
         try {
                connect();
                String query="SELECT count(*) FROM income;";

                Statement stm = connection.createStatement();
                ResultSet resultSet = stm.executeQuery(query);

                while (resultSet.next()){
                    count = resultSet.getInt(1);
                }

                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        return count;
    }

    public List<Income> getIncomeList(){
        List<Income> incomeList = new ArrayList<>();
        String query = "SELECT id,name,value,income_date,user_id FROM income;";

        connect();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                Income income = new Income();
                income.setID(resultSet.getString("id"));
                income.setName(resultSet.getString("name"));
                income.setValue(Float.parseFloat( ""+ resultSet.getDouble("value")));
                income.setIncomeDate(LocalDateTime.parse(resultSet.getString("income_date")));
                income.setOwner(new User("user1@binarycod.com", "user1123"));
                incomeList.add(income);
            }
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return incomeList;
    }
}
