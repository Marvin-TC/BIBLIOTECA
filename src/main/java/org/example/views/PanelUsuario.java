package org.example.views;

import org.example.models.LibroModel;
import org.example.models.personas.UsuarioModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class PanelUsuario extends JFrame{
    private JComboBox cbLibros;
    private JPanel panelMain;
    private JLabel jLabelDisponibilidad;
    private JLabel jLabelNombre;
    private JLabel JLabelAutor;
    private JLabel jLabelFechaPublicacion;
    private JLabel jlNombreUsuario;
    private JButton PRESTARLIBROButton;
    private JLabel jlMensaje;
    private JButton misPrestamosButton;
    private JButton estadoDeCuentaButton;
    private JButton loginButton;

    private UsuarioModel usuarioLogeado;
    private LibroModel libroSeleccionado;

    public PanelUsuario(UsuarioModel usuarioParametro){
        this.usuarioLogeado=usuarioParametro;
        setContentPane(panelMain);
        setTitle("BIBLIOTECA PARA TODOS");
        setSize(450,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);


        DefaultComboBoxModel<LibroModel> model = new DefaultComboBoxModel<>(LibroModel.getListaLibros().toArray(new LibroModel[0]));
        cbLibros.setModel(model);
        cbLibros.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                libroSeleccionado = (LibroModel)cbLibros.getSelectedItem();
                llenardatosLibros(libroSeleccionado);
            }
        });
        jlNombreUsuario.setText(usuarioLogeado.getNombres()+" "+usuarioLogeado.getApellidos());

        PRESTARLIBROButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (libroSeleccionado!=null)
                {
                    System.out.println("libro a prestar: "+libroSeleccionado.getNombre()+ " ID: "+libroSeleccionado.getIdLibro());
                   String resultado = libroSeleccionado.prestarLibro(usuarioLogeado.getId(),libroSeleccionado.getIdLibro());
                   if (resultado.equals("libro prestado con exito"))
                   {
                       jlMensaje.setText(resultado);
                       limpiarLibros();
                   }else{
                       jlMensaje.setText(resultado);
                   }
                }else{
                    jlMensaje.setText("Seleeccione un libro para poder prestar");
                }
            }
        });
        misPrestamosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HistorialPrestamos panelPrestamo = new HistorialPrestamos(usuarioLogeado);
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login login = new Login();
                dispose();
            }
        });
    }



    public void llenardatosLibros(LibroModel libro)
    {
        String textoDisponible ="";
        if (libro.isDisponible()){textoDisponible="Disponible";}else{textoDisponible="No disponible";}
        jLabelDisponibilidad.setText(textoDisponible);
        jLabelNombre.setText(libro.getNombre());
        JLabelAutor.setText(libro.getAutor());
        jLabelFechaPublicacion.setText(libro.getFechaPublicacionLibro().toString());
    }
    public void limpiarLibros()
    {
        libroSeleccionado=null;
        jLabelDisponibilidad.setText("");
        jLabelNombre.setText("");
        JLabelAutor.setText("");
        jLabelFechaPublicacion.setText("");
    }

}
