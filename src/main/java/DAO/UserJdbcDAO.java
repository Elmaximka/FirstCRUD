package main.java.DAO;

import main.java.model.User;
import main.java.util.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDAO implements UserDAO {
    private final Connection connection;

    public UserJdbcDAO() {
        connection = DBHelper.instance().getConnection();
    }

    public boolean deleteUser(String name) {
        if (getUserByName(name) != null) {
            try (PreparedStatement st = connection.prepareStatement("delete from users where name='" + name + "'")) {
                st.executeUpdate();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public User getUserByName(String name) {
        try (PreparedStatement st = connection.prepareStatement("select * from users where name='" + name + "'")) {
            ResultSet res = st.executeQuery();
            res.next();
            return new User(res.getString("name"),
                    res.getString("password"), res.getString("gender"));
        } catch (SQLException e) {
            return null;
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("select * from users")) {
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                list.add(new User(res.getLong("id"), res.getString("name"),
                        res.getString("password"), res.getString("gender")));
            }
            res.close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void addUser(User user) throws Throwable {
        String name = user.getName();
        String password = user.getPassword();
        String gender = user.getGender();
        try (PreparedStatement sq = connection.prepareStatement("select name from users where name='" + name + "'")) {
            ResultSet res = sq.executeQuery();
            res.next();
            if (name.compareToIgnoreCase(res.getString(1)) == 0) {
                throw new Throwable();
            }
        } catch (SQLException eq) {
            eq.printStackTrace();
        }
        try (PreparedStatement st = connection.prepareStatement("insert into users(name, password, gender)" +
                " values ('" + name + "','" + password + "','" + gender + "')")) {
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable() {
        try (PreparedStatement stmt = connection.prepareStatement("create table if not exists users (id bigint auto_increment, name varchar(256)," +
                " password varchar(256), gender varchar(256), primary key (id))")) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropTable() {
        try (PreparedStatement stmt = connection.prepareStatement("DROP TABLE IF EXISTS users")) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
