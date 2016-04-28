import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class LoginScreen extends JFrame implements ActionListener
{
  JButton btnLogin, btnReset, btnExit;
  JTextField jtfUser;
  JPasswordField jpfPass;
  JLabel jlabUser, jlabPass, jlabLoginPress;

    public LoginScreen(String name)
    {
      super(name);
      btnLogin = new JButton("Login");
      btnReset = new JButton("Register");
      btnExit = new JButton("Exit");

      jtfUser = new JTextField();
      jpfPass = new JPasswordField();

      jlabUser = new JLabel("Username:");
      jlabPass = new JLabel("Password:");
      jlabLoginPress = new JLabel("fw");

      this.setLayout(null);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      btnLogin.addActionListener(this);
      btnReset.addActionListener(this);
      btnExit.addActionListener(this);

      jlabUser.setBounds(10,10,120,20);
      jlabPass.setBounds(10,30,120,10);

      jtfUser.setBounds(140,10,300,20);
      jpfPass.setBounds(140,30,300,20);

      btnLogin.setBounds(140,55,100,30);
      btnReset.setBounds(240,55,100,30);
      btnExit.setBounds(340,55,100,30);

      /*jlabLoginPress.setBounds(140,60,300,30);*/

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
    jlabLoginPress.setIcon(new ImageIcon("/Users/mac/Documents/Atom/Java/SEF/Files/sample.png"));
  }

  public static void main(String args[])
  {
    LoginScreen ls = new LoginScreen("Snake&LadderLogin");

  }




}
