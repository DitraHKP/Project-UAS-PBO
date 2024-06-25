/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectuas.Controller;

import projectuas.Model.Anggota;
import projectuas.Model.DBConnection;
import java.sql.*;

/**
 *
 * @author AdityaHKP
 */
public class ControllerAnggota {
    
    public String login(String nas, String password) {
        String query = "SELECT * FROM anggota WHERE nas = ?";
        try (Connection conn = DBConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, nas);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String storedPassword = rs.getString("password");
                if (storedPassword.equals(password)) {
                    return "Login Berhasil";
                } else {
                    return "Password tidak sesuai";
                }
            } else {
                return "NAS tidak terdaftar";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Database error: " + e.getMessage();
        }
    }
    
}
