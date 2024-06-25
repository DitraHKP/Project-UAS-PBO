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
public class Riwayat implements IRiwayat, Serializable {
    
    private String nama;
    private int jumlah;
    private String keterangan;
    private String waktu;
    
    public Riwayat() {
    }

    public Riwayat(String nama, int jumlah, String keterangan, String waktu) {
        this.nama = nama;
        this.jumlah = jumlah;
        this.keterangan = keterangan;
        this.waktu = waktu;
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

    @Override
    public String getKeterangan() {
        return keterangan;
    }

    @Override
    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    @Override
    public String getWaktu() {
        return waktu;
    }

    @Override
    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }
    
}
