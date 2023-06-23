package src.com.unsasat.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class User extends Model
{
    public String name = "test";
    public String firstName = "test";
    public String username = "test";
    public String pin = "0000";

    public User()
    {
        table = "users";
    }

    public void Insert()
    {
        String query = "Insert into users (name, firstname, username, pin) values(?,?,?,?)";
        try
        {
            PreparedStatement stmt = connection().prepareStatement(query);
            stmt.setString(1, name);
            stmt.setString(2, firstName);
            stmt.setString(3, username);
            stmt.setString(4, pin);
            stmt.execute();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public void delete(int id)
    {
        String query = "Insert into users (name, firstname, username, pin) values(?,?,?,?)";
        try
        {
            PreparedStatement stmt = connection().prepareStatement(query);
            stmt.setString(1, name);
            stmt.setString(2, firstName);
            stmt.setString(3, username);
            stmt.setString(4, pin);
            stmt.execute();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public ResultSet getAll()
    {
        String query = "Select * from users order by id desc";
        try
        {
            PreparedStatement stmt = connection().prepareStatement(query);
            return stmt.executeQuery();
        }
        catch (Exception e)
        {
            System.out.println(e);
            return null;
        }
    }

    public ResultSet getOne(int id)
    {
        String query = "Select * from users where id=? order by id desc";
        try
        {
            PreparedStatement stmt = connection().prepareStatement(query);
            stmt.setInt(1, id);
            return stmt.executeQuery();
        }
        catch (Exception e)
        {
            System.out.println(e);
            return null;
        }
    }

    public boolean auth(String username, String pin)
    {
        String query = "Select * from users where username=?, pin=? order by id desc";
        try
        {
            PreparedStatement stmt = connection().prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(1, pin);
            ResultSet result =  stmt.executeQuery();
            if (result.next())
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public static void main(String[] args)
    {
        User test = new User();
//        test.Insert();
        ResultSet users = test.getOne(1);
        try
        {
            while (users.next())
            {
                System.out.println("Name: " + users.getString(2) +" " + users.getString(3));
                System.out.println("Username: " + users.getString(4));
            }
        }
        catch (Exception e)
        {

        }

    }

}
