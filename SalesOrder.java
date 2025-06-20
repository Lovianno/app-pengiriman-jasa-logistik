/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgfinal.project.pbo;

/**
 *
 * @author LOVIANNO
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.Random;


public class SalesOrder {
    protected String noSO;
    protected Date tanggal;
    protected String dikirimDari;
    protected String tujuan;
    protected int customerId;
    protected int supplierId;
    protected int pegawaiId;
    protected int status;
    protected long hargaTotal;
    
     private static Connection conn = DatabaseConnection.getConnection();

     private List<Customer> customers;
    private List<Supplier> suppliers;
    private List<Pegawai> pegawais;
     private List<DetailOrder> detailOrders;
     
     
    public SalesOrder(String noSO, Date tanggal, String dikirimDari, String tujuan, int customerId,
                      int supplierId, int pegawaiId, long hargaTotal, int status ) {
        this.noSO = noSO;
        this.tanggal = tanggal;
        this.dikirimDari = dikirimDari;
        this.tujuan = tujuan;
        this.customerId = customerId;
        this.supplierId = supplierId;
        this.pegawaiId = pegawaiId;
        this.status = status;
        this.hargaTotal = hargaTotal;
    }
    
    public List<Customer> getCustomers() {
    return customers;
    }
    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public List<Supplier> getSuppliers() {
        return suppliers;
    }
    public void setSuppliers(List<Supplier> suppliers) {
        this.suppliers = suppliers;
    }

    public List<Pegawai> getPegawais() {
        return pegawais;
    }
    public void setPegawais(List<Pegawai> pegawais) {
        this.pegawais = pegawais;
    }

    public List<DetailOrder> getDetailOrders() {
        return detailOrders;
    }
     public void setDetailOrders(List<DetailOrder> detailOrders) {
        this.detailOrders = detailOrders;
    }
   public static List<SalesOrder> getDataSalesOrderJoin(String cari) {
    List<SalesOrder> daftarSO = new ArrayList<>();
    String sql = "SELECT so.*, c.id AS customer_id, c.nama AS nama_customer, " +
                 "s.id AS supplier_id, s.nama AS nama_supplier, " +
                 "p.id AS pegawai_id, p.nama AS nama_pegawai " +
                 "FROM sales_order so " +
                 "JOIN mitra c ON so.customer_id = c.id " +
                 "JOIN mitra s ON so.supplier_id = s.id " +
                 "JOIN pegawai p ON so.pegawai_id = p.id " +
                 "WHERE so.no_so LIKE ? OR so.tujuan LIKE ? OR so.dikirim_dari LIKE ? " +
                 "ORDER BY so.created_at DESC";

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        String keyword = "%" + cari + "%";
        pstmt.setString(1, keyword);
        pstmt.setString(2, keyword);
        pstmt.setString(3, keyword);

        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                SalesOrder so = new SalesOrder(
                    rs.getString("no_so"),
                    rs.getDate("tanggal"),
                    rs.getString("dikirim_dari"),
                    rs.getString("tujuan"),
                    rs.getInt("customer_id"),
                    rs.getInt("supplier_id"),
                    rs.getInt("pegawai_id"),
                    rs.getLong("harga_total"),
                    rs.getInt("status")
                );

                // Buat List satuan untuk masing-masing relasi (agar konsisten List)
                List<Customer> custList = Customer.getDataCustomerById(so.customerId);  
                so.setCustomers(custList);
                List<Supplier> suppList = Supplier.getDataSupplierById(so.supplierId);  
                 so.setSuppliers(suppList);
               List<Pegawai> pegList = Pegawai.getDataPegawaiById(so.pegawaiId);  
                 so.setPegawais(pegList);

                daftarSO.add(so);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return daftarSO;
}

   
   public static List<SalesOrder> getSalesOrderToLaporan(Date fromDate, Date toDate, Integer status) {
    List<SalesOrder> daftarSO = new ArrayList<>();
    StringBuilder sql = new StringBuilder()
        .append("SELECT so.*, ")
        .append("       c.nama AS nama_customer, ")
        .append("       s.nama AS nama_supplier, ")
        .append("       p.nama AS nama_pegawai ")
        .append("  FROM sales_order so ")
        .append("  JOIN mitra c   ON so.customer_id = c.id ")
        .append("  JOIN mitra s   ON so.supplier_id = s.id ")
        .append("  JOIN pegawai p ON so.pegawai_id  = p.id ")
        .append(" WHERE so.tanggal BETWEEN ? AND ? ");

    if (status != null) {
        sql.append(" AND so.status = ? ");
    }

    sql.append(" ORDER BY so.tanggal DESC, so.created_at DESC");

    try (PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
        int idx = 1;
        pstmt.setDate(idx++, fromDate);
        pstmt.setDate(idx++, toDate);
        if (status != null) {
            pstmt.setInt(idx++, status);
        }

        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                SalesOrder so = new SalesOrder(
                    rs.getString("no_so"),
                    rs.getDate("tanggal"),
                    rs.getString("dikirim_dari"),
                    rs.getString("tujuan"),
                    rs.getInt("customer_id"),
                    rs.getInt("supplier_id"),
                    rs.getInt("pegawai_id"),
                    rs.getLong("harga_total"),
                    rs.getInt("status")
                );
               List<Customer> custList = Customer.getDataCustomerById(so.customerId);  
                so.setCustomers(custList);
                List<Supplier> suppList = Supplier.getDataSupplierById(so.supplierId);  
                 so.setSuppliers(suppList);
             

                // muat detail‐order
                List<DetailOrder> dets = DetailOrder.getDetailJoinByNoSO(so.noSO);
                so.setDetailOrders(dets);

                daftarSO.add(so);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return daftarSO;
}

   
// untuk combobox
public static List<SalesOrder> getDataSalesOrderByStatus(Integer status) {
    List<SalesOrder> daftarSO = new ArrayList<>();
    StringBuilder sql = new StringBuilder("SELECT * FROM sales_order");

    if (status != null) {
        sql.append(" WHERE status = ?");
    }

    sql.append(" ORDER BY tanggal DESC");

    try (PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
        if (status != null) {
            pstmt.setInt(1, status);
        }

        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                SalesOrder so = new SalesOrder(
                    rs.getString("no_so"),
                    rs.getDate("tanggal"),
                    rs.getString("dikirim_dari"),
                    rs.getString("tujuan"),
                    rs.getInt("customer_id"),
                    rs.getInt("supplier_id"),
                    rs.getInt("pegawai_id"),
                    rs.getLong("harga_total"),
                    rs.getInt("status")
                );
                daftarSO.add(so);
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return daftarSO;
}



    public static String generateNoSO(int length){
    String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    StringBuilder result = new StringBuilder();
    Random random = new Random();

    for (int i = 0; i < length; i++) {
        int index = random.nextInt(characters.length());
        result.append(characters.charAt(index));
    }
   
    
    return "SO-"+result.toString();
    }
    
    
      public static SalesOrder getNoSO(String noSO) {
        String sql = "SELECT * FROM sales_order WHERE no_so = ?";
        try (PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(sql)) {
            stmt.setString(1, noSO);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                   SalesOrder so = new SalesOrder(
                    rs.getString("no_so"),
                    rs.getDate("tanggal"),
                    rs.getString("dikirim_dari"),
                    rs.getString("tujuan"),
                    rs.getInt("customer_id"),
                    rs.getInt("supplier_id"),
                    rs.getInt("pegawai_id"),
                    rs.getLong("harga_total"),
                    rs.getInt("status")
                );
                    // baca kolom lain sesuai kebutuhan…
                    return so;
                }
            }
        } catch (SQLException e) {
            System.out.println("Gagal mengambil SalesOrder by noSO: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    public void createSO() {
        // logika menyimpan ke database
         String sql = "INSERT INTO sales_order(no_so, tanggal, dikirim_dari, tujuan, customer_id, supplier_id, pegawai_id, harga_total, status) VALUES (?,?,?,?, ?, ?,?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, noSO);
            stmt.setDate(2, tanggal);
            stmt.setString(3, dikirimDari);
            stmt.setString(4, tujuan);
            stmt.setInt(5, customerId);
            stmt.setInt(6, supplierId);
            stmt.setInt(7, pegawaiId );
            stmt.setLong(8, hargaTotal);
            stmt.setInt(9, status);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void ubahStatusOrder(String noso) {
        // logika update ke database nanti di sini
        String sql = "UPDATE sales_order SET status = 1  WHERE no_so = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, noso);
            int rowsAffected = stmt.executeUpdate();
         
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void deleteSO(String noso) {
         String sql = "DELETE FROM sales_order WHERE no_so = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
             stmt.setString(1, noso);

            int rowsAffected = stmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
       @Override
        public String toString() {
            return noSO; 
        }

}