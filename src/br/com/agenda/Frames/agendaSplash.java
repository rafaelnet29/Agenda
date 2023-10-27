package br.com.agenda.Frames;

import br.com.agenda.BancodeDados.agendaConexao;
import static java.lang.Thread.sleep;
import javax.swing.JOptionPane;

public final class agendaSplash extends javax.swing.JFrame {

    /** Creates new form Splash */
    public agendaSplash() {
        initComponents();
        Carregar();
        this.setLocationRelativeTo(null);
    }
    
    /**
     *Métódo para carregar a barra de progresso
     */
    public void Carregar() {
        new Thread() {
            @Override
            public void run() {
                agendaConexao conn = new agendaConexao();
                try {
                    if (conn.getConectMySQl() != null) {
                        for (int i = 0; i < 101; ++i) {
                            sleep(100);
                            jPBar.setValue(i);
                            if (jPBar.getValue() <= 25) {
                                jLbStatus.setText(" Carregando Interface... ");
                            }
                            if (jPBar.getValue() == 50) {
                                jLbStatus.setText(" Carregando Banco de Dados... ");
                            }
                            if (jPBar.getValue() == 75) {
                                jLbStatus.setText(" Carregando tabelas... ");
                            }
                            if (jPBar.getValue() == 90) {
                                jLbStatus.setText(" Iniciando... ");
                            }
                        }
                         dispose();
                         new agendaTelaCadastro().setVisible(true);
                    } else {
                        jLbStatus.setText("Problemas de conexão com o Banco de dados.");
                        JOptionPane.showMessageDialog(null,
                                " O banco de dados não responde ", " Atenção ", JOptionPane.ERROR_MESSAGE);
                        dispose();
                        new agendaTelaCadastro().dispose();
                    }
                } catch (InterruptedException ex) {
                    JOptionPane.showMessageDialog(null, "Problemas!!" + ex.getMessage());
                }
            }
        }.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPBar = new javax.swing.JProgressBar();
        jLbStatus = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabelImg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPBar.setStringPainted(true);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setText("AGENDA DE CONTATOS");

        jLabelImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/agenda/imagem/user.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabelImg)
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelImg, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(jPBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
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
            java.util.logging.Logger.getLogger(agendaSplash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(agendaSplash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(agendaSplash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(agendaSplash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               // new Splash().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelImg;
    private javax.swing.JLabel jLbStatus;
    private javax.swing.JProgressBar jPBar;
    // End of variables declaration//GEN-END:variables

}
