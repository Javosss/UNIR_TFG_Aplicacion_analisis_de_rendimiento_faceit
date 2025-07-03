package com.app.frontend.views;

import com.app.frontend.controllers.DashboardController;
import com.app.frontend.controllers.MenuLateralController;
import com.app.frontend.models.Jugador;
import com.app.frontend.utils.GestionIdiomas;
import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 * Clase Dashboard de la interfaz.
 * En esta parte de la interfaz se mostrará un pequeño resumen de datos de importancia para el usuario
 * @author Javier
 */
public class Dashboard extends javax.swing.JFrame implements MainView{
    private Jugador jugador;
    private DashboardController controlador;
    private MenuLateral menuLateral;
    
    public Dashboard(Jugador jugador) {
        this.jugador = jugador;  
        
        // Configuración básica del JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Añadir el menu lateral
        this.menuLateral = new MenuLateral(); 
        new MenuLateralController(menuLateral, this);
        
     
        initComponents();
        
        jPanel2.setLayout(new BorderLayout()); // Se organizan los componentes
        jPanel2.add(menuLateral, BorderLayout.CENTER); // Se añade el menú al jPanel2   
        add(jPanel2, BorderLayout.WEST);  // Menú en el WEST
        add(jPanel1, BorderLayout.CENTER); // Contenido principal en el CENTER      
        //this.getContentPane().add(menuLateral, java.awt.BorderLayout.WEST);
        
        this.controlador = new DashboardController(this);
        ConfigurarEventos();
              
        // Se incluyen las funciones en la inicialización
        CargarDatosPanelResumenStats();
        CargarClasificacionesRegionalesLista();
        CargarClasificacionesPais();
        CargarPosicionJugadorRegion();
        CargarPosicionJugadorPais();
        CargarHistorialPartidos();
    }

    private void ConfigurarEventos() {
        
    }
        
    // Ejecutar las Funciones de la parte Controladora:
    private void CargarDatosPanelResumenStats() {
        controlador.CargarDatosPanelResumenStats(jugador, labelImagen, labelNickname, labelCuentaCreada, labelEloCs2, labelEloCsgo);
    }
    
    private void CargarClasificacionesRegionalesLista() {
        controlador.CargarTablasDeClasificacioin(jugador.getRegion_cs2(), "cs2", tablaClasificacionRegion); // Se utilza el objeto jugador para coger su región de juego
    }
    
    private void CargarClasificacionesPais() {
        controlador.CargarTablaClasificacionPais(tablaClasificacionPais);
    }
    
    private void CargarPosicionJugadorRegion() {
        controlador.CargarPosicionJugadorRegion(labelClasificacionRegion);
    }
    
    private void CargarPosicionJugadorPais() {
        controlador.CargarPosicionJugadorPais(labelClasificacionPais);
    }
    
    private void CargarHistorialPartidos() {
        controlador.CargarHistorialPartidos(tablaHistorialPartidos);
    }
    
    @Override
    public Jugador getJugador() {
        return jugador;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panelResumenStats = new javax.swing.JPanel();
        labelImagen = new javax.swing.JLabel();
        labelNickname = new javax.swing.JLabel();
        labelCuentaCreada = new javax.swing.JLabel();
        labelClasificacionPais = new javax.swing.JLabel();
        labelClasificacionRegion = new javax.swing.JLabel();
        labelEloCs2 = new javax.swing.JLabel();
        labelEloCsgo = new javax.swing.JLabel();
        panelTablasClasificacion = new javax.swing.JPanel();
        labelClasificacion = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaClasificacionRegion = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaClasificacionPais = new javax.swing.JTable();
        panelStatsDetalladas = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        panelUltimosPartidos = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaHistorialPartidos = new javax.swing.JTable();
        labelUltimosPartidos = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(218, 244, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1440, 900));

        panelResumenStats.setBackground(new java.awt.Color(153, 204, 255));

        labelNickname.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        labelNickname.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelNickname.setText("jLabel1");

        labelCuentaCreada.setText("jLabel1");

        labelClasificacionPais.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        labelClasificacionPais.setText("jLabel1");

        labelClasificacionRegion.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        labelClasificacionRegion.setText("jLabel1");

        labelEloCs2.setText("Elo CS2: ");

        labelEloCsgo.setText("Elo CSGO:");

        javax.swing.GroupLayout panelResumenStatsLayout = new javax.swing.GroupLayout(panelResumenStats);
        panelResumenStats.setLayout(panelResumenStatsLayout);
        panelResumenStatsLayout.setHorizontalGroup(
            panelResumenStatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelResumenStatsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelResumenStatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelImagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelNickname, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(panelResumenStatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelResumenStatsLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(panelResumenStatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelClasificacionPais, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelEloCs2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelResumenStatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelEloCsgo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelClasificacionRegion, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(labelCuentaCreada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelResumenStatsLayout.setVerticalGroup(
            panelResumenStatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelResumenStatsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelResumenStatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelResumenStatsLayout.createSequentialGroup()
                        .addComponent(labelCuentaCreada)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelResumenStatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelClasificacionPais, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelClasificacionRegion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(labelImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panelResumenStatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelResumenStatsLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(labelNickname, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                    .addGroup(panelResumenStatsLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelResumenStatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelEloCs2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelEloCsgo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        panelTablasClasificacion.setBackground(new java.awt.Color(255, 255, 255));

        labelClasificacion.setText(GestionIdiomas.getMensaje("label_clasificacion_2"));

        jLabel3.setText(GestionIdiomas.getMensaje("label_clasificacion_pais_2"));

        jScrollPane3.setPreferredSize(new java.awt.Dimension(400, 200));

        tablaClasificacionRegion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(tablaClasificacionRegion);

        tablaClasificacionPais.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaClasificacionPais);

        javax.swing.GroupLayout panelTablasClasificacionLayout = new javax.swing.GroupLayout(panelTablasClasificacion);
        panelTablasClasificacion.setLayout(panelTablasClasificacionLayout);
        panelTablasClasificacionLayout.setHorizontalGroup(
            panelTablasClasificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTablasClasificacionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTablasClasificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelClasificacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        panelTablasClasificacionLayout.setVerticalGroup(
            panelTablasClasificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTablasClasificacionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelClasificacion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelStatsDetalladas.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 220, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 138, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 232, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 220, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 138, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelStatsDetalladasLayout = new javax.swing.GroupLayout(panelStatsDetalladas);
        panelStatsDetalladas.setLayout(panelStatsDetalladasLayout);
        panelStatsDetalladasLayout.setHorizontalGroup(
            panelStatsDetalladasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelStatsDetalladasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        panelStatsDetalladasLayout.setVerticalGroup(
            panelStatsDetalladasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelStatsDetalladasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelStatsDetalladasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelUltimosPartidos.setBackground(new java.awt.Color(255, 255, 255));

        tablaHistorialPartidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tablaHistorialPartidos);

        javax.swing.GroupLayout panelUltimosPartidosLayout = new javax.swing.GroupLayout(panelUltimosPartidos);
        panelUltimosPartidos.setLayout(panelUltimosPartidosLayout);
        panelUltimosPartidosLayout.setHorizontalGroup(
            panelUltimosPartidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUltimosPartidosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );
        panelUltimosPartidosLayout.setVerticalGroup(
            panelUltimosPartidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUltimosPartidosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE)
                .addContainerGap())
        );

        labelUltimosPartidos.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        labelUltimosPartidos.setText(GestionIdiomas.getMensaje("label_ultimos_partidos_2"));
        labelUltimosPartidos.setMaximumSize(new java.awt.Dimension(170, 17));
        labelUltimosPartidos.setMinimumSize(new java.awt.Dimension(100, 17));
        labelUltimosPartidos.setPreferredSize(new java.awt.Dimension(100, 17));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelUltimosPartidos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelUltimosPartidos, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(panelTablasClasificacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelResumenStats, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelStatsDetalladas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(657, 657, 657))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(panelResumenStats, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(panelTablasClasificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelStatsDetalladas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelUltimosPartidos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelUltimosPartidos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 119, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1059, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1910, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1080, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        Jugador jugador = new Jugador();
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard(jugador).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel labelClasificacion;
    private javax.swing.JLabel labelClasificacionPais;
    private javax.swing.JLabel labelClasificacionRegion;
    private javax.swing.JLabel labelCuentaCreada;
    private javax.swing.JLabel labelEloCs2;
    private javax.swing.JLabel labelEloCsgo;
    private javax.swing.JLabel labelImagen;
    private javax.swing.JLabel labelNickname;
    private javax.swing.JLabel labelUltimosPartidos;
    private javax.swing.JPanel panelResumenStats;
    private javax.swing.JPanel panelStatsDetalladas;
    private javax.swing.JPanel panelTablasClasificacion;
    private javax.swing.JPanel panelUltimosPartidos;
    private javax.swing.JTable tablaClasificacionPais;
    public javax.swing.JTable tablaClasificacionRegion;
    private javax.swing.JTable tablaHistorialPartidos;
    // End of variables declaration//GEN-END:variables
}
