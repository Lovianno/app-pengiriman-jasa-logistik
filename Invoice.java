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
import java.sql.Date;

public class Invoice {
    protected String noInvoice;
    protected String noSO;
    protected String noPembayaran;
    protected Date tanggalInvoice;
    protected Date tanggalJatuhTempo;

    public Invoice(String noInvoice, String noSO, String noPembayaran, Date tanggalInvoice, Date tanggalJatuhTempo) {
        this.noInvoice = noInvoice;
        this.noSO = noSO;
        this.noPembayaran = noPembayaran;
        this.tanggalInvoice = tanggalInvoice;
        this.tanggalJatuhTempo = tanggalJatuhTempo;
    }

    public void cetakInvoice() {
        System.out.println("INVOICE");
        System.out.println("No. Invoice: " + noInvoice);
        System.out.println("No. SO: " + noSO);
        System.out.println("Tanggal: " + tanggalInvoice);
        System.out.println("Jatuh Tempo: " + tanggalJatuhTempo);
    }
    
    private static Connection conn = DatabaseConnection.getConnection();
    
       // Menampilkan data pegawai
   public static List<Invoice> getDataInvoice(String cari ) {
    List<Invoice> daftarInvoice = new ArrayList<>();
    String sql = "SELECT * FROM invoice WHERE nama LIKE ?";

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, "%" + cari + "%");  // cari bisa ada di mana saja (mengandung)
        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Invoice invoice = new Invoice(
                    rs.getString("No. Invoice"),
                    rs.getString("No. Sales Order"),
                    rs.getString("No. Pembayaran"),
                    rs.getDate("Tgl.Invoice"),
                    rs.getDate("Tgl.Jatuh Tempo")
                );
                daftarInvoice.add(invoice);
            }
        }
    } catch (SQLException e) {
        System.out.println("Gagal mengambil data invoice.");
        e.printStackTrace();
    }
    return daftarInvoice;
}
   
    public void createInvoice() {
        // logika simpan ke database nanti di sini
         String sql = "INSERT INTO invoice (no_invoice, no_so, no_pembayaran, tanggal_invoice, tanggal_jatuh_tempo) VALUES (?,?,?,?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, noInvoice);
            stmt.setString(2, noSO);
            stmt.setString(3, noPembayaran);
            stmt.setDate(4, tanggalInvoice);
            stmt.setDate(5, tanggalJatuhTempo);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Gagal menyimpan invoice.");
            e.printStackTrace();
        }
    }
    
    public void updateInvoice() {
        // logika update ke database nanti di sini
        String sql = "UPDATE invoice SET no_so = ?, no_pembayaran = ?, tanggal_invoice = ?, tanggal_jatuh_tempo = ?  WHERE no_invoice = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, noSO);
            stmt.setString(2, noPembayaran);
            stmt.setDate(3, tanggalInvoice);
            stmt.setDate(4, tanggalJatuhTempo);
            stmt.setString(5, noInvoice);

            int rowsAffected = stmt.executeUpdate();
           
        } catch (SQLException e) {
            System.out.println("Gagal memperbarui data invoice.");
            e.printStackTrace();
        }
    }

    public static void deleteInvoice(String noInvoice) {
    String sql = "DELETE FROM invoice WHERE no_invoice = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, noInvoice);
        int rowsAffected = stmt.executeUpdate();
        
        if (rowsAffected > 0) {
            System.out.println("Invoice berhasil dihapus.");
        } else {
            System.out.println("Invoice tidak ditemukan.");
        }
    } catch (SQLException e) {
        System.out.println("Gagal menghapus invoice.");
        e.printStackTrace();
    }
}
       
}

