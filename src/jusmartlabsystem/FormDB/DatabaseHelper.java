/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jusmartlabsystem.FormDB;
import java.sql.Connection;
import java.sql.DriverManager;


/**
 *
 * @author PC
 */

public class DatabaseHelper {
    
    
    public static Connection getConnection() {

        Connection conn = null;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost/jusmartlabdb",
                    "root",
                    ""
            );

        } catch (Exception ex) {

            ex.printStackTrace();

        }

        return conn;
    }
    
}
