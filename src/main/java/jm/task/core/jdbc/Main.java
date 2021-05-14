package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;


public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserDao userDao = new UserDaoJDBCImpl();
        userDao.createUsersTable();
        userDao.saveUser("Матвей", "Иванов", (byte) 24);
        userDao.saveUser("Иван", "Матвеев", (byte) 42);
        userDao.saveUser("Шильдт", "Петров", (byte) 56);
        userDao.saveUser("Фома", "Великий", (byte) 5);
        System.out.println(userDao.getAllUsers());
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}
