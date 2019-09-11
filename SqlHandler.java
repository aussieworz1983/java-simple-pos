
/**
 * Write a description of class SqlHandler here.
 * handler for 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.* ;
import java.util.*;

public class SqlHandler
{
    // instance variables - replace the example below with your own
    public Connection conn;
    public String db ;
    public String query;
    public String user;
    public static boolean manager;
    public static String managerLogged;
    public static String prodToEditName;
    public static float prodToEditDiscount;
    public static float prodToEditRetail;

    public static double prodToEditBarcode;
    public static int prodToEditQuantity;
    public static float prodToEditCost;
    public static String prodToEditCat;
    public EditProduct prodEdit;

    /**
     * Constructor for objects of class SqlHandler
     */
    public SqlHandler()
    {
        // initialise instance variables
        db ="jdbc:mysql://localhost/";
        user = "";

    }

    public boolean getConn( ){
        boolean state;

        try{

            conn = DriverManager.getConnection(db,"adminepos","password");
            state=true;
            System.out.println("db connected sucessfully");
        }catch (Exception e){
            state=false;
            System.out.println("error connecting to db"+e);
        }
        return state;
    }

    public void sqlClose(){
        try{
            conn.close();
            System.out.println("db connection closed");
        }catch(Exception e){

        }

    }

    public boolean loginUser(String username,String password){
        boolean loggedIn = false;
        getConn();
        try{
            query = "SELECT id, admin FROM  users.staff WHERE username=? AND password=? ";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1,username);
            pst.setString(2,password);
            ResultSet rs;
            rs = pst.executeQuery();
            if(rs.next()){

                System.out.println("correct logim"+rs);
                loggedIn=true;
                try{
                    managerLogged = rs.getString(2);

                }catch(SQLException sqlError){
                    System.out.println(sqlError);
                }
                int num = Integer.parseInt(rs.getString(2));
                if(num==0){
                    System.out.println("employee account");
                    manager=false;
                }else if(num==1){ 
                    System.out.println("manager account");
                    manager=true;
                }else{
                    System.out.println("else nothing"+num);                        }

            }else{
                System.out.println("incorrect logim");
                loggedIn=false;
            }
        }catch(Exception e){
            System.out.println("login sql exception");
        }
        sqlClose();
        return loggedIn;
    }

    public void InsertProduct(String productName,float productDiscount,float productRetailPrice,double productCode,int productQuantity,float productWholesaleCost,String productCategory ){
        try{
            getConn();
            query = "insert into epos.products (product_name, product_discount, productprice, productbarcode, quantity_available, cost_of_product, category ) values ( '"+productName+"' ,"+productDiscount+" , "+productRetailPrice+" , "+productCode+" , "+productQuantity+","+productWholesaleCost+" , '"+productCategory+"' )";
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
        }catch(SQLException err){
            System.out.println("product not uploaded try again "+err);
        }  
        sqlClose(); 
    }
    
    public boolean SearchBarcode(String code,String user){
        boolean found = false;
        try{
            getConn();
            System.out.println("connection"+conn);
            query = "SELECT * FROM epos.products WHERE productbarcode =?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1,code);
            ResultSet rs;
            rs = pst.executeQuery();
            if(rs.next()){
                //barcode found add product to order 
                System.out.println("barcode found");
                //System.out.println(rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5)+" "+rs.getString(6));
                if(user!="editing"){
                    addProductToOrder(code);
                }else{
                    System.out.println(rs.getString(1)+" id "+rs.getString(2)+" name "+rs.getString(3)+" discount "+rs.getString(4)+" retail "+rs.getString(5)+" code "+rs.getString(6)+" quantity "+rs.getString(7)+" category "+rs.getString(8)+" wholesale");
                    int prodId = Integer.parseInt(rs.getString(1));
                    prodToEditName = rs.getString(2);
                    prodToEditDiscount = Float.parseFloat(rs.getString(3));
                    prodToEditRetail = Float.parseFloat(rs.getString(4));
                    prodToEditBarcode = Double.parseDouble(rs.getString(5));
                    prodToEditQuantity =Integer.parseInt( rs.getString(6));
                    prodToEditCost =Float.parseFloat(rs.getString(8));
                    prodToEditCat = rs.getString(7);
                    prodEdit = new EditProduct();
                    prodEdit.FillEditingGui(prodId,prodToEditName,prodToEditDiscount, prodToEditRetail, prodToEditBarcode,prodToEditQuantity,prodToEditCost,prodToEditCat);
 
                    
                }

                found=true;
            }else{
                System.out.println("barcode not found name search ask");
                found=false;

            }
        }catch(Exception e){
            System.out.println("sql failure"+e);
        }
        return found;
    }

    public boolean AdminCheck(){

        boolean admin = manager;

        return admin;
    }

    public boolean UserAdd(String userNm,String firstNm,String lastNm,String userPass,boolean isAdmin){
        boolean isAdded = false;
        try{
            getConn();
            query = "insert into users.staff (username, firstname, lastname, password, admin ) values ('"+userNm+"' , '"+firstNm+"' , '"+lastNm+"' , '"+userPass+"' , "+isAdmin+") ";
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
            isAdded=true;
            System.out.println("new user added");
        }catch( Exception err){
            System.out.println("new user not added"+err);
            isAdded = false;
        }
        return isAdded;
    }

    public void addProductToOrder(String code){
        //find product deduct quantity then add to client order and display on screen

    }

    public boolean AddCategory(String category){
        boolean isAdded = false;
        try{
            getConn();
            query = "insert into epos.product_categories (cat_name) values ('"+category+"') ";
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
            isAdded=true;
            System.out.println("new category added");
        }catch(Exception e){
            System.out.println("new category not added");
            isAdded=false;
        }
        return isAdded;
    }

    public String[] GetAllCats(){
        ArrayList<String> cats = new ArrayList<String>();
        try{
            getConn();
            query = "SELECT * FROM epos.product_categories";
            PreparedStatement pst = conn.prepareStatement(query);

            ResultSet rs;
            rs = pst.executeQuery();
            /*if(rs.next()){
            //barcode found add product to order 

            //cats.add(rs.getString(1));

            }else{
            System.out.println("no categories found");

            }*/
            while(rs.next()){
                cats.add(rs.getString("cat_name"));
            }
        }catch(Exception e){
            System.out.println("no categories found sql error");
        }
        String[] list = new String[cats.size()];
        list = cats.toArray(list);
        for(int i =0;i<cats.size();i++){
            System.out.println(list[i]);
        }
        return list;
    }
    public void EditProduct(int id,String prodToEditName,float prodToEditDiscount,float prodToEditRetail,double prodToEditBarcode,int prodToEditQuantity,float prodToEditCost){
    // public void EditProduct(int id,String prodToEditName,float prodToEditDiscount){
        try{
        getConn();
        query = "update epos.products set product_name = ? , product_discount = ? , productprice = ? , productbarcode = ? , quantity_available = ? , cost_of_product = ? where id = ?";
        //query = "update epos.products set product_name = ? , product_discount = ? where id = ?";
        PreparedStatement stmnt = conn.prepareStatement(query);
        stmnt.setString(1,prodToEditName);
        stmnt.setFloat(2,prodToEditDiscount);
        stmnt.setFloat(3,prodToEditRetail);
        stmnt.setDouble(4,prodToEditBarcode);
        stmnt.setInt(5,prodToEditQuantity);
        stmnt.setFloat(6,prodToEditCost);
        stmnt.setInt(7,id);
        stmnt.executeUpdate();
        }catch(SQLException e){
        System.out.println("editing error "+e);
        }
    }
    public boolean RemoveProduct(double code){
            boolean isRemoved = false;
            try{
            getConn();
            query = "delete from epos.products where productbarcode = ?";
           PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setDouble(1, code);

           // execute the preparedstatement
            preparedStmt.execute();
            isRemoved = true;
            }catch(Exception e){
            System.out.println("delete err"+e);
            isRemoved = false;
            }
            return isRemoved;
    }
}
