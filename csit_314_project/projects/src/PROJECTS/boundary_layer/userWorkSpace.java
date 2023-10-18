package PROJECTS.boundary_layer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * basic work space
 * aim to create role's work space
 *
 */
public class userWorkSpace extends JFrame{
        public userWorkSpace(String name, String profile) {
            super("User workspace");
            JTabbedPane controlTab; //定义选项卡
            JPanel viewPart, addPart, searchPart,deletePart,exitPart;	//定义面板

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setSize(700,500);
            this.setLocationRelativeTo(null);
            setVisible(true);

            viewPart = new JPanel();
            addPart = new JPanel();
            searchPart = new JPanel();
            deletePart = new JPanel();
            exitPart=new JPanel();

            //设置侧面背景
            viewPart.setBackground(Color.WHITE);
            addPart.setBackground(Color.WHITE);
            searchPart.setBackground(Color.pink);
            deletePart.setBackground(Color.GRAY);


            viewPart.setLayout(null);//自由布局
            addPart.setLayout(null);//自由布局
            searchPart.setLayout(null);//自由布局


            JButton exitButton = new JButton("Exit");
            exitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose(); // Close main screen
                    new loginScreen(); // Open login screen again
                }
            });
            exitPart.add(exitButton);

            controlTab =new JTabbedPane(JTabbedPane.LEFT); //创建选项卡并使选项卡垂直排列
            controlTab.add("View user list", viewPart);
            controlTab.add("Add user", addPart);
            controlTab.add("Search user", searchPart);
            controlTab.add("Delete user", deletePart);
            controlTab.add("Log out", exitPart);
            controlTab.setFont(new Font("STYLE_BOLD",Font.PLAIN,20));
            this.add(controlTab);    //添加选项卡窗格到容器

        }
}
