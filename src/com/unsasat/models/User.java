package src.com.unsasat.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class User extends Model
{
    public String name;
    public String firstName;
    public String username;
    public String pin;

    public User()
    {
        table = "users";
    }

    public boolean register()
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
            return true;
        }
        catch (Exception e)
        {
            System.out.println(e);
            return false;
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
    public boolean auth()
    {
        String query = "Select * from users where username = ? and pin = ? order by id desc";
        try
        {
            PreparedStatement stmt = connection().prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, pin);
            ResultSet result =  stmt.executeQuery();
            while (result.next())
            {
                return true;
            }
            return false;
        }
        catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }
    public ResultSet getAuthUser()
    {
        String query = "Select * from users where username=? and pin=? order by id desc";
        try
        {
            PreparedStatement stmt = connection().prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, pin);
            ResultSet result =  stmt.executeQuery();

            while (result.next())
            {
                return result;
            }

        }
        catch (Exception e)
        {
            System.out.println(e);
            return null;
        }
        return null;
    }

}
