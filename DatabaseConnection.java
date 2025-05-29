/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgfinal.project.pbo;
import java.sql.*;
/**
 *
 * @author LOVIANNO
 */
public class DatabaseConnection {
     private static final String URL = "jdbc:mysql://localhost:3309/sba-logistik"; // Ubah sesuai database Anda
    private static final String USER = "root"; // Username MySQL Anda
    private static final String PASSWORD = ""; // Kosongkan jika tidak ada password

    // Method untuk mendapatkan koneksi ke database
    public static Connection getConnection() {
        Connection con = null;
        try {
            // Load driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Membuat koneksi ke database
            con = DriverManager.getConnection(URL, USER, PASSWORD);
//            System.out.println("Koneksi ke database berhasil!");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver JDBC tidak ditemukan! Pastikan sudah ditambahkan.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Koneksi ke database gagal! Periksa konfigurasi koneksi.");
            e.printStackTrace();
        }
        return con;
    }
}
