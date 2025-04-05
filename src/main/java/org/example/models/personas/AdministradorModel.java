package org.example.models.personas;

import org.example.models.PrestamoLibroModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdministradorModel extends PersonaModel{
    private static int idCounter =0;
    protected int id;
    private boolean permisoEliminarUsuario;
    private boolean permisoEliminarLibro;
    private boolean permisoEliminarAdministrador;
    private boolean permitidoELiminar;
    private boolean permitidoCobrarMora;
    public static final String TIPO_PERSONA = "ADMINISTRADOR";
    private static List<AdministradorModel> ListAdmin = new ArrayList<>();
    static {
        ListAdmin.add(new AdministradorModel("Miguel", "Castro Leon", new Date(), "Calle 123", "1234567890101", "555-1234", "admin1", "password1", new Date(), true, false, true, false, true));
        ListAdmin.add(new AdministradorModel("Maria", "Lopez Gomez", new Date(), "Avenida 45", "9876543210102", "555-5678", "admin2", "password2", new Date(), false, true, false, true, false));
        ListAdmin.add(new AdministradorModel("Carlos", "Perez Diaz", new Date(), "Zona 10", "1928374650103", "555-7890", "carlospd", "password3", new Date(), true, true, false, false, true));
        ListAdmin.add(new AdministradorModel("Ana", "Ramirez Soto", new Date(), "Boulevard 8", "1029384756104", "555-1111", "anar", "password4", new Date(), false, false, true, true, false));
        ListAdmin.add(new AdministradorModel("Luis", "Martinez Rojas", new Date(), "Colonia 7", "5647382910105", "555-2222", "luismr", "password5", new Date(), true, false, false, true, true));

    }

    public AdministradorModel() {
        System.out.println("Constructor Vacio AdministradorMode");
        id=++idCounter;
    }

    public AdministradorModel(String nombres, String apellidos, Date fechaNacimiento, String direccion, String documentoDPI, String numeroTelefono, String usuario, String contrasena, Date fechaRegistro, boolean permisoEliminarUsuario, boolean permisoEliminarLibro, boolean permisoEliminarAdministrador, boolean permitidoELiminar, boolean permitidoCobrarMora) {
        super(nombres, apellidos, fechaNacimiento, direccion, documentoDPI, numeroTelefono, usuario, contrasena, fechaRegistro);
        id=++idCounter;
        this.permisoEliminarUsuario = permisoEliminarUsuario;
        this.permisoEliminarLibro = permisoEliminarLibro;
        this.permisoEliminarAdministrador = permisoEliminarAdministrador;
        this.permitidoELiminar = permitidoELiminar;
        this.permitidoCobrarMora = permitidoCobrarMora;
    }

    public boolean isPermisoEliminarUsuario() {
        return permisoEliminarUsuario;
    }

    public boolean isPermisoEliminarLibro() {
        return permisoEliminarLibro;
    }

    public boolean isPermisoEliminarAdministrador() {
        return permisoEliminarAdministrador;
    }

    public boolean isPermitidoELiminar() {
        return permitidoELiminar;
    }

    public boolean isPermitidoCobrarMora() {
        return permitidoCobrarMora;
    }

    public String getTIPO_PERSONA() {
        return TIPO_PERSONA;
    }

    public void setPermisoEliminarUsuario(boolean permisoEliminarUsuario) {
        this.permisoEliminarUsuario = permisoEliminarUsuario;
    }

    public void setPermisoEliminarLibro(boolean permisoEliminarLibro) {
        this.permisoEliminarLibro = permisoEliminarLibro;
    }

    public void setPermisoEliminarAdministrador(boolean permisoEliminarAdministrador) {
        this.permisoEliminarAdministrador = permisoEliminarAdministrador;
    }

    public void setPermitidoELiminar(boolean permitidoELiminar) {
        this.permitidoELiminar = permitidoELiminar;
    }

    public void setPermitidoCobrarMora(boolean permitidoCobrarMora) {
        this.permitidoCobrarMora = permitidoCobrarMora;
    }

    public AdministradorModel loginAdmin (String _usuario, String  _contrasena)
    {
        for (AdministradorModel useradmin:ListAdmin)
        {
            if (useradmin.getUsuario().equals(_usuario) && useradmin.getContrasena().equals(_contrasena))
            {
                return useradmin;
            }
        }
        return null;
    }

}
