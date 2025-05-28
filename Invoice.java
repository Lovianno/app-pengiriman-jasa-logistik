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

public class Invoice {
    private String noInvoice;
    private String noSO;
    private String noPembayaran;
    private Date tanggalInvoice;
    private Date tanggalJatuhTempo;

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
}

