
/**
 * Write a description of class MainGui here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainGui
{
    // instance variables - replace the example below with your own#
    public AddUserGui userGui;
    private int x;
    public String userNm;
    public String passWd;
    public SqlHandler sHandler;
    public EditProduct editProdGui;
    /**
     * Constructor for objects of class MainGui
     */
    public MainGui(String userNm,String passWd)
    {
        // initialise instance variables
        
        mainGui(userNm, passWd);
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void mainGui (String user,String pass){
        sHandler = new SqlHandler();
        
        boolean isAdmin = sHandler.AdminCheck();
        passWd = pass;
        userNm = user;
        JFrame frame = new JFrame();
        JMenuBar mBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JPanel panel = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel0 = new JPanel();
        JPanel panel3 = new JPanel();
        JList list = new JList<>();
        JList list2 = new JList<>();

        JTextArea txArea = new JTextArea("text area hello world");
        JButton btn0 = new JButton("0");
        JButton btn1 = new JButton("1");
        JButton btn2 = new JButton("2");
        JButton btn3 = new JButton("3");
        JButton btn4 = new JButton("4");
        JButton btn5 = new JButton("5");
        JButton btn6 = new JButton("6");
        JButton btn7 = new JButton("7");
        JButton btn8 = new JButton("8");
        JButton btn9 = new JButton("9");
        JButton btnYes = new JButton("search");
        JButton btnNo = new JButton("no");
        JButton addUser = new JButton("add user");
        JButton addCat = new JButton("add product category");
        JButton addProd = new JButton("add product");
        JButton editBtn = new JButton("edit product");
        JButton delBtn = new JButton("remove product");
        JTextField barcode = new JTextField("Scan or input barcode");
        JLabel label = new JLabel("point of sale");
        JLabel label2 = new JLabel("order details");
       
        JScrollPane scroller = new JScrollPane(txArea);
        JScrollPane scroller2 = new JScrollPane(txArea);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Worzel's Simple Pos");
        frame.setLayout(new FlowLayout());
        frame.setSize(1080,760);
        frame.setVisible(true);
        panel0.setPreferredSize(new Dimension(1080,20));
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel2.setLayout(new FlowLayout());
        frame.add(panel0);
        frame.add(panel);
        frame.add(panel2);
        mBar.add(fileMenu);
        frame.setJMenuBar(mBar);
        panel0.add(label);
        panel0.add(label2);
        panel.add(btn0);
        panel.add(btn1);
        panel.add(btn2);
        panel.add(btn3);
        panel.add(btn4);
        panel.add(btn5);
        panel.add(btn6);
        panel.add(btn7);
        panel.add(btn8);
        panel.add(btn9);
        panel.add(btnYes);
        //panel.add(btnNo);
        frame.add(panel3);
        panel3.add(barcode);
        panel2.add(scroller);
        panel2.add(scroller2);
        if(isAdmin==true){
        panel2.add(addUser);
        panel2.add(addCat);
         panel2.add(addProd);
          panel2.add(editBtn);
           panel2.add(delBtn);
        }
        panel.setPreferredSize(new Dimension(200,200));
        //list.setPreferredSize(new Dimension(300,300));
        list.setModel(new javax.swing.AbstractListModel<String>(){
                String[] strings = {"aaa.","bbb"};  
                public int getSize() { return strings.length; }

                public String getElementAt(int i) { return strings[i]; }
            });
        scroller.setViewportView(list);
         list2.setPreferredSize(new Dimension(100,300));
        list2.setModel(new javax.swing.AbstractListModel<String>(){
                String[] strings = {"aaa.","bbb"};  
                public int getSize() { return strings.length; }

                public String getElementAt(int i) { return strings[i]; }
            });
        scroller2.setViewportView(list2);
        btn0.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){

                    barcode.setText(barcode.getText()+0);

                }    
            });
        btn1.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){

                    barcode.setText(barcode.getText()+1);

                }    
            });
        btn2.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){

                    barcode.setText(barcode.getText()+2);

                }    
            });
        btn3.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){

                    barcode.setText(barcode.getText()+3);

                }    
            });
        btn4.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){

                    barcode.setText(barcode.getText()+4);

                }    
            });
        btn5.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){

                    barcode.setText(barcode.getText()+5);

                }    
            });
        btn6.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){

                    barcode.setText(barcode.getText()+6);

                }    
            });
        btn7.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){

                    barcode.setText(barcode.getText()+7);

                }    
            });
        btn8.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){

                    barcode.setText(barcode.getText()+8);

                }    
            });
        btn9.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){

                    barcode.setText(barcode.getText()+9);

                }    
            });
        btnYes.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){

                    String code = barcode.getText();
                    System.out.println("barcode caught ."+code+user+pass);
                    SearchProduct searchP = new SearchProduct(userNm,passWd);
                    searchP.checkBarcode(code,user);

                }    
            });
            addUser.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                System.out.println("add user btn pressed");
                //open user add gui 
                if(isAdmin==true){
                //AddUserGui();
                userGui = new AddUserGui();
                userGui.OpenGui();
                }
                }
            });
            addCat.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                System.out.println("add category btn pressed");
                //open user add gui 
                if(isAdmin==true){
                //AddUserGui();
                String category = JOptionPane.showInputDialog(null,"please enter new category name to add");
                boolean isAdded = sHandler.AddCategory(category);
                if(isAdded){
                JOptionPane.showMessageDialog(null,"category added successfully");
                }else{
                JOptionPane.showMessageDialog(null,"category added successfully");
                }
                }
                }
            });
             addProd.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                System.out.println("add product btn pressed");
                //open user add gui 
                if(isAdmin==true){
                 SearchProduct searchP = new SearchProduct(userNm,passWd);
                    searchP.addProductGui();
                }
                }
            });
             editBtn.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                System.out.println("edit product btn pressed");
                //open user add gui 
                editProdGui = new EditProduct();
                if(isAdmin==true){
                 
                }
                }
            });
             delBtn.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                System.out.println("edit product btn pressed");
                //open user add gui 
                
                if(isAdmin==true){
                 RemoveProduct delProd = new RemoveProduct();
                }
                }
            });

    }

   
}
