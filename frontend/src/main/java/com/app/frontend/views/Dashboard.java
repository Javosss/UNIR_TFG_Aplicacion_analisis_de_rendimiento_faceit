package com.app.frontend.views;

import com.app.frontend.controllers.DashboardController;
import com.app.frontend.models.Jugador;
import com.app.frontend.utils.GestionIdiomas;

/**
 * Clase Dashboard de la interfaz.
 * En esta parte de la interfaz se mostrará un pequeño resumen de datos de importancia para el usuario
 * @author Javier
 */
public class Dashboard extends javax.swing.JFrame {
    private Jugador jugador;
    private DashboardController controlador;
    
    public Dashboard(Jugador jugador) {
        this.jugador = jugador;
        this.controlador = new DashboardController(this);
        initComponents();
        ConfigurarEventos();
        
        // Algunas funciones a incluir en la inicializacion
        CargarDatosPanelResumenStats();
        CargarClasificacionesRegionalesLista();
        CargarClasificacionesPais();
    }

    private void ConfigurarEventos() {
        
    }
    
    private void CargarDatosPanelResumenStats() {
        controlador.CargarDatosPanelResumenStats(jugador, labelImagen, labelNickname, labelCuentaCreada, labelEloCs2, labelEloCsgo);
    }
    
    private void CargarClasificacionesRegionalesLista() {
        controlador.CargarTablasDeClasificacioin(jugador.getRegion_cs2(), "cs2", tablaClasificacionRegion); // Se utilza el objeto jugador para coger su región de juego
    }
    
    private void CargarClasificacionesPais() {
        controlador.CargarTablaClasificacionPais(tablaClasificacionPais);
    }
    
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
        jLabel1 = new javax.swing.JLabel();
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
        panelUltimosPartidos = new javax.swing.JPanel();
        labelUltimosPartidos = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(218, 244, 255));

        panelResumenStats.setBackground(new java.awt.Color(204, 204, 204));

        labelNickname.setText("jLabel1");

        labelCuentaCreada.setText("jLabel1");

        labelClasificacionPais.setText("jLabel1");

        jLabel1.setText("jLabel1");

        labelEloCs2.setText("Elo CS2: ");

        labelEloCsgo.setText("Elo CSGO:");

        javax.swing.GroupLayout panelResumenStatsLayout = new javax.swing.GroupLayout(panelResumenStats);
        panelResumenStats.setLayout(panelResumenStatsLayout);
        panelResumenStatsLayout.setHorizontalGroup(
            panelResumenStatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelResumenStatsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelResumenStatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelNickname, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                    .addComponent(labelImagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelResumenStatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelCuentaCreada, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelResumenStatsLayout.createSequentialGroup()
                        .addGroup(panelResumenStatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(labelEloCs2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                            .addComponent(labelClasificacionPais, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelResumenStatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelEloCsgo, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        panelResumenStatsLayout.setVerticalGroup(
            panelResumenStatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelResumenStatsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelResumenStatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelResumenStatsLayout.createSequentialGroup()
                        .addComponent(labelCuentaCreada)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelResumenStatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelClasificacionPais, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelResumenStatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNickname)
                    .addComponent(labelEloCs2)
                    .addComponent(labelEloCsgo))
                .addContainerGap(12, Short.MAX_VALUE))
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
        tablaClasificacionRegion.setFocusable(false);
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
        tablaClasificacionPais.setFocusable(false);
        jScrollPane1.setViewportView(tablaClasificacionPais);

        javax.swing.GroupLayout panelTablasClasificacionLayout = new javax.swing.GroupLayout(panelTablasClasificacion);
        panelTablasClasificacion.setLayout(panelTablasClasificacionLayout);
        panelTablasClasificacionLayout.setHorizontalGroup(
            panelTablasClasificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTablasClasificacionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTablasClasificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelClasificacion, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
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

        javax.swing.GroupLayout panelStatsDetalladasLayout = new javax.swing.GroupLayout(panelStatsDetalladas);
        panelStatsDetalladas.setLayout(panelStatsDetalladasLayout);
        panelStatsDetalladasLayout.setHorizontalGroup(
            panelStatsDetalladasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 870, Short.MAX_VALUE)
        );
        panelStatsDetalladasLayout.setVerticalGroup(
            panelStatsDetalladasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panelUltimosPartidos.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelUltimosPartidosLayout = new javax.swing.GroupLayout(panelUltimosPartidos);
        panelUltimosPartidos.setLayout(panelUltimosPartidosLayout);
        panelUltimosPartidosLayout.setHorizontalGroup(
            panelUltimosPartidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelUltimosPartidosLayout.setVerticalGroup(
            panelUltimosPartidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 195, Short.MAX_VALUE)
        );

        labelUltimosPartidos.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        labelUltimosPartidos.setText(GestionIdiomas.getMensaje("label_ultimos_partidos_2"));
        labelUltimosPartidos.setMinimumSize(new java.awt.Dimension(100, 17));
        labelUltimosPartidos.setPreferredSize(new java.awt.Dimension(100, 17));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelUltimosPartidos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panelTablasClasificacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelResumenStats, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelUltimosPartidos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(panelStatsDetalladas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
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
                .addComponent(panelUltimosPartidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel labelClasificacion;
    private javax.swing.JLabel labelClasificacionPais;
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
    // End of variables declaration//GEN-END:variables
}
