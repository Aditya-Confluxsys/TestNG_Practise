package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://10.1.27.41:5432/dummydb";
        String user = "dummyuser";
        String password = "password1";
        String query = "select * from aditya";

        try{
            Connection con = DriverManager.getConnection(url,user,password);
            PreparedStatement st = con.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            System.out.println("ID\t\tName");
            System.out.println("-------------------------");

            // Iterating through the ResultSet
            while (rs.next()) {
                String id = rs.getString("account_id");
                String name = rs.getString("account_login");
                System.out.println(id + "\t\t" + name);
            }

            // Check if no data found
            if (!rs.isBeforeFirst()) { // Check if the ResultSet is empty
                System.out.println("No data found.");
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}