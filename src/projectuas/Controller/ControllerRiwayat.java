/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectuas.Controller;

import projectuas.Model.DBConnection;
import projectuas.Model.Riwayat;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AdityaHKP
 */
public class ControllerRiwayat {
    
    public void tambahRiwayat(Riwayat riwayat) {
        String sql = "INSERT INTO riwayat(nama, jumlah, keterangan, waktu) VALUES(?, ?, ?, ?)";
        try (Connection conn = DBConnection.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, riwayat.getNama());
            pstmt.setInt(2, riwayat.getJumlah());
            pstmt.setString(3, riwayat.getKeterangan());
            pstmt.setString(4, riwayat.getWaktu());
            pstmt.executeUpdate();
            System.out.println("Data riwayat berhasil ditambahkan ke database.");
        } catch (SQLException e) {
            System.out.println("Error saat menambahkan data riwayat: " + e.getMessage());
        }
    }
    
    public List<Riwayat> getAllRiwayat() {
        List<Riwayat> riwayatList = new ArrayList<>();
        String sql = "SELECT * FROM riwayat";
        try (Connection conn = DBConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String nama = rs.getString("nama");
                int jumlah = rs.getInt("jumlah");
                String keterangan = rs.getString("keterangan");
                String waktu = rs.getString("waktu");
                Riwayat riwayat = new Riwayat(nama, jumlah, keterangan, waktu);
                riwayatList.add(riwayat);
            }
        } catch (SQLException e) {
            System.out.println("Error saat mengambil data riwayat: " + e.getMessage());
        }
        return riwayatList;
    }
    
    public void deleteRiwayat() {
        String sql = "DELETE FROM riwayat";
        try (Connection conn = DBConnection.connect();
             Statement stmt = conn.createStatement()) {
            int rowsDeleted = stmt.executeUpdate(sql);
            System.out.println("Jumlah data riwayat yang dihapus: " + rowsDeleted);
        } catch (SQLException e) {
            System.out.println("Error saat menghapus data riwayat: " + e.getMessage());
        }
    }
    
}