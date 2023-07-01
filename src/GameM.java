package src.com.unsasat;

import src.com.unsasat.models.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.util.*;
import java.util.concurrent.TimeUnit;


public class GameM implements ActionListener {



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

    public GameM() {
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



    public void setUpGame() {
        clearMain();



        board = new String[20];
        for (int i = 0; i < 20; i++) {
            btn[i] = new JButton("");
            btn[i].setBackground(new Color(220, 220, 220));
            btn[i].addActionListener(this);
            btn[i].setEnabled(true);
            field.add(btn[i]);
            btn[i].setText("");  // Set the initial text of buttons to empty
        }



        String[] b = { ":-D", "X", "O", "-(*.*)-", "<>", "<(^-^)>", "=>", ";^P", "ABC", "123" };
        a = b;



        ArrayList<Integer> indexes = new ArrayList<Integer>();
        for (int i = 0; i < 20; i++) {
            indexes.add(i);
        }



        for (int i = 0; i < 10; i++) {
            for (int z = 0; z < 2; z++) {
                int randomIndex = indexes.remove(randomGenerator.nextInt(indexes.size()));
                board[randomIndex] = a[i];
            }
        }
        createBoard();
    }



    public void hideField() {
        for (int i = 0; i < 20; i++) {
            btn[i].setText("");
        }
        shown = false;
    }



    public void switchSpot(int i) {
        if (board[i] != "done") {
            if (btn[i].getText() == "") {
                btn[i].setText(board[i]);
            } else {
                btn[i].setText("");
            }
        }
    }



    public void showSpot(int i) {
        btn[i].setText(board[i]);
    }



    public void showField() {
        for (int i = 0; i < 20; i++) {
            btn[i].setText(a[i]);
        }
        shown = true;
    }



    public boolean checkWin() {
        for (int i = 0; i < 20; i++) {
            if (board[i] != "done") {
                return false;
            }
        }
        winner();
        return true;
    }



    public void winner() {
        start_screen.remove(field);
        start_screen.add(end_screen, BorderLayout.CENTER);
        end_screen.add(new TextField("You Win"), BorderLayout.NORTH);
        end_screen.add(new TextField("Score: " + score), BorderLayout.SOUTH);
        end_screen.add(goBack);
        goBack.setEnabled(true);
        goBack.addActionListener(this);
    }



    public void goToMainScreen() {
        new GameM();
    }


    public void createBoard() {
        field.setLayout(new BorderLayout());
        start_screen.add(field, BorderLayout.CENTER);
        field.setLayout(new GridLayout(4, 5, 2, 2));
        field.setBackground(Color.black);
        field.requestFocus();
    }

    public void clearMain() {
        start_screen.remove(menu);
        start_screen.remove(menu2);
        start_screen.remove(menu3);
        start_screen.revalidate();
        start_screen.repaint();
    }



    public void actionPerformed(ActionEvent click) {
        Object source = click.getSource();
        if (purgatory) {
            switchSpot(temp2);
            switchSpot(temp);
            score++;
            incorrectClicks++; // Increment the incorrect clicks counter
            temp = 30;
            temp2 = 30;
            purgatory = false;
        }

        if (source == start) {
            setUpGame();
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

        if (source == submitRegister) {
            register();

//            loginFailed();
        }

        if (source == submitLogin) {
            login();

//            loginFailed();
        }




        if (source == inst) {
            clearMain();
            start_screen.add(instruct_screen, BorderLayout.NORTH);
            JPanel one = new JPanel();
            one.setLayout(new FlowLayout(FlowLayout.CENTER));
            JPanel two = new JPanel();
            two.setLayout(new FlowLayout(FlowLayout.CENTER));
            instruct_screen.setLayout(new BorderLayout());
            instruct_screen.add(one, BorderLayout.NORTH);
            instruct_screen.add(two, BorderLayout.SOUTH);
            one.add(instructM);
            two.add(goBack);
            goBack.addActionListener(this);
            goBack.setEnabled(true);
        }



        if (source == goBack) {
            frame.dispose();
            goToMainScreen();
        }

        for (int i = 0; i < 20; i++) {
            if (source == btn[i]) {
                if (shown) {
                    hideField();
                } else {
                    switchSpot(i);
                    if (temp >= 20) {
                        temp = i;
                    } else {
                        if ((board[temp] != board[i]) || (temp == i)) {
                            temp2 = i;
                            purgatory = true;
                            score++;  // Increment score for non-matching pair
                            incorrectClicks++; // Increment the incorrect clicks counter
                            if (incorrectClicks >= 20) {
                                gameOver();  // Check if game over condition is met
                            }
                        } else {
                            board[i] = "done";
                            board[temp] = "done";
                            checkWin();
                            temp = 20;
                        }
                    }
                }
            }
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
        mini.add(inst, BorderLayout.SOUTH);


        start.addActionListener(this);
        start.setEnabled(true);
        menu2.add(start);
        over.addActionListener(this);
        over.setEnabled(true);
        menu2.add(over);
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



    public void gameOver() {
        start_screen.remove(field);
        start_screen.add(end_screen, BorderLayout.CENTER);
        end_screen.add(new TextField("Game Over"), BorderLayout.NORTH);
        end_screen.add(new TextField("Score: " + score), BorderLayout.SOUTH);
        end_screen.add(goBack);
        goBack.setEnabled(true);
        goBack.addActionListener(this);
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
//        System.out.println(pin.getText());
//        System.out.println(username.getText());
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

            }

            mainMenu();
        }
        else
        {
            registrationFailed();
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
        JLabel l = new JLabel("Username or pin incorrect");
        d.add(l);
        // setsize of dialog
        d.setSize(400, 100);
        // set visibility of dialog
        d.setVisible(true);
        try
        {
            d.add(l);
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
        GameM mg = new GameM();
    }
}
