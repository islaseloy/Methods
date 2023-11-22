package Usuarios.Administrativos.GestionDocentes;

import Usuarios.Administrativos.Interfaces.Administrativos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;


public class GestionDocentes {
    private JPanel panel1;
    private JTable tablaDocentesInscriptos;
    private JButton DARDEBAJADOCENTEButton;
    private JButton REGRESARButton;
    private JButton VERCURSOSDELDOCENTEButton;
    private JButton REINICIARCONTRASEÑAButton;
    private final MetodosGestionDocentes metodos;
public GestionDocentes() {

    metodos = new MetodosGestionDocentes(this);
    metodos.generarTablas();

    DARDEBAJADOCENTEButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if ((metodos.consultarHabilitado())){
                metodos.deshabilitarDocente();
                JOptionPane.showMessageDialog(null, "Docente dado de baja exitosamente");
            }
            else if (!metodos.consultarHabilitado()){
                metodos.deshabilitarDocente();
                JOptionPane.showMessageDialog(null, "Docente habilitado exitosamente");
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

    VERCURSOSDELDOCENTEButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            metodos.listadecursos();
        }
    });

    REINICIARCONTRASEÑAButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                metodos.reiniciarPassword();
                JOptionPane.showMessageDialog(null, "Contraseña reiniciada exitosamente");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    });
}
    public JTable getTabla(){
    return tablaDocentesInscriptos;
    }

    public void setVisible(boolean visible) {
        JFrame frame = new JFrame("Gestión de docentes");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(visible);
    }
}
