package main.java.factory;


import main.java.DAO.UserDAO;
import main.java.DAO.UserHibernateDAO;
import main.java.DAO.UserJdbcDAO;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Properties;

public class UserDaoFactory {
    public static UserDAO getUserDAO() {

        Properties properties = new Properties();
        FileInputStream fis;

        try {
            fis = new FileInputStream("C:\\Users\\Максим\\IdeaProjects\\FirstCRUD\\src\\main\\resources\\config.properties");
            properties.load(fis);
            String daotype = properties.getProperty("daotype");
            if (daotype.equalsIgnoreCase("UserHibernateDAO")) {
                return new UserHibernateDAO();
            } else {
                return new UserJdbcDAO();
            }

        } catch (IOException e) {
            System.out.print("No such file excepted");
            e.printStackTrace();
            throw new NoSuchElementException();
        }

    }

}
