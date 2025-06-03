/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pkgfinal.project.pbo;

import java.awt.CardLayout;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LOVIANNO
 */
public class MasterMenu extends javax.swing.JFrame {

    /**
     * Creates new form MasterMenu
     */
    public MasterMenu() {
        initComponents();
            tampilkanPegawai(txtCariPegawai.getText());
            tampikanProduk(txtCariProduk.getText());
            tampikanMitra(txtCariMitra.getText());

        lblUsername.setText(Login.nama);
        
        if(Login.jabatan.equals("staf")){
            btnPegawai.setVisible(false);
        }
    }

    private void tampilkanPegawai(String cari) {
    List<Pegawai> daftar = Pegawai.getDataPegawai(cari);
    String[] kolom = {"No", "ID", "Nama", "Jabatan", "No Telepon", "Email", "Password"};

    // Buat model baru yang tidak bisa diedit
    DefaultTableModel model = new DefaultTableModel(kolom, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false; // Semua kolom tidak bisa diedit
        }
    };

    int i = 0;
    for (Pegawai p : daftar) {
        Object[] row = {
            ++i,
            p.id,
            p.nama,
            p.jabatan,
            p.no_telp,
            p.email,
            p.password
        };
        model.addRow(row);
    }

    tblPegawai.setModel(model); // set model baru ke JTable

    // Sembunyikan kolom ID dan Password
    tblPegawai.getColumnModel().getColumn(1).setMinWidth(0); // ID
    tblPegawai.getColumnModel().getColumn(1).setMaxWidth(0);
    tblPegawai.getColumnModel().getColumn(1).setWidth(0);

    tblPegawai.  getColumnModel().getColumn(6).setMinWidth(0); // Password
    tblPegawai.getColumnModel().getColumn(6).setMaxWidth(0);
    tblPegawai.getColumnModel().getColumn(6).setWidth(0);
    
       tblPegawai.getColumnModel().getColumn(0).setMinWidth(30); 
    tblPegawai.getColumnModel().getColumn(0).setMaxWidth(30);
    tblPegawai.getColumnModel().getColumn(0).setWidth(30);
}
    private void tampikanProduk(String cari) {
    List<Produk> daftar = Produk.getDataProduk(cari);
    String[] kolom = {"No", "Id", "Nama", "Kategori", "Deskripsi", "Kapasitas Maksimal", "Panjang Bak"};

    DefaultTableModel model = new DefaultTableModel(kolom, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    
    int no = 1;
    for (Produk p : daftar) {
        String kapasitas = "";
        String panjangBak = "";

        if (p instanceof Truk) {
            kapasitas = ((Truk) p).getKapasitasMaksimal(); // getter dari Truk
        } else if (p instanceof Pickup) {
            panjangBak = ((Pickup) p).getPanjangBak(); // getter dari Pickup
        }

        Object[] rowData = {
            no++,
            p.id,
            p.nama,
            p.kategori,
            p.deskripsi,
            kapasitas,
            panjangBak
        };

        model.addRow(rowData);
    }

    tblProduk.setModel(model);
    
    tblProduk.getColumnModel().getColumn(1).setMinWidth(0); // ID
    tblProduk.getColumnModel().getColumn(1).setMaxWidth(0);
    tblProduk.getColumnModel().getColumn(1).setWidth(0);
    
      tblProduk.getColumnModel().getColumn(0).setMinWidth(30); 
    tblProduk.getColumnModel().getColumn(0).setMaxWidth(30);
    tblProduk.getColumnModel().getColumn(0).setWidth(30);
    
}

    private void tampikanMitra(String cari) {
    List<Mitra> daftar = Mitra.getDataMitra(cari);
    String[] kolom = {"No", "ID Mitra", "Nama", "Badan Usaha", "Kategori",  "Email", "NoTelp", "No Rek", "Jangkauan Area"};

    DefaultTableModel model = new DefaultTableModel(kolom, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    int no = 1;
    for (Mitra p : daftar) {
        String noRek = "";
        String jangkauanArea = "";

        if (p instanceof Customer) {
            noRek = ((Customer) p).getNorek();
        } else if (p instanceof Supplier) {
            jangkauanArea = ((Supplier) p).getJangkauan();
        }

        Object[] rowData = {
            no++,
            p.id,
            p.nama,
            p.badanUsaha,
            p.kategori,
            p.email,
            p.noTelp,
            noRek,
            jangkauanArea
        };

        model.addRow(rowData);
    }

    tblMitra.setModel(model);

    // Sembunyikan kolom ID
    tblMitra.getColumnModel().getColumn(1).setMinWidth(0);
    tblMitra.getColumnModel().getColumn(1).setMaxWidth(0);
    tblMitra.getColumnModel().getColumn(1).setWidth(0);

    // Atur lebar kolom "No"
    tblMitra.getColumnModel().getColumn(0).setMinWidth(30);
    tblMitra.getColumnModel().getColumn(0).setMaxWidth(30);
    tblMitra.getColumnModel().getColumn(0).setWidth(30);
}

   public void setFormPegawaiMode(Boolean flag){
         txtNamapegawai.setEnabled(flag);
        cbJabatanpegawai.setEnabled(flag);
        txtTelppegawai.setEnabled(flag);
        txtEmailpegawai.setEnabled(flag);
        txtPasswordpegawai.setEnabled(flag);
        btnSimpanPegawai.setEnabled(flag);
        btnResetPegawai.setEnabled(flag);
        
        btnUbahPegawai.setEnabled(!flag);
        btnTambahPegawai.setEnabled(!flag);
        btnHapusPegawai.setEnabled(!flag);
        
         if (!flag) {
        // Hapus isi form saat nonaktif
       clearForm("pegawai");
    }
   }
   public void setFormProdukMode(Boolean flag){
        txtNamaProduk.setEnabled(flag);
        cbKategoriProduk.setEnabled(flag);
//        txtKapasitas.setEnabled(flag);
//        txtPanjang.setEnabled(flag);
        txtDeskripsi.setEnabled(flag);
        btnSimpanProduk.setEnabled(flag);
        btnResetProduk.setEnabled(flag);
   
        btnUbahProduk.setEnabled(!flag);
        btnTambahProduk.setEnabled(!flag);
        btnHapusProduk.setEnabled(!flag);
        
         if (!flag) {
        // Hapus isi form saat nonaktif
       clearForm("produk");
    }
   }
   
   public void setFormMitraMode(Boolean flag){
        txtNamaMitra.setEnabled(flag);
        cbKategoriMitra.setEnabled(flag);
        cbBadanUsaha.setEnabled(flag);

        txtTelpMitra.setEnabled(flag);
//        txtNorekMitra.setEnabled(flag);
//        txtAreaMitra.setEnabled(flag);
        txtEmailMitra.setEnabled(flag);
        btnSimpanMitra.setEnabled(flag);
        btnResetMitra.setEnabled(flag);
        
        btnUbahMitra.setEnabled(!flag);
        btnTambahMitra.setEnabled(!flag);
        btnHapusMitra.setEnabled(!flag);
        
         if (!flag) {
        // Hapus isi form saat nonaktif
       clearForm("mitra");
    }
   }
   public void clearForm(String jenis){
       
       if(jenis.equals("pegawai")){
       txtCariPegawai.setText("");
         txtIdpegawai.setText("");
        txtNamapegawai.setText("");
        cbJabatanpegawai.setSelectedIndex(0); // asumsi index 0 adalah default
        txtTelppegawai.setText("");
        txtEmailpegawai.setText("");
        txtPasswordpegawai.setText("");
   }
       else if(jenis.equals("produk")){
        txtCariPegawai.setText("");
        txtIdProduk.setText("");    
           txtNamaProduk.setText("");
        cbKategoriProduk.setSelectedIndex(0);
        txtKapasitas.setText("");
        txtPanjang.setText("");
        txtDeskripsi.setText("");
       }
       else if(jenis.equals("mitra")){
                   txtIdMitra.setText("");    
   
            txtNamaMitra.setText("");
        cbKategoriMitra.setSelectedIndex(0);
        cbBadanUsaha.setSelectedIndex(0);

        txtEmailMitra.setText("");
        txtTelpMitra.setText("");
        txtNorekMitra.setText("");
        txtAreaMitra.setText("");
       }
      
   }
    private void validasiComboBoxProduk(){
       
      if(cbKategoriProduk.getSelectedItem().equals("Pickup")){
            txtKapasitas.setEnabled(false);
            txtPanjang.setEnabled(true);

        }
        else if(cbKategoriProduk.getSelectedItem().equals("Truk")){
            txtPanjang.setEnabled(false);
            txtKapasitas.setEnabled(true);
        }
        else{
             txtPanjang.setEnabled(false);
            txtKapasitas.setEnabled(false);
        }   
    }
    private void validasiComboBoxMitra(){
       
      if(cbKategoriMitra.getSelectedItem().equals("Customer")){
            txtAreaMitra.setEnabled(false);
            txtNorekMitra.setEnabled(true);

        }
        else if(cbKategoriMitra.getSelectedItem().equals("Supplier")){
            txtNorekMitra.setEnabled(false);
            txtAreaMitra.setEnabled(true);
        }
        else{
             txtNorekMitra.setEnabled(false);
            txtAreaMitra.setEnabled(false);
        }   
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnPegawai = new javax.swing.JButton();
        btnKembali1 = new javax.swing.JButton();
        btnMitra = new javax.swing.JButton();
        btnProduk = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        cardProduk = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblProduk = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cbKategoriProduk = new javax.swing.JComboBox<>();
        txtNamaProduk = new javax.swing.JTextField();
        txtIdProduk = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btnSimpanProduk = new javax.swing.JButton();
        btnResetProduk = new javax.swing.JButton();
        btnTambahProduk = new javax.swing.JButton();
        btnUbahProduk = new javax.swing.JButton();
        btnHapusProduk = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtDeskripsi = new javax.swing.JTextArea();
        txtKapasitas = new javax.swing.JTextField();
        txtPanjang = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        txtCariProduk = new javax.swing.JTextField();
        btnCariProduk = new javax.swing.JButton();
        cardMitra = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblMitra = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtIdMitra = new javax.swing.JTextField();
        txtNamaMitra = new javax.swing.JTextField();
        cbKategoriMitra = new javax.swing.JComboBox<>();
        txtEmailMitra = new javax.swing.JTextField();
        btnSimpanMitra = new javax.swing.JButton();
        btnResetMitra = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        btnTambahMitra = new javax.swing.JButton();
        btnUbahMitra = new javax.swing.JButton();
        btnHapusMitra = new javax.swing.JButton();
        txtNorekMitra = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtAreaMitra = new javax.swing.JTextField();
        cbBadanUsaha = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        txtTelpMitra = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        txtCariMitra = new javax.swing.JTextField();
        btnCariMitra = new javax.swing.JButton();
        cardPegawai = new javax.swing.JPanel();
        txtNamapegawai = new javax.swing.JTextField();
        txtEmailpegawai = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        btnSimpanPegawai = new javax.swing.JButton();
        txtTelppegawai = new javax.swing.JTextField();
        btnResetPegawai = new javax.swing.JButton();
        txtIdpegawai = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        cbJabatanpegawai = new javax.swing.JComboBox<>();
        btnTambahPegawai = new javax.swing.JButton();
        btnUbahPegawai = new javax.swing.JButton();
        btnHapusPegawai = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblPegawai = new javax.swing.JTable();
        jLabel31 = new javax.swing.JLabel();
        txtPasswordpegawai = new javax.swing.JPasswordField();
        jLabel32 = new javax.swing.JLabel();
        txtCariPegawai = new javax.swing.JTextField();
        btnCariPegawai = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(8, 63, 114));

        btnPegawai.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnPegawai.setText("Pegawai");
        btnPegawai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPegawaiActionPerformed(evt);
            }
        });

        btnKembali1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnKembali1.setText("Kembali");
        btnKembali1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKembali1ActionPerformed(evt);
            }
        });

        btnMitra.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnMitra.setText("Mitra");
        btnMitra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMitraActionPerformed(evt);
            }
        });

        btnProduk.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnProduk.setText("Produk");
        btnProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProdukActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnKembali1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnPegawai, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnMitra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnProduk, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(btnProduk)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnMitra)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPegawai)
                .addGap(45, 45, 45)
                .addComponent(btnKembali1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(8, 63, 114));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("SBA Logistik");

        lblUsername.setForeground(new java.awt.Color(255, 255, 255));
        lblUsername.setText("Hi, Username");

        btnLogout.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnLogout.setText("Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(lblUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btnLogout)
                        .addGap(40, 40, 40))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel24)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLogout)))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setLayout(new java.awt.CardLayout());

        cardProduk.setBackground(new java.awt.Color(255, 255, 255));

        tblProduk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Id", "Nama", "Kategori", "Deskripsi", "Kapasitas Mesin", "Panjang Bak"
            }
        ));
        tblProduk.setCellSelectionEnabled(true);
        tblProduk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProdukMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblProduk);

        jLabel9.setText("ID Produk :");

        jLabel10.setText("Nama :");

        jLabel11.setText("Kategori :");

        jLabel12.setText("Deskripsi :");

        cbKategoriProduk.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Kategori", "Pickup", "Truk" }));
        cbKategoriProduk.setEnabled(false);
        cbKategoriProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbKategoriProdukActionPerformed(evt);
            }
        });

        txtNamaProduk.setEnabled(false);

        txtIdProduk.setEnabled(false);
        txtIdProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdProdukActionPerformed(evt);
            }
        });

        jLabel13.setText("Panjang Bak :");

        jLabel14.setText("Kapasitas Maksimal :");

        btnSimpanProduk.setText("Simpan");
        btnSimpanProduk.setEnabled(false);
        btnSimpanProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanProdukActionPerformed(evt);
            }
        });

        btnResetProduk.setText("Reset");
        btnResetProduk.setEnabled(false);
        btnResetProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetProdukActionPerformed(evt);
            }
        });

        btnTambahProduk.setText("Tambah");
        btnTambahProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahProdukActionPerformed(evt);
            }
        });

        btnUbahProduk.setText("Ubah");
        btnUbahProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahProdukActionPerformed(evt);
            }
        });

        btnHapusProduk.setText("Hapus");
        btnHapusProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusProdukActionPerformed(evt);
            }
        });

        txtDeskripsi.setColumns(20);
        txtDeskripsi.setRows(5);
        txtDeskripsi.setEnabled(false);
        jScrollPane3.setViewportView(txtDeskripsi);

        txtKapasitas.setEnabled(false);

        txtPanjang.setEnabled(false);

        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel33.setText("Data Produk");

        jLabel34.setText("Cari Nama :");

        txtCariProduk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCariProdukKeyPressed(evt);
            }
        });

        btnCariProduk.setText("Cari");
        btnCariProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariProdukActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout cardProdukLayout = new javax.swing.GroupLayout(cardProduk);
        cardProduk.setLayout(cardProdukLayout);
        cardProdukLayout.setHorizontalGroup(
            cardProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(cardProdukLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cardProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cardProdukLayout.createSequentialGroup()
                        .addGroup(cardProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)
                            .addComponent(jLabel13)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGroup(cardProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(cardProdukLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 286, Short.MAX_VALUE)
                                .addComponent(btnTambahProduk)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnUbahProduk)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnHapusProduk))
                            .addGroup(cardProdukLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(cardProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNamaProduk)
                                    .addComponent(txtIdProduk)
                                    .addComponent(txtKapasitas)
                                    .addComponent(cbKategoriProduk, 0, 196, Short.MAX_VALUE)
                                    .addComponent(txtPanjang))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(cardProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cardProdukLayout.createSequentialGroup()
                                        .addComponent(btnSimpanProduk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnResetProduk))
                                    .addComponent(jScrollPane3)
                                    .addGroup(cardProdukLayout.createSequentialGroup()
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(216, 216, 216))))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cardProdukLayout.createSequentialGroup()
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel34)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCariProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCariProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        cardProdukLayout.setVerticalGroup(
            cardProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardProdukLayout.createSequentialGroup()
                .addGroup(cardProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cardProdukLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(cardProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCariProduk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCariProduk)
                            .addComponent(jLabel34)))
                    .addGroup(cardProdukLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel33)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cardProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTambahProduk)
                    .addComponent(btnUbahProduk)
                    .addComponent(btnHapusProduk))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(cardProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cardProdukLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(cardProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnResetProduk)
                            .addComponent(btnSimpanProduk))
                        .addGap(38, 38, 38))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cardProdukLayout.createSequentialGroup()
                        .addGroup(cardProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addGroup(cardProdukLayout.createSequentialGroup()
                                .addComponent(txtIdProduk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(cardProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtNamaProduk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(cardProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbKategoriProduk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(cardProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtKapasitas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(cardProdukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txtPanjang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35))))
        );

        jPanel3.add(cardProduk, "card3");

        cardMitra.setBackground(new java.awt.Color(255, 255, 255));

        tblMitra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "ID Mitra", "Nama", "Badan Usaha", "Kategori", "Email", "Nomor Telp", "Nomor Rekening", "Jangkauan Area"
            }
        ));
        tblMitra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMitraMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblMitra);

        jLabel15.setText("ID Mitra :");

        jLabel16.setText("Nama :");

        jLabel17.setText("Badan Usaha :");

        jLabel18.setText("Kategori :");

        jLabel19.setText("Email :");

        txtIdMitra.setEnabled(false);

        txtNamaMitra.setEnabled(false);

        cbKategoriMitra.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Kategori", "Customer", "Supplier" }));
        cbKategoriMitra.setEnabled(false);
        cbKategoriMitra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbKategoriMitraActionPerformed(evt);
            }
        });

        txtEmailMitra.setEnabled(false);

        btnSimpanMitra.setText("Simpan");
        btnSimpanMitra.setEnabled(false);
        btnSimpanMitra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanMitraActionPerformed(evt);
            }
        });

        btnResetMitra.setText("Reset");
        btnResetMitra.setEnabled(false);
        btnResetMitra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetMitraActionPerformed(evt);
            }
        });

        jLabel20.setText("Nomor Rekening :");

        btnTambahMitra.setText("Tambah");
        btnTambahMitra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahMitraActionPerformed(evt);
            }
        });

        btnUbahMitra.setText("Ubah");
        btnUbahMitra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahMitraActionPerformed(evt);
            }
        });

        btnHapusMitra.setText("Hapus");
        btnHapusMitra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusMitraActionPerformed(evt);
            }
        });

        txtNorekMitra.setEnabled(false);

        jLabel22.setText("Jangkauan Area :");

        txtAreaMitra.setEnabled(false);

        cbBadanUsaha.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Perorangan", "CV", "PT" }));
        cbBadanUsaha.setEnabled(false);
        cbBadanUsaha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbBadanUsahaActionPerformed(evt);
            }
        });

        jLabel23.setText("No. Telp :");

        txtTelpMitra.setEnabled(false);
        txtTelpMitra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelpMitraActionPerformed(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel35.setText("Data Mitra");

        jLabel36.setText("Cari Nama :");

        txtCariMitra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCariMitraKeyPressed(evt);
            }
        });

        btnCariMitra.setText("Cari");
        btnCariMitra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariMitraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout cardMitraLayout = new javax.swing.GroupLayout(cardMitra);
        cardMitra.setLayout(cardMitraLayout);
        cardMitraLayout.setHorizontalGroup(
            cardMitraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4)
            .addGroup(cardMitraLayout.createSequentialGroup()
                .addGroup(cardMitraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cardMitraLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnTambahMitra)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnUbahMitra)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnHapusMitra))
                    .addGroup(cardMitraLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(cardMitraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(cardMitraLayout.createSequentialGroup()
                                .addGroup(cardMitraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(cardMitraLayout.createSequentialGroup()
                                        .addGroup(cardMitraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel18)
                                            .addGroup(cardMitraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel15)))
                                        .addGap(28, 28, 28)))
                                .addGap(18, 18, 18)
                                .addGroup(cardMitraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(cbKategoriMitra, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbBadanUsaha, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtNamaMitra, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtIdMitra, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(cardMitraLayout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtEmailMitra, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                        .addGroup(cardMitraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(cardMitraLayout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(cardMitraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cardMitraLayout.createSequentialGroup()
                                        .addComponent(btnSimpanMitra)
                                        .addGap(26, 26, 26)
                                        .addComponent(btnResetMitra))
                                    .addComponent(txtAreaMitra, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(cardMitraLayout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addGap(63, 63, 63)
                                .addComponent(txtTelpMitra, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(cardMitraLayout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addGap(18, 18, 18)
                                .addComponent(txtNorekMitra, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(9, 9, 9))
                    .addGroup(cardMitraLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel36)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCariMitra, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCariMitra, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        cardMitraLayout.setVerticalGroup(
            cardMitraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardMitraLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cardMitraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cardMitraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCariMitra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCariMitra)
                        .addComponent(jLabel36))
                    .addComponent(jLabel35))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(cardMitraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cardMitraLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(cardMitraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnTambahMitra)
                            .addComponent(btnUbahMitra)
                            .addComponent(btnHapusMitra))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addGroup(cardMitraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTelpMitra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23))
                        .addGap(18, 18, 18)
                        .addGroup(cardMitraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(txtNorekMitra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(cardMitraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtAreaMitra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22)))
                    .addGroup(cardMitraLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(cardMitraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(txtIdMitra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(cardMitraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNamaMitra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addGap(15, 15, 15)
                        .addGroup(cardMitraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbBadanUsaha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(cardMitraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(cbKategoriMitra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(cardMitraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnResetMitra)
                    .addComponent(btnSimpanMitra)
                    .addComponent(jLabel19)
                    .addComponent(txtEmailMitra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38))
        );

        jPanel3.add(cardMitra, "card4");

        cardPegawai.setBackground(new java.awt.Color(255, 255, 255));

        txtNamapegawai.setEnabled(false);

        txtEmailpegawai.setEnabled(false);

        jLabel25.setText("No. Telp :");

        jLabel26.setText("Jabatan :");

        btnSimpanPegawai.setText("Simpan");
        btnSimpanPegawai.setEnabled(false);
        btnSimpanPegawai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanPegawaiActionPerformed(evt);
            }
        });

        txtTelppegawai.setEnabled(false);
        txtTelppegawai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelppegawaiActionPerformed(evt);
            }
        });

        btnResetPegawai.setText("Reset");
        btnResetPegawai.setEnabled(false);
        btnResetPegawai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetPegawaiActionPerformed(evt);
            }
        });

        txtIdpegawai.setEnabled(false);

        jLabel27.setText("Password :");

        jLabel28.setText("Email :");

        jLabel29.setText("ID Pegawai :");

        jLabel30.setText("Nama :");

        cbJabatanpegawai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "admin", "staf" }));
        cbJabatanpegawai.setEnabled(false);

        btnTambahPegawai.setText("Tambah");
        btnTambahPegawai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahPegawaiActionPerformed(evt);
            }
        });

        btnUbahPegawai.setText("Ubah");
        btnUbahPegawai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahPegawaiActionPerformed(evt);
            }
        });

        btnHapusPegawai.setText("Hapus");
        btnHapusPegawai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusPegawaiActionPerformed(evt);
            }
        });

        tblPegawai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Id", "Nama", "Jabatan", "Nomor Telp", "Email", "Password"
            }
        ));
        tblPegawai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pegawaiclick(evt);
            }
        });
        jScrollPane5.setViewportView(tblPegawai);

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel31.setText("Data Pegawai");

        txtPasswordpegawai.setEnabled(false);

        jLabel32.setText("Cari Nama :");

        txtCariPegawai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCariPegawaiKeyPressed(evt);
            }
        });

        btnCariPegawai.setText("Cari");
        btnCariPegawai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariPegawaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout cardPegawaiLayout = new javax.swing.GroupLayout(cardPegawai);
        cardPegawai.setLayout(cardPegawaiLayout);
        cardPegawaiLayout.setHorizontalGroup(
            cardPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 648, Short.MAX_VALUE)
            .addGroup(cardPegawaiLayout.createSequentialGroup()
                .addGroup(cardPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cardPegawaiLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(cardPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel29)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(cardPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(cardPegawaiLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnTambahPegawai)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnUbahPegawai)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnHapusPegawai))
                            .addGroup(cardPegawaiLayout.createSequentialGroup()
                                .addGroup(cardPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(cardPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtTelppegawai)
                                        .addComponent(cbJabatanpegawai, 0, 185, Short.MAX_VALUE))
                                    .addGroup(cardPegawaiLayout.createSequentialGroup()
                                        .addGroup(cardPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtIdpegawai, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                                            .addComponent(txtNamapegawai))
                                        .addGap(26, 26, 26)
                                        .addGroup(cardPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel27)
                                            .addComponent(jLabel28))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(cardPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(cardPegawaiLayout.createSequentialGroup()
                                        .addComponent(btnSimpanPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnResetPegawai))
                                    .addGroup(cardPegawaiLayout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addGroup(cardPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtPasswordpegawai, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                                            .addComponent(txtEmailpegawai))))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(cardPegawaiLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCariPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCariPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        cardPegawaiLayout.setVerticalGroup(
            cardPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardPegawaiLayout.createSequentialGroup()
                .addGroup(cardPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cardPegawaiLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(cardPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCariPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCariPegawai)
                            .addComponent(jLabel32)))
                    .addGroup(cardPegawaiLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel31)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cardPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTambahPegawai)
                    .addComponent(btnUbahPegawai)
                    .addComponent(btnHapusPegawai))
                .addGroup(cardPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cardPegawaiLayout.createSequentialGroup()
                        .addGroup(cardPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(cardPegawaiLayout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addGroup(cardPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtIdpegawai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel28))
                                .addGap(9, 9, 9))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cardPegawaiLayout.createSequentialGroup()
                                .addComponent(jLabel29)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(cardPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNamapegawai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30)))
                    .addGroup(cardPegawaiLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(txtEmailpegawai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(cardPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(txtPasswordpegawai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cardPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(cbJabatanpegawai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cardPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTelppegawai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25)
                    .addComponent(btnSimpanPegawai)
                    .addComponent(btnResetPegawai))
                .addGap(41, 41, 41))
        );

        jPanel3.add(cardPegawai, "card5");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cbBadanUsahaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbBadanUsahaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbBadanUsahaActionPerformed

    private void btnPegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPegawaiActionPerformed
       CardLayout ly =(CardLayout)jPanel3.getLayout();
        ly.show(jPanel3, "card5");
    }//GEN-LAST:event_btnPegawaiActionPerformed

    private void btnKembali1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKembali1ActionPerformed
        // TODO add your handling code here:
        Dashboard master = new Dashboard();
        master.setVisible(true);
         dispose();
    }//GEN-LAST:event_btnKembali1ActionPerformed

    private void btnTambahPegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahPegawaiActionPerformed
            // TODO add your handling code here:
                       clearForm("pegawai");

        setFormPegawaiMode(true);
    }//GEN-LAST:event_btnTambahPegawaiActionPerformed

    private void btnResetPegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetPegawaiActionPerformed
        // TODO add your handling code here:
                clearForm("pegawai");
                setFormPegawaiMode(false);

    }//GEN-LAST:event_btnResetPegawaiActionPerformed

    private void pegawaiclick(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pegawaiclick
        // TODO add your handling code here:
           DefaultTableModel model = (DefaultTableModel) tblPegawai.getModel();
int selectedIndexRow = tblPegawai.getSelectedRow();

if (selectedIndexRow != -1) { // pastikan ada yang dipilih
    txtIdpegawai.setText(model.getValueAt(selectedIndexRow, 1).toString());
    txtNamapegawai.setText(model.getValueAt(selectedIndexRow, 2).toString());
    cbJabatanpegawai.setSelectedItem(model.getValueAt(selectedIndexRow, 3).toString());
    txtTelppegawai.setText(model.getValueAt(selectedIndexRow, 4).toString());
    txtEmailpegawai.setText(model.getValueAt(selectedIndexRow, 5).toString());
    txtPasswordpegawai.setText(model.getValueAt(selectedIndexRow, 6).toString());

}

    }//GEN-LAST:event_pegawaiclick

    private void btnSimpanPegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanPegawaiActionPerformed

    if (
        txtNamapegawai.getText().trim().isEmpty() ||
        cbJabatanpegawai.getSelectedItem() == null ||
        txtTelppegawai.getText().trim().isEmpty() ||
        txtEmailpegawai.getText().trim().isEmpty() ||
        txtPasswordpegawai.getText().trim().isEmpty()
    ){
        JOptionPane.showMessageDialog(this, "Mohon isi semua data pegawai!", "Peringatan", JOptionPane.WARNING_MESSAGE);
        return;
    }
    if (!txtTelppegawai.getText().trim().matches("\\d+")) {
        JOptionPane.showMessageDialog(this, "No. Telp hanya boleh berisi angka!", "Peringatan", JOptionPane.WARNING_MESSAGE);
        return;
    }
    if (!txtEmailpegawai.getText().trim().contains("@")) {
        JOptionPane.showMessageDialog(this, "Email anda salah!", "Peringatan", JOptionPane.WARNING_MESSAGE);
        return;
    }
    if(txtIdpegawai.getText().equals("")){
       Pegawai pgw = new Pegawai(txtNamapegawai.getText(), cbJabatanpegawai.getSelectedItem().toString(), txtTelppegawai.getText(), txtEmailpegawai.getText(), txtPasswordpegawai.getText());
       pgw.createPegawai();
       JOptionPane.showMessageDialog(this, "Pegawai Berhasil disimpan", "Info", JOptionPane.INFORMATION_MESSAGE);

    }
    else{
       Pegawai pgw = new Pegawai(Integer.parseInt(txtIdpegawai.getText()), txtNamapegawai.getText(), cbJabatanpegawai.getSelectedItem().toString(), txtTelppegawai.getText(), txtEmailpegawai.getText(), txtPasswordpegawai.getText());
       pgw.updatePegawai();
       JOptionPane.showMessageDialog(this, "Pegawai Berhasil disimpan");
    }
     setFormPegawaiMode(false);

           tampilkanPegawai(txtCariPegawai.getText());

    }//GEN-LAST:event_btnSimpanPegawaiActionPerformed

    private void btnUbahPegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahPegawaiActionPerformed
        // TODO add your handling code here:
        if(txtIdpegawai.getText().equals("")){
             JOptionPane.showMessageDialog(this, "Pilih pegawai terlebih dahulu!", "Error", JOptionPane.WARNING_MESSAGE);
             return;
        }

        setFormPegawaiMode(true);
    }//GEN-LAST:event_btnUbahPegawaiActionPerformed

    private void btnHapusPegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusPegawaiActionPerformed
        // TODO add your handling code here:
         if(txtIdpegawai.getText().equals("")){
             JOptionPane.showMessageDialog(this, "Pilih pegawai terlebih dahulu!", "Error", JOptionPane.WARNING_MESSAGE);
             return;
        }
         
   int pilihan = JOptionPane.showConfirmDialog(
    null,
    "Apakah anda ingin menghapus data tersebut?",
    "Hapus",
    JOptionPane.OK_CANCEL_OPTION,
    JOptionPane.WARNING_MESSAGE
);

if (pilihan == JOptionPane.OK_OPTION) {
    Pegawai.deletePegawai(Integer.parseInt(txtIdpegawai.getText()));
    JOptionPane.showMessageDialog(this, "Berhasil menghapus pegawai!", "Alert", JOptionPane.INFORMATION_MESSAGE);
    setFormProdukMode(false);
        clearForm("produk");

    tampilkanPegawai(txtCariPegawai.getText());
}
    }//GEN-LAST:event_btnHapusPegawaiActionPerformed

    private void txtCariPegawaiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariPegawaiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariPegawaiKeyPressed

    private void btnCariPegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariPegawaiActionPerformed
        // TODO add your handling code here:
     tampilkanPegawai(txtCariPegawai.getText());

    }//GEN-LAST:event_btnCariPegawaiActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
         int pilihan = JOptionPane.showConfirmDialog(
    null,
    "Apakah Anda ingin logout?",
    "Logout",
    JOptionPane.OK_CANCEL_OPTION,
    JOptionPane.WARNING_MESSAGE
);
        
    if (pilihan == JOptionPane.OK_OPTION) {
        Login login = new Login();
        login.setVisible(true);
         dispose();
}
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnTambahProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahProdukActionPerformed
        // TODO add your handling code here:
        clearForm("produk");

        setFormProdukMode(true);
    }//GEN-LAST:event_btnTambahProdukActionPerformed

    private void txtCariProdukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariProdukKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariProdukKeyPressed

    private void btnCariProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariProdukActionPerformed
        // TODO add your handling code here:
        tampikanProduk(txtCariProduk.getText());
    }//GEN-LAST:event_btnCariProdukActionPerformed

    private void txtCariMitraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariMitraKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariMitraKeyPressed

    private void btnCariMitraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariMitraActionPerformed
              tampikanMitra(txtCariMitra.getText());

    }//GEN-LAST:event_btnCariMitraActionPerformed

    private void btnUbahProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahProdukActionPerformed
        // TODO add your handling code here:
        if(txtIdProduk.getText().equals("")){
             JOptionPane.showMessageDialog(this, "Pilih produk terlebih dahulu!", "Error", JOptionPane.WARNING_MESSAGE);
             return;
        }
validasiComboBoxProduk();
        setFormProdukMode(true);
    }//GEN-LAST:event_btnUbahProdukActionPerformed

    private void btnHapusProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusProdukActionPerformed
        // TODO add your handling code here:
        if(txtIdProduk.getText().equals("")){
             JOptionPane.showMessageDialog(this, "Pilih produk terlebih dahulu!", "Error", JOptionPane.WARNING_MESSAGE);
             return;
        }
         
   int pilihan = JOptionPane.showConfirmDialog(
    null,
    "Apakah anda ingin menghapus data tersebut?",
    "Hapus",
    JOptionPane.OK_CANCEL_OPTION,
    JOptionPane.WARNING_MESSAGE
);

if (pilihan == JOptionPane.OK_OPTION) {
    Produk.deleteProduk(Integer.parseInt(txtIdProduk.getText()));
    JOptionPane.showMessageDialog(this, "Berhasil menghapus produk!", "Alert", JOptionPane.INFORMATION_MESSAGE);
    setFormProdukMode(false);
        clearForm("produk");

    tampikanProduk(txtCariProduk.getText());
            }
    }//GEN-LAST:event_btnHapusProdukActionPerformed
    

    private void tblProdukMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProdukMouseClicked
        // TODO add your handling code here:
    DefaultTableModel model = (DefaultTableModel) tblProduk.getModel();
    int selectedIndexRow = tblProduk.getSelectedRow();

    if (selectedIndexRow != -1) { // pastikan ada yang dipilih
    txtIdProduk.setText(model.getValueAt(selectedIndexRow, 1).toString());
    txtNamaProduk.setText(model.getValueAt(selectedIndexRow, 2).toString());
    cbKategoriProduk.setSelectedItem(model.getValueAt(selectedIndexRow, 3).toString());
    txtKapasitas.setText(model.getValueAt(selectedIndexRow, 5).toString());
    txtPanjang.setText(model.getValueAt(selectedIndexRow, 6).toString());
    txtDeskripsi.setText(model.getValueAt(selectedIndexRow, 4).toString());
    txtKapasitas.setEnabled(false);
        txtPanjang.setEnabled(false);

    }
    }//GEN-LAST:event_tblProdukMouseClicked

    private void cbKategoriProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbKategoriProdukActionPerformed
            // TODO add your handling code here:
            validasiComboBoxProduk();
    }//GEN-LAST:event_cbKategoriProdukActionPerformed

    private void btnResetProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetProdukActionPerformed
         clearForm("produk");
                setFormProdukMode(false);        // TODO add your handling code here:
    }//GEN-LAST:event_btnResetProdukActionPerformed

    private void btnSimpanProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanProdukActionPerformed
        // TODO add your handling code here:
        String nama = txtNamaProduk.getText().trim();
        String deskripsi = txtDeskripsi.getText().trim();
        String panjang = txtPanjang.getText().trim();
        String kapasitas = txtKapasitas.getText().trim();
        Object kategoriObj = cbKategoriProduk.getSelectedItem();

        if (nama.isEmpty() || deskripsi.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Mohon isi semua data produk!",
                    "Peringatan",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (kategoriObj == null || kategoriObj.toString().equals("Pilih Kategori")) {
            JOptionPane.showMessageDialog(this,
                    "Mohon pilih kategori produk!",
                    "Peringatan",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String kategori = kategoriObj.toString();

        if (kategori.equals("Pickup") && panjang.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Panjang Bak harus diisi!",
                    "Peringatan",
                    JOptionPane.WARNING_MESSAGE);
            return;
        } else if (kategori.equals("Truk") && kapasitas.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Kapasitas Maksimal harus diisi!",
                    "Peringatan",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        if(txtIdProduk.getText().equals("")){
           if(cbKategoriProduk.getSelectedItem().equals("Pickup")){
              Pickup pck = new Pickup(txtNamaProduk.getText(), cbKategoriProduk.getSelectedItem().toString(),  txtDeskripsi.getText(), txtPanjang.getText());
              pck.createProduk();
           }
           else{
              Truk trk = new Truk(txtNamaProduk.getText(), cbKategoriProduk.getSelectedItem().toString(),  txtDeskripsi.getText(), txtKapasitas.getText());
              trk.createProduk();
           }
    }
    else{
       if(cbKategoriProduk.getSelectedItem().equals("Pickup")){
              Pickup pck = new Pickup(Integer.parseInt(txtIdProduk.getText()),txtNamaProduk.getText(), cbKategoriProduk.getSelectedItem().toString(),  txtDeskripsi.getText(), txtPanjang.getText());
              pck.updateProduk();
           }
           else{
              Truk trk = new Truk(Integer.parseInt(txtIdProduk.getText()),txtNamaProduk.getText(), cbKategoriProduk.getSelectedItem().toString(),  txtDeskripsi.getText(), txtKapasitas.getText());
              trk.updateProduk();
           }       
    }
        
     setFormProdukMode(false);
         JOptionPane.showMessageDialog(this, "Produk Berhasil disimpan", "Info", JOptionPane.INFORMATION_MESSAGE);
        clearForm("produk");


           tampikanProduk(txtCariPegawai.getText());
    }//GEN-LAST:event_btnSimpanProdukActionPerformed

    private void cbKategoriMitraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbKategoriMitraActionPerformed
        // TODO add your handling code here:
        validasiComboBoxMitra();
    }//GEN-LAST:event_cbKategoriMitraActionPerformed

    private void tblMitraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMitraMouseClicked
        // TODO add your handling code here:
        
        DefaultTableModel model = (DefaultTableModel) tblMitra.getModel();
    int selectedIndexRow = tblMitra.getSelectedRow();

    if (selectedIndexRow != -1) { // pastikan ada yang dipilih
    txtIdMitra.setText(model.getValueAt(selectedIndexRow, 1).toString());
    txtNamaMitra.setText(model.getValueAt(selectedIndexRow, 2).toString());
    cbBadanUsaha.setSelectedItem(model.getValueAt(selectedIndexRow, 3).toString());
    cbKategoriMitra.setSelectedItem(model.getValueAt(selectedIndexRow, 4).toString());
    txtEmailMitra.setText(model.getValueAt(selectedIndexRow, 5).toString());
    txtTelpMitra.setText(model.getValueAt(selectedIndexRow, 6).toString());
    txtNorekMitra.setText(model.getValueAt(selectedIndexRow, 7).toString());
    txtAreaMitra.setText(model.getValueAt(selectedIndexRow, 8).toString());
    txtNorekMitra.setEnabled(false);
        txtAreaMitra.setEnabled(false);
        
    }
    }//GEN-LAST:event_tblMitraMouseClicked

    private void btnTambahMitraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahMitraActionPerformed
        // TODO add your handling code here:
         clearForm("mitra");

        setFormMitraMode(true);
    }//GEN-LAST:event_btnTambahMitraActionPerformed

    private void btnUbahMitraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahMitraActionPerformed
        // TODO add your handling code here:
         if(txtIdMitra.getText().equals("")){
             JOptionPane.showMessageDialog(this, "Pilih mitra terlebih dahulu!", "Error", JOptionPane.WARNING_MESSAGE);
             return;
        }
         validasiComboBoxMitra();
        setFormMitraMode(true);
    }//GEN-LAST:event_btnUbahMitraActionPerformed

    private void btnHapusMitraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusMitraActionPerformed
        // TODO add your handling code here:
        if(txtIdMitra.getText().equals("")){
             JOptionPane.showMessageDialog(this, "Pilih mitra terlebih dahulu!", "Error", JOptionPane.WARNING_MESSAGE);
             return;
        }
         
   int pilihan = JOptionPane.showConfirmDialog(
    null,
    "Apakah anda ingin menghapus data tersebut?",
    "Hapus",
    JOptionPane.OK_CANCEL_OPTION,
    JOptionPane.WARNING_MESSAGE
);

if (pilihan == JOptionPane.OK_OPTION) {
    Mitra.deleteMitra(Integer.parseInt(txtIdMitra.getText()));
    JOptionPane.showMessageDialog(this, "Berhasil menghapus mitra!", "Alert", JOptionPane.INFORMATION_MESSAGE);
    setFormMitraMode(false);
        clearForm("mitra");

    tampikanMitra(txtCariMitra.getText());
            }
    }//GEN-LAST:event_btnHapusMitraActionPerformed

    private void btnResetMitraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetMitraActionPerformed
        // TODO add your handling code here:
        clearForm("mitra");
                setFormMitraMode(false);
    }//GEN-LAST:event_btnResetMitraActionPerformed

    private void btnSimpanMitraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanMitraActionPerformed
        // TODO add your handling code here:
        String nama = txtNamaMitra.getText().trim();
        String email = txtEmailMitra.getText().trim();
        String telp = txtTelpMitra.getText().trim();
        String norek = txtNorekMitra.getText().trim();
        String area = txtAreaMitra.getText().trim();
        
        Object kategoriObj = cbKategoriMitra.getSelectedItem();

        if (nama.isEmpty() || email.isEmpty() || telp.isEmpty()
            || kategoriObj == null || kategoriObj.toString().equals("Pilih Kategori")
            ) {

            JOptionPane.showMessageDialog(this,
                    "Mohon isi semua data mitra!",
                    "Peringatan",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        String kategori = kategoriObj.toString();

        if (kategori.equals("Customer") && norek.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Untuk kategori Customer, No. Rek harus diisi!",
                    "Peringatan",
                    JOptionPane.WARNING_MESSAGE);
            return;
        } else if (kategori.equals("Supplier") && area.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Untuk kategori Supplier, Area harus diisi!",
                    "Peringatan",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!txtTelpMitra.getText().trim().matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "No. Telp hanya boleh berisi angka!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
       
       
        if (!txtEmailMitra.getText().trim().contains("@")) {
            JOptionPane.showMessageDialog(this, "Email anda salah!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if(txtIdMitra.getText().equals("")){
            
           if(cbKategoriMitra.getSelectedItem().equals("Customer")){
                if (!txtNorekMitra.getText().trim().matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Nomor Rekening hanya boleh berisi angka!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
              Customer cst = new Customer(txtNamaMitra.getText(), cbBadanUsaha.getSelectedItem().toString(), cbKategoriMitra.getSelectedItem().toString(),  txtEmailMitra.getText(), txtTelpMitra.getText(), txtNorekMitra.getText());
              cst.createMitra();
           }
           else{
             Supplier spl = new Supplier(txtNamaMitra.getText(), cbBadanUsaha.getSelectedItem().toString(), cbKategoriMitra.getSelectedItem().toString(),  txtEmailMitra.getText(), txtTelpMitra.getText(), txtAreaMitra.getText());
              spl.createMitra();
           }       
    }
    else{
       if(cbKategoriMitra.getSelectedItem().equals("Customer")){
             if (!txtNorekMitra.getText().trim().matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Nomor Rekening hanya boleh berisi angka!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
           
             Customer cst = new Customer(Integer.parseInt(txtIdMitra.getText()),txtNamaMitra.getText(), cbBadanUsaha.getSelectedItem().toString(), cbKategoriMitra.getSelectedItem().toString(),  txtEmailMitra.getText(), txtTelpMitra.getText(), txtNorekMitra.getText());
              cst.updateMitra();
           }
           else{
               Supplier spl = new Supplier(Integer.parseInt(txtIdMitra.getText()),txtNamaMitra.getText(), cbBadanUsaha.getSelectedItem().toString(), cbKategoriMitra.getSelectedItem().toString(),  txtEmailMitra.getText(), txtTelpMitra.getText(), txtAreaMitra.getText());
              spl.updateMitra();
           }       
    }
        
     setFormMitraMode(false);
        clearForm("mitra");
         JOptionPane.showMessageDialog(this, "Mitra Berhasil disimpan", "Info", JOptionPane.INFORMATION_MESSAGE);


           tampikanMitra(txtCariMitra.getText());
    }//GEN-LAST:event_btnSimpanMitraActionPerformed

    private void txtIdProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdProdukActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdProdukActionPerformed

    private void txtTelppegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelppegawaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelppegawaiActionPerformed

    private void txtTelpMitraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelpMitraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelpMitraActionPerformed

    private void btnMitraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMitraActionPerformed
        // TODO add your handling code here:
           CardLayout ly =(CardLayout)jPanel3.getLayout();
        ly.show(jPanel3, "card4");
    }//GEN-LAST:event_btnMitraActionPerformed

    private void btnProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProdukActionPerformed
        // TODO add your handling code here:
          CardLayout ly =(CardLayout)jPanel3.getLayout();
        ly.show(jPanel3, "card3");
    }//GEN-LAST:event_btnProdukActionPerformed
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
            java.util.logging.Logger.getLogger(MasterMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MasterMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MasterMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MasterMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MasterMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCariMitra;
    private javax.swing.JButton btnCariPegawai;
    private javax.swing.JButton btnCariProduk;
    private javax.swing.JButton btnHapusMitra;
    private javax.swing.JButton btnHapusPegawai;
    private javax.swing.JButton btnHapusProduk;
    private javax.swing.JButton btnKembali1;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnMitra;
    private javax.swing.JButton btnPegawai;
    private javax.swing.JButton btnProduk;
    private javax.swing.JButton btnResetMitra;
    private javax.swing.JButton btnResetPegawai;
    private javax.swing.JButton btnResetProduk;
    private javax.swing.JButton btnSimpanMitra;
    private javax.swing.JButton btnSimpanPegawai;
    private javax.swing.JButton btnSimpanProduk;
    private javax.swing.JButton btnTambahMitra;
    private javax.swing.JButton btnTambahPegawai;
    private javax.swing.JButton btnTambahProduk;
    private javax.swing.JButton btnUbahMitra;
    private javax.swing.JButton btnUbahPegawai;
    private javax.swing.JButton btnUbahProduk;
    private javax.swing.JPanel cardMitra;
    private javax.swing.JPanel cardPegawai;
    private javax.swing.JPanel cardProduk;
    private javax.swing.JComboBox<String> cbBadanUsaha;
    private javax.swing.JComboBox<String> cbJabatanpegawai;
    private javax.swing.JComboBox<String> cbKategoriMitra;
    private javax.swing.JComboBox<String> cbKategoriProduk;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JTable tblMitra;
    private javax.swing.JTable tblPegawai;
    private javax.swing.JTable tblProduk;
    private javax.swing.JTextField txtAreaMitra;
    private javax.swing.JTextField txtCariMitra;
    private javax.swing.JTextField txtCariPegawai;
    private javax.swing.JTextField txtCariProduk;
    private javax.swing.JTextArea txtDeskripsi;
    private javax.swing.JTextField txtEmailMitra;
    private javax.swing.JTextField txtEmailpegawai;
    private javax.swing.JTextField txtIdMitra;
    private javax.swing.JTextField txtIdProduk;
    private javax.swing.JTextField txtIdpegawai;
    private javax.swing.JTextField txtKapasitas;
    private javax.swing.JTextField txtNamaMitra;
    private javax.swing.JTextField txtNamaProduk;
    private javax.swing.JTextField txtNamapegawai;
    private javax.swing.JTextField txtNorekMitra;
    private javax.swing.JTextField txtPanjang;
    private javax.swing.JPasswordField txtPasswordpegawai;
    private javax.swing.JTextField txtTelpMitra;
    private javax.swing.JTextField txtTelppegawai;
    // End of variables declaration//GEN-END:variables
}
