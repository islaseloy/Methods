package Usuarios.Docentes.Interfaces;

import Conexion.PasswordChange;
import Usuarios.Docentes.GestionRecursos.CargaABD;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Docentes {
    private JPanel panel1;
    private JButton GESTIONARMISCURSOSButton;
    private JButton CREARCURSOSButton;
    private JButton CAMBIARCONTRASEÑAButton;
    private final CargaABD carga = new CargaABD(new CrearCurso());
    private final PasswordChange change = new PasswordChange();

    public Docentes() {

        try {
            change.changePassword();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        CREARCURSOSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CrearCurso vent = new CrearCurso();
                vent.setVisible(true);
                JFrame frame = (JFrame) SwingUtilities.getRoot(CREARCURSOSButton);
                frame.dispose();
            }
        });
        GESTIONARMISCURSOSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                GestionCursosDoc vent = null;
                try {
                    vent = new GestionCursosDoc();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                vent.setVisible(true);
                JFrame frame = (JFrame) SwingUtilities.getRoot(GESTIONARMISCURSOSButton);
                frame.dispose();
        }
    });

        CAMBIARCONTRASEÑAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    carga.changePassword();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
    public void setVisible(boolean visible) {
        JFrame frame = new JFrame("Perfil docente");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(visible);
    }
}
