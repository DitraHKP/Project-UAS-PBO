/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectuas.Model;

/**
 *
 * @author AdityaHKP
 */
public abstract class AbstractLogistik {
    
    protected String nama;
    protected int jumlah;

    public AbstractLogistik(String nama, int jumlah) {
        this.nama = nama;
        this.jumlah = jumlah;
    }

    abstract public String getNama();
    abstract public void setNama(String nama);
    abstract public int getJumlah();
    abstract public void setJumlah(int jumlah);
    
}
