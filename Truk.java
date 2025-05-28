/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgfinal.project.pbo;

/**
 *
 * @author LOVIANNO
 */
public class Truk extends Produk {
    private String kapasitasMaksimal;

    public Truk(int id, String nama, String kategori, String deskripsi, String kapasitasMaksimal) {
        super(id, nama, kategori, deskripsi);
        this.kapasitasMaksimal = kapasitasMaksimal;
    }

    @Override
    public void detailProduk() {
        System.out.println("Truk: " + nama + " | Kapasitas Maks: " + kapasitasMaksimal);
    }
    @Override
    public void createProduk() {
        System.out.println("Pickup: " + nama + " | Panjang Bak: " + kapasitasMaksimal);
    }
    @Override
     public void updateProduk() {
        System.out.println("Pickup: " + nama + " | Panjang Bak: " + kapasitasMaksimal);
    }
}