package org.example.models.personas;

import java.util.Date;

public class PersonaModel {
    private static int idCounter = 0;
     protected int id;
    private String nombres;
    private String apellidos;
    private Date fechaNacimiento;
    private String direccion;
    private String documentoDPI;
    private String numeroTelefono;
    private String usuario;
    private String contrasena;
    private Date fechaRegistro;

    public PersonaModel() {
        this.id = ++idCounter;
        System.out.println("Constructor Vacio PersonaModel");
    }

    public PersonaModel(String nombres, String apellidos, Date fechaNacimiento, String direccion, String documentoDPI, String numeroTelefono, String usuario, String contrasena, Date fechaRegistro) {
        this.id = ++idCounter;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.documentoDPI = documentoDPI;
        this.numeroTelefono = numeroTelefono;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.fechaRegistro = fechaRegistro;
        System.out.println("Constructor con parametros PersonaModel");
    }

    public int getId() {return id;}

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getDocumentoDPI() {
        return documentoDPI;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setDocumentoDPI(String documentoDPI) {
        this.documentoDPI = documentoDPI;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public String toString() {
        return "PersonaModel{" +
                "nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", direccion='" + direccion + '\'' +
                ", documentoDPI='" + documentoDPI + '\'' +
                ", numeroTelefono='" + numeroTelefono + '\'' +
                ", usuario='" + usuario + '\'' +
                ", contrasena='" + getContrasena() + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                '}';
    }
}
