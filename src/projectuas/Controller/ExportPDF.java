/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectuas.Controller;

import projectuas.Model.Riwayat;
import projectuas.Model.Logistik;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import java.io.FileNotFoundException;
import java.util.List;

/**
 *
 * @author AdityaHKP
 */
public class ExportPDF {

    public static void exportPDFRiwayat(String dest, List<Riwayat> riwayatList) {
        try {
            PdfWriter writer = new PdfWriter(dest);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);
            Paragraph title = new Paragraph("Riwayat Logistik Markas Komando SPD")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setFontSize(18);
            document.add(title);
            document.add(new Paragraph("\n"));
            Table table = new Table(4);
            table.addCell("Nama");
            table.addCell("Jumlah");
            table.addCell("Keterangan");
            table.addCell("Waktu");
            for (Riwayat riwayat : riwayatList) {
                table.addCell(riwayat.getNama());
                table.addCell(String.valueOf(riwayat.getJumlah()));
                table.addCell(riwayat.getKeterangan());
                table.addCell(riwayat.getWaktu());
            }
            document.add(table);
            document.close();
            System.out.println("Berhasil menyimpan PDF.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public static void exportPDFLogistik(String dest, List<Logistik> logistikList) {
        try {
            PdfWriter writer = new PdfWriter(dest);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);
            Paragraph title = new Paragraph("Daftar Logistik Markas Komando SPD")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setFontSize(18);
            document.add(title);
            document.add(new Paragraph("\n"));
            Table table = new Table(2);
            table.addCell("Nama");
            table.addCell("Jumlah");
            for (Logistik logistik : logistikList) {
                table.addCell(logistik.getNama());
                table.addCell(String.valueOf(logistik.getJumlah()));
            }
            document.add(table);
            document.close();
            System.out.println("Berhasil menyimpan PDF.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
}
