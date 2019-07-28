/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.and.singleton.implementation;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nk
 */
public class DBConnection {
    private static DBConnection dbConnection = null;
    private static Connection connection = null;
    private static Statement statement = null;

    private DBConnection() {
        try {
            FileReader file = new FileReader( "db.properties");
            Properties p = new Properties();
            p.load(file);
            String user = p.getProperty("user");
            String password = p.getProperty("password");
            String url = p.getProperty("url");
            
            connection = DriverManager.getConnection(url,user,password);
            statement = connection.createStatement();
            System.out.println("Connection created");
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static Connection getConnection(){
        if(dbConnection == null)
           dbConnection = new DBConnection();
        return connection;
        
    }
    public static Statement getStatement(){
        if(dbConnection == null)
            dbConnection = new DBConnection();
        return statement;
    }
    
}
