package org.example.models.personas;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UsuarioModel extends PersonaModel {
    private double mora;
    private String tarjetaCredito;
    public static final String TIPO_PERSONA = "USUARIO";
    private static List<UsuarioModel> listUsuarios = new ArrayList<>();


    static {
        Date fechaActual = new Date();
        listUsuarios.add(new UsuarioModel("Marvin Magdiel", "Tiu Castro", fechaActual, "Ciudad", "132154512", "12345678", "user1", "password1", fechaActual, 0.0, ""));
        listUsuarios.add(new UsuarioModel("Pedro Antonio", "Gomez Reyes", fechaActual, "Centro", "987654321", "87654321", "user2", "password2", fechaActual, 10.5, "1234-5678-9101-1121"));
        listUsuarios.add(new UsuarioModel("Luis Fernando", "Ramirez López", fechaActual, "Zona 5", "456123789", "5553333", "user3", "password3", fechaActual, 5.0, "4321-8765-2109-3456"));
        listUsuarios.add(new UsuarioModel("Andrea Paola", "Martínez González", fechaActual, "Colonia 9", "741852963", "5554444", "user4", "password4", fechaActual, 2.5, "6789-1234-5678-9012"));
    }

    public UsuarioModel(){
        System.out.println("Constructor Vacio ClienteModel");
    }

    public UsuarioModel(String nombres, String apellidos, Date fechaNacimiento, String direccion, String documentoDPI, String numeroTelefono, String usuario, String contrasena, Date fechaRegistro, double mora, String tarjetaCredito) {
        super(nombres, apellidos, fechaNacimiento, direccion, documentoDPI, numeroTelefono, usuario, contrasena, fechaRegistro);
        id++;
        this.mora = mora;
        this.tarjetaCredito = tarjetaCredito;
        System.out.println("constructor con parametros ClienteModel");
    }

    public double getMora() {
        return mora;
    }

    public String getTarjetaCredito() {
        String mascara ="";
        if (!tarjetaCredito.isEmpty())
        { int i =0;
            do {mascara=mascara+"x";
                i++;
            }while(i<=tarjetaCredito.length());
            {
                mascara=mascara+tarjetaCredito.substring(i,tarjetaCredito.length()-4);
            }}
        return mascara;
    }

    public String getTIPO_PERSONA() {
        return TIPO_PERSONA;
    }

    public void setMora(double mora) {
        this.mora = mora;
    }

    public void setTarjetaCredito(String tarjetaCredito) {
        this.tarjetaCredito = tarjetaCredito;
    }

    public UsuarioModel login(String usuarioIngresado, String contrasenaIngresado)
    {
        for (UsuarioModel usuarioTemp: listUsuarios)
        {
            if (usuarioTemp.getUsuario().equals(usuarioIngresado)&& usuarioTemp.getContrasena().equals(contrasenaIngresado))
            {
                return usuarioTemp;
            }
        }
        return null;
    }

    public boolean eliminarUsuario (PersonaModel usuario)
    {
        listUsuarios.remove(usuario);
        return true;
    }

    public String agregarUsuario(UsuarioModel usuario)
    {
        id++;
        listUsuarios.add(usuario);
        return "usuario cliente registrado exitosamente";
    }

    public UsuarioModel findUserById(int id)
    {
        if (!listUsuarios.isEmpty())
        {
            for (UsuarioModel usuarioTemp : listUsuarios)
            {
                if (usuarioTemp.getId()==id)
                {
                    return usuarioTemp;
                }
            }
        }
        return null;
    }

}
