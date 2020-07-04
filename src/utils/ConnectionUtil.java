/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;
import java.sql.DriverManager;
import java.sql.Connection;
/**
 *
 * @author user
 */
public class ConnectionUtil {
    Connection conn = null;
    public static Connection conDB(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bioskop","root","");
            return con;
        } catch (Exception e) {
            return null;
        }
    }
}
