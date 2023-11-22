package Usuarios.Administrativos.Interfaces;

import Usuarios.Administrativos.GestionAlumnos.GestionAlumnos;
import Usuarios.Administrativos.GestionCursos.GestorCursos;
import Usuarios.Administrativos.GestionDocentes.GestionDocentes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Administrativos {
    private JButton GESTIONARPERSONALDOCENTEButton;
    private JButton GESTIONARPERFILALUMNADOButton;
    private JButton GESTIONARCURSOSDISPONIBLESButton;
    private JPanel panel1;
    private JButton INGRESARNUEVOUSUARIOButton;

    public Administrativos() {
    GESTIONARPERSONALDOCENTEButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            GestionDocentes vent = new GestionDocentes();
            vent.setVisible(true);
            JFrame frame = (JFrame) SwingUtilities.getRoot(GESTIONARPERSONALDOCENTEButton);
            frame.dispose();
        }
    });

    GESTIONARPERFILALUMNADOButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            GestionAlumnos vent = new GestionAlumnos();
            vent.setVisible(true);
            JFrame frame = (JFrame) SwingUtilities.getRoot(GESTIONARPERFILALUMNADOButton);
            frame.dispose();
        }
    });

    GESTIONARCURSOSDISPONIBLESButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            GestorCursos curso = null;
            try {
                curso = new GestorCursos();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            curso.setVisible(true);
        }
    });
        INGRESARNUEVOUSUARIOButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Registro vent = new Registro();
                vent.setVisible(true);
            }
        });

    }
    public void setVisible(boolean visible) {
        JFrame frame = new JFrame("Perfil administrativo");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(visible);
    }
}
