package org.example.views;

import org.example.models.LibroModel;
import org.example.models.PrestamoLibroModel;
import org.example.models.personas.UsuarioModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class HistorialPrestamos  extends JFrame{
    private JPanel panelMain;
    private JComboBox jComboBoxLibrosPrestados;
    private JLabel jlUsuario;
    private JLabel jlLibro;
    private JLabel jlFechaEntrega;
    private JLabel jlDiasFaltantes;
    private JLabel jlMora;
    private JLabel jlRecogido;
    private JTable tablePrestamo;
    UsuarioModel usuariLogeado;
    PrestamoLibroModel prestamoLibro;

    public HistorialPrestamos(UsuarioModel _usuarioLogeado) {
        setContentPane(panelMain);
        setTitle("MIS LIBROS PRESTADOS");
        setSize(450,800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Esto solo cierra la ventana secundaria
        setVisible(true);
        this.usuariLogeado=_usuarioLogeado;


        DefaultComboBoxModel<PrestamoLibroModel> listaPorUsuario = new DefaultComboBoxModel<>(PrestamoLibroModel.librosPrestadosPorUsuario(usuariLogeado.getId()).toArray(new PrestamoLibroModel[0]));
        System.out.println(listaPorUsuario.getSize());
        jComboBoxLibrosPrestados.setModel(listaPorUsuario);
        jlUsuario.setText(usuariLogeado.getNombres()+" "+usuariLogeado.getApellidos());

        jComboBoxLibrosPrestados.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                prestamoLibro = (PrestamoLibroModel) jComboBoxLibrosPrestados.getSelectedItem();
                llenarDatos();
            }
        });

    }
    public void llenarDatos(){
        jlLibro.setText(LibroModel.obtenerObjetLibro(prestamoLibro.getIdLibro()).getNombre());
        jlFechaEntrega.setText(String.valueOf(prestamoLibro.getFechaDevolucion()));
        jlDiasFaltantes.setText(prestamoLibro.getDiasFaltantes());
        jlMora.setText(String.valueOf(prestamoLibro.getMora()));
        if (prestamoLibro.isLibroRecogido()) {jlRecogido.setText("ya recogido por el usuario");}else{jlRecogido.setText("pendiente de recoger");}
    }
}
