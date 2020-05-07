package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    public static Connection getMysqlConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("crud?").                //db name
                    append("useUnicode=true&").     //unicode
                    append("serverTimezone=Europe/Moscow");  // setTimeZone

            return DriverManager.getConnection(url.toString(), "root", "root");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException(e);
        }
    }
}
