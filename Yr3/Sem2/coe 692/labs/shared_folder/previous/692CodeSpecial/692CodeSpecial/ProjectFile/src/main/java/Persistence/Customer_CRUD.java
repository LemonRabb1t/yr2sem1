/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistence;

import Helper.UserInfo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author sarahjawwad
 */
public class Customer_CRUD {
    
    private static Connection getCon() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SCS?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "student123");
            System.out.println("Connection established.");
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }

 public static UserInfo read(String username, String password){
        UserInfo bean =null;
        
        
        try{
            Connection con=getCon();
             if (con!=null){
            PreparedStatement ps=con.prepareStatement("SELECT * FROM SCS.Customer WHERE username=? and password=?");
            ps.setString(1, username);
            ps.setString(2, password);

                      
            ResultSet rs=ps.executeQuery();
                       
            if(rs.next()){
                System.out.println("hello");

                String fname=rs.getString("first name");
                String lname=rs.getString("last name");
                String email=rs.getString("email");
                String adr=rs.getString("address");
                String pass=rs.getString("password");
                
                if (pass.equals(password)){
                    System.out.println("byebye");
                    bean = new UserInfo(fname, lname, email, adr, username,password);
                }
            }   
            con.close();
            ps.close();
             } else {
                 System.out.println("No connection");
             }
        }catch(Exception e){System.out.println(e);}
        
        return bean;
                  
    }
    
    //when customer registers
    public static UserInfo create(String fname, String lname, String email, String adr, String username, String password){
        UserInfo bean =null;
        try{
            Connection con=getCon();
             
            PreparedStatement ps=con.prepareStatement("INSERT INTO CUSTOMER (name, email, address, username, password) VALUES (?, ?, ?, ?, ?)");
            ps.setString(1,fname);
            ps.setString(2,lname);
            ps.setString(3,email);
            ps.setString(4,adr);                 
            ps.setString(5,username);
            ps.setString(6, password);
            
            int count = ps.executeUpdate();
            
            if(count >0)
               bean = new UserInfo(fname, lname, email, adr,username,password);
            
            con.close();
            
        }catch(Exception e){System.out.println(e);}
        
        return bean;
    }

   

}