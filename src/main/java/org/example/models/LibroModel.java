package org.example.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LibroModel {
    private static int idcounter=0;
    protected int idLibro;
    private String nombre;
    private String autor;
    private String editorial;
    private int cantidadPaginas;
    private Date fechaIngresoEnBiblioteca;
    private Date fechaPublicacionLibro;
    private boolean libroPerdido;
    private boolean disponible;
    private int diasPermitidoPrestamo;


    private static List<LibroModel> listaLibros = new ArrayList<>();
    private static List<PrestamoLibroModel> historialPrestamo = new ArrayList<>();
    private final Long MILISEGUNDOS_POR_DIA =24 * 60 * 60 * 1000L;
    static {
        Date fechaActual = new Date();
        listaLibros.add(new LibroModel("El Quijote", "Miguel de Cervantes", "Editorial Espasa", 1023, fechaActual, fechaActual, false, true, 7));
        listaLibros.add(new LibroModel("Cien Años de Soledad", "Gabriel García Márquez", "Editorial Sudamericana", 471, fechaActual, fechaActual, false, false, 14));
        listaLibros.add(new LibroModel("1984", "George Orwell", "Secker & Warburg", 328, fechaActual, fechaActual, false, true, 10));
        listaLibros.add(new LibroModel("Fahrenheit 451", "Ray Bradbury", "Ballantine Books", 158, fechaActual, fechaActual, false, true, 7));
        listaLibros.add(new LibroModel("Matar a un Ruiseñor", "Harper Lee", "J.B. Lippincott & Co.", 281, fechaActual, fechaActual, false, true, 7));
        listaLibros.add(new LibroModel("El Principito", "Antoine de Saint-Exupéry", "Reynal & Hitchcock", 96, fechaActual, fechaActual, false, true, 7));
        listaLibros.add(new LibroModel("El Hobbit", "J.R.R. Tolkien", "Allen & Unwin", 310, fechaActual, fechaActual, false, true, 10));
        listaLibros.add(new LibroModel("Donde los Árboles Cantan", "Laura Gallego", "SM", 477, fechaActual, fechaActual, false, true, 15));
        listaLibros.add(new LibroModel("El Nombre del Viento", "Patrick Rothfuss", "DAW Books", 662, fechaActual, fechaActual, false, true, 12));
        listaLibros.add(new LibroModel("Harry Potter y la Piedra Filosofal", "J.K. Rowling", "Bloomsbury", 223, fechaActual, fechaActual, false, true, 7));

    }

    public LibroModel() {
    }

    public LibroModel(String nombre, String autor, String editorial, int cantidadPaginas, Date fechaIngresoEnBiblioteca, Date fechaPublicacionLibro, boolean libroPerdido, boolean disponible, int diasPermitidoPrestamo) {
        this.idLibro=++idcounter;
        this.nombre = nombre;
        this.autor = autor;
        this.editorial = editorial;
        this.cantidadPaginas = cantidadPaginas;
        this.fechaIngresoEnBiblioteca = fechaIngresoEnBiblioteca;
        this.fechaPublicacionLibro = fechaPublicacionLibro;
        this.libroPerdido = libroPerdido;
        this.disponible = disponible;
        this.diasPermitidoPrestamo = diasPermitidoPrestamo;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public String getNombre() {
        return nombre;
    }

    public String getAutor() {
        return autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public int getCantidadPaginas() {
        return cantidadPaginas;
    }

    public Date getFechaIngresoEnBiblioteca() {
        return fechaIngresoEnBiblioteca;
    }

    public Date getFechaPublicacionLibro() {
        return fechaPublicacionLibro;
    }

    public boolean isLibroPerdido() {
        return libroPerdido;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public int getDiasPermitidoPrestamo() {
        return diasPermitidoPrestamo;
    }

    public static List<LibroModel> getListaLibros() {
        return listaLibros;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public void setCantidadPaginas(int cantidadPaginas) {
        this.cantidadPaginas = cantidadPaginas;
    }

    public void setFechaIngresoEnBiblioteca(Date fechaIngresoEnBiblioteca) {
        this.fechaIngresoEnBiblioteca = fechaIngresoEnBiblioteca;
    }

    public void setFechaPublicacionLibro(Date fechaPublicacionLibro) {
        this.fechaPublicacionLibro = fechaPublicacionLibro;
    }

    public void setLibroPerdido(boolean libroPerdido) {
        this.libroPerdido = libroPerdido;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public void setDiasPermitidoPrestamo(int diasPermitidoPrestamo) {
        this.diasPermitidoPrestamo = diasPermitidoPrestamo;
    }

    public void listarLibro()
    {
        for(LibroModel libro:listaLibros)
        {
          System.out.println("ID: "+libro.getIdLibro()+" Libro: "+libro.getNombre());
        }
    }

    public String prestarLibro(int idUsuario, int idLibro)
    {
        String mensaje ="";
        if (consultarDisponiblidadLibro(idLibro))
        {
            Date fechaHoy = new Date();
            LibroModel libroAPrestar = obtenerObjetLibro(idLibro);
            libroAPrestar.setDisponible(false);
            Date fechaDevolucion = new Date(fechaHoy.getTime()+(libroAPrestar.getDiasPermitidoPrestamo()*MILISEGUNDOS_POR_DIA));

            PrestamoLibroModel historialLibro = new PrestamoLibroModel(
                    idUsuario,fechaHoy,fechaDevolucion,libroAPrestar.getIdLibro(),0.0,true,false);
            PrestamoLibroModel.listaPrestamos.add(historialLibro);
            mensaje = "libro prestado con exito";
        }else
        {
            mensaje = "no hay disponibilidad de este libro";
        }
        return mensaje;
    }
    public boolean consultarDisponiblidadLibro (int idLibro) {
        if (!listaLibros.isEmpty()) {
            for (LibroModel librotemp : listaLibros) {
                if (librotemp.getIdLibro() == idLibro) {
                    if (librotemp.isDisponible())
                    {
                        System.out.println(librotemp.getNombre()+" disponible: "+librotemp.isDisponible());
                        return true;
                    }else{
                        System.out.println(librotemp.getNombre()+" disponible: "+librotemp.isDisponible());
                        return false;
                    }

                }
            }
        }
        return false;
    }
    public static LibroModel obtenerObjetLibro(int idLibro)
    {
        for(LibroModel libro:listaLibros)
        {
            if (libro.getIdLibro()==idLibro)
            {
                return libro;
            }
        }
        return null;
    }

    public void agregarLibro (LibroModel libro)
    {
        listaLibros.add(libro);
    }
    public boolean eliminarLibro (int _idLibro)
    {
        LibroModel libro = obtenerObjetLibro(_idLibro);
        if (libro!=null)
        {
            listaLibros.remove(libro);
        }
        return false;
    }


    @Override
    public String toString() {
       return nombre+", "+autor;
    }
}
