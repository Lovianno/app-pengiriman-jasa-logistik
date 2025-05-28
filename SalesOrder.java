/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgfinal.project.pbo;

/**
 *
 * @author LOVIANNO
 */
import java.util.Date;

public class SalesOrder {
    private String noSO;
    private Date tanggal;
    private String dikirimDari;
    private String tujuan;
    private int customerId;
    private int supplierId;
    private int pegawaiId;
    private int status;
    private int hargaTotal;

    public SalesOrder(String noSO, Date tanggal, String dikirimDari, String tujuan, int customerId,
                      int supplierId, int pegawaiId, int status, int hargaTotal) {
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

    public void showSO() {
        System.out.println("========== SALES ORDER ==========");
        System.out.println("No. SO       : " + noSO);
        System.out.println("Tanggal      : " + tanggal);
        System.out.println("Dikirim dari : " + dikirimDari);
        System.out.println("Tujuan       : " + tujuan);
        System.out.println("Customer ID  : " + customerId);
        System.out.println("Supplier ID  : " + supplierId);
        System.out.println("Pegawai ID   : " + pegawaiId);
        System.out.println("Harga Total  : " + hargaTotal);
        System.out.println("Status       : " + status);
    }

    public void createSO() {
        // logika menyimpan ke database
    }

    public void deleteSO() {
        // logika menghapus SO dari database
    }

    // Getters & setters bisa ditambahkan
}
