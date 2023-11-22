package LogIN;

import Conexion.Connect;
import Usuarios.Administrativos.Interfaces.Administrativos;
import Usuarios.Alumnos.Interfaces.Alumnos;
import Usuarios.Docentes.Interfaces.Docentes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LogIN {
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton INGRESARButton1;
    private JButton SALIRButton;
    private JPanel panel1;
    private  boolean credencialesValidas = false;
    private final Connect r = new Connect();
    private static LogIN instance;
    private int tipoUsuario;
    private int ID_USUARIO;
    private long user;
    private LogIN() {

        INGRESARButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    consultarCredenciales();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                getUsuario();

                if (credencialesValidas){
                    try {
                        if(habilitado()) {
                        switch (tipoUsuario) {
                            case 1 -> {
                                JOptionPane.showMessageDialog(null, "Ingreso exitoso");
                                Alumnos vent = new Alumnos();
                                vent.setVisible(true);
                                JFrame frame = (JFrame) SwingUtilities.getRoot(INGRESARButton1);
                                frame.dispose();
                            }
                            case 2 -> {
                                JOptionPane.showMessageDialog(null, "Ingreso exitoso");
                                Docentes ventd = new Docentes();
                                ventd.setVisible(true);
                                JFrame frame = (JFrame) SwingUtilities.getRoot(INGRESARButton1);
                                frame.dispose();
                            }
                            case 3 -> {
                                JOptionPane.showMessageDialog(null, "Ingreso exitoso");
                                Administrativos vent = new Administrativos();
                                vent.setVisible(true);
                                JFrame frame = (JFrame) SwingUtilities.getRoot(INGRESARButton1);
                                frame.dispose();
                            }
                            default -> JOptionPane.showMessageDialog(null, "No se ha encontrado el usuario");
                        }
                    }
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "Credenciales incorrectas");
                }
            }
        });

        SALIRButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
    private void consultarCredenciales()  throws SQLException {

        r.conectar();

        long user;
        String contraseña;

        try {
            user = Long.parseLong(textField1.getText());
            contraseña = passwordField1.getText();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Los datos introducidos son incorrectos");
            return;
        }

        String login = "SELECT * FROM usuarios";

        ResultSet rs;
        PreparedStatement ps;

        try {
            ps = r.cc().prepareStatement(login);
            rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt(1);
                int getID = rs.getInt(1);
                int getUSER = rs.getInt(2);
                String getContraseña = rs.getString(3);
                tipoUsuario = rs.getInt(4);

                if (getUSER == user && getID == id && getContraseña.equals(contraseña)) {
                    credencialesValidas = true;
                    break; // Salir del bucle si las credenciales son válidas
                }
            }

        } catch (SQLException d) {
            Logger.getLogger(LogIN.class.getName()).log(Level.SEVERE, null, d);
        }
    }

    private void getUsuario(){

        try {
            r.conectar();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        user = Long.parseLong(textField1.getText());

        String sql = "SELECT id FROM usuarios WHERE usuario = ?";
        PreparedStatement ps;
        ResultSet rs;

        try {
            ps = r.cc().prepareStatement(sql);
            ps.setLong(1, user);
            rs = ps.executeQuery();

            while (rs.next()){
                ID_USUARIO = rs.getInt("id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getID_USUARIO(){
        return ID_USUARIO;
    }

    public static LogIN getInstance() {
        if (instance == null) {
            instance = new LogIN();
        }
        return instance;
    }

    private boolean habilitado() throws  SQLException {
        r.conectar();

        String SQL = "SELECT habilitado FROM usuarios WHERE id = ?";
        PreparedStatement ps;
        ps = r.cc().prepareStatement(SQL);
        ps.setInt(1, getID_USUARIO());
        ResultSet rs;
        rs = ps.executeQuery();

        while(rs.next()){
            boolean habilitado = rs.getBoolean("habilitado");
            if (habilitado){
                return true;
            }
        }
        JOptionPane.showMessageDialog(null, "Su usuario ha sido deshabilitado. Consulte con administración");
        return false;
    }

    public void setVisible(boolean visible) {
        JFrame frame = new JFrame("INGRESO");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(visible);
    }
}

