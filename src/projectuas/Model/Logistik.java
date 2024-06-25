/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectuas.Model;

import java.io.Serializable;
/**
 *
 * @author AdityaHKP
 */
public class Logistik extends AbstractLogistik implements Serializable {

    public Logistik(String nama, int jumlah) {
        super(nama, jumlah);
    }

    @Override
    public String getNama() {
        return nama;
    }

    @Override
    public void setNama(String nama) {
        this.nama = nama;
    }

    @Override
    public int getJumlah() {
        return jumlah;
    }

    @Override
    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }
    
}