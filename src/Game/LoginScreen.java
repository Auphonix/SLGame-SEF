package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class LoginScreen extends JFrame implements ActionListener {
    JButton btnLogin, btnRegister, btnExit;
    JTextField jtfUser;
    JPasswordField jpfPass;
    JLabel jlabUser, jlabPass, jlabLoginPress;
    JFrame loginScreenFrame;
    String username;
    String password;

    public LoginScreen() {

        loginScreenFrame = new JFrame("Snakes and Ladders Login");

        btnLogin = new JButton("Login");
        btnRegister = new JButton("Register");
        btnExit = new JButton("Exit");

        jtfUser = new JTextField();
        jpfPass = new JPasswordField();

        jlabUser = new JLabel("Username:");
        jlabPass = new JLabel("Password:");
        jlabLoginPress = new JLabel();

        loginScreenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginScreenFrame.setLayout(null);
        loginScreenFrame.setResizable(false);

        btnLogin.addActionListener(this);
        btnRegister.addActionListener(this);
        btnExit.addActionListener(this);

        jlabUser.setBounds(10, 10, 120, 20);
        jlabPass.setBounds(10, 30, 120, 10);

        jtfUser.setBounds(140, 10, 300, 20);
        jpfPass.setBounds(140, 30, 300, 20);

        btnLogin.setBounds(140, 55, 100, 30);
        btnRegister.setBounds(240, 55, 100, 30);
        btnExit.setBounds(340, 55, 100, 30);

        jlabLoginPress.setBounds(140,140,300,20);

        loginScreenFrame.add(jlabUser);
        loginScreenFrame.add(jlabPass);
        loginScreenFrame.add(jtfUser);
        loginScreenFrame.add(jpfPass);
        loginScreenFrame.add(btnLogin);
        loginScreenFrame.add(btnRegister);
        loginScreenFrame.add(btnExit);
        loginScreenFrame.add(jlabLoginPress);

        loginScreenFrame.setSize(500, 300);
        loginScreenFrame.setVisible(true);

    }

    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == btnLogin){
            username = jtfUser.getText();
            password = jpfPass.getText();
            Account.findUser(username, password);
        }
        if(ae.getSource() == btnRegister){
            jlabLoginPress.setText("Register Was Triggered");
        }
        if(ae.getSource() == btnExit){
            jlabLoginPress.setText("Exit Was Triggered");
            loginScreenFrame.dispose();
        }

    }
}

