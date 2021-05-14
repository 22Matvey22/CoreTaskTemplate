package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Util util = new Util();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = util.getConnection().createStatement()) {
            String sqlCommandCreate = "CREATE TABLE IF NOT EXISTS Users(id INT PRIMARY KEY AUTO_INCREMENT, userName VARCHAR(50), userLastName VARCHAR(50), userAge INT )";
            statement.execute(sqlCommandCreate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement statement = util.getConnection().createStatement()) {
            String sqlCommandDrop = "DROP TABLE IF EXISTS Users";
            statement.executeUpdate(sqlCommandDrop);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            String sqlCommandSave = "INSERT INTO Users(userName, userLastName, userAge) VALUES(?, ?, ?)";
            PreparedStatement prSt = util.getConnection().prepareStatement(sqlCommandSave);
            prSt.setString(1, name);
            prSt.setString(2, lastName);
            prSt.setInt(3, age);
            prSt.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Statement statement = util.getConnection().createStatement()) {
            String sqlRemoveUserById = "DELETE FROM Users WHERE id=" + id;
            statement.executeUpdate(sqlRemoveUserById);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = null;
        try {
            Statement statement = util.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id, userName, userLastName, userAge FROM Users");
            userList = new ArrayList<>();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String userName = resultSet.getString("userName");
                String userLastName = resultSet.getString("userLastName");
                int userAge = resultSet.getInt("userAge");
                User user = new User(userName, userLastName, (byte) userAge);
                user.setId(id);
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        try (Statement statement = util.getConnection().createStatement()) {
            String sqlRemoveUsers = "DELETE FROM Users";
            statement.executeUpdate(sqlRemoveUsers);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
