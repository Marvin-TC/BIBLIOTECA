package org.example.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PrestamoLibroModel {
    public static int idCounter=0;
    protected int id;
    private int idpersona;
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    private int idLibro;
    private double mora;
    private boolean libroSolisitado;
    private boolean libroRecogido;

    public static List<PrestamoLibroModel> listaPrestamos = new ArrayList<>();

    public PrestamoLibroModel(int idpersona, Date fechaPrestamo, Date fechaDevolucion, int idLibro , double _mora, boolean _libroSolisitado, boolean _libroRecogido) {
        this.id=++idCounter;
        this.idpersona = idpersona;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.idLibro = idLibro;
        this.mora = _mora;
        this.libroSolisitado=_libroSolisitado;
        this.libroRecogido=_libroRecogido;
    }

    public PrestamoLibroModel() {
        this.id=++idCounter;
    }

    public int getId() {
        return id;
    }

    public int getIdpersona() {
        return idpersona;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public double getMora() {
        return mora;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public boolean isLibroSolisitado() {
        return libroSolisitado;
    }

    public boolean isLibroRecogido() {
        return libroRecogido;
    }

    public static List<PrestamoLibroModel> getListaPrestamos() {
        return listaPrestamos;
    }

    public void setIdpersona(int idpersona) {
        this.idpersona = idpersona;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public void setMora(double mora) {
        this.mora = mora;
    }

    public void setLibroSolisitado(boolean libroSolisitado) {
        this.libroSolisitado = libroSolisitado;
    }

    public void setLibroRecogido(boolean libroRecogido) {
        this.libroRecogido = libroRecogido;
    }

    @Override
    public String toString() {
        return  LibroModel.obtenerObjetLibro(this.idLibro).getNombre();
    }

    public static List<PrestamoLibroModel> librosPrestadosPorUsuario (int idUsuario)
    {
        List<PrestamoLibroModel> listPorUsuario = new ArrayList<>();
        for (PrestamoLibroModel prestamos: listaPrestamos)
        {
            if (prestamos.idpersona==idUsuario)
            {
                listPorUsuario.add(prestamos);
            }
        }
        return listPorUsuario;
    }

    public String getDiasFaltantes() {
        Date hoy = new Date(); // Fecha actual
        long diferenciaMillis = fechaDevolucion.getTime() - hoy.getTime();
        long diasFaltantes = TimeUnit.MILLISECONDS.toDays(diferenciaMillis);
        return String.valueOf(diasFaltantes);
    }
    public  List<PrestamoLibroModel> getLibrosPendientesDeEntregar()
    {
        List<PrestamoLibroModel> listaPendientes = new ArrayList<>();
        for (PrestamoLibroModel libroPrestado: listaPrestamos)
        {
            if (!libroPrestado.isLibroRecogido())
            {listaPendientes.add(libroPrestado);}
        }
        return listaPendientes;
    }

    public List<PrestamoLibroModel> getHistorialLibrosPrestados()
    {
        List<PrestamoLibroModel> listaLibrosPrestados = new ArrayList<>();
        for (PrestamoLibroModel libroPrestado: listaPrestamos)
        {
            if (libroPrestado.isLibroRecogido())
            {listaLibrosPrestados.add(libroPrestado);}
        }
        return listaLibrosPrestados;
    }

    public void entregarLibroAUsuario(int id)
    {
        PrestamoLibroModel prestamoLibroModel = obtenerPrestamo(id);
        if (prestamoLibroModel!=null)
        {
            prestamoLibroModel.setLibroRecogido(true);
        }
    }

    public void entregarLibroABiblioteca(int id)
    {
        PrestamoLibroModel prestamoLibroModel = obtenerPrestamo(id);
        if (prestamoLibroModel!=null)
        {
           LibroModel libro= LibroModel.obtenerObjetLibro(prestamoLibroModel.idLibro);
           libro.setDisponible(true);
            listaPrestamos.remove(prestamoLibroModel);
        }
    }

    public PrestamoLibroModel obtenerPrestamo(int id)
    {
        for (PrestamoLibroModel temp:listaPrestamos)
        {
            if (temp.getId()==id)
            {
                return temp;
            }
        }
        return null;
    }

}
