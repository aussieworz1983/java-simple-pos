
/**
 * Write a description of class RemoveProduct here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class RemoveProduct
{
    // instance variables - replace the example below with your own


    /**
     * Constructor for objects of class RemoveProduct
     */
    public double barcode;
    public RemoveProduct()
    {
        // initialise instance variables
        SqlHandler mySql = new SqlHandler();
        JFrame frame = new JFrame();
        JTextField bScan = new JTextField("scan barcode to remove product");
        JButton delBtn = new JButton("apply");
        frame.setTitle("remove product");
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
        frame.setSize(250,250);
        frame.setVisible(true);
        frame.add(bScan);
        frame.add(delBtn);
        delBtn.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    boolean remove = false;

                    barcode = Double.parseDouble(bScan.getText());
                    //TODO add name of product here
                    //int dialogButton = JOptionPane.YES_NO_OPTION;
                    //int dialogResult = JOptionPane.showConfirmDialog(this, "Your Message", "Title on Box", dialogButton);
                  //  if(dialogResult == 0) {
                    //    System.out.println("Yes option");
                   // } else {
                    //    System.out.println("No Option");
                   // } 
                    JOptionPane.showConfirmDialog(null,"are you sure u wish to remove product","confirm",JOptionPane.YES_NO_OPTION);
                    if(JOptionPane.YES_NO_OPTION==0){
                        boolean isRemoved = mySql.RemoveProduct(barcode);
                        if(isRemoved){
                            JOptionPane.showMessageDialog(null,"product was removed from database");
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"product was not removed from database");
                    }


                }    
            });

    }
    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */

}
