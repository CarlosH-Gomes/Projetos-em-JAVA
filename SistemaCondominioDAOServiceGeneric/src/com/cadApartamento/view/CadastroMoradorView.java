/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cadApartamento.view;

import com.model.Morador;
import com.servico.MoradorService;
import com.servicoimp.MoradorServiceImp;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Carlos
 */
public class CadastroMoradorView extends javax.swing.JFrame {

    /**
     * Creates new form Principal
     */
    public CadastroMoradorView() {
        initComponents();
         DefaultTableModel modelo = (DefaultTableModel) jTableMorador.getModel();
        jTableMorador.setRowSorter(new TableRowSorter(modelo));
        readJTable();
       inicializaBotao();
    }
       Integer cod=0; 
      private void inicializaBotao(){
       if(cod == 0)
       {
           btnAtualizar.setEnabled( false );
           btnExcluir.setEnabled( false );
           btnSalvar.setEnabled(true);
       }else{
           btnAtualizar.setEnabled( true );
           btnExcluir.setEnabled( true );
            btnSalvar.setEnabled(false);
       }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        btnSalvar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        btnAtualizar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableMorador = new javax.swing.JTable();
        pDadosMorador = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtCPF = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        txtNacionalidade = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtRG = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        txtDataNasc = new javax.swing.JFormattedTextField();
        pDadosApartamento = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtNumApt = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtAndar = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtBloco = new javax.swing.JFormattedTextField();

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnLimpar.setText("Limpar");
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });

        btnAtualizar.setText("Atualizar");
        btnAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizarActionPerformed(evt);
            }
        });

        jTableMorador.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Nome", "CPF", "RG", "Nacionalidade", "Data de Nascimento", "Número Ap", "Bloco Ap", "Andar Ap"
            }
        ));
        jTableMorador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableMoradorMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableMorador);

        pDadosMorador.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados Morador"));

        jLabel1.setText("Nome:");

        txtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeActionPerformed(evt);
            }
        });

        jLabel2.setText("CPF:");

        try {
            txtCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel5.setText("Nacionalidade:");

        jLabel3.setText("RG");

        try {
            txtRG.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###-#")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel4.setText("Data de Nascimento:");

        try {
            txtDataNasc.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout pDadosMoradorLayout = new javax.swing.GroupLayout(pDadosMorador);
        pDadosMorador.setLayout(pDadosMoradorLayout);
        pDadosMoradorLayout.setHorizontalGroup(
            pDadosMoradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDadosMoradorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pDadosMoradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pDadosMoradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pDadosMoradorLayout.createSequentialGroup()
                        .addComponent(txtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtRG))
                    .addGroup(pDadosMoradorLayout.createSequentialGroup()
                        .addComponent(txtNacionalidade, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDataNasc, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(32, Short.MAX_VALUE))
            .addGroup(pDadosMoradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pDadosMoradorLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(31, Short.MAX_VALUE)))
        );
        pDadosMoradorLayout.setVerticalGroup(
            pDadosMoradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDadosMoradorLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(pDadosMoradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(txtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pDadosMoradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(txtNacionalidade)
                    .addComponent(txtDataNasc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
            .addGroup(pDadosMoradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pDadosMoradorLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(pDadosMoradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(98, 98, 98)))
        );

        pDadosApartamento.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados Apartamento"));

        jLabel7.setText("Número do Apartamento:");

        jLabel8.setText("Andar");

        jLabel9.setText("Bloco");

        try {
            txtBloco.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout pDadosApartamentoLayout = new javax.swing.GroupLayout(pDadosApartamento);
        pDadosApartamento.setLayout(pDadosApartamentoLayout);
        pDadosApartamentoLayout.setHorizontalGroup(
            pDadosApartamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pDadosApartamentoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtNumApt, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAndar, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtBloco, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pDadosApartamentoLayout.setVerticalGroup(
            pDadosApartamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDadosApartamentoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pDadosApartamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtNumApt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtAndar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtBloco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pDadosMorador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(btnSalvar)
                        .addGap(18, 18, 18)
                        .addComponent(btnAtualizar)
                        .addGap(18, 18, 18)
                        .addComponent(btnExcluir)
                        .addGap(18, 18, 18)
                        .addComponent(btnLimpar)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1)
                    .addComponent(pDadosApartamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pDadosMorador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pDadosApartamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar)
                    .addComponent(btnAtualizar)
                    .addComponent(btnExcluir)
                    .addComponent(btnLimpar))
                .addGap(13, 13, 13)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        limpaCampos();
    }//GEN-LAST:event_btnLimparActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        excluiMorador();
        readJTable();
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarActionPerformed
        atualizaMorador();
        readJTable();
    }//GEN-LAST:event_btnAtualizarActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        
     
        cadastraMorador();
        readJTable();
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void jTableMoradorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMoradorMouseClicked
        if (jTableMorador.getSelectedRow() != -1) {
            txtNome.setText(jTableMorador.getValueAt(jTableMorador.getSelectedRow(), 1).toString());
            txtCPF.setText(jTableMorador.getValueAt(jTableMorador.getSelectedRow(), 2).toString());
            txtRG.setText(jTableMorador.getValueAt(jTableMorador.getSelectedRow(), 3).toString());
            txtNacionalidade.setText(jTableMorador.getValueAt(jTableMorador.getSelectedRow(), 4).toString());
            txtDataNasc.setText(jTableMorador.getValueAt(jTableMorador.getSelectedRow(), 5).toString());
            txtNumApt.setText(jTableMorador.getValueAt(jTableMorador.getSelectedRow(), 6).toString());
            txtAndar.setText(jTableMorador.getValueAt(jTableMorador.getSelectedRow(), 7).toString());
            txtBloco.setText(jTableMorador.getValueAt(jTableMorador.getSelectedRow(), 8).toString());
        }
        cod = 1;
        inicializaBotao();
    }//GEN-LAST:event_jTableMoradorMouseClicked

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed
     
    public void readJTable() {

        DefaultTableModel modelo = (DefaultTableModel) jTableMorador.getModel();
        modelo.setNumRows(0);
        MoradorService service = new MoradorServiceImp();
        for (Morador m : service.findAll("Morador")) {
            modelo.addRow(new Object[]{
                m.getCodigo(),
                m.getNome(),
                m.getCpf(),
                m.getRg(),
                m.getNacionalidade(),
                m.getDataNasc(),
                m.getNumApt(),
                m.getAndarApt(),
                m.getBlocoApt(),
            });

        }
    }
    private void cadastraMorador() {
        Morador m = new Morador();
        MoradorService service = new MoradorServiceImp();
        m.setNome(txtNome.getText());
        m.setCpf(txtCPF.getText());
        m.setRg(txtRG.getText());
        m.setNacionalidade(txtNacionalidade.getText());
        m.setDataNasc(txtDataNasc.getText());
        m.setAndarApt(txtAndar.getText());
        m.setBlocoApt(txtNumApt.getText());
        m.setNumApt(txtBloco.getText());
        service.inserir(m);
        limpaCampos();
    }
    
    
    
    private void atualizaMorador() {
        Morador m = new Morador();
        MoradorService service = new MoradorServiceImp();
        m.setCodigo((int) jTableMorador.getValueAt(jTableMorador.getSelectedRow(), 0));
        m.setNome(txtNome.getText());
        m.setCpf(txtCPF.getText());
        m.setRg(txtRG.getText());
        m.setNacionalidade(txtNacionalidade.getText());
        m.setDataNasc(txtDataNasc.getText());
        m.setAndarApt(txtAndar.getText());
        m.setBlocoApt(txtNumApt.getText());
        m.setNumApt(txtBloco.getText());
        service.update(m);
        limpaCampos();
    }
    
    private void excluiMorador(){
        MoradorService service = new MoradorServiceImp();
        Morador m = new Morador();
        service.remove(m,(Integer) jTableMorador.getValueAt(jTableMorador.getSelectedRow(), 0));
        limpaCampos();
        readJTable();
    }
    
    
    
   private void limpaCampos()
   {
       txtNome.setText("");
       txtCPF.setText("");
       txtRG.setText("");
       txtNacionalidade.setText("");
       txtDataNasc.setText("");
       txtNumApt.setText("");
       txtAndar.setText("");
       txtBloco.setText("");
       cod = 0;
        inicializaBotao();
   }
    
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
            java.util.logging.Logger.getLogger(CadastroMoradorView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroMoradorView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroMoradorView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroMoradorView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastroMoradorView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableMorador;
    private javax.swing.JPanel pDadosApartamento;
    private javax.swing.JPanel pDadosMorador;
    private javax.swing.JTextField txtAndar;
    private javax.swing.JFormattedTextField txtBloco;
    private javax.swing.JFormattedTextField txtCPF;
    private javax.swing.JFormattedTextField txtDataNasc;
    private javax.swing.JTextField txtNacionalidade;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtNumApt;
    private javax.swing.JFormattedTextField txtRG;
    // End of variables declaration//GEN-END:variables
}
