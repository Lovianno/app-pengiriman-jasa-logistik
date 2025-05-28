/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgfinal.project.pbo;

/**
 *
 * @author LOVIANNO
 */
public class Pickup extends Produk {
    private String panjangBak;

    public Pickup(int id, String nama, String kategori, String deskripsi, String panjangBak) {
        super(id, nama, kategori, deskripsi);
        this.panjangBak = panjangBak;
    }

    @Override
    public void detailProduk() {
        System.out.println("Pickup: " + nama + " | Panjang Bak: " + panjangBak);
    }
    @Override
    public void createProduk() {
        System.out.println("Pickup: " + nama + " | Panjang Bak: " + panjangBak);
    }
    @Override
     public void updateProduk() {
        System.out.println("Pickup: " + nama + " | Panjang Bak: " + panjangBak);
    }
      
}

