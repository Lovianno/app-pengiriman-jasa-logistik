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

    // Mengambil daftar kwitansi berdasarkan nomor invoice
    public static List<Kwitansi> getDataKwitansi(String cariInvoice) {
        List<Kwitansi> daftar = new ArrayList<>();
        String sql = "SELECT * FROM kwitansi WHERE no_kwitansi LIKE ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + cariInvoice + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Kwitansi k = new Kwitansi(
                        rs.getString("no_kwitansi"),
                        rs.getString("no_invoice"),
                        rs.getString("metode_bayar"),
                        rs.getDate("tanggal"),
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
        String sql = "UPDATE kwitansi SET no_invoice = ?, metode_bayar = ?, tanggal_kwitansi = ?, catatan = ?, total_bayar = ? WHERE no_kwitansi = ?";
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

    // Hapus kwitansi
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

    // Cetak kwitansi
    public void cetakKwitansi() {
        System.out.println("===== KWITANSI =====");
        System.out.println("No. Kwitansi   : " + noKwitansi);
        System.out.println("Tanggal        : " + tanggal);
        System.out.println("Metode Bayar   : " + metodeBayar);
        System.out.println("No. Invoice    : " + noInvoice);
        System.out.println("Catatan        : " + catatan);
        System.out.println("Total Bayar    : Rp " + totalBayar);
        System.out.println("====================");
    }
}
