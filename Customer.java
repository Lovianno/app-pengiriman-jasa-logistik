/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgfinal.project.pbo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static pkgfinal.project.pbo.Produk.conn;

/**
 *
 * @author LOVIANNO
 */
public class Customer extends Mitra {
    private String nomorRekening;

    public Customer(String nama, String badanUsaha, String kategori,  String email,String noTelp, String nomorRekening) {
        super(nama, badanUsaha, kategori,  email, noTelp);
        this.nomorRekening = nomorRekening;
    }

    
    public Customer(int id, String nama, String badanUsaha, String kategori, String email,String noTelp,  String nomorRekening) {
        super(id, nama, badanUsaha, kategori, email, noTelp);
        this.nomorRekening = nomorRekening;
    }
    
     public String getNorek(){
         return nomorRekening;
     }
    
    
    @Override
    public void detailMitra() {
        System.out.println("SUPPLIER: " + nama + " | Area: " + nomorRekening);
    }
     @Override
    public void createMitra() {
        String sql = "INSERT INTO mitra (id, nama, badan_usaha, kategori,  email,no_telp, nomor_rekening) VALUES (?,?,?,?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.setString(2, nama);
            stmt.setString(3, badanUsaha);
            stmt.setString(4, kategori);
            stmt.setString(5, email);
            stmt.setString(6, noTelp);
            stmt.setString(7, nomorRekening);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     @Override
    public void updateMitra() {
         String sql = "UPDATE mitra SET nama = ?, badan_usaha = ?, kategori = ?,  email = ?, no_telp = ?,nomor_rekening = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nama);
            stmt.setString(2, badanUsaha);

            stmt.setString(3, kategori);
                        stmt.setString(4, email);

            stmt.setString(5, noTelp);
           stmt.setString(6, nomorRekening);

            stmt.setInt(7, id);

            int rowsAffected = stmt.executeUpdate();
           
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static List<Customer> getDataCustomerById(int idCust ){
        List<Customer> daftarCustomer = new ArrayList<>();
    String sql = "SELECT * FROM mitra WHERE id = ?";

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, idCust);  // cari bisa ada di mana saja (mengandung)
        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Customer cust = new Customer(
                     rs.getInt("id"),
                            rs.getString("nama"),
                            rs.getString("badan_usaha"),
                            rs.getString("kategori"),
                            rs.getString("email"),
                            rs.getString("no_telp"),
                            rs.getString("nomor_rekening")
                );
                daftarCustomer.add(cust);
            }
        }
    } catch (SQLException e) {
        System.out.println("Gagal mengambil data cust.");
        e.printStackTrace();
    }
    return daftarCustomer;
    }
    
    
}

