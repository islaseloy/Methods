package Usuarios.Administrativos.GestionAlumnos;

import Usuarios.Administrativos.GestionDocentes.MetodosGestionDocentes;
import Usuarios.Administrativos.Interfaces.Administrativos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class GestionAlumnos {
    private JPanel panel1;
    private JTable tablaAlumnadoInscripto;
    private JButton HABILITARDESHABILITARALUMNOButton;
    private JButton REGRESARButton;
    private JButton GESTIONARCURSOSDELALUMNOButton;
    private JButton REINICIARCONTRASEÑAButton;
    private final MetodosGestionAlumnos metodos;

public GestionAlumnos() {

    metodos = new MetodosGestionAlumnos(this);
    metodos.generarTablas();

    GESTIONARCURSOSDELALUMNOButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            metodos.listadecursos();
        }
    });
    HABILITARDESHABILITARALUMNOButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if ((metodos.consultarHabilitado())){
                metodos.deshabilitarAlumno();
                JOptionPane.showMessageDialog(null, "Alumno dado de baja exitosamente");
            }
            else if (!metodos.consultarHabilitado()){
                metodos.deshabilitarAlumno();
                JOptionPane.showMessageDialog(null, "Alumno habilitado exitosamente");
            }
        }
    });
    REGRESARButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Administrativos vent = new Administrativos();
            vent.setVisible(true);
            JFrame frame = (JFrame) SwingUtilities.getRoot(REGRESARButton);
            frame.dispose();
        }
    });
    REINICIARCONTRASEÑAButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                metodos.reiniciarPassword();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    });
}
    public JTable getTabla(){
    return tablaAlumnadoInscripto;
    }

    public void setVisible(boolean visible) {
        JFrame frame = new JFrame("Gestión de alumnos");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(visible);
    }
}
