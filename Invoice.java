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
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author LOVIANNO
 */
import java.sql.Date;

public class Invoice {
    protected String noInvoice;
    protected String noSO;
    protected Date tanggalInvoice;
    protected Date tanggalJatuhTempo;

    public Invoice(String noInvoice, String noSO, Date tanggalInvoice, Date tanggalJatuhTempo) {
        this.noInvoice = noInvoice;
        this.noSO = noSO;
        this.tanggalInvoice = tanggalInvoice;
        this.tanggalJatuhTempo = tanggalJatuhTempo;
    }

    private static Connection conn = DatabaseConnection.getConnection();
    
    // FIXED: Query dan nama kolom database
    
   
    
  public static List<Invoice> getDataInvoiceWithStatus(String cari, Integer status) {
    List<Invoice> daftar = new ArrayList<>();
    StringBuilder sql = new StringBuilder("""
        SELECT i.* 
        FROM invoice i
        JOIN sales_order so ON i.no_so = so.no_so
        WHERE i.no_invoice LIKE ?
    """);

    if (status != null) {
        sql.append(" AND so.status = ?");
    }

    sql.append(" ORDER BY i.created_at DESC"); 

    try (PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
        stmt.setString(1, "%" + cari + "%");

        if (status != null) {
            stmt.setInt(2, status);
        }

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Invoice inv = new Invoice(
                rs.getString("no_invoice"),
                rs.getString("no_so"),
                rs.getDate("tanggal_invoice"),
                rs.getDate("tanggal_jatuh_tempo")
            );
            daftar.add(inv);
        }
    } catch (SQLException e) {
        System.out.println("Gagal mengambil data invoice.");
        e.printStackTrace();
    }

    return daftar;
}


//   public static boolean isInvoiceExitstsKwitansi(String noInvoice) {
//    String sql = "SELECT COUNT(*) AS jumlah FROM kwitansi WHERE no_invoice = ?";
//    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
//        stmt.setString(1, noInvoice);
//        try (ResultSet rs = stmt.executeQuery()) {
//            if (rs.next()) {
//                int count = rs.getInt("jumlah");
//                return count > 0;
//            }
//        }
//    } catch (SQLException e) {
//        System.out.println("Gagal memeriksa no_invoice: " + e.getMessage());
//        e.printStackTrace();
//    }
//    // Jika terjadi error (atau rs.next() false), kita anggap belum ada
//    return false;
//}
  
    
    public void createInvoice() {
        // FIXED: Query INSERT hanya dibutuh 5 parameter, bukan 6
        String sql = "INSERT INTO invoice (no_invoice, no_so,  tanggal_invoice, tanggal_jatuh_tempo) VALUES (?,?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, noInvoice);
            stmt.setString(2, noSO);
            stmt.setDate(3, tanggalInvoice);
            stmt.setDate(4, tanggalJatuhTempo);
            
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
        String sql = "UPDATE invoice SET no_so = ?, tanggal_invoice = ?, tanggal_jatuh_tempo = ? WHERE no_invoice = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, noSO);
            stmt.setDate(2, tanggalInvoice);
            stmt.setDate(3, tanggalJatuhTempo);
            stmt.setString(4, noInvoice);

            int rowsAffected = stmt.executeUpdate();
           
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public static void deleteInvoice(String noInvoice) {
//        String sql = "DELETE FROM invoice WHERE no_invoice = ?";
//        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setString(1, noInvoice);
//            int rowsAffected = stmt.executeUpdate();
//            
//            if (rowsAffected > 0) {
//                System.out.println("Invoice berhasil dihapus.");
//            } else {
//                System.out.println("Invoice tidak ditemukan.");
//                throw new RuntimeException("Invoice tidak ditemukan.");
//            }
//        } catch (SQLException e) {
//            System.out.println("Gagal menghapus invoice: " + e.getMessage());
//            e.printStackTrace();
//            throw new RuntimeException("Gagal menghapus invoice: " + e.getMessage());
//        }
//    }
    
    // FIXED: Tambahkan method untuk mengecek apakah invoice sudah ada
//    public static boolean isInvoiceExists(String noInvoice) {
//        String sql = "SELECT COUNT(*) FROM invoice WHERE no_invoice = ?";
//        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setString(1, noInvoice);
//            try (ResultSet rs = stmt.executeQuery()) {
//                if (rs.next()) {
//                    return rs.getInt(1) > 0;
//                }
//            }
//        } catch (SQLException e) {
//            System.out.println("Error checking invoice existence: " + e.getMessage());
//            e.printStackTrace();
//        }
//        return false;
//    }
            @Override
        public String toString() {
            return noInvoice; 
        }
}