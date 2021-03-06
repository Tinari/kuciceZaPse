/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import domen.IDomain;
import domen.NacinIzrade;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import model.TableModelNacinIzrade;
import model.TableModelNaciniIzrade;

/**
 *
 * @author Win 7
 */
public class FrmPretragaNacinaIzrade extends javax.swing.JDialog {

    GlavnaForma frmGlavna;

    /**
     * Creates new form FrmPretragaNacinaIzrade
     */
    public FrmPretragaNacinaIzrade(java.awt.Frame parent, boolean modal, GlavnaForma frmGlavna) {
        super(parent, modal);
        initComponents();
        this.frmGlavna = frmGlavna;
        setLocationRelativeTo(null);
        popuniTabelu();
        pripremiZaPretragu();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtKriterijum = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNacini = new javax.swing.JTable();
        btnPrikaziDetalje = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Pretraga nacina izrade"));

        jLabel1.setText("Unesite kriterijum pretrage:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(txtKriterijum, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(89, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtKriterijum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        tblNacini.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblNacini);

        btnPrikaziDetalje.setText("Prikazi detalje");
        btnPrikaziDetalje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrikaziDetaljeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnPrikaziDetalje)
                        .addGap(114, 114, 114)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(btnPrikaziDetalje)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPrikaziDetaljeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrikaziDetaljeActionPerformed
        int selektovani = tblNacini.getSelectedRow();
        if (selektovani >= 0) {
            int id = (int) tblNacini.getValueAt(selektovani, 0);

            JOptionPane.showMessageDialog(null, "Sistem je nasao podatke o izabranom nacinu izrade. Izabrani nacn izrade ima ID: " + id);
            
            JDialog frmNacinIzrade = new FrmNacinIzrade(null, true, id, this, frmGlavna);
            frmNacinIzrade.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(null, "Sistem ne moze da pronadje nacin izrade.");
        }
    }//GEN-LAST:event_btnPrikaziDetaljeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPrikaziDetalje;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblNacini;
    private javax.swing.JTextField txtKriterijum;
    // End of variables declaration//GEN-END:variables

    public void popuniTabelu() {

        try {
            List<NacinIzrade> naciniIzrade = new ArrayList<>();
            List<NacinIzrade> lista = controler.Controler.getInstance().vratiListuNacinaIzrade();

            for (IDomain iDomain : lista) {
                naciniIzrade.add((NacinIzrade) iDomain);
            }

            TableModelNaciniIzrade model = new TableModelNaciniIzrade(naciniIzrade);
            tblNacini.setModel(model);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrmPretragaNacinaIzrade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(FrmPretragaNacinaIzrade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void pripremiZaPretragu() {
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tblNacini.getModel());
        tblNacini.setRowSorter(rowSorter);

        txtKriterijum.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                String kriterijum = txtKriterijum.getText();

                if (kriterijum.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + kriterijum));
                }
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                String kriterijum = txtKriterijum.getText();

                if (kriterijum.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + kriterijum));
                }
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }
}
