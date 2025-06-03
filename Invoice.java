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
    
    // FIXED: Query dan nama kolom database
    public static List<Invoice> getDataInvoice(String cari) {
        List<Invoice> daftarInvoice = new ArrayList<>();
        // FIXED: Query seharusnya mencari berdasarkan no_invoice atau kolom yang relevan, bukan 'nama'
        String sql = "SELECT * FROM invoice WHERE no_invoice LIKE ? OR no_so LIKE ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + cari + "%");
            pstmt.setString(2, "%" + cari + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Invoice invoice = new Invoice(
                        // FIXED: Nama kolom database harus sesuai dengan yang ada di database
                        rs.getString("no_invoice"),         // bukan "No. Invoice"
                        rs.getString("no_so"),              // bukan "No. Sales Order"
                        rs.getString("no_pembayaran"),      // bukan "No. Pembayaran"
                        rs.getDate("tanggal_invoice"),      // bukan "Tgl.Invoice"
                        rs.getDate("tanggal_jatuh_tempo")   // bukan "Tgl.Jatuh Tempo"
                    );
                    daftarInvoice.add(invoice);
                }
            }
        } catch (SQLException e) {
            System.out.println("Gagal mengambil data invoice: " + e.getMessage());
            e.printStackTrace();
        }
        return daftarInvoice;
    }
   
    public void createInvoice() {
        // FIXED: Query INSERT hanya butuh 5 parameter, bukan 6
        String sql = "INSERT INTO invoice (no_invoice, no_so, no_pembayaran, tanggal_invoice, tanggal_jatuh_tempo) VALUES (?,?,?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, noInvoice);
            stmt.setString(2, noSO);
            stmt.setString(3, noPembayaran);
            stmt.setDate(4, tanggalInvoice);
            stmt.setDate(5, tanggalJatuhTempo);
            
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Invoice berhasil disimpan.");
            }
        } catch (SQLException e) {
            System.out.println("Gagal menyimpan invoice: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Gagal menyimpan invoice: " + e.getMessage());
        }
    }
    
    public void updateInvoice() {
        String sql = "UPDATE invoice SET no_so = ?, no_pembayaran = ?, tanggal_invoice = ?, tanggal_jatuh_tempo = ? WHERE no_invoice = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, noSO);
            stmt.setString(2, noPembayaran);
            stmt.setDate(3, tanggalInvoice);
            stmt.setDate(4, tanggalJatuhTempo);
            stmt.setString(5, noInvoice);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Invoice berhasil diperbarui.");
            } else {
                System.out.println("Invoice tidak ditemukan untuk diperbarui.");
            }
        } catch (SQLException e) {
            System.out.println("Gagal memperbarui data invoice: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Gagal memperbarui invoice: " + e.getMessage());
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
                throw new RuntimeException("Invoice tidak ditemukan.");
            }
        } catch (SQLException e) {
            System.out.println("Gagal menghapus invoice: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Gagal menghapus invoice: " + e.getMessage());
        }
    }
    
    // FIXED: Tambahkan method untuk mengecek apakah invoice sudah ada
    public static boolean isInvoiceExists(String noInvoice) {
        String sql = "SELECT COUNT(*) FROM invoice WHERE no_invoice = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, noInvoice);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error checking invoice existence: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}