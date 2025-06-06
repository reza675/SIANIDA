/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View.AksesAdmin;

import Controller.ManajemenBukuController;
import Model.Buku.Buku;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

/**
 *
 * @author ASUS
 */
public class ManajemenBukuView extends javax.swing.JFrame {

    private String username;
    DefaultTableModel model;
    ManajemenBukuController controller;

    public ManajemenBukuView(String username) {
        this.username = username;
        initComponents();
        controller = new ManajemenBukuController(this, username);
        controller.loadTable();

    }

    public DefaultTableModel getTblModel() {
        return (DefaultTableModel) tbl_detailBuku.getModel();
    }

    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    public void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
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
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        text_idbuku = new app.bolivia.swing.JCTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        text_namabuku = new app.bolivia.swing.JCTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        text_penulis = new app.bolivia.swing.JCTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        text_jumlah = new app.bolivia.swing.JCTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        tambahBuku = new rojerusan.RSMaterialButtonRectangle();
        hapusBuku = new rojerusan.RSMaterialButtonRectangle();
        editBuku = new rojerusan.RSMaterialButtonRectangle();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        text_kategori = new app.bolivia.swing.JCTextField();
        jPanel5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_detailBuku = new rojeru_san.complementos.RSTableMetro();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(200, 172, 144));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(150, 99, 31));
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel3MouseClicked(evt);
            }
        });
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(150, 99, 31));
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel4MouseClicked(evt);
            }
        });
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/gambarTambahBukuIcons/icons8_Rewind_48px.png"))); // NOI18N
        jLabel3.setText("Back");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 110, 40));

        jPanel6.setBackground(new java.awt.Color(150, 99, 31));
        jPanel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel6MouseClicked(evt);
            }
        });
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/gambarTambahBukuIcons/icons8_Rewind_48px.png"))); // NOI18N
        jLabel4.setText("Back");
        jPanel6.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 110, 40));

        jPanel4.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 120, 40));

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 120, 40));

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/gambarTambahBukuIcons/icons8_Rewind_48px.png"))); // NOI18N
        jLabel5.setText("Back");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 110, 40));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 120, 40));

        text_idbuku.setBackground(new java.awt.Color(200, 172, 144));
        text_idbuku.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        text_idbuku.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        text_idbuku.setPlaceholder("Masukkan Id Buku...");
        text_idbuku.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                text_idbukuFocusLost(evt);
            }
        });
        jPanel2.add(text_idbuku, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 300, 40));

        jLabel9.setBackground(new java.awt.Color(240, 240, 240));
        jLabel9.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/gambarTambahBukuIcons/icons8_Contact_26px.png"))); // NOI18N
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 40, 50));

        jLabel13.setBackground(new java.awt.Color(240, 240, 240));
        jLabel13.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("ID Buku");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 90, 20));

        text_namabuku.setBackground(new java.awt.Color(200, 172, 144));
        text_namabuku.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        text_namabuku.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        text_namabuku.setPlaceholder("Masukkan nama buku...");
        text_namabuku.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                text_namabukuFocusLost(evt);
            }
        });
        jPanel2.add(text_namabuku, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, 300, 40));

        jLabel10.setBackground(new java.awt.Color(240, 240, 240));
        jLabel10.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/gambarTambahBukuIcons/icons8_Moleskine_26px.png"))); // NOI18N
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 40, 50));

        jLabel14.setBackground(new java.awt.Color(240, 240, 240));
        jLabel14.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Nama Buku");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 170, 90, 20));

        text_penulis.setBackground(new java.awt.Color(200, 172, 144));
        text_penulis.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        text_penulis.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        text_penulis.setPlaceholder("Masukkan penulis buku...");
        text_penulis.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                text_penulisFocusLost(evt);
            }
        });
        jPanel2.add(text_penulis, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 280, 300, 40));

        jLabel11.setBackground(new java.awt.Color(240, 240, 240));
        jLabel11.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/gambarTambahBukuIcons/icons8_Collaborator_Male_26px.png"))); // NOI18N
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 40, 50));

        jLabel15.setBackground(new java.awt.Color(240, 240, 240));
        jLabel15.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Penulis Buku");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 260, 110, 20));

        text_jumlah.setBackground(new java.awt.Color(200, 172, 144));
        text_jumlah.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        text_jumlah.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        text_jumlah.setPlaceholder("Masukkan jumlah buku...");
        text_jumlah.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                text_jumlahFocusLost(evt);
            }
        });
        jPanel2.add(text_jumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 370, 300, 40));

        jLabel12.setBackground(new java.awt.Color(240, 240, 240));
        jLabel12.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/gambarTambahBukuIcons/icons8_Unit_26px.png"))); // NOI18N
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 40, 50));

        jLabel16.setBackground(new java.awt.Color(240, 240, 240));
        jLabel16.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Jumlah Buku");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 350, 120, 20));

        tambahBuku.setBackground(new java.awt.Color(22, 206, 12));
        tambahBuku.setText("Tambah Buku");
        tambahBuku.setFont(new java.awt.Font("Roboto Medium", 1, 20)); // NOI18N
        tambahBuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahBukuActionPerformed(evt);
            }
        });
        jPanel2.add(tambahBuku, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 510, 170, 70));

        hapusBuku.setBackground(new java.awt.Color(255, 0, 0));
        hapusBuku.setText("hapus buku");
        hapusBuku.setFont(new java.awt.Font("Roboto Medium", 1, 20)); // NOI18N
        hapusBuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusBukuActionPerformed(evt);
            }
        });
        jPanel2.add(hapusBuku, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 600, 170, 70));

        editBuku.setBackground(new java.awt.Color(35, 0, 255));
        editBuku.setText("edit buku");
        editBuku.setFont(new java.awt.Font("Roboto Medium", 1, 20)); // NOI18N
        editBuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBukuActionPerformed(evt);
            }
        });
        jPanel2.add(editBuku, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 510, 170, 70));

        jLabel17.setBackground(new java.awt.Color(240, 240, 240));
        jLabel17.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Kategori Buku");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 430, 120, 20));

        jLabel18.setBackground(new java.awt.Color(240, 240, 240));
        jLabel18.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/gambarTambahBukuIcons/icons8-books-24.png"))); // NOI18N
        jPanel2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 450, 30, 40));

        text_kategori.setBackground(new java.awt.Color(200, 172, 144));
        text_kategori.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        text_kategori.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        text_kategori.setPlaceholder("Masukkan kategori buku...");
        text_kategori.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                text_kategoriFocusLost(evt);
            }
        });
        text_kategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_kategoriActionPerformed(evt);
            }
        });
        jPanel2.add(text_kategori, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 450, 300, 40));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 720));

        jPanel5.setBackground(new java.awt.Color(239, 233, 226));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(150, 99, 31));
        jPanel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel7MouseClicked(evt);
            }
        });
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel5.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, -1, -1));

        jPanel8.setBackground(new java.awt.Color(150, 99, 31));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 30)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("X");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        jPanel8.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 3, 30, 30));

        jPanel5.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 0, 70, 40));

        tbl_detailBuku.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Buku", "Nama Buku", "Penulis", "Jumlah", "Kategori"
            }
        ));
        tbl_detailBuku.setColorBackgoundHead(new java.awt.Color(162, 132, 94));
        tbl_detailBuku.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        tbl_detailBuku.setColorFilasForeground1(new java.awt.Color(0, 0, 0));
        tbl_detailBuku.setColorFilasForeground2(new java.awt.Color(0, 0, 0));
        tbl_detailBuku.setColorSelBackgound(new java.awt.Color(235, 206, 148));
        tbl_detailBuku.setFont(new java.awt.Font("Yu Gothic Light", 0, 25)); // NOI18N
        tbl_detailBuku.setFuenteFilas(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        tbl_detailBuku.setFuenteFilasSelect(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        tbl_detailBuku.setFuenteHead(new java.awt.Font("Yu Gothic UI Semibold", 1, 20)); // NOI18N
        tbl_detailBuku.setRowHeight(40);
        tbl_detailBuku.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_detailBukuMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_detailBuku);
        if (tbl_detailBuku.getColumnModel().getColumnCount() > 0) {
            tbl_detailBuku.getColumnModel().getColumn(0).setMinWidth(100);
            tbl_detailBuku.getColumnModel().getColumn(0).setMaxWidth(100);
            tbl_detailBuku.getColumnModel().getColumn(3).setMinWidth(100);
            tbl_detailBuku.getColumnModel().getColumn(3).setMaxWidth(100);
        }

        jPanel5.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 780, 300));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(136, 107, 70));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/gambarTambahBukuIcons/icons8-books-52.png"))); // NOI18N
        jLabel1.setText("Manajemen Buku");
        jPanel5.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 80, -1, -1));

        jPanel1.setBackground(new java.awt.Color(136, 107, 70));
        jPanel1.setForeground(new java.awt.Color(136, 107, 70));
        jPanel5.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 140, 330, 5));

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 0, 800, 720));

        setSize(new java.awt.Dimension(1200, 720));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tambahBukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahBukuActionPerformed
        try {
            int idBaru = Integer.parseInt(text_idbuku.getText());
            String namaBaru = text_namabuku.getText();
            String penulis = text_penulis.getText();
            int jumlah = Integer.parseInt(text_jumlah.getText());
            String kategori = text_kategori.getText();

            Buku bukuBaru = new Buku(idBaru, namaBaru, penulis, jumlah, penulis);
            bukuBaru.setIdBuku(idBaru);
            bukuBaru.setNamaBuku(namaBaru);
            bukuBaru.setPenulis(penulis);
            bukuBaru.setJumlah(jumlah);
            bukuBaru.setJenisBuku(kategori);

            controller.addBuku(bukuBaru);
            text_namabuku.setText("");
            text_penulis.setText("");
            text_jumlah.setText("");
            text_kategori.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Semua field wajib diisi dan sesuai dengan format",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_tambahBukuActionPerformed

    private void text_jumlahFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_text_jumlahFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_text_jumlahFocusLost

    private void text_penulisFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_text_penulisFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_text_penulisFocusLost

    private void text_namabukuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_text_namabukuFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_text_namabukuFocusLost

    private void text_idbukuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_text_idbukuFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_text_idbukuFocusLost

    private void jPanel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseClicked

    }//GEN-LAST:event_jPanel3MouseClicked

    private void jPanel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel4MouseClicked

    private void jPanel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel6MouseClicked

    private void jPanel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel7MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        HomePageAdminView homepage = new HomePageAdminView(username);
        homepage.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel3MouseClicked

    private void tbl_detailBukuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_detailBukuMouseClicked
        int rowNo = tbl_detailBuku.getSelectedRow();
        if (rowNo == -1) {
            return;
        }
        TableModel m = tbl_detailBuku.getModel();

        text_idbuku.setText(m.getValueAt(rowNo, 0).toString());
        text_namabuku.setText(m.getValueAt(rowNo, 1).toString());
        text_penulis.setText(m.getValueAt(rowNo, 2).toString());
        text_jumlah.setText(m.getValueAt(rowNo, 3).toString());
        text_kategori.setText(m.getValueAt(rowNo, 4).toString());

        editBuku.setEnabled(true);
        hapusBuku.setEnabled(true);
    }//GEN-LAST:event_tbl_detailBukuMouseClicked

    private void hapusBukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusBukuActionPerformed

        int rowNo = tbl_detailBuku.getSelectedRow();
        if (rowNo == -1) {
            JOptionPane.showMessageDialog(this,
                    "Silakan pilih baris yang akan dihapus.",
                    "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int idForm = Integer.parseInt(text_idbuku.getText());
        String namaForm = text_namabuku.getText();
        String penulisForm = text_penulis.getText();
        int jumlahForm = Integer.parseInt(text_jumlah.getText());
        String kategoriForm = text_kategori.getText();

        Buku bukuForm = new Buku(idForm, namaForm, penulisForm, jumlahForm, penulisForm);
        bukuForm.setIdBuku(idForm);
        bukuForm.setNamaBuku(namaForm);
        bukuForm.setPenulis(penulisForm);
        bukuForm.setJumlah(jumlahForm);
        bukuForm.setJenisBuku(kategoriForm);

        int konfirm = JOptionPane.showConfirmDialog(this,
                "Yakin ingin menghapus buku \"" + namaForm + "\"?",
                "Konfirmasi Hapus",
                JOptionPane.YES_NO_OPTION);
        if (konfirm != JOptionPane.YES_OPTION) {
            return;
        }

        controller.deleteBuku(bukuForm);
    }//GEN-LAST:event_hapusBukuActionPerformed

    private void editBukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBukuActionPerformed

        if (text_idbuku.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Silakan pilih baris terlebih dahulu.");
            return;
        }
        try {
            int idBaru = Integer.parseInt(text_idbuku.getText());
            String namaBaru = text_namabuku.getText();
            String penulisBaru = text_penulis.getText();
            int jumlahBaru = Integer.parseInt(text_jumlah.getText());
            String kategoriBaru = text_kategori.getText();

            Buku bukuTerpilih = new Buku(idBaru, namaBaru, penulisBaru, jumlahBaru, kategoriBaru);
            bukuTerpilih.setIdBuku(idBaru);
            bukuTerpilih.setNamaBuku(namaBaru);
            bukuTerpilih.setPenulis(penulisBaru);
            bukuTerpilih.setJumlah(jumlahBaru);
            bukuTerpilih.setJenisBuku(kategoriBaru);

            controller.editBuku(bukuTerpilih);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Format angka salah: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_editBukuActionPerformed

    private void text_kategoriFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_text_kategoriFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_text_kategoriFocusLost

    private void text_kategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_kategoriActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_kategoriActionPerformed

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
            java.util.logging.Logger.getLogger(ManajemenBukuView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManajemenBukuView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManajemenBukuView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManajemenBukuView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManajemenBukuView("Guest").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSMaterialButtonRectangle editBuku;
    private rojerusan.RSMaterialButtonRectangle hapusBuku;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private rojerusan.RSMaterialButtonRectangle tambahBuku;
    private rojeru_san.complementos.RSTableMetro tbl_detailBuku;
    private app.bolivia.swing.JCTextField text_idbuku;
    private app.bolivia.swing.JCTextField text_jumlah;
    private app.bolivia.swing.JCTextField text_kategori;
    private app.bolivia.swing.JCTextField text_namabuku;
    private app.bolivia.swing.JCTextField text_penulis;
    // End of variables declaration//GEN-END:variables
}
