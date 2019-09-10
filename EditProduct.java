
/**
 * Write a description of class EditProduct here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class EditProduct
{
    // instance variables - replace the example below with your own
    public SqlHandler sHandler;
    public String barCode; 
    public static boolean frameIsActive;
    public JFrame frame = new JFrame();
    public JTextField scanBox = new JTextField("scan or input barcode");
    public JButton schBtn = new JButton ("search");
    public JTextField prodNm = new JTextField("product name");
    public JTextField prodDt = new JTextField("product Discount");
    public JTextField prodRt = new JTextField("product Retail");
    public JTextField prodCe = new JTextField("product Barcode");
    public JTextField prodQy = new JTextField("product Quantity");
    public JTextField prodWe = new JTextField("product Wholesale");
    public JComboBox prodCt ;
    public JButton appBtn = new JButton("apply");
    public int productId;
    /**
     * Constructor for objects of class EditProduct
     */

    public EditProduct()
    {
        //boolean active
        // initialise instance variables
        // frameIsActive = active;
        sHandler = new SqlHandler();
        String[] cats = sHandler.GetAllCats();
        //if(active){
        prodCt = new JComboBox(cats);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setTitle("Worzel's Simple Pos");
        frame.setLayout(new FlowLayout());
        frame.setSize(540,380);

        frame.setVisible(true);
        frame.add(scanBox);
        frame.add(schBtn);
        frame.add(prodNm);
        frame.add(prodDt);
        frame.add(prodRt);
        frame.add(prodCe);
        frame.add(prodQy);
        frame.add(prodWe);
        frame.add(prodCt);
        frame.add(appBtn);
        //}

        schBtn.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    barCode = scanBox.getText();
                    sHandler.SearchBarcode(barCode, "editing");
                    frame.dispose();
                }    
            });
        appBtn.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    System.out.println("apply pressed");
                    sHandler.EditProduct(productId,prodNm.getText(),Float.parseFloat(prodDt.getText()),Float.parseFloat(prodRt.getText()),Double.parseDouble(prodCe.getText()),Integer.parseInt(prodQy.getText()),Float.parseFloat(prodWe.getText()) );
                   // sHandler.EditProduct(productId,prodNm.getText(),Float.parseFloat(prodDt.getText()));
                }    
            });
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void EditingGui(){

    }

    public void FillEditingGui(int prodId,String prodToEditName,float prodToEditDiscount,float prodToEditRetail,double prodToEditBarcode,int prodToEditQuantity,float prodToEditCost,String prodToEditCat){
        
        try{
            productId = prodId;
            prodNm.setText(prodToEditName);
            prodDt.setText(Float.toString(prodToEditDiscount));
            prodRt.setText(Float.toString(prodToEditRetail));
            prodCe.setText(Double.toString(prodToEditBarcode));
            prodQy.setText(Integer.toString(prodToEditQuantity));
            prodWe.setText(Float.toString(prodToEditCost));

        }catch( Exception e){
            System.out.println(e);
        }

    }
}
