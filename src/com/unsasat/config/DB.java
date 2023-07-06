package src.com.unsasat.config;

import java.sql.*;

public class DB
{
    public Connection connect(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:49411/unasat","root","Jamil121!");
        }
        catch(Exception e)
        {
            System.out.println(e);
            return null;
        }


    }
}
