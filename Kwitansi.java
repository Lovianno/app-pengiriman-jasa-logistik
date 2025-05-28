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

public class Kwitansi {
    private String noPembayaran;
    private Date tanggal;
    private String metodeBayar;
    private String noInvoice;
    private int totalHarga;

    public Kwitansi(String noPembayaran, Date tanggal, String metodeBayar, String noInvoice, int totalHarga) {
        this.noPembayaran = noPembayaran;
        this.tanggal = tanggal;
        this.metodeBayar = metodeBayar;
        this.noInvoice = noInvoice;
        this.totalHarga = totalHarga;
    }

    public void cetakKwitansi() {
        System.out.println("KWITANSI");
        System.out.println("No. Pembayaran: " + noPembayaran);
        System.out.println("Tanggal: " + tanggal);
        System.out.println("Metode Bayar: " + metodeBayar);
        System.out.println("No. Invoice: " + noInvoice);
        System.out.println("Total: " + totalHarga);
    }
}

