package Usuarios.Docentes.Interfaces;

import Usuarios.Docentes.GestionRecursos.CargaABD;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CrearCurso {
    private JTextField txtNombre;
    private JTextField txtDescripcion;
    private JTextField txtTope;
    private JButton PREVISUALIZARCURSOButton;
    private JComboBox<String> comboBox1;
    private JList<String> listaPrefabCurso;
    private JButton CREARButton;
    private JPanel panel1;
    private JButton REGRESARButton;
    private JButton AÑADIRCURSOREQUISITOButton;
    private DefaultListModel<String> lista;
    private String nombre;
    private String descripcion;
    private int tope;
    private CargaABD carga;

    public CrearCurso() {

        carga = new CargaABD(this);
        lista = new DefaultListModel<>();
        listaPrefabCurso.setModel(lista);
        cargarBox();

        PREVISUALIZARCURSOButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                previsualizar();
            }
        });

        CREARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                carga.crearCurso();
                cargarBox();
                lista.removeAllElements();
                txtDescripcion.setText(null);
                txtNombre.setText(null);
                txtTope.setText(null);

            }
        });
        REGRESARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Docentes vent = new Docentes();
                vent.setVisible(true);
                JFrame frame = (JFrame) SwingUtilities.getRoot(REGRESARButton);
                frame.dispose();
            }
        });
        AÑADIRCURSOREQUISITOButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (comboBox1.getSelectedIndex() == 0){
                    JOptionPane.showMessageDialog(null, "Ha seleccionado SIN REQUISITOS");
                } else {
                    carga.getNombreCursoRequisito();
                    lista.addElement("Curso requisito: " + getComboBox());
                }
            }
        });
    }

    private void previsualizar(){

        nombre = txtNombre.getText();
        descripcion = txtDescripcion.getText();
        tope = Integer.parseInt(txtTope.getText());

        lista.addElement("Nombre del curso: " + nombre);
        lista.addElement("Descripción: " + descripcion);
        lista.addElement("Tope de alumnos: " + tope);

    }
    private void cargarBox(){
        comboBox1.addItem("Sin requisitos");
        ArrayList<String> nombresCursos = carga.getNombresCursos();

        for (String nombreCurso : nombresCursos) {
            comboBox1.addItem(nombreCurso);
        }
    }
    public String getComboBox() {
        return (String) comboBox1.getSelectedItem();
    }

    public String getNombre(){
        nombre = txtNombre.getText();
        return nombre;
    }
    public String getDescripcion(){
        descripcion = txtDescripcion.getText();
        return descripcion;
    }

    public int getTope(){
        tope = Integer.parseInt(txtTope.getText());
        return tope;
    }

    public void setVisible(boolean visible) {
        JFrame frame = new JFrame("Creación de cursos");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(visible);
    }
}
