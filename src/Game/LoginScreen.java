package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class LoginScreen extends JFrame implements ActionListener {
    private String username;
    private String password;
    private int index;

    JButton btnLogin, btnReset, btnExit;
    JTextField jtfUser;
    JPasswordField jpfPass;
    JLabel jlabUser, jlabPass;



    public LoginScreen() {

        JFrame loginScreen = new JFrame("Login Screen");  //creates the frame and sets properties
        loginScreen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loginScreen.setSize(500, 300);
        loginScreen.setVisible(true);

        JTextField jtfUser = new JTextField("Please Enter Username"); //Creates an instance of all elements
        JPasswordField jpfPass = new JPasswordField();
        JLabel jlabUser = new JLabel("Username:");
        JLabel jlabPass = new JLabel("Password:");
        JButton btnLogin = new JButton("Login");
        JButton btnReset = new JButton("Reset");
        JButton btnExit = new JButton("Exit");

        btnLogin.addActionListener(this); //Adds listeners on all buttons
        btnReset.addActionListener(this);
        btnExit.addActionListener(this);

        jlabUser.setBounds(10, 10, 120, 20); //Adds positioning and sizes to all elements
        jlabPass.setBounds(10, 30, 120, 20);

        jtfUser.setBounds(140, 10, 300, 20);
        jpfPass.setBounds(140, 30, 300, 20);

        btnLogin.setBounds(140, 55, 100, 30);
        btnReset.setBounds(240, 55, 100, 30);
        btnExit.setBounds(340, 55, 100, 30);

        loginScreen.add(jlabUser); //Adds all instances to the JFrame
        loginScreen.add(jlabPass);
        loginScreen.add(jpfPass);
        loginScreen.add(jtfUser);
        loginScreen.add(btnLogin);
        loginScreen.add(btnReset);
        loginScreen.add(btnExit);
    }

    public void userSearch(String username, String password){
    }

    public void actionPerformed(ActionEvent ae) {
        if(btnReset.isEnabled() == true){ //Checks if btnReset is pressed
            jtfUser.setText("");
            jpfPass.setText("");
        }
        else if(btnExit.isEnabled() == true){ //Used to quit application
        }
        else if(btnLogin.isEnabled() == true){ //Used to login
            super.dispose();
        }
    }
}
