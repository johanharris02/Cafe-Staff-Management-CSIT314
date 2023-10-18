package PROJECTS.boundary_layer;
import PROJECTS.control_layer.User;
import PROJECTS.control_layer.systemAdmin;
import PROJECTS.entity_layer.LoginVerification;
import PROJECTS.util.DButil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class loginScreen {
    JFrame frame =new JFrame("Cafe staff management system");
    Container c = frame.getContentPane();//创建视图
    JLabel userLabel=new JLabel(" Account :");
    JTextField usernameField= new JTextField();
    JLabel passwdLabel=new JLabel("Password :");
    JPasswordField passwordField=new JPasswordField();
    JButton loginButton = new JButton("Log in");
    JButton clearButton = new JButton("Clear");

    /**
     * This method is used to set the login screen
     */
    public loginScreen(){
        frame.setBounds(600, 200, 350, 250);//设置窗体位置&大小
        c.setLayout(new BorderLayout());//设置视图的布局
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();//初始化，把控件放在布局里
        frame.setVisible(true);//设置窗体可见
    }

    /**
     * This method is used to initialize the login screen
     */
    public void init(){
        //        标题——上方 //Title -- above
        JPanel titlePanel =new JPanel();//创建一个放置标题的面板
        titlePanel.setLayout(new FlowLayout());

        titlePanel.add(new JLabel("Welcome To Use Staff Management System"));
        c.add(titlePanel, "North");//加入视图中

        //        输入框——中间 //Input box -- middle
        JPanel inputPanel =new JPanel();
        inputPanel.setLayout(null);
        userLabel.setBounds(50, 20, 70, 23);//标签位置
        passwdLabel.setBounds(50, 60, 70, 23);
        inputPanel.add(userLabel);
        inputPanel.add(passwdLabel);
        usernameField.setBounds(140, 20, 120, 23);
        passwordField.setBounds(140, 60, 120, 23);
        inputPanel.add(usernameField);
        inputPanel.add(passwordField);
        c.add(inputPanel, "Center");


        /**
         * The login button involves the comparison of database information and the realization of specific user types
         */
//        按钮底部 //Button - Bottom //login button action listener
        JPanel buttonPanel =new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                /**
                 *  The following code creates an empty User data type
                 *  defined by the static method in entity_layer to create the specific user type (admin,owner,manager,staff).
                 */
                User user = null;
                //Call static methods to access database, the entity layer method will check account and password then return a User type to define user.
                user = LoginVerification.LoginVerify(username,password);
                if (user.getProfile().equals("Invalid Account or Password or the account has been disabled. please contact administrator")){
                    JOptionPane.showMessageDialog(frame, "Invalid Account or Password or the account has been disabled. please contact administrator");
                }else {
                    user.UserLogin(); //Calls the current user's work panel,UserLogin() be rewritten in each role's class
                    frame.dispose(); // Close login screen
                };
            }
        });


        /**
         * Used to clear the information entered by the user
         */
        // Clear button
        clearButton.setBounds(100, 80, 80, 25);
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usernameField.setText(""); // Clear username field
                passwordField.setText(""); // Clear password field
            }
        });

        //Add two buttons to the bottom
        buttonPanel.add(clearButton);
        buttonPanel.add(loginButton);
        c.add(buttonPanel, "South");
    }
}


