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
public class Truk extends Produk {
    private String kapasitasMaksimal;

    
     public Truk(String nama, String kategori, String deskripsi, String kapasitasMaksimal) {
        super(nama, kategori, deskripsi);
        this.kapasitasMaksimal = kapasitasMaksimal;
    }
    
    public Truk(int id, String nama, String kategori, String deskripsi, String kapasitasMaksimal) {
        super(id, nama, kategori, deskripsi);
        this.kapasitasMaksimal = kapasitasMaksimal;
    }

    
    @Override
    public void createProduk() {
       String sql = "INSERT INTO produk (id, nama, kategori, deskripsi, kapasitas_maksimal) VALUES (?,?,?,?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.setString(2, nama);
            stmt.setString(3, kategori);
            stmt.setString(4, deskripsi);
            stmt.setString(5, kapasitasMaksimal);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
    }
    }
    @Override
     public void updateProduk() {
       String sql = "UPDATE produk SET nama = ?, kategori = ?, deskripsi = ?, kapasitas_maksimal = ?, panjang_bak = NULL WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nama);
            stmt.setString(2, kategori);
            stmt.setString(3, deskripsi);
            stmt.setString(4, kapasitasMaksimal);
            stmt.setInt(5, id);

            int rowsAffected = stmt.executeUpdate();
           
        } catch (SQLException e) {
            e.printStackTrace();
    }
}
     public String getKapasitasMaksimal(){
         return kapasitasMaksimal;
     }
}