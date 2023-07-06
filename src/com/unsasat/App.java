package src.com.unsasat;

import src.com.unsasat.gui.Main;
import src.com.unsasat.gui.TopScores;
import src.com.unsasat.models.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.util.*;


public class App implements ActionListener {
    JButton submitLogin = new JButton("Login");
    JButton submitRegister = new JButton("Register");
    JTextField name = new JTextField("", 20);
    JTextField firstname = new JTextField("", 20);
    JTextField username = new JTextField("", 20);
    JTextField pin = new JTextField("", 20);
    JFrame frame = new JFrame("Memory Game");
    JPanel field = new JPanel();
    JPanel auth = new JPanel();
    JPanel menu = new JPanel();
    JPanel menu2 = new JPanel();
    JPanel menu3 = new JPanel();
    JPanel mini = new JPanel();
    JPanel start_screen = new JPanel();
    JPanel end_screen = new JPanel();
    JPanel instruct_screen = new JPanel();
    JButton btn[] = new JButton[20];
    JButton login = new JButton("Login");
    JButton scores = new JButton("Top scores");
    JButton register = new JButton("Register");
    JButton start = new JButton("Start");
    JButton over = new JButton("Exit");
    JButton inst = new JButton("Instructions");
    JButton goBack = new JButton("Main Menu");
    Random randomGenerator = new Random();
    int userID;
    ResultSet userI;
    private boolean purgatory = false;
    Boolean game_over = false;
    int level = 0;
    int score = 0;
    int incorrectClicks = 0; // Counter for incorrect clicks
    String[] board;
    int[] boardQ = new int[20];
    Boolean shown = true;
    int temp = 30;
    int temp2 = 30;
    String a[] = new String[10];
    boolean eh = true;

    private JLabel label = new JLabel("Instructions: Memorize the symbols and click on the matching pairs.");
    private JTextArea instructM = new JTextArea("When the game begins, the screen will be filled with pairs of buttons.\nMemorize their placement. Once you press any button, they will all clear.\nYour goal is to click the matching buttons in a row. When you finish that, you win.\nEvery incorrect click gives you a point (those are bad).\nGOOD LUCK!");

    public App() {
        initiate();
    }


    public void initiate()
    {
        if (userID == 0)
        {
            System.out.println("new Session");
            frame.setSize(500, 300);
            frame.setLocation(500, 300);
            frame.setLayout(new BorderLayout());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            start_screen.setLayout(new BorderLayout());
            auth.setLayout(new FlowLayout(FlowLayout.CENTER));
            menu.setLayout(new FlowLayout(FlowLayout.CENTER));
            menu2.setLayout(new FlowLayout(FlowLayout.CENTER));
            menu3.setLayout(new FlowLayout(FlowLayout.CENTER));
            mini.setLayout(new FlowLayout(FlowLayout.CENTER));
            start_screen.add(auth);
            start_screen.setLayout(new GridLayout(5,2));
            auth.add(login);
            auth.add(register);
            login.addActionListener(this);
            login.setEnabled(true);
            register.addActionListener(this);
            register.setEnabled(true);
            frame.add(start_screen, BorderLayout.CENTER);
            frame.setVisible(true);
        }
        else
        {
            System.out.println("old Session");
            mainMenu();
        }
    }

    public void actionPerformed(ActionEvent click) {
        Object source = click.getSource();

        if (source == start) {
            Main game = new Main();
            game.userID = userID;
        }

        if (source == over) {
            System.exit(0);
        }

        if (source == login) {
            clear();
            loginForm();
        }

        if (source == register) {
            clear();
            registerForm();
        }

        if (source == scores) {
            TopScores ts= new TopScores(userID);

//            new TopScores();
        }

        if (source == submitRegister) {
            register();

//            loginFailed();
        }

        if (source == submitLogin) {
            login();
        }


    }

    public void mainMenu()
    {
        clear();
        start_screen.add(menu, BorderLayout.NORTH);
        start_screen.add(menu3, BorderLayout.CENTER);
        start_screen.add(menu2, BorderLayout.SOUTH);
        menu3.add(mini, BorderLayout.CENTER);
        menu.add(label);
        start.addActionListener(this);
        start.setEnabled(true);
        menu2.add(start);
        over.addActionListener(this);
        over.setEnabled(true);
        menu2.add(over);
        menu2.add(scores);
        scores.addActionListener(this);
        scores.setEnabled(true);
        inst.addActionListener(this);
        inst.setEnabled(true);
        inst.addActionListener(this);
        inst.setEnabled(true);
        inst.addActionListener(this);
        inst.setEnabled(true);
    }

    public void clear()
    {
        start_screen.removeAll();
        start_screen.revalidate();
        start_screen.repaint();
    }


    public void registerForm()
    {
        JPanel nameRow = new JPanel();
        JPanel firstnameRow = new JPanel();
        JPanel usernameRow = new JPanel();
        JPanel pinRow = new JPanel();
        start_screen.add(new JLabel("Name"));
        start_screen.add(name);
        start_screen.add(new JLabel("First name"));
        start_screen.add(firstname);
        start_screen.add(new JLabel("Username"));
        start_screen.add(username);
        start_screen.add(new JLabel("Pin"));
        start_screen.add(pin);
        submitRegister.addActionListener(this);
        submitRegister.setEnabled(true);
        start_screen.add(login);
        start_screen.add(submitRegister);
    }

    public void loginForm()
    {
//        start_screen.setLayout(new GridLayout(3,2));
        start_screen.add(new JLabel("Username"));
        start_screen.add(username);
        start_screen.add(new JLabel("Password"));
        submitLogin.addActionListener(this);
        submitLogin.setEnabled(true);
        start_screen.add(pin);
        start_screen.add(submitLogin);
        start_screen.add(register);
    }


    public void register()
    {
        User user = new User();
        user.name = name.getText();
        user.firstName = firstname.getText();
        user.pin= pin.getText();
        user.username= username.getText();
        if (user.register())
        {
            mainMenu();
        }
        else
        {
            registrationFailed();
        }
    }

    public void login()
    {
        User user = new User();
        user.pin= pin.getText();
        user.username= username.getText();
        if (user.auth())
        {
            ResultSet userInfo = user.getAuthUser();
            try
            {
                userID = userInfo.getInt(1);
                userI = userInfo;
                mainMenu();
            }
            catch(Exception e)
            {

            }}
        else
        {
            loginFailed();
        }
    }

    public void loginFailed()
    {
        System.out.println("failed");
        frame.setEnabled(false);
        JDialog d = new JDialog(frame, "Login failed");
//        d.setUndecorated(true);
        d.setLocation(700, 200);
        d.setLayout(new FlowLayout(FlowLayout.CENTER));

        // create a label
        // setsize of dialog
        d.setSize(400, 100);
        // set visibility of dialog
        d.setVisible(true);
        try
        {
            Thread.sleep(2000);
            d.setVisible(false);
            frame.setEnabled(true);
        }
        catch(Exception e)
        {

        }
    }

    public void registrationFailed()
    {
        System.out.println("failed");
        frame.setEnabled(false);
        JDialog d = new JDialog(frame, "Registration failed");
//        d.setUndecorated(true);
        d.setLocation(700, 200);
        d.setLayout(new FlowLayout(FlowLayout.CENTER));

        // create a label
        // setsize of dialog
        d.setSize(400, 100);
        // set visibility of dialog
        d.setVisible(true);
        try
        {
            Thread.sleep(2000);
            d.setVisible(false);
            frame.setEnabled(true);
        }
        catch(Exception e)
        {

        }
    }

    public static void main(String[] arguments) {
        App mg = new App();
    }
}
