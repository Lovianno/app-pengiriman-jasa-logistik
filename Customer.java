/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgfinal.project.pbo;

/**
 *
 * @author LOVIANNO
 */
public class Customer extends Mitra {
    private String nomorRekening;

    public Customer(int id, String nama, String badanUsaha, String kategori, String noTelp, String email, String nomorRekening) {
        super(id, nama, badanUsaha, kategori, noTelp, email);
        this.nomorRekening = nomorRekening;
    }

    @Override
    public void detailMitra() {
        System.out.println("SUPPLIER: " + nama + " | Area: " + nomorRekening);
    }
     @Override
    public void createMitra() {
        System.out.println("SUPPLIER: " + nama + " | Area: " + nomorRekening);
    }
     @Override
    public void updateMitra() {
        System.out.println("SUPPLIER: " + nama + " | Area: " + nomorRekening);
    }
}

