/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgfinal.project.pbo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LOVIANNO
 */
public abstract class Produk {
    protected int id;
    protected String nama;
    protected String kategori;
    protected String deskripsi;

     public Produk( String nama, String kategori, String deskripsi) {
        
        this.nama = nama;
        this.kategori = kategori;
        this.deskripsi = deskripsi;
    }
    
    public Produk(int id, String nama, String kategori, String deskripsi) {
        this.id = id;
        this.nama = nama;
        this.kategori = kategori;
        this.deskripsi = deskripsi;
    }
    
     private static Connection conn = DatabaseConnection.getConnection();
    
    public abstract void detailProduk();
    public abstract void createProduk();
    public abstract void updateProduk();
    public void deleteProduk(){
        System.out.println("hapus");
    };


    public static List<Produk> getDataProduk(String cari) {
         List<Produk> daftarProduk = new ArrayList<>();
//    String sql = "SELECT * FROM produk WHERE nama LIKE ?";
//
//    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
//        pstmt.setString(1, "%" + cari + "%");  // cari bisa ada di mana saja (mengandung)
//        try (ResultSet rs = pstmt.executeQuery()) {
//            while (rs.next()) {
//                Produk produk = new Produk(
//                    rs.getInt("id"),
//                    rs.getString("nama"),
//                    rs.getString("jabatan"),
//                    rs.getString("no_telp"),
//                    rs.getString("email"),
//                    rs.getString("password")
//                );
//                daftarProduk.add(produk);
//            }
//        }
//    } catch (SQLException e) {
//        System.out.println("Gagal mengambil data pegawai.");
//        e.printStackTrace();
//    }
    return daftarProduk      ;
    }
}

