package db2;

import java.sql.*;

public class Main {

    // docker run -itd --name mydb2 --privileged=true -p 50000:50000 -e LICENSE=accept -e DB2INST1_PASSWORD=pass -e DBNAME=testdb -v storage:/database ibmcom/db2

    private static final String DB2_URL = "jdbc:db2://127.0.0.1:50000/testdb";
    private static final String DB2_USER = "db2inst1";
    private static final String DB2_PASS = "pass";

    private static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS test_table (\n" +
            "person_id int PRIMARY KEY NOT NULL,\n" +
            "firstName char(255) NOT NULL,\n" +
            "lastName char(255) NOT NULL\n" +
            ");";
    private static final String INSERT_INTO =
            "INSERT INTO test_table (person_id, firstname, lastname)\n" +
            "VALUES (2, 'Name', 'Surname')";
    private static final String SELECT = "SELECT * FROM test_table";

    public static void main(String[] args) {
//        execute(CREATE_TABLE);
//        execute(INSERT_INTO);
        executeQuery(SELECT);
    }

    private static void execute(String query) {
        try (Connection connection = DriverManager.getConnection(DB2_URL, DB2_USER, DB2_PASS);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void executeQuery(String query) {
        ResultSet resultSet = null;
        try (Connection connection = DriverManager.getConnection(DB2_URL, DB2_USER, DB2_PASS);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("firstname").trim();
                String surname = resultSet.getString("lastname").trim();
                System.out.println(name + " " + surname);
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
