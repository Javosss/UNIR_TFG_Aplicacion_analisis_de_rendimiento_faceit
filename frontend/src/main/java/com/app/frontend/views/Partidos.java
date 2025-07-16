package com.app.frontend.views;

import com.app.frontend.controllers.MenuLateralController;
import com.app.frontend.controllers.PartidosController;
import com.app.frontend.models.Jugador;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.*;

/**
 * Interfaz partidos donde se mostrarán los partidos del jugador en una tabla. Además, en el momento en el que se seleccione un partido de la tabla,
 * se mostrarán las estadísticas detalladas del jugador en ese partido, junto con los jugadores del partido.
 * @author Javier
 */
public class Partidos extends javax.swing.JFrame implements MainView{
    private Jugador jugador;
    private MenuLateral menuLateral;
    private PartidosController controlador;

    
    public Partidos(Jugador jugador) {
        this.jugador = jugador;
        // Configuración básica del JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Añadir el menu lateral
        this.menuLateral = new MenuLateral(); 
        new MenuLateralController(menuLateral, this);

        initComponents();

        // Configuración correcta del layout
        panelMenuLateral.setLayout(new BorderLayout());
        panelMenuLateral.add(menuLateral, BorderLayout.CENTER);

        // Añadir componentes al frame principal
        add(panelMenuLateral, BorderLayout.WEST);
        add(panelInforrmacion, BorderLayout.CENTER);  // Asegúrate que panelInforrmacion contiene el contenido principal

        this.controlador = new PartidosController(this, jugador);
    }
    
    // Getters de los componentes
    public JTable getTablaPartidos() {
        return tablaPartidos;
    }
    
    public JTextArea getTextAreaClutch() {
        return textAreaClucthes;
    }
    
    public JTextArea getTextAreaEstadisticasIndividuales() {
        return textAreaEstadisticasIndividuales;
    }
    
    public JTextArea getTextAreaUtilidad() {
        return textAreaUtilidad;
    }
    
    public JTextArea getTextAreaArmas() {
        return jTextArea1;
    }
    

    public JPanel getPanelJugadorEquipo1() { 
        return panelJugadorEquipoJugador1; 
    }
    
    public JPanel getPanelJugadorEquipo2() { 
        return panelJugadorEquipoJugador2; 
    }
    
    public JPanel getPanelJugadorEquipo3() { 
        return panelJugadorEquipoJugador3; 
    }
    
    public JPanel getPanelJugadorEquipo4() { 
        return panelJugadorEquipoJugador4; 
    }
    
    public JPanel getPanelJugadorEquipo5() { 
        return panelJugadorEquipoJugador5; 
    }

    public JLabel getLabelNombreJugadorEquipo1() { 
        return labelNombreJugadorEquipoJugador1; 
    }
    
    public JLabel getLabelNombreJugadorEquipo2() { 
        return labelNombreJugadorEquipoJugador2; 
    }
    
    public JLabel getLabelNombreJugadorEquipo3() { 
        return labelNombreJugadorEquipoJugador3; 
    }
    
    public JLabel getLabelNombreJugadorEquipo4() { 
        return labelNombreJugadorEquipoJugador4; 
    }
    
    public JLabel getLabelNombreJugadorEquipo5() { 
        return labelNombreJugadorEquipoJugador5; 
    }

    public JLabel getImagenJugadorEquipo1() { 
        return imagenJugadorEquipoJugador1; 
    }
    
    public JLabel getImagenJugadorEquipo2() { 
        return imagenJugadorEquipoJugador2; 
    }
    
    public JLabel getImagenJugadorEquipo3() { 
        return imagenJugadorEquipoJugador3; 
    }
    
    public JLabel getImagenJugadorEquipo4() { 
        return imagenJugadorEquipoJugador4; 
    }
    
    public JLabel getImagenJugadorEquipo5() { 
        return imagenJugadorEquipoJugador5; 
    }

    public JLabel getImagenNivelJugadorEquipo1() { 
        return imagenNivelJugadorEquipoJugador1; 
    }
    
    public JLabel getImagenNivelJugadorEquipo2() { 
        return imagenNivelJugadorEquipoJugador2; 
    }
    
    public JLabel getImagenNivelJugadorEquipo3() { 
        return imagenNivelJugadorEquipoJugador3; 
    }
    
    public JLabel getImagenNivelJugadorEquipo4() { 
        return imagenNivelJugadorEquipoJugador4; 
    }
    
    public JLabel getImagenNivelJugadorEquipo5() { 
        return imagenNivelJugadorEquipoJugador5; 
    }

    public JPanel getPanelJugadorContrario1() { 
        return panelJugadorEquipoContrario1; 
    }
    
    public JPanel getPanelJugadorContrario2() { 
        return panelJugadorEquipoContrario2; 
    }
    
    public JPanel getPanelJugadorContrario3() { 
        return panelJugadorEquipoContrario3; 
    }
    
    public JPanel getPanelJugadorContrario4() { 
        return panelJugadorEquipoContrario4; 
    }
    
    public JPanel getPanelJugadorContrario5() { 
        return panelJugadorEquipoContrario5; 
    }

    public JLabel getLabelNombreJugadorContrario1() { 
        return labelNombreJugadorEquipoContrario1; 
    }
    
    public JLabel getLabelNombreJugadorContrario2() { 
        return labelNombreJugadorEquipoContrario2; 
    }
    
    public JLabel getLabelNombreJugadorContrario3() { 
        return labelNombreJugadorEquipoContrario3; 
    }
    
    public JLabel getLabelNombreJugadorContrario4() { 
        return labelNombreJugadorEquipoContrario4; 
    }
    
    public JLabel getLabelNombreJugadorContrario5() { 
        return labelNombreJugadorEquipoContrario5; 
    }

    public JLabel getImagenJugadorContrario1() { 
        return imagenJugadorEquipoContrario1; 
    }
    
    public JLabel getImagenJugadorContrario2() { 
        return imagenJugadorEquipoContrario2; 
    }
    public JLabel getImagenJugadorContrario3() { 
        return imagenJugadorEquipoContrario3; 
    }
    
    public JLabel getImagenJugadorContrario4() { 
        return imagenJugadorEquipoContrario4; 
    }
    
    public JLabel getImagenJugadorContrario5() { 
        return imagenJugadorEquipoContrario5; 
    }

    public JLabel getImagenNivelJugadorContrario1() { 
        return imagenNivelJugadorEquipoContrario1; 
    }
    
    public JLabel getImagenNivelJugadorContrario2() { 
        return imagenNivelJugadorEquipoContrario2; 
    }
    
    public JLabel getImagenNivelJugadorContrario3() { 
        return imagenNivelJugadorEquipoContrario3; 
    }
    
    public JLabel getImagenNivelJugadorContrario4() { 
        return imagenNivelJugadorEquipoContrario4; 
    }
    
    public JLabel getImagenNivelJugadorContrario5() { 
        return imagenNivelJugadorEquipoContrario5; 
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMenuLateral = new javax.swing.JPanel();
        panelInforrmacion = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPartidos = new javax.swing.JTable();
        panelEstadisticas = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        textAreaClucthes = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        textAreaEstadisticasIndividuales = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        textAreaUtilidad = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        panelInfoPartido = new javax.swing.JPanel();
        panelJugadorEquipoJugador5 = new javax.swing.JPanel();
        imagenJugadorEquipoJugador1 = new javax.swing.JLabel();
        labelNombreJugadorEquipoJugador1 = new javax.swing.JLabel();
        imagenNivelJugadorEquipoJugador1 = new javax.swing.JLabel();
        panelJugadorEquipoJugador4 = new javax.swing.JPanel();
        imagenJugadorEquipoJugador5 = new javax.swing.JLabel();
        labelNombreJugadorEquipoJugador5 = new javax.swing.JLabel();
        imagenNivelJugadorEquipoJugador5 = new javax.swing.JLabel();
        panelJugadorEquipoJugador3 = new javax.swing.JPanel();
        imagenJugadorEquipoJugador4 = new javax.swing.JLabel();
        labelNombreJugadorEquipoJugador4 = new javax.swing.JLabel();
        imagenNivelJugadorEquipoJugador4 = new javax.swing.JLabel();
        panelJugadorEquipoJugador2 = new javax.swing.JPanel();
        imagenJugadorEquipoJugador3 = new javax.swing.JLabel();
        labelNombreJugadorEquipoJugador3 = new javax.swing.JLabel();
        imagenNivelJugadorEquipoJugador3 = new javax.swing.JLabel();
        panelJugadorEquipoJugador1 = new javax.swing.JPanel();
        imagenJugadorEquipoJugador2 = new javax.swing.JLabel();
        labelNombreJugadorEquipoJugador2 = new javax.swing.JLabel();
        imagenNivelJugadorEquipoJugador2 = new javax.swing.JLabel();
        panelJugadorEquipoContrario1 = new javax.swing.JPanel();
        imagenJugadorEquipoContrario2 = new javax.swing.JLabel();
        labelNombreJugadorEquipoContrario2 = new javax.swing.JLabel();
        imagenNivelJugadorEquipoContrario2 = new javax.swing.JLabel();
        panelJugadorEquipoContrario2 = new javax.swing.JPanel();
        imagenJugadorEquipoContrario3 = new javax.swing.JLabel();
        labelNombreJugadorEquipoContrario3 = new javax.swing.JLabel();
        imagenNivelJugadorEquipoContrario3 = new javax.swing.JLabel();
        panelJugadorEquipoContrario3 = new javax.swing.JPanel();
        imagenJugadorEquipoContrario4 = new javax.swing.JLabel();
        labelNombreJugadorEquipoContrario4 = new javax.swing.JLabel();
        imagenNivelJugadorEquipoContrario4 = new javax.swing.JLabel();
        panelJugadorEquipoContrario4 = new javax.swing.JPanel();
        imagenJugadorEquipoContrario5 = new javax.swing.JLabel();
        labelNombreJugadorEquipoContrario5 = new javax.swing.JLabel();
        imagenNivelJugadorEquipoContrario5 = new javax.swing.JLabel();
        panelJugadorEquipoContrario5 = new javax.swing.JPanel();
        imagenJugadorEquipoContrario1 = new javax.swing.JLabel();
        labelNombreJugadorEquipoContrario1 = new javax.swing.JLabel();
        imagenNivelJugadorEquipoContrario1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelMenuLateral.setBackground(new java.awt.Color(153, 204, 255));

        javax.swing.GroupLayout panelMenuLateralLayout = new javax.swing.GroupLayout(panelMenuLateral);
        panelMenuLateral.setLayout(panelMenuLateralLayout);
        panelMenuLateralLayout.setHorizontalGroup(
            panelMenuLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 140, Short.MAX_VALUE)
        );
        panelMenuLateralLayout.setVerticalGroup(
            panelMenuLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 947, Short.MAX_VALUE)
        );

        panelInforrmacion.setBackground(new java.awt.Color(204, 204, 204));

        tablaPartidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaPartidos);

        panelEstadisticas.setBackground(new java.awt.Color(153, 153, 153));
        panelEstadisticas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        textAreaClucthes.setEditable(false);
        textAreaClucthes.setColumns(20);
        textAreaClucthes.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        textAreaClucthes.setLineWrap(true);
        textAreaClucthes.setRows(5);
        textAreaClucthes.setText("Clutching");
        textAreaClucthes.setWrapStyleWord(true);
        textAreaClucthes.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        textAreaClucthes.setMargin(new java.awt.Insets(5, 5, 5, 5));
        jScrollPane2.setViewportView(textAreaClucthes);

        textAreaEstadisticasIndividuales.setEditable(false);
        textAreaEstadisticasIndividuales.setColumns(20);
        textAreaEstadisticasIndividuales.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        textAreaEstadisticasIndividuales.setLineWrap(true);
        textAreaEstadisticasIndividuales.setRows(5);
        textAreaEstadisticasIndividuales.setText("Estadísticas individuales");
        textAreaEstadisticasIndividuales.setWrapStyleWord(true);
        textAreaEstadisticasIndividuales.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        textAreaEstadisticasIndividuales.setMargin(new java.awt.Insets(5, 5, 5, 5));
        jScrollPane3.setViewportView(textAreaEstadisticasIndividuales);

        textAreaUtilidad.setEditable(false);
        textAreaUtilidad.setColumns(20);
        textAreaUtilidad.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        textAreaUtilidad.setLineWrap(true);
        textAreaUtilidad.setRows(5);
        textAreaUtilidad.setText("Utility");
        textAreaUtilidad.setWrapStyleWord(true);
        textAreaUtilidad.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        textAreaUtilidad.setMargin(new java.awt.Insets(5, 5, 5, 5));
        jScrollPane4.setViewportView(textAreaUtilidad);

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setText("Rendimiento con armas");
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTextArea1.setMargin(new java.awt.Insets(5, 5, 5, 5));
        jScrollPane5.setViewportView(jTextArea1);

        panelJugadorEquipoJugador5.setBackground(new java.awt.Color(255, 255, 255));
        panelJugadorEquipoJugador5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        panelJugadorEquipoJugador5.setLayout(new java.awt.GridLayout(1, 0));
        panelJugadorEquipoJugador5.add(imagenJugadorEquipoJugador1);
        panelJugadorEquipoJugador5.add(labelNombreJugadorEquipoJugador1);
        panelJugadorEquipoJugador5.add(imagenNivelJugadorEquipoJugador1);

        panelJugadorEquipoJugador4.setBackground(new java.awt.Color(255, 255, 255));
        panelJugadorEquipoJugador4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        panelJugadorEquipoJugador4.setLayout(new java.awt.GridLayout(1, 0));
        panelJugadorEquipoJugador4.add(imagenJugadorEquipoJugador5);
        panelJugadorEquipoJugador4.add(labelNombreJugadorEquipoJugador5);
        panelJugadorEquipoJugador4.add(imagenNivelJugadorEquipoJugador5);

        panelJugadorEquipoJugador3.setBackground(new java.awt.Color(255, 255, 255));
        panelJugadorEquipoJugador3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        panelJugadorEquipoJugador3.setLayout(new java.awt.GridLayout(1, 0));
        panelJugadorEquipoJugador3.add(imagenJugadorEquipoJugador4);
        panelJugadorEquipoJugador3.add(labelNombreJugadorEquipoJugador4);
        panelJugadorEquipoJugador3.add(imagenNivelJugadorEquipoJugador4);

        panelJugadorEquipoJugador2.setBackground(new java.awt.Color(255, 255, 255));
        panelJugadorEquipoJugador2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        panelJugadorEquipoJugador2.setLayout(new java.awt.GridLayout(1, 0));
        panelJugadorEquipoJugador2.add(imagenJugadorEquipoJugador3);
        panelJugadorEquipoJugador2.add(labelNombreJugadorEquipoJugador3);
        panelJugadorEquipoJugador2.add(imagenNivelJugadorEquipoJugador3);

        panelJugadorEquipoJugador1.setBackground(new java.awt.Color(255, 255, 255));
        panelJugadorEquipoJugador1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        panelJugadorEquipoJugador1.setLayout(new java.awt.GridLayout(1, 0));
        panelJugadorEquipoJugador1.add(imagenJugadorEquipoJugador2);
        panelJugadorEquipoJugador1.add(labelNombreJugadorEquipoJugador2);
        panelJugadorEquipoJugador1.add(imagenNivelJugadorEquipoJugador2);

        panelJugadorEquipoContrario1.setBackground(new java.awt.Color(255, 255, 255));
        panelJugadorEquipoContrario1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        panelJugadorEquipoContrario1.setLayout(new java.awt.GridLayout(1, 0));
        panelJugadorEquipoContrario1.add(imagenJugadorEquipoContrario2);
        panelJugadorEquipoContrario1.add(labelNombreJugadorEquipoContrario2);
        panelJugadorEquipoContrario1.add(imagenNivelJugadorEquipoContrario2);

        panelJugadorEquipoContrario2.setBackground(new java.awt.Color(255, 255, 255));
        panelJugadorEquipoContrario2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        panelJugadorEquipoContrario2.setLayout(new java.awt.GridLayout(1, 0));
        panelJugadorEquipoContrario2.add(imagenJugadorEquipoContrario3);
        panelJugadorEquipoContrario2.add(labelNombreJugadorEquipoContrario3);
        panelJugadorEquipoContrario2.add(imagenNivelJugadorEquipoContrario3);

        panelJugadorEquipoContrario3.setBackground(new java.awt.Color(255, 255, 255));
        panelJugadorEquipoContrario3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        panelJugadorEquipoContrario3.setLayout(new java.awt.GridLayout(1, 0));
        panelJugadorEquipoContrario3.add(imagenJugadorEquipoContrario4);
        panelJugadorEquipoContrario3.add(labelNombreJugadorEquipoContrario4);
        panelJugadorEquipoContrario3.add(imagenNivelJugadorEquipoContrario4);

        panelJugadorEquipoContrario4.setBackground(new java.awt.Color(255, 255, 255));
        panelJugadorEquipoContrario4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        panelJugadorEquipoContrario4.setLayout(new java.awt.GridLayout(1, 0));
        panelJugadorEquipoContrario4.add(imagenJugadorEquipoContrario5);
        panelJugadorEquipoContrario4.add(labelNombreJugadorEquipoContrario5);
        panelJugadorEquipoContrario4.add(imagenNivelJugadorEquipoContrario5);

        panelJugadorEquipoContrario5.setBackground(new java.awt.Color(255, 255, 255));
        panelJugadorEquipoContrario5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        panelJugadorEquipoContrario5.setLayout(new java.awt.GridLayout(1, 0));
        panelJugadorEquipoContrario5.add(imagenJugadorEquipoContrario1);
        panelJugadorEquipoContrario5.add(labelNombreJugadorEquipoContrario1);
        panelJugadorEquipoContrario5.add(imagenNivelJugadorEquipoContrario1);

        javax.swing.GroupLayout panelInfoPartidoLayout = new javax.swing.GroupLayout(panelInfoPartido);
        panelInfoPartido.setLayout(panelInfoPartidoLayout);
        panelInfoPartidoLayout.setHorizontalGroup(
            panelInfoPartidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInfoPartidoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelInfoPartidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelJugadorEquipoJugador3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelJugadorEquipoJugador4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelJugadorEquipoJugador5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelJugadorEquipoJugador2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelJugadorEquipoJugador1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(106, 106, 106)
                .addGroup(panelInfoPartidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelJugadorEquipoContrario4, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                    .addComponent(panelJugadorEquipoContrario5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                    .addComponent(panelJugadorEquipoContrario1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelJugadorEquipoContrario2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelJugadorEquipoContrario3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelInfoPartidoLayout.setVerticalGroup(
            panelInfoPartidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoPartidoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelInfoPartidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelJugadorEquipoContrario5, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(panelJugadorEquipoJugador5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelInfoPartidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelJugadorEquipoJugador1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelJugadorEquipoContrario1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelInfoPartidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelJugadorEquipoJugador2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelJugadorEquipoContrario2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelInfoPartidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelJugadorEquipoJugador3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelJugadorEquipoContrario3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelInfoPartidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelJugadorEquipoJugador4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelJugadorEquipoContrario4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelEstadisticasLayout = new javax.swing.GroupLayout(panelEstadisticas);
        panelEstadisticas.setLayout(panelEstadisticasLayout);
        panelEstadisticasLayout.setHorizontalGroup(
            panelEstadisticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEstadisticasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelEstadisticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEstadisticasLayout.createSequentialGroup()
                        .addGroup(panelEstadisticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
                            .addComponent(jScrollPane4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addGroup(panelEstadisticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
                            .addComponent(jScrollPane5)))
                    .addComponent(panelInfoPartido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelEstadisticasLayout.setVerticalGroup(
            panelEstadisticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEstadisticasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelInfoPartido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelEstadisticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelEstadisticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(jScrollPane5))
                .addContainerGap())
        );

        javax.swing.GroupLayout panelInforrmacionLayout = new javax.swing.GroupLayout(panelInforrmacion);
        panelInforrmacion.setLayout(panelInforrmacionLayout);
        panelInforrmacionLayout.setHorizontalGroup(
            panelInforrmacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInforrmacionLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelEstadisticas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelInforrmacionLayout.setVerticalGroup(
            panelInforrmacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(panelEstadisticas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelMenuLateral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelInforrmacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMenuLateral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelInforrmacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Partidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Partidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Partidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Partidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        Jugador jugador = new Jugador();
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Partidos(jugador).setVisible(true);
            }
        });
    }

    @Override
    public Jugador getJugador() {
        return jugador;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel imagenJugadorEquipoContrario1;
    private javax.swing.JLabel imagenJugadorEquipoContrario2;
    private javax.swing.JLabel imagenJugadorEquipoContrario3;
    private javax.swing.JLabel imagenJugadorEquipoContrario4;
    private javax.swing.JLabel imagenJugadorEquipoContrario5;
    private javax.swing.JLabel imagenJugadorEquipoJugador1;
    private javax.swing.JLabel imagenJugadorEquipoJugador2;
    private javax.swing.JLabel imagenJugadorEquipoJugador3;
    private javax.swing.JLabel imagenJugadorEquipoJugador4;
    private javax.swing.JLabel imagenJugadorEquipoJugador5;
    private javax.swing.JLabel imagenNivelJugadorEquipoContrario1;
    private javax.swing.JLabel imagenNivelJugadorEquipoContrario2;
    private javax.swing.JLabel imagenNivelJugadorEquipoContrario3;
    private javax.swing.JLabel imagenNivelJugadorEquipoContrario4;
    private javax.swing.JLabel imagenNivelJugadorEquipoContrario5;
    private javax.swing.JLabel imagenNivelJugadorEquipoJugador1;
    private javax.swing.JLabel imagenNivelJugadorEquipoJugador2;
    private javax.swing.JLabel imagenNivelJugadorEquipoJugador3;
    private javax.swing.JLabel imagenNivelJugadorEquipoJugador4;
    private javax.swing.JLabel imagenNivelJugadorEquipoJugador5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel labelNombreJugadorEquipoContrario1;
    private javax.swing.JLabel labelNombreJugadorEquipoContrario2;
    private javax.swing.JLabel labelNombreJugadorEquipoContrario3;
    private javax.swing.JLabel labelNombreJugadorEquipoContrario4;
    private javax.swing.JLabel labelNombreJugadorEquipoContrario5;
    private javax.swing.JLabel labelNombreJugadorEquipoJugador1;
    private javax.swing.JLabel labelNombreJugadorEquipoJugador2;
    private javax.swing.JLabel labelNombreJugadorEquipoJugador3;
    private javax.swing.JLabel labelNombreJugadorEquipoJugador4;
    private javax.swing.JLabel labelNombreJugadorEquipoJugador5;
    private javax.swing.JPanel panelEstadisticas;
    private javax.swing.JPanel panelInfoPartido;
    private javax.swing.JPanel panelInforrmacion;
    private javax.swing.JPanel panelJugadorEquipoContrario1;
    private javax.swing.JPanel panelJugadorEquipoContrario2;
    private javax.swing.JPanel panelJugadorEquipoContrario3;
    private javax.swing.JPanel panelJugadorEquipoContrario4;
    private javax.swing.JPanel panelJugadorEquipoContrario5;
    private javax.swing.JPanel panelJugadorEquipoJugador1;
    private javax.swing.JPanel panelJugadorEquipoJugador2;
    private javax.swing.JPanel panelJugadorEquipoJugador3;
    private javax.swing.JPanel panelJugadorEquipoJugador4;
    private javax.swing.JPanel panelJugadorEquipoJugador5;
    private javax.swing.JPanel panelMenuLateral;
    private javax.swing.JTable tablaPartidos;
    private javax.swing.JTextArea textAreaClucthes;
    private javax.swing.JTextArea textAreaEstadisticasIndividuales;
    private javax.swing.JTextArea textAreaUtilidad;
    // End of variables declaration//GEN-END:variables
}
