/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgfinal.project.pbo;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author LOVIANNO
 */
public class Supplier extends Mitra {
    private String jangkauanArea;
    
    public Supplier( String nama, String badanUsaha, String kategori, String noTelp, String email, String jangkauanArea) {
        super( nama, badanUsaha, kategori, noTelp, email);
        this.jangkauanArea = jangkauanArea;
    }
    
    public Supplier(int id, String nama, String badanUsaha, String kategori, String noTelp, String email, String jangkauanArea) {
        super(id, nama, badanUsaha, kategori, noTelp, email);
        this.jangkauanArea = jangkauanArea;
    }
    
    public String getJangkauan(){
         return jangkauanArea;
     }
    
    @Override
    public void detailMitra() {
        System.out.println("SUPPLIER: " + nama + " | Area: " + jangkauanArea);
    }
     @Override
    public void createMitra() {
         String sql = "INSERT INTO mitra (id, nama, badan_usaha, kategori, email,no_telp, jangkauan_area) VALUES (?,?,?,?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.setString(2, nama);
            stmt.setString(3, badanUsaha);
            stmt.setString(4, kategori);
            stmt.setString(5, noTelp);
            stmt.setString(6, email);
            stmt.setString(7, jangkauanArea);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     @Override
    public void updateMitra() {
        String sql = "UPDATE mitra SET nama = ?, badan_usaha = ?, kategori = ?, no_telp = ?, email = ?, jangkauan_area = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nama);
            stmt.setString(2, badanUsaha);

            stmt.setString(3, kategori);
            stmt.setString(4, noTelp);
            stmt.setString(5, email);
            stmt.setString(6, jangkauanArea);

            stmt.setInt(7, id);

            int rowsAffected = stmt.executeUpdate();
           
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

