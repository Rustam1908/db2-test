package db2;

import java.sql.*;

public class Main {

    private static final String CREATE_TABLE = "create table if not exists test_table (\n" +
            " person_id int primary key not null,\n" +
            " firstName char(255) not null,\n" +
            " lastName char(255) not null\n" +
            " );";
    private static final String INSERT_INTO = "insert into test_table (person_id, firstname, lastname)\n" +
            "values (1, 'firstname1', 'lastname1')";
    private static final String SELECT = "select * from test_table";

    public static void main(String[] args) {
        ResultSet resultSet = null;

        try (Connection connection = DriverManager.getConnection(
                "jdbc:db2://127.0.0.1:50000/testdb",
                "db2inst1",
                "pass");
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT)) {

//            preparedStatement.execute();
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                System.out.println(resultSet.getString("firstname"));
                System.out.println(resultSet.getString("lastname"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
