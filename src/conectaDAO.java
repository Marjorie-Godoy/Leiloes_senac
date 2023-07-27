
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;




/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
   public class conectaDAO { 
       Connection conn;
       PreparedStatement st;
       ResultSet rs;
       
       public Connection getConexao(){
           return conn;
       }
       
       public boolean connectDB(){
       try {
           Class.forName("com.mysql.cj.jdbc.Driver");
           conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/uc11", "root", "marjorie2023");
           
           return true;
           
       } catch (SQLException | ClassNotFoundException ex) {
           System.out.println("Erro ConectaDAO" + ex.getMessage());
           
           return false;
           
       }
       
       }
       
       public void desconectarDB(){
           try {
               conn.close();
           } catch (Exception e) {
           }
       }
   }
