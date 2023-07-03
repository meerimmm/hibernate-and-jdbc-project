package peaksoft.dao;

import peaksoft.model.User;
import peaksoft.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {

    public UserDaoJdbcImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = Util.getConnection()) {

            Statement statement = connection.createStatement();

            statement.execute("""
                    create table users (
                    id serial primary key ,
                    name varchar(50)not null,
                    last_name varchar(70)not null ,
                    age integer not null 
                    );
                    """);

            System.out.println("Table users successfully created!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void dropUsersTable() {
        try (Connection connection = Util.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("""
                    drop table users;
                    """);
            preparedStatement.execute();
            System.out.println("The users table  successfully deleted");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        ;
        try (Connection connection = Util.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("""
                    insert into users (name, last_name, age) values (?, ?, ?);
                    """);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            System.out.println("The data was successfully added to the users table");

            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("""
                    delete from users where id=?;
                    """);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            System.out.println("The users table  successfully deleted");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = Util.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("""
                    select * from users;
                    """);
            while (resultSet.next()) {

                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute("""
                    truncate table users;
                    """);

            System.out.println("successfully cleared");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
