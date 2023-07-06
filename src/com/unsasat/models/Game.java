package src.com.unsasat.models;

import src.com.unsasat.models.Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Game extends Model
{
    public int userID;
    public int score;

    public Game()
    {
        table = "games";
    }

    public void insertScore()
    {
        String query = "Insert into games (user_id, score, datetime) values(?,?, ?)";
        try
        {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            PreparedStatement stmt = connection().prepareStatement(query);
            stmt.setInt(1, userID);
            stmt.setInt(2, score);
            stmt.setString(3, formatter.format(date));
            stmt.execute();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public ResultSet getTopScores()
    {
        String query = "Select * from games where user_id = ? order by score desc limit 10";
        try
        {
            PreparedStatement stmt = connection().prepareStatement(query);
            stmt.setInt(1, userID);
            ResultSet result =  stmt.executeQuery();
            return result;
        }
        catch (Exception e)
        {
            System.out.println(e);
            return null;
        }
    }

}
