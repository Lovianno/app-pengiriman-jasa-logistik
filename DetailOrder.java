/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgfinal.project.pbo;

/**
 *
 * @author LOVIANNO
 */
public class DetailOrder {
    private int id;
    private String noSO;
    private String kodeProduk;
    private String deskripsi;
    private int kuantitas;
    private int hargaBeli;
    private int hargaJual;

    public DetailOrder(int id, String noSO, String kodeProduk, String deskripsi, int kuantitas, int hargaBeli, int hargaJual) {
        this.id = id;
        this.noSO = noSO;
        this.kodeProduk = kodeProduk;
        this.deskripsi = deskripsi;
        this.kuantitas = kuantitas;
        this.hargaBeli = hargaBeli;
        this.hargaJual = hargaJual;
    }

    public void createDetailSO() {
        // logika insert ke database
    }

    // Getter dan setter bisa ditambahkan
}

