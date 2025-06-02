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
import java.util.Date;

public class Kwitansi {
    protected String noKwitansi;
    protected String noInvoice;
    protected String namaPelanggan;
    protected Date tanggalKwitansi;
    protected double jumlah;
    protected String keterangan;

    private static Connection conn = DatabaseConnection.getConnection();

    // Constructor
    public Kwitansi(String noKwitansi, String noInvoice, String namaPelanggan, Date tanggalKwitansi, double jumlah, String keterangan) {
        this.noKwitansi = noKwitansi;
        this.noInvoice = noInvoice;
        this.namaPelanggan = namaPelanggan;
        this.tanggalKwitansi = tanggalKwitansi;
        this.jumlah = jumlah;
        this.keterangan = keterangan;
    }

    // Untuk menyimpan kwitansi ke database
    public void createKwitansi() {
        String sql = "INSERT INTO kwitansi (no_kwitansi, no_invoice, nama_pelanggan, tanggal_kwitansi, jumlah, keterangan) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, noKwitansi);
            stmt.setString(2, noInvoice);
            stmt.setString(3, namaPelanggan);
            stmt.setDate(4, (java.sql.Date) tanggalKwitansi);
            stmt.setDouble(5, jumlah);
            stmt.setString(6, keterangan);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Gagal menyimpan kwitansi.");
            e.printStackTrace();
        }
    }

    // Untuk mengambil daftar kwitansi berdasarkan nama pelanggan
    public static List<Kwitansi> getDataKwitansi(String cari) {
        List<Kwitansi> daftar = new ArrayList<>();
        String sql = "SELECT * FROM kwitansi WHERE nama_pelanggan LIKE ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + cari + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Kwitansi k = new Kwitansi(
                        rs.getString("no_kwitansi"),
                        rs.getString("no_invoice"),
                        rs.getString("nama_pelanggan"),
                        rs.getDate("tanggal_kwitansi"),
                        rs.getDouble("jumlah"),
                        rs.getString("keterangan")
                    );
                    daftar.add(k);
                }
            }
        } catch (SQLException e) {
            System.out.println("Gagal mengambil data kwitansi.");
            e.printStackTrace();
        }

        return daftar;
    }

    // Untuk mengupdate kwitansi
    public void updateKwitansi() {
        String sql = "UPDATE kwitansi SET no_invoice = ?, nama_pelanggan = ?, tanggal_kwitansi = ?, jumlah = ?, keterangan = ? WHERE no_kwitansi = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, noInvoice);
            stmt.setString(2, namaPelanggan);
            stmt.setDate(3, (java.sql.Date) tanggalKwitansi);
            stmt.setDouble(4, jumlah);
            stmt.setString(5, keterangan);
            stmt.setString(6, noKwitansi);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Gagal memperbarui kwitansi.");
            e.printStackTrace();
        }
    }

    // Untuk menghapus kwitansi
    public static void deleteKwitansi(String noKwitansi) {
        String sql = "DELETE FROM kwitansi WHERE no_kwitansi = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, noKwitansi);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Gagal menghapus kwitansi.");
            e.printStackTrace();
        }
    }

    // Untuk mencetak kwitansi
    public void cetakKwitansi() {
        System.out.println("===== KWITANSI =====");
        System.out.println("No. Kwitansi   : " + noKwitansi);
        System.out.println("Tanggal        : " + tanggalKwitansi);
        System.out.println("Metode Bayar   : " + keterangan);
        System.out.println("No. Invoice    : " + noInvoice);
        System.out.println("Nama Pelanggan : " + namaPelanggan);
        System.out.println("Jumlah         : Rp " + jumlah);
        System.out.println("====================");
    }
}
