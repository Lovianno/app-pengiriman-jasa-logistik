/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgfinal.project.pbo;

/**
 *
 * @author LOVIANNO
 */
public abstract class Mitra {
    protected int id;
    protected String nama;
    protected String badanUsaha;
    protected String kategori;
    protected String noTelp;
    protected String email;

    public Mitra(int id, String nama, String badanUsaha, String kategori, String noTelp, String email) {
        this.id = id;
        this.nama = nama;
        this.badanUsaha = badanUsaha;
        this.kategori = kategori;
        this.noTelp = noTelp;
        this.email = email;
    }

    public abstract void detailMitra();
    public abstract void createMitra();
    public abstract void updateMitra();
    
    
    public  void tampilkanMitra(){
        System.out.println("Produk: " + nama + " | Kategori: " + kategori);
    }
    public  void deleteMitra(){
        System.out.println("Produk: " + nama + " | Kategori: " + kategori);
    };
}
