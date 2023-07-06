//note the count variable is not reset when a new game is pressed
package src.com.unsasat.gui;

import src.com.unsasat.models.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Random;
import javax.swing.table.DefaultTableModel;

public class TopScores {

    public TopScores(int userID)
    {
        Game scores = new Game();
        scores.userID = userID;
        ResultSet results = scores.getTopScores();
        // Create a new JFrame
        JFrame frame = new JFrame("Top 10 scores");

        // Create a DefaultTableModel
        DefaultTableModel model = new DefaultTableModel();

        // Add columns to the model
        model.addColumn("#");
        model.addColumn("Date");
        model.addColumn("Score");


        // Create a JTable with the DefaultTableModel
        JTable table = new JTable(model);

        // Create a JScrollPane and add the table to it
        JScrollPane scrollPane = new JScrollPane(table);

        // Create a JPanel and add the scroll pane to it
        JPanel panel = new JPanel();
        panel.add(scrollPane);

        // Add the panel to the frame
        frame.getContentPane().add(panel);

        // Set the frame size and make it visible
        frame.setSize(500,300);
        frame.setLocation(500,300);
//        frame.setSize(300, 200);
        frame.setVisible(true);

        // Example loop to add results to the table
        try
        {
            int pos =0;
            while (results.next())
            {
                pos++;
                model.addRow(new Object[]
                        {
                                pos,
                                results.getString(4),
                                results.getString(3)
                        });
            }
        }
        catch(Exception e)
        {

        }

    }

}
	