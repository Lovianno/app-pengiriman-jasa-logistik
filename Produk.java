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
    
     protected static Connection conn = DatabaseConnection.getConnection();
    
    public abstract void createProduk();
    public abstract void updateProduk();
    


   public static List<Produk> getDataProduk(String cari) {
    List<Produk> daftarProduk = new ArrayList<>();
    String sql = "SELECT * FROM produk WHERE nama LIKE ? ORDER BY kategori";


    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, "%" + cari + "%");

        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String kategori = rs.getString("kategori");
                Produk produk = null;

                switch (kategori) {
                    case "Truk":
                        produk = new Truk(
                            rs.getInt("id"),
                            rs.getString("nama"),
                            kategori,
                            rs.getString("deskripsi"),
                            rs.getString("kapasitas_maksimal")
                        );
                        break;
                    case "Pickup":
                        produk = new Pickup(
                            rs.getInt("id"),
                            rs.getString("nama"),
                            kategori,
                            rs.getString("deskripsi"),
                            rs.getString("panjang_bak")
                        );
                        break;
                    // Tambah kategori lain jika perlu
                }

                if (produk != null) {
                    daftarProduk.add(produk);
                }
            }
        }
    } catch (SQLException e) {
        System.out.println("Gagal mengambil data produk.");
        e.printStackTrace();
    }
    
    return daftarProduk;
}
   
   public static void deleteProduk(int idProduk){
         String sql = "DELETE FROM produk WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, idProduk);
            int rowsAffected = stmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    };
   
    @Override
    public String toString() {
        return nama; // ini yang akan tampil di JComboBox
    }

}

