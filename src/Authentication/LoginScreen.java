package Authentication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class LoginScreen extends JFrame implements ActionListener
{
  JButton btnLogin, btnReset, btnExit;
  JTextField jtfUser;
  JPasswordField jpfPass;
  JLabel jlabUser, jlabPass;

    public LoginScreen(String name)
    {
      super(name);
      btnLogin = new JButton("Login");
      btnReset = new JButton("Reset");
      btnExit = new JButton("Exit");

      jtfUser = new JTextField();
      jpfPass = new JPasswordField();

      jlabUser = new JLabel("Username:");
      jlabPass = new JLabel("Password:");

      this.setLayout(null);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      btnLogin.addActionListener(this);
      btnReset.addActionListener(this);
      btnExit.addActionListener(this);

      jlabUser.setBounds(10,10,120,20);
      jlabPass.setBounds(10,30,120,20);

      jtfUser.setBounds(140,10,300,20);
      jpfPass.setBounds(140,30,300,20);

      btnLogin.setBounds(140,55,100,30);
      btnReset.setBounds(240,55,100,30);
      btnExit.setBounds(340,55,100,30);

      this.add(jlabUser);
      this.add(jlabPass);
      this.add(jpfPass);
      this.add(btnLogin);
      this.add(btnReset);
      this.add(btnExit);

      this.setSize(500,300);
      this.setVisible(true);
    }

  public void actionPerformed(ActionEvent ae)
  {

  }

  public static void main(String args[])
  {
    LoginScreen ls = new LoginScreen("Snake&LadderLogin");

  }




}
