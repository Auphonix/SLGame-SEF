package Authentication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class LoginScreen extends JFrame implements ActionListener {
    JButton btnLogin, btnReset, btnExit;
    JTextField jtfUser;
    JPasswordField jpfPass;
    JLabel jlabUser, jlabPass;

    public LoginScreen(String name) {
        super(name);

        JFrame loginScreen = new JFrame();  //creates the frame and sets properties
        loginScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginScreen.setSize(500, 300);
        loginScreen.setVisible(true);

        jtfUser = new JTextField("Please Enter Username"); //Creates an instance of all elements
        jpfPass = new JPasswordField();
        jlabUser = new JLabel("Username:");
        jlabPass = new JLabel("Password:");
        btnLogin = new JButton("Login");
        btnReset = new JButton("Reset");
        btnExit = new JButton("Exit");

        loginScreen.add(jlabUser); //Adds all instances to the JFrame
        loginScreen.add(jlabPass);
        loginScreen.add(jpfPass);
        loginScreen.add(jtfUser);
        loginScreen.add(btnLogin);
        loginScreen.add(btnReset);
        loginScreen.add(btnExit);

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

    }

    public void actionPerformed(ActionEvent ae) {
        if(btnReset.isEnabled() == true){ //Checks if btnReset is pressed
            jtfUser.setText("");
            jpfPass.setText("");
        }
    }

    public static void main(String args[]) {
        LoginScreen ls = new LoginScreen("Snake&LadderLogin");

    }


}
