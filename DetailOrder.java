/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgfinal.project.pbo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.Date;
/**
 *
 * @author LOVIANNO
 */
public class DetailOrder {
    protected int id;
    protected String noSO;
    protected int produkId;
    protected String deskripsi;
    protected int kuantitas;
    protected long hargaBeli;
    protected long hargaJual;
    protected String namaProduk;
    private static Connection conn = DatabaseConnection.getConnection();

    
    public DetailOrder(String noSO, int produkId, String deskripsi, int kuantitas, long hargaBeli, long hargaJual) {
        this.id = id;
        this.noSO = noSO;
        this.produkId = produkId;
        this.deskripsi = deskripsi;
        this.kuantitas = kuantitas;
        this.hargaBeli = hargaBeli;
        this.hargaJual = hargaJual;
    }
    public DetailOrder(int id, String noSO, int produkId, String deskripsi, int kuantitas, long hargaBeli, long hargaJual) {
        this.id = id;
        this.noSO = noSO;
        this.produkId = produkId;
        this.deskripsi = deskripsi;
        this.kuantitas = kuantitas;
        this.hargaBeli = hargaBeli;
        this.hargaJual = hargaJual;
    }

    public void createDetailSO() {
          String sql = "INSERT INTO detail_sales_order(no_so, produk_id,deskripsi, kuantitas, harga_beli, harga_jual) VALUES (?,?,?,?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, noSO);
            stmt.setInt(2, produkId);
            stmt.setString(3, deskripsi);
            stmt.setInt(4, kuantitas);
            stmt.setLong(5, hargaBeli);
            stmt.setLong(6, hargaJual);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static List<DetailOrder> getDetailJoinByNoSO(String noSO) {
    List<DetailOrder> daftarDetail = new ArrayList<>();
    String sql = "SELECT dso.*, p.nama AS nama_produk " +
                 "FROM detail_sales_order dso " +
                 "JOIN produk p ON dso.produk_id = p.id " +
                 "WHERE dso.no_so = ?";

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, noSO);

        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                DetailOrder detail = new DetailOrder(
                    rs.getInt("id"),
                    rs.getString("no_so"),
                    rs.getInt("produk_id"),
                    rs.getString("deskripsi"),
                    rs.getInt("kuantitas"),
                    rs.getLong("harga_beli"),
                    rs.getLong("harga_jual")
                );
                detail.namaProduk = rs.getString("nama_produk");

                daftarDetail.add(detail);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return daftarDetail;
}

}

