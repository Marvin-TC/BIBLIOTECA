package org.example.views;

import org.example.models.personas.AdministradorModel;
import org.example.models.personas.UsuarioModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    private JPanel PanelMain;
    private JComboBox cbTipoUsuarios;
    private JTextField tfNombreUsuario;
    private JTextField tfContraseñaUsuario;
    private JButton btnIniciarSesion;
    private JLabel jlMensaje;
    private JButton btnRegistrar;

    private String usuarioIngresado;
    private String contraseñaIngresado;

    public Login()
    {
        setContentPane(PanelMain);
        setTitle("Bienvenido");
        setSize(450,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        UsuarioModel usuarioSesion = new UsuarioModel();
        AdministradorModel adminSesion = new AdministradorModel();


        btnIniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usuarioIngresado = tfNombreUsuario.getText();
                contraseñaIngresado = tfContraseñaUsuario.getText();
               if (!usuarioIngresado.isEmpty() || !contraseñaIngresado.isEmpty())
               {
                   if (cbTipoUsuarios.getSelectedItem().toString().equals(UsuarioModel.TIPO_PERSONA))
                   {
                       UsuarioModel usuarioLogeado = usuarioSesion.login(usuarioIngresado, contraseñaIngresado);
                       if (usuarioLogeado!=null)
                       {
                           jlMensaje.setText("Bienvenido "+usuarioLogeado.getNombres());
                           PanelUsuario panelUsuario = new PanelUsuario(usuarioLogeado);
                           dispose();
                       }else
                       {
                           jlMensaje.setText("usuario y contraseña Incorrectas");
                       }
                   } else if (cbTipoUsuarios.getSelectedItem().toString().equals(AdministradorModel.TIPO_PERSONA)) {
                        AdministradorModel adminLogeado = adminSesion.loginAdmin(usuarioIngresado,contraseñaIngresado);
                        if (adminLogeado!=null)
                        {
                            jlMensaje.setText("Bienvenido "+adminLogeado.getNombres());
                            PanelAdmin panelUsuario = new PanelAdmin(adminLogeado);
                            dispose();
                        }
                        else {
                            jlMensaje.setText("usuario y contraseña Incorrectas");
                        }
                   }
               }else{jlMensaje.setText("Ingrese los datos del usuario");}
            }
        });
    }

    public static void main(String [] args)
    {
        Login loginFrame = new Login();
    }



}
