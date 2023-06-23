package src.com.unsasat.config;

import java.sql.*;

public class DB
{
    public Connection connect(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:49852/unasat","root","Jamil121!");
//            Statement stmt=con.createStatement();
//            ResultSet rs=stmt.executeQuery("select * from test");
//            while(rs.next())
//                System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
//            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
            return null;
        }


    }
}
