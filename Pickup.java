/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgfinal.project.pbo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author LOVIANNO
 */
public class Pickup extends Produk {
    private String panjangBak;

    public Pickup( String nama, String kategori, String deskripsi, String panjangBak) {
        super(nama, kategori, deskripsi);
        this.panjangBak = panjangBak;
    }
    
    public Pickup(int id, String nama, String kategori, String deskripsi, String panjangBak) {
        super(id, nama, kategori, deskripsi);
        this.panjangBak = panjangBak;
    }

    
    @Override
    public void createProduk() {
       String sql = "INSERT INTO produk (id, nama, kategori, deskripsi, panjang_bak) VALUES (?,?,?,?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.setString(2, nama);
            stmt.setString(3, kategori);
            stmt.setString(4, deskripsi);
            stmt.setString(5, panjangBak);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
    }
    }
    @Override
     public void updateProduk() {
       String sql = "UPDATE produk SET nama = ?, kategori = ?, deskripsi = ?, panjang_bak = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nama);
            stmt.setString(2, kategori);
            stmt.setString(3, deskripsi);
            stmt.setString(4, panjangBak);
            stmt.setInt(5, id);

            int rowsAffected = stmt.executeUpdate();
           
        } catch (SQLException e) {
            e.printStackTrace();
    }
    }
      
     public String getPanjangBak(){
         return panjangBak;
}

}

