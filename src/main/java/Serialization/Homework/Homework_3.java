package Serialization.Homework;

import Serialization.Homework.annotations.SQLHandler;
import Serialization.Homework.annotations.Table;
import Serialization.Homework.model.Student;

import java.sql.*;

public class Homework_3 {
    private static Connection connection = null;
    private static final SQLHandler sqlHandler = new SQLHandler();

    public static void main(String[] args) throws SQLException {
        createConnection(Student.class);
    }

    public static void createConnection(Class<?> type) throws SQLException {
        connection = DriverManager.getConnection("jdbc:h2:mem:myData");
        String table = type.getAnnotation(Table.class).table();
        createTable(type);
        insertData(table,
                "id,age,firstName,lastName",
                """
                        (1, 20, 'firstName_1', 'lastName_1'),
                        (2, 25, 'firstName_2', 'lastName_2'),
                        (3, 30, 'firstName_3', 'lastName_3'),
                        (4, 35, 'firstName_4', 'lastName_4'),
                        (5, 40, 'firstName_5', 'lastName_5')
                        """);
        deleteRowWithId(table, 3);
        acceptConnection(table);
        connection.close();
    }

    public static void createTable(Class<?> type) {
        try {
            var statement = connection.createStatement();
            String sql = "CREATE TABLE " + sqlHandler.createSQLTable(type, true);
            statement.execute(sql);
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void acceptConnection(String table) throws SQLException {
        try (var statement = connection.createStatement()) {
            var resultSet = statement.executeQuery(
                    "select id,age,firstName,lastName from " + table);
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                long age = resultSet.getLong("age");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                System.out.println(id + " " + age + " " + firstName + " " + lastName);
            }
        }
    }

    public static void insertData(String table, String params, String values) throws SQLException {
        try (var statement = connection.createStatement()) {
            String sql = "INSERT INTO " + table + "(" + params + ")" + " VALUES " + values;
            statement.executeUpdate(sql);
        }
    }

    private static void deleteRowWithId(String table, int id) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            System.out.println("DELETED: " + statement.executeUpdate("delete from " + table + " where id = " + id));
        }
    }
}