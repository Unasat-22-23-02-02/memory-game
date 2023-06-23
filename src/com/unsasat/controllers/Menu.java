package src.com.unsasat.controllers;

import src.com.unsasat.models.User;

import java.sql.ResultSet;

public class Menu
{
    public void welcome(int id)
    {
        User model = new User();
        ResultSet user = model.getOne(id);
        try
        {
            while (user.next())
            {
                System.out.println("Welcome " + user.getString(2));
            }
        }
        catch (Exception e)
        {
            return;
        }

    }

}
