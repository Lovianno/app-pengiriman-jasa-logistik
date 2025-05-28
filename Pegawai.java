/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgfinal.project.pbo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LOVIANNO
 */
public class Pegawai {
    protected int id;
    protected String nama;
    protected String jabatan;
    protected String no_telp;
    protected String email;
    protected String password;

    // Constructor
    public Pegawai( String nama, String jabatan, String no_telp, String email, String password) {
        this.id = id;
        this.nama = nama;
        this.jabatan = jabatan;
        this.no_telp = no_telp;
        this.email = email;
        this.password = password;
    }
    
    public Pegawai(int id, String nama, String jabatan, String no_telp, String email, String password) {
        this.id = id;
        this.nama = nama;
        this.jabatan = jabatan;
        this.no_telp = no_telp;
        this.email = email;
        this.password = password;
    }
    

    // Constructor tanpa ID (saat buat data baru)
//    public Pegawai(String nama, String jabatan, String no_telp, String email, String password) {
//        this.nama = nama;
//        this.jabatan = jabatan;
//        this.no_telp = no_telp;
//        this.email = email;
//        this.password = password;
//    }
    
        private static Connection conn = DatabaseConnection.getConnection();


    // Menampilkan data pegawai
   public static List<Pegawai> getDataPegawai(String cari ) {
    List<Pegawai> daftarPegawai = new ArrayList<>();
    String sql = "SELECT * FROM pegawai WHERE nama LIKE ?";

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, "%" + cari + "%");  // cari bisa ada di mana saja (mengandung)
        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Pegawai pegawai = new Pegawai(
                    rs.getInt("id"),
                    rs.getString("nama"),
                    rs.getString("jabatan"),
                    rs.getString("no_telp"),
                    rs.getString("email"),
                    rs.getString("password")
                );
                daftarPegawai.add(pegawai);
            }
        }
    } catch (SQLException e) {
        System.out.println("Gagal mengambil data pegawai.");
        e.printStackTrace();
    }
    return daftarPegawai;
}

    public void createPegawai() {
        // logika simpan ke database nanti di sini
         String sql = "INSERT INTO pegawai (id, nama, jabatan, no_telp, email, password) VALUES (?,?,?,?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.setString(2, nama);
            stmt.setString(3, jabatan);
            stmt.setString(4, no_telp);
            stmt.setString(5, email);
            stmt.setString(6, password);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Gagal menyimpan pegawai.");
            e.printStackTrace();
        }
    }

    public void updatePegawai() {
        // logika update ke database nanti di sini
        String sql = "UPDATE pegawai SET nama = ?, jabatan = ?, no_telp = ?, email = ?, password = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nama);
            stmt.setString(2, jabatan);
            stmt.setString(3, no_telp);
            stmt.setString(4, email);
            stmt.setString(5, password);
            stmt.setInt(6, id);

            int rowsAffected = stmt.executeUpdate();
           
        } catch (SQLException e) {
            System.out.println("Gagal memperbarui data pegawai.");
            e.printStackTrace();
        }
    }

    public static void deletePegawai(int idPegawai) {
        // logika hapus dari database nanti di sini
         String sql = "DELETE FROM pegawai WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPegawai);
            int rowsAffected = stmt.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Gagal menghapus kendaraan.");
            e.printStackTrace();
        }
    }
    
    public static void login(String email, String password){
        
        
        String sql = "SELECT * FROM pegawai WHERE email = ? AND password = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
    stmt.setString(1, email);
    stmt.setString(2, password);

    try (ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {
            // TODO: buka jendela utama aplikasi
            
        } else {
            
        }
    }
} catch (SQLException ex) {
    ex.printStackTrace();
}
    }
}

