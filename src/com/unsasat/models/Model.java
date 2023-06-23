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

//    public static void main(String[] args)
//    {
//        String[] columns = {"hello", "world"};
//        ArrayList<String> qm = new ArrayList<String>();
//        int count = columns.length;
//        for (int k = 0; k < count; k++)
//        {
//            qm.add("?");
//        }
//        String[] qmArray = qm.toArray(new String[0]);
//        String query = "Insert into users (" + Arrays.toString(columns).replace("[", "").replace("]", "")  +  ")"
//                + " values (" + Arrays.toString(qmArray).replace("[", "").replace("]", "") + ")";
//        try
//        {
//            DatabaseMetaData getTable = connection().getMetaData();
//            ResultSet tables = getTable.getTables(null, null, "test", null);
//            if (tables.next())
//            {
//                System.out.println("Table exists");
//            }
//            else
//            {
//                System.out.println("Table does not exists");
//            }
//            PreparedStatement stmt = connection().prepareStatement(query);
//            stmt.executeQuery();
//        }
//        catch (Exception e)
//        {
//
//        }
//
//
//    }
}
