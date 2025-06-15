package pkgfinal.project.pbo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Kwitansi {
    protected String noKwitansi;
    protected String noInvoice;
    protected String metodeBayar;
    protected java.util.Date tanggal;
    protected String catatan;
    protected long totalBayar;

    private static Connection conn = DatabaseConnection.getConnection();

    // Constructor
    public Kwitansi(String noKwitansi, String noInvoice, String metodeBayar, java.util.Date tanggal, String catatan, long totalBayar) {
        this.noKwitansi = noKwitansi;
        this.noInvoice = noInvoice;
        this.metodeBayar = metodeBayar;
        this.tanggal = tanggal;
        this.catatan = catatan;
        this.totalBayar = totalBayar;
    }

    // Menyimpan kwitansi ke database
    public void createKwitansi() {
        String sql = "INSERT INTO kwitansi (no_kwitansi, no_invoice, metode_bayar, tanggal, catatan, total_bayar) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, noKwitansi);
            stmt.setString(2, noInvoice);
            stmt.setString(3, metodeBayar);
            stmt.setDate(4, new Date(tanggal.getTime()));
            stmt.setString(5, catatan);
            stmt.setLong(6, totalBayar);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Gagal menyimpan kwitansi.");
            e.printStackTrace();
        }
    }

    
    /**
 * Cek apakah sebuah no_invoice sudah terdaftar di tabel invoice.
 *
 * @param noInvoice  nomor invoice yang akan dicek
 * @return true  jika no_invoice sudah ada (duplicate),
 *         false jika no_invoice belum ada
 */


    // Mengambil daftar kwitansi berdasarkan nomor invoice
   public static List<Kwitansi> getDataKwitansi(String cari, Integer status) {
    List<Kwitansi> daftar = new ArrayList<>();

    // Susun SQL dasar: JOIN kwitansi -> invoice -> sales_order
    StringBuilder sql = new StringBuilder("""
        SELECT k.* 
        FROM kwitansi k
        JOIN invoice i ON k.no_invoice = i.no_invoice
        JOIN sales_order so ON i.no_so = so.no_so
        WHERE k.no_kwitansi LIKE ?
    """);

    // Jika status tidak null, tambahkan filter
    if (status != null) {
        sql.append(" AND so.status = ?");
    }

    // Kita urutkan hasil berdasarkan tanggal pembuatan kwitansi terbaru
    sql.append(" ORDER BY k.created_at DESC");

    try (PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
        // Parameter pertama: pencarian no_kwitansi
        stmt.setString(1, "%" + cari + "%");

        // Jika ada filter status, set parameter kedua
        if (status != null) {
            stmt.setInt(2, status);
        }

        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Kwitansi k = new Kwitansi(
                    rs.getString("no_kwitansi"),
                    rs.getString("no_invoice"),
                    rs.getString("metode_bayar"),
                    rs.getDate("tanggal"),       // kolom di DB diasumsikan bernama "tanggal"
                    rs.getString("catatan"),
                    rs.getLong("total_bayar")
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
    
    
   
    // Update kwitansi
    public void updateKwitansi() {
        String sql = "UPDATE kwitansi SET no_invoice = ?, metode_bayar = ?, tanggal = ?, catatan = ?, total_bayar = ? WHERE no_kwitansi = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, noInvoice);
            stmt.setString(2, metodeBayar);
            stmt.setDate(3, new Date(tanggal.getTime()));
            stmt.setString(4, catatan);
            stmt.setLong(5, totalBayar);
            stmt.setString(6, noKwitansi);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Gagal memperbarui kwitansi.");
            e.printStackTrace();
        }
    }
public static boolean isInvoiceExitstsKwitansi(String noInvoice) {
    String sql = "SELECT COUNT(*) AS jumlah FROM kwitansi WHERE no_invoice = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, noInvoice);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                int count = rs.getInt("jumlah");
                return count > 0;
            }
        }
    } catch (SQLException e) {
        System.out.println("Gagal memeriksa no_invoice: " + e.getMessage());
        e.printStackTrace();
    }
    // Jika terjadi error (atau rs.next() false), kita anggap belum ada
    return false;
}
    // Hapus kwitansi
//    public static void deleteKwitansi(String noKwitansi) {
//        String sql = "DELETE FROM kwitansi WHERE no_kwitansi = ?";
//        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setString(1, noKwitansi);
//            stmt.executeUpdate();
//        } catch (SQLException e) {
//            System.out.println("Gagal menghapus kwitansi.");
//            e.printStackTrace();
//        }
//    }

   
}
