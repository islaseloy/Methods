package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {
    Statement statement;
    Connection con;
    public Statement coo() {
        return statement;
    }

    public Connection cc() {
        return con;
    }

    public void conectar() throws SQLException {

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/CeT?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        String user = "root";
        String password = "root";

        try {
            Class.forName(driver);

            con = DriverManager.getConnection(url, user, password);

            statement = con.createStatement();

        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }
}

