import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class RegisterLoginTest extends JFrame {
    JLabel usernameLabel = new JLabel("用户名");

    JLabel usernumLabel = new JLabel("密码");
    JTextField usernameTextField = new JTextField(18);
    JTextField usernumTextField = new JTextField(18);
    JPanel jpanel = new JPanel();

    JButton registerButton = new JButton("注册");
    JButton loginButton = new JButton("登录");
    public RegisterLoginTest() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        int height = (int)dimension.getHeight();
        int width = (int)dimension.getWidth();
        this.setBounds((width - 300) / 2, (height - 400) / 2, 250, 150);
        this.setDefaultCloseOperation(3);
        this.setTitle("登录系统");
        init();
        this.setVisible(true);
    }

    public void init() {
        this.add(jpanel);
        jpanel.add(usernameLabel);
        jpanel.add(usernameTextField);
        jpanel.add(usernumLabel);
        jpanel.add(usernumTextField);
        jpanel.add(registerButton);
        jpanel.add(loginButton);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    BufferedWriter bufferWriter = new BufferedWriter(new FileWriter("D:/登录.txt"));
                    String summessage = usernameTextField.getText() + " " + usernameTextField.getText();
                    BufferedReader reader = new BufferedReader(new FileReader("D:/登录.txt"));
                    boolean cot = true;
                    String s;
                    while ((s = reader.readLine()) != null) {
                        if (summessage.equals(s)) {
                            cot = false;
                        }
                    }

                    if (cot) {
                        bufferWriter.write(summessage);
                        bufferWriter.newLine();
                        bufferWriter.flush();
                        bufferWriter.close();
                        JOptionPane.showMessageDialog(null, "注册成功！");
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "已经存在了，请更换用户名和密码！");
                    }

                }
                catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String summessage = usernameTextField.getText() + " " + usernumTextField.getText();
                boolean cot = false;
                String s;
                try {
                    BufferedReader reader = new BufferedReader(new FileReader("D:/登录.txt"));
                    while ((summessage = reader.readLine()) != null) {
                        if (summessage.equals(summessage)) {
                            cot = true;
                        }
                    }

                    if (cot) {
                        JOptionPane.showMessageDialog(null, "登录成功！");
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "用户名或者密码错误，登录失败！");
                    }
                }
                catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        new RegisterLoginTest();
    }
}
