
/**
 * Write a description of class AddUserGui here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AddUserGui
{
    // instance variables - replace the example below with your own
    public JFrame frame = new JFrame();
    public JPanel panel1 = new JPanel();
    public JPanel panel2 = new JPanel();
    public SqlHandler sqlHandler = new SqlHandler();
    public boolean isAdmin = false;
    /**
     * Constructor for objects of class AddUserGui
     */
    public AddUserGui()
    {
        // initialise instance variables
        OpenGui();
    }

    public void OpenGui(){
        JLabel labelNm = new JLabel("First name");
        JLabel labelLastNm = new JLabel("Last name");
        JLabel labelUserNm = new JLabel("Username");
        JLabel labelPwd = new JLabel("Password");
        JCheckBox adminCk = new JCheckBox();
        JTextField firstName = new JTextField();
        JTextField lastName = new JTextField();
        JTextField userName = new JTextField();
        JTextField userPass = new JTextField();
        JButton subBtn = new JButton("Submit");
        JButton clBtn = new JButton("Cancel");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("User Manager");
        frame.setLayout(new FlowLayout(FlowLayout.LEFT));
        frame.setSize(540,380);
        frame.add(panel1);
        frame.add(panel2);

        panel1.setLayout(new FlowLayout( FlowLayout.LEFT));
        panel1.setPreferredSize(new Dimension(150,380));
        panel2.setPreferredSize(new Dimension(280,380));

        panel1.add(labelNm);
        panel1.add(labelLastNm);
        panel1.add(labelUserNm);
        panel1.add(labelPwd);
         panel1.add(subBtn);

        firstName.setPreferredSize(new Dimension(260,20));
        lastName.setPreferredSize(new Dimension(260,20));
        userName.setPreferredSize(new Dimension(260,20));
        userPass.setPreferredSize(new Dimension(260,20));

        panel2.add(firstName);
        panel2.add(lastName);
        panel2.add(userName);
        panel2.add(userPass);
        panel2.add(adminCk);
          panel2.add(clBtn);
        frame.setVisible(true);
        adminCk.addItemListener(new ItemListener(){
        public void itemStateChanged(ItemEvent e){
        System.out.println("check state change");
        if(isAdmin == false){
          isAdmin = true;
        }else{
        isAdmin=false;
        }
        }
        
        });
        
        subBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
            System.out.println("submitting user details");
             
            
            
            boolean isAdded = sqlHandler.UserAdd(userName.getText(),firstName.getText(),lastName.getText(),userPass.getText(),isAdmin);
           if(!isAdded){
            JOptionPane.showMessageDialog(null,"Adding user unsuccessfull please check credentials and try again");
            }else{
            JOptionPane.showMessageDialog(null,"Adding user successfull");
            frame.dispose();
            }
        }
        
        });
    }

}
