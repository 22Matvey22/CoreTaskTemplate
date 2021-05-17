package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;


public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Матвей", "Иванов", (byte) 24);
        userService.saveUser("Иван", "Матвеев", (byte) 42);
        userService.saveUser("Шильдт", "Петров", (byte) 56);
        userService.saveUser("Фома", "Великий", (byte) 5);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();
        try {
            Util.getConnection().close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
