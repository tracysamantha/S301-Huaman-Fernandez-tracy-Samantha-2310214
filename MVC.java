/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejerciciosem4;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author tracy
 */

public class MVC extends JFrame {

    private JTextField txtDescripcion;
    private JTextField txtEmail;
    private JFormattedTextField txtFecha;
    private JRadioButton rbtnDiario;
    private JRadioButton rbtnSemanal;
    private JRadioButton rbtnMensual;
    private JCheckBox chkAlarma;
    private JButton btnGuardar;
    private JButton btnLimpiar;
    private JTable tablaEventos;
    private DefaultTableModel modeloTabla;

    public MVC() {
        setTitle("Administrador de Eventos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JTabbedPane pestañas = new JTabbedPane();

        JPanel pnlFormulario = new JPanel();
        pnlFormulario.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        pnlFormulario.add(new JLabel("Event description"), gbc);
        gbc.gridx = 1;
        txtDescripcion = new JTextField(20);
        pnlFormulario.add(txtDescripcion, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        pnlFormulario.add(new JLabel("Foward e-mail"), gbc);
        gbc.gridx = 1;
        txtEmail = new JTextField(20);
        pnlFormulario.add(txtEmail, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        pnlFormulario.add(new JLabel("Date"), gbc);
        gbc.gridx = 1;
        txtFecha = new JFormattedTextField(new SimpleDateFormat("dd/MM/yyyy"));
        txtFecha.setColumns(10);
        pnlFormulario.add(txtFecha, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        pnlFormulario.add(new JLabel("Frecuency"), gbc);
        JPanel pnlFrecuencia = new JPanel();
        ButtonGroup grupoFrecuencia = new ButtonGroup();
        rbtnDiario = new JRadioButton("Daily");
        rbtnSemanal = new JRadioButton("Weekly");
        rbtnMensual = new JRadioButton("Monthly");
        grupoFrecuencia.add(rbtnDiario);
        grupoFrecuencia.add(rbtnSemanal);
        grupoFrecuencia.add(rbtnMensual);
        pnlFrecuencia.add(rbtnDiario);
        pnlFrecuencia.add(rbtnSemanal);
        pnlFrecuencia.add(rbtnMensual);
        gbc.gridx = 1;
        pnlFormulario.add(pnlFrecuencia, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        chkAlarma = new JCheckBox("Alarm");
        pnlFormulario.add(chkAlarma, gbc);


        gbc.gridx = 0;
        gbc.gridy = 5;
        btnGuardar = new JButton("Save");
        pnlFormulario.add(btnGuardar, gbc);
        gbc.gridx = 1;
        btnLimpiar = new JButton("Clean");
        pnlFormulario.add(btnLimpiar, gbc);

        pestañas.add("New event", pnlFormulario);


        JPanel pnlTabla = new JPanel(new BorderLayout());
        String[] columnas = {"Date", "Description", "Frecuency", "E-mail", "Alarm"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaEventos = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaEventos);
        pnlTabla.add(scrollPane, BorderLayout.CENTER);
        pestañas.add("Events", pnlTabla);

        add(pestañas);

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener los datos del formulario
                String descripcion = txtDescripcion.getText();
                String email = txtEmail.getText();
                String fecha = txtFecha.getText();

                String frecuencia = "";
                if (rbtnDiario.isSelected()) {
                    frecuencia = "Daily";
                } else if (rbtnSemanal.isSelected()) {
                    frecuencia = "Weekly";
                } else if (rbtnMensual.isSelected()) {
                    frecuencia = "Monthly";
                }
                String alarma = chkAlarma.isSelected() ? "ON" : "OFF";

                if (descripcion.isEmpty() || email.isEmpty() || fecha.isEmpty() || frecuencia.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Agregar los datos a la tabla
                    modeloTabla.addRow(new Object[]{fecha, descripcion, frecuencia, email, alarma});
                    limpiarFormulario();
                }
            }
        });

        // Acción del botón Limpiar
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarFormulario();
            }
        });
    }

    private void limpiarFormulario() {
        txtDescripcion.setText("");
        txtEmail.setText("");
        txtFecha.setValue(null);
        rbtnDiario.setSelected(false);
        rbtnSemanal.setSelected(false);
        rbtnMensual.setSelected(false);
        chkAlarma.setSelected(false);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MVC().setVisible(true);
            }
        });
    }
}