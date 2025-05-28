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
import static pkgfinal.project.pbo.Produk.conn;

/**
 *
 * @author LOVIANNO
 */
public abstract class Mitra {
    protected int id;
    protected String nama;
    protected String badanUsaha;
    protected String kategori;
    protected String noTelp;
    protected String email;

     public Mitra( String nama, String badanUsaha, String kategori, String noTelp, String email) {
        this.nama = nama;
        this.badanUsaha = badanUsaha;
        this.kategori = kategori;
        this.noTelp = noTelp;
        this.email = email;
    }
    
    public Mitra(int id, String nama, String badanUsaha, String kategori, String noTelp, String email) {
        this.id = id;
        this.nama = nama;
        this.badanUsaha = badanUsaha;
        this.kategori = kategori;
        this.noTelp = noTelp;
        this.email = email;
    }
     protected static Connection conn = DatabaseConnection.getConnection();

    public abstract void detailMitra();
    public abstract void createMitra();
    public abstract void updateMitra();
    
    
    public static List<Mitra> getDataMitra(String cari) {
    List<Mitra> daftarMitra = new ArrayList<>();
    String sql = "SELECT * FROM mitra WHERE nama LIKE ? ORDER BY kategori";


    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, "%" + cari + "%");

        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String kategori = rs.getString("kategori");
                Mitra mitra = null;

                switch (kategori) {
                    case "Customer":
                        mitra = new Customer(
                            rs.getInt("id"),
                            rs.getString("nama"),
                            rs.getString("badan_usaha"),
                            kategori,
                            rs.getString("no_telp"),
                            rs.getString("email"),
                            rs.getString("nomor_rekening")
                        );
                        break;
                    case "Supplier":
                        mitra = new Supplier(
                           rs.getInt("id"),
                            rs.getString("nama"),
                                                            rs.getString("badan_usaha"),

                            kategori,
                            rs.getString("no_telp"),
                            rs.getString("email"),
                            rs.getString("jangkauan_area")
                        );
                        break;
                    // Tambah kategori lain jika perlu
                }

                if (mitra != null) {
                    daftarMitra.add(mitra);
                }
            }
        }
    } catch (SQLException e) {
        System.out.println("Gagal mengambil data mitra.");
        e.printStackTrace();
    }
    
    return daftarMitra;
}
    public static void deleteMitra(int idMitra){
       String sql = "DELETE FROM mitra WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, idMitra);
            int rowsAffected = stmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    };
}
