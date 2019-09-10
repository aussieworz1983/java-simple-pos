
/**
 * Write a description of class SearchProduct here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SearchProduct
{
    // instance variables - replace the example below with your own
    private int x;
   
    public String query;
    public String user;
    public String pass;
    public JFrame addingFrame;
    public String productName;
    public double productCode;
    public float productRetailPrice;
    public int productQuantity;
    public float productWholesaleCost;
    public float productDiscount;
    public String productCategory;
    public SqlHandler sqlHandler;
    /**
     * Constructor for objects of class SearchProduct
     */
    public SearchProduct(String userNm, String passWd)
    {
        // initialise instance variables
        user = userNm;
        pass = passWd;
       
        sqlHandler = new SqlHandler();

    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void checkBarcode (String code,String user){
        boolean prodFound =  sqlHandler.SearchBarcode(code,user);
        if(prodFound){
        JOptionPane.showMessageDialog(null,"product barcode found adding to order");
        }else{
            boolean adminLogged = sqlHandler.AdminCheck();
         if(adminLogged){
            addProductConfirm();
        }else{
            getManagerAlert();
        }
        }
           

        System.out.println("searching product");
        System.out.println("code from search script"+code);
    }
   

   

    public void addProductConfirm(){
        System.out.println("add product confirmation");
        int result = JOptionPane.showConfirmDialog(null,"product not found would you like to add it to the system");
        if(JOptionPane.YES_OPTION == result){
            System.out.println("yes chose"+result);
            addProductGui();

        }else if(JOptionPane.NO_OPTION == result){
            System.out.println("no chose"+result);
        }else{
            System.out.println("no chose"+result);
        }

    }

    public void getManagerAlert(){
        System.out.println("employee this product is not in our database");
        JOptionPane.showMessageDialog(null , "Product not found in our database please contact a manager.");
    }

    public void addProductGui(){
        String[] cats = sqlHandler.GetAllCats();
        addingFrame = new JFrame();
        addingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addingFrame.setTitle("Product Manager");
        addingFrame.setLayout(new FlowLayout(15,5,5));
        addingFrame.setSize(540,380);
        addingFrame.setVisible(true);
        JTextField prodNm = new JTextField("product name");
        JTextField prodCe = new JTextField("product barcode");
        JTextField prodPe = new JTextField("product retail price");
        JTextField prodCt = new JTextField("product wholesale cost");
        JTextField prodQy = new JTextField("product quantity available");
        JTextField prodDt = new JTextField("product discount");
        JTextField prodCat = new JTextField("product category");
        JButton appBtn = new JButton("Apply");
        JButton clBtn = new JButton("Cancel");
        
        JComboBox catList = new JComboBox(cats);
        
        addingFrame.add(prodNm);
        addingFrame.add(prodCe);
        addingFrame.add(prodPe);
        addingFrame.add(prodCt);
        addingFrame.add(prodQy);
        addingFrame.add(prodDt);
        //addingFrame.add(prodCat);
         addingFrame.add(catList);
        addingFrame.add(appBtn);
        
        addingFrame.add(clBtn);
       
        
        
        appBtn.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){

                    System.out.println("apply");

                    try{
                        productName = prodNm.getText();
                        productCode = Double.parseDouble(prodCe.getText());
                        productRetailPrice = Float.parseFloat(prodPe.getText());
                        productWholesaleCost = Float.parseFloat(prodCt.getText());
                        productQuantity = Integer.parseInt(prodQy.getText());
                        productDiscount = Float.parseFloat(prodDt.getText());
                        //productCategory = prodCat.getText();
                        
                        
                        
                         
                        sqlHandler.InsertProduct(productName,productDiscount,productRetailPrice,productCode,productQuantity,productWholesaleCost,productCategory);
                        JOptionPane.showMessageDialog(null,"product uploaded succesfully");
                        System.out.println("product uploaded successfully");
                        addingFrame.dispose();

                    }catch(Exception err){
                        System.out.println("product not uploaded try again "+err);
                        JOptionPane.showMessageDialog(null,"product not uploaded try again");
                    }

                }    
            });
        clBtn.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){

                    System.out.println("cancel ");

                }    
            });
             catList.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    JComboBox cb = (JComboBox)e.getSource();
                    String catName = (String)cb.getSelectedItem();
                    productCategory = catName;
                    System.out.println("cancel ");

                }    
            });
            

    }

    public void addProductToOrder(String code){
    }
}
