import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class RegisterScreen extends JFrame implements ActionListener
{
  JButton btnLogin, btnReset, btnExit;
  JTextField jtfUser;
  JPasswordField jpfPass;
  JLabel jlabUser, jlabPass, jlabLoginPress,jlabTESTHIT, inGameName;

    public RegisterScreen(String name)
    {
      super(name);
      btnLogin = new JButton("Login");
      btnReset = new JButton("Reset Fields");
      btnExit = new JButton("Exit");

      jtfUser = new JTextField();
      jpfPass = new JPasswordField();

      jlabUser = new JLabel("Username:");
      jlabPass = new JLabel("Password:");
      jlabTESTHIT = new JLabel("System: A button has been pressed");
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

      btnLogin.setBounds(140,50,100,30);
      btnReset.setBounds(240,50,100,30);
      btnExit.setBounds(340,50,100,30);

      this.add(jlabUser);
      this.add(jlabPass);

      this.add(jtfUser);
      this.add(jpfPass);

      this.add(btnLogin);
      this.add(btnReset);
      this.add(btnExit);

      this.setSize(500,300);
      this.setVisible(true);
    }

  public void actionPerformed(ActionEvent click)
  {
  Object source = click.getSource();
  if(source == btnLogin){

    jlabTESTHIT.setBounds(140,100,300,30);
    this.add(jlabTESTHIT);
  }
  }

  public static void main(String args[])
  {
    RegisterScreen ls = new RegisterScreen("Snake&LadderLogin");

  }
}
