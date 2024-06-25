/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectuas.Controller;

import projectuas.Model.Riwayat;
import projectuas.Model.Logistik;
import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author AdityaHKP
 */
public class ExportCSV {

    public void exportCSVRiwayat(String path, List<Riwayat> riwayatList) throws IOException {
        CSVWriter writer = new CSVWriter(new FileWriter(path));
        String[] header = { "Nama", "Jumlah", "Keterangan", "Waktu" };
        writer.writeNext(header);
        for (Riwayat riwayat : riwayatList) {
            String[] record = {
                riwayat.getNama(),
                String.valueOf(riwayat.getJumlah()),
                riwayat.getKeterangan(),
                riwayat.getWaktu()
            };
            writer.writeNext(record);
        }
        writer.close();
    }
    
    public void exportCSVLogistik(String path, List<Logistik> logistikList) throws IOException {
        CSVWriter writer = new CSVWriter(new FileWriter(path));
        String[] header = { "Nama", "Jumlah" };
        writer.writeNext(header);
        for (Logistik logistik : logistikList) {
            String[] record = {
                logistik.getNama(),
                String.valueOf(logistik.getJumlah())
            };
            writer.writeNext(record);
        }
        writer.close();
    }
    
}