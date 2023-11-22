package Conexion;

import LogIN.LogIN;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PasswordChange {
    private LogIN log = LogIN.getInstance();
    private Connect r = new Connect();
    public void changePassword() throws SQLException {

        r.conectar();
        String SQL = "SELECT usuario, contraseña FROM usuarios WHERE id = ?";

        PreparedStatement ps;
        ResultSet rs;

        ps = r.cc().prepareStatement(SQL);
        ps.setInt(1, log.getID_USUARIO());
        rs = ps.executeQuery();

        while(rs.next()){
            String usuario = String.valueOf(rs.getLong("usuario"));
            String password = rs.getString("contraseña");
            if (usuario.equals(password)){
                JOptionPane.showMessageDialog(null, "Recuerde modificar su contraseña");
            }
        }
    }
}
