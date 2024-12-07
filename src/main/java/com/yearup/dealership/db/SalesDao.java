package com.yearup.dealership.db;

import com.yearup.dealership.models.SalesContract;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SalesDao {
    private DataSource dataSource;

    public SalesDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addSalesContract(SalesContract salesContract) {
        //
        try (Connection connect = dataSource.getConnection();
             PreparedStatement statement = connect.prepareStatement(
                     "INSERT INTO sales_contracts  (sale_date, price) VALUES (?,?)")){
            statement.setDate(1,Date.valueOf(salesContract.getSaleDate()));
            statement.setDouble(2,salesContract.getPrice());
            statement.executeUpdate();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
