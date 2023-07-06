package src.com.unsasat.models;

import src.com.unsasat.config.DB;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Model
{
    protected static List<List<String>> columns = new ArrayList<List<String>>();

    protected String table;
    protected DB db = new DB();

    protected Connection connection()
    {
        try
        {
            return db.connect();
        }
        catch(Exception e)
        {
            return null;
        }

    }
}
