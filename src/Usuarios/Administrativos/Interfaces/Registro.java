package Usuarios.Administrativos.Interfaces;

import Conexion.Connect;
import Usuarios.Administrativos.Interfaces.Administrativos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Registro {
    private JTextField textDNI;
    private JTextField textDIRECCION;
    private JTextField textTELEFONO;
    private JTextField textFECHANAC;
    private JTextField textNOMBRE;
    private JButton REGISTRARButton;
    private JButton BORRARButton;
    private JButton SALIRButton;
    private JPanel panel1;
    private JComboBox<String> comboBox1;
    private int usuario;
    public Registro() {

    comboBox1.addItem("Alumno");
    comboBox1.addItem("Docente");

    REGISTRARButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            
            crearUsuario();
            JOptionPane.showMessageDialog(null, "REGISTRO EXITOSO");
            limpiar();
        }
    });
    BORRARButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            limpiar();
        }
    });
    SALIRButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Administrativos vent = new Administrativos();
            vent.setVisible(true);
            JFrame frame = (JFrame) SwingUtilities.getRoot(SALIRButton);
            frame.dispose();
        }
    });
}
    private void crearUsuario() {

        //Gestiona los inserts según la tabla a la que deba ir
        //El switch es mil veces más rápido que el if además queda fachero

            int opcion = comboBox1.getSelectedIndex();
        switch (opcion) {
            case 0 -> {
                System.out.println("Alumnos elegido");
                String alumnos = "alumnos";
                try {
                    obtenerLastUser();
                    generarUsuario(1);
                    insertarEnBase(alumnos);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            case 1 -> {
                System.out.println("doc eleg");
                String docente = "docentes";
                try {
                    obtenerLastUser();
                    generarUsuario(2);
                    insertarEnBase(docente);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    private void insertarEnBase(String tabla) throws SQLException {

        Connect r = new Connect();
        try {
            r.conectar();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        PreparedStatement ps;

        java.util.Date Invocar = new java.util.Date(textFECHANAC.getText());
        java.sql.Date fechaNac = new java.sql.Date(Invocar.getTime());

        String nombre, direccion;
        long dni, telefono;

        nombre = textNOMBRE.getText();
        direccion = textDIRECCION.getText();
        dni = Long.parseLong(textDNI.getText());
        telefono = Long.parseLong(textTELEFONO.getText());

        try {

            //Insert en la tabla que corresponda
            ps = r.cc().prepareStatement("insert into" + " " + tabla + "(nombre, dni, fecha_nac, telefono, direccion, usuario) values (?,?,?,?,?,?);");
            ps.setString(1, nombre);
            ps.setLong(2, dni);
            ps.setDate(3, fechaNac);
            ps.setLong(4, telefono);
            ps.setString(5, direccion);
            ps.setInt(6,usuario + 1);

            ps.executeUpdate();

            r.cc().close(); //Cierra la conexión a la base como es debido así no queda nada raro
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void generarUsuario(int tipouser){

        Connect r = new Connect();
        try {
            r.conectar();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        String contraseña = textDNI.getText();
        long dni = Long.parseLong(textDNI.getText());
        PreparedStatement us;

        try {

            us = r.cc().prepareStatement("Insert into USUARIOS(usuario, contraseña, usuariotipo) VALUES (?, ?, ?)");
            us.setLong(1, dni);
            us.setString(2, contraseña);
            us.setInt(3, tipouser);
            us.executeUpdate();

            r.cc().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void obtenerLastUser(){

        Connect r = new Connect();
        try {
            r.conectar();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        String SQL = "SELECT id FROM usuarios ORDER BY id DESC LIMIT 1;";
        PreparedStatement ps;
        ResultSet rs;

        try {

            ps = r.cc().prepareStatement(SQL);
            rs = ps.executeQuery();

            while(rs.next()){
                usuario = rs.getInt("id");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    private void limpiar(){
        textDIRECCION.setText(null);
        textDNI.setText(null);
        textFECHANAC.setText(null);
        textNOMBRE.setText(null);
        textTELEFONO.setText(null);
    }

    public void setVisible(boolean visible) {
        JFrame frame = new JFrame("Registro de usuarios");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(visible);
    }
}
