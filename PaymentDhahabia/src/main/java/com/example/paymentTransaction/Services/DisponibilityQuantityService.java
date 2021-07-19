package com.example.paymentTransaction.Services;

import com.example.paymentTransaction.Entities.Product;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Service
public class DisponibilityQuantityService {


   // @Autowired
    private Connection connectDB;

    public boolean isDisponibleQuantity(List<Product> productList) throws SQLException, ClassNotFoundException {

        int k,id;

        for (int i  = 0; i < productList.size(); i++) {

            k= Integer.parseInt(productList.get(i).getQuantity());
            id= Integer.parseInt(productList.get(i).getProductID());
            ResultSet data= getDATA(id);
            data.next();

            if (data.getInt("quantity") < k ){
                return false;
            }
        }
        return true;

    }

    public ResultSet getDATA(int id) throws SQLException {

        Statement stmt = connectDB.createStatement();
        ResultSet rs = ((Statement) stmt).executeQuery("select quantity,productID from product where productID="+id);
        rs.next();

        return rs;
    }
}
