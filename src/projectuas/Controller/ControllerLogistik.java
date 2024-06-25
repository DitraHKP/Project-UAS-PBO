/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectuas.Controller;

import projectuas.Model.DBConnection;
import projectuas.Model.Logistik;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;


/**
 *
 * @author AdityaHKP
 */
public class ControllerLogistik {
    
    public boolean createUpdateLogistik(Logistik logistik) {
    String selectSql = "SELECT jumlah FROM logistik WHERE nama = ?";
    String insertSql = "INSERT INTO logistik(nama, jumlah) VALUES(?, ?)";
    String updateSql = "UPDATE logistik SET jumlah = ? WHERE nama = ?";
    try (Connection conn = DBConnection.connect();
        PreparedStatement selectStmt = conn.prepareStatement(selectSql)) {
        selectStmt.setString(1, logistik.getNama());
        ResultSet rs = selectStmt.executeQuery();
        if (rs.next()) {
            int existingJumlah = rs.getInt("jumlah");
            int newJumlah = existingJumlah + logistik.getJumlah();
            try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                updateStmt.setInt(1, newJumlah);
                updateStmt.setString(2, logistik.getNama());
                updateStmt.executeUpdate();
            }
        } else {
            try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                insertStmt.setString(1, logistik.getNama());
                insertStmt.setInt(2, logistik.getJumlah());
                insertStmt.executeUpdate();
            }
        }
        return true;
    } catch (SQLException e) {
        System.out.println(e.getMessage());
        return false;
        }
    }
    
    public boolean decrementLogistik(Logistik logistik) {
        String selectSql = "SELECT jumlah FROM logistik WHERE nama = ?";
        String updateSql = "UPDATE logistik SET jumlah = ? WHERE nama = ?";
        try (Connection conn = DBConnection.connect();
             PreparedStatement selectStmt = conn.prepareStatement(selectSql)) {
            selectStmt.setString(1, logistik.getNama());
            ResultSet rs = selectStmt.executeQuery();
            if (rs.next()) {
                int existingJumlah = rs.getInt("jumlah");
                if (existingJumlah >= logistik.getJumlah()) {
                    int newJumlah = existingJumlah - logistik.getJumlah();
                    try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                        updateStmt.setInt(1, newJumlah);
                        updateStmt.setString(2, logistik.getNama());
                        updateStmt.executeUpdate();
                        return true;
                    }
                } else {
                    System.out.println("Error: Jumlah barang yang diminta lebih banyak dari jumlah yang tersedia.");
                    return false;
                }
            } else {
                System.out.println("Error: Barang tidak ditemukan.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public List<Logistik> getAllLogistik() {
        String selectSql = "SELECT nama, jumlah FROM logistik";
        List<Logistik> logistikList = new ArrayList<>();
        try (Connection conn = DBConnection.connect();
             PreparedStatement selectStmt = conn.prepareStatement(selectSql)) {
            ResultSet rs = selectStmt.executeQuery();
            while (rs.next()) {
                String nama = rs.getString("nama");
                int jumlah = rs.getInt("jumlah");
                Logistik logistik = new Logistik(nama, jumlah);
                logistikList.add(logistik);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return logistikList;
    }
    
    public boolean deleteLogistik() {
        String deleteSql = "DELETE FROM logistik";
        try (Connection conn = DBConnection.connect();
             PreparedStatement deleteStmt = conn.prepareStatement(deleteSql)) {
            deleteStmt.executeUpdate();
            System.out.println("Berhasil menghapus seluruh data logistik dari database.");
            return true;
        } catch (SQLException e) {
            System.out.println("Gagal menghapus data logistik: " + e.getMessage());
            return false;
        }
    }
    
    public boolean hapusKosong() {
        String deleteSql = "DELETE FROM logistik WHERE jumlah = 0";
        try (Connection conn = DBConnection.connect();
             PreparedStatement deleteStmt = conn.prepareStatement(deleteSql)) {
            int rowsDeleted = deleteStmt.executeUpdate();
            System.out.println("Berhasil menghapus " + rowsDeleted + " data logistik dengan jumlah 0 dari database.");
            return true;
        } catch (SQLException e) {
            System.out.println("Gagal menghapus data logistik kosong: " + e.getMessage());
            return false;
        }
    }
    
    public void importLogistikFromDatFile(String filePath) throws IOException, ClassNotFoundException {
        List<Logistik> logistikList = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            logistikList = (List<Logistik>) ois.readObject();
        }

        // Simpan data logistik dari file .dat ke database
        for (Logistik logistik : logistikList) {
            createUpdateLogistik(logistik);
        }
    }

}
