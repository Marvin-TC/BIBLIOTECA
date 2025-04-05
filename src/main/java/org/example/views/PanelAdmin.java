package org.example.views;

import org.example.models.LibroModel;
import org.example.models.PrestamoLibroModel;
import org.example.models.personas.AdministradorModel;
import org.example.models.personas.UsuarioModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class PanelAdmin extends JFrame {
    AdministradorModel adminLogeado;
    private JPanel PanelMain;
    private JTabbedPane tabbedPane1;
    private JTable tablePendientes;
    private JButton ENTREGARALAPERSONAButton;
    private JTable tableHistorial;
    private JButton butonRecibir;
    private JButton butomLogin;
    private List<PrestamoLibroModel> listLibrosPendientesEntrega;
    private List<PrestamoLibroModel> listLibrosPrestados;
    PrestamoLibroModel prestamos = new PrestamoLibroModel();
    UsuarioModel usuarioPrestador = new UsuarioModel();
    DefaultTableModel tableLibrosPendienteEntrega;
    DefaultTableModel tableLibrosHistorial;
    PrestamoLibroModel prestamoSeleccionado;
    PrestamoLibroModel libroAEntregar;

    public PanelAdmin(AdministradorModel adminLogeado) {
        this.adminLogeado=adminLogeado;

        setContentPane(PanelMain);
        setTitle("Bienvenido");
        setSize(450,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        listLibrosPendientesEntrega = new ArrayList<>();
        listLibrosPrestados = new ArrayList<>();


        tableLibrosPendienteEntrega = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Evita la edición
            }
        };
        tableLibrosHistorial = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Evita la edición
            }
        };


        tableLibrosPendienteEntrega.addColumn("Libro");
        tableLibrosPendienteEntrega.addColumn("Usuario");
        tableLibrosPendienteEntrega.addColumn("Fecha Entrega");

        tableLibrosHistorial.addColumn("Libro");
        tableLibrosHistorial.addColumn("Usuario");
        tableLibrosHistorial.addColumn("Fecha Entrega");

        llenarTabla();
        LlenarTablaHistorial();

        tablePendientes.setModel(tableLibrosPendienteEntrega);
        tablePendientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Permite seleccionar solo una fila
        tablePendientes.setRowSelectionAllowed(true); // Habilita la selección de filas
        tablePendientes.setColumnSelectionAllowed(false);

        tableHistorial.setModel(tableLibrosHistorial);
        tableHistorial.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Permite seleccionar solo una fila
        tableHistorial.setRowSelectionAllowed(true); // Habilita la selección de filas
        tableHistorial.setColumnSelectionAllowed(false);





        tablePendientes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                   if (e.getClickCount()==2)
                   {
                       int fila = tablePendientes.getSelectedRow();
                       if (fila != -1) {
                           prestamoSeleccionado = listLibrosPendientesEntrega.get(fila);
                       }
                   }
            }
        });

        tableHistorial.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount()==2)
                {
                    int fila = tableHistorial.getSelectedRow();
                    if (fila != -1) {
                        libroAEntregar = listLibrosPrestados.get(fila);
                    }
                }
            }
        });





        butomLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login login = new Login();
                dispose();
            }
        });
        ENTREGARALAPERSONAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prestamoSeleccionado.entregarLibroAUsuario(prestamoSeleccionado.getId());
                llenarTabla();
            }
        });
        butonRecibir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                libroAEntregar.entregarLibroABiblioteca(libroAEntregar.getId());
                LlenarTablaHistorial();
            }
        });
    }

    public void llenarTabla()
    {
        tableLibrosPendienteEntrega.setRowCount(0);
        listLibrosPendientesEntrega.clear();
        listLibrosPendientesEntrega.addAll(prestamos.getLibrosPendientesDeEntregar());
        for (PrestamoLibroModel fila: listLibrosPendientesEntrega)
        {
            tableLibrosPendienteEntrega.addRow(new Object[]{
                    LibroModel.obtenerObjetLibro(fila.getIdLibro()).getNombre(),
                    usuarioPrestador.findUserById(fila.getIdpersona()).getNombres()+" "+usuarioPrestador.findUserById(fila.getIdpersona()).getApellidos(),
                    fila.getFechaDevolucion()});
        }
        LlenarTablaHistorial();
    }

    public void LlenarTablaHistorial()
    {
        tableLibrosHistorial.setRowCount(0);
        listLibrosPrestados.clear();
        listLibrosPrestados.addAll(prestamos.getHistorialLibrosPrestados());
        for (PrestamoLibroModel fila: listLibrosPrestados)
        {
            tableLibrosHistorial.addRow(new Object[]{
                    LibroModel.obtenerObjetLibro(fila.getIdLibro()).getNombre(),
                    usuarioPrestador.findUserById(fila.getIdpersona()).getNombres()+" "+usuarioPrestador.findUserById(fila.getIdpersona()).getApellidos(),
                    fila.getFechaDevolucion()});
        }
    }
}
