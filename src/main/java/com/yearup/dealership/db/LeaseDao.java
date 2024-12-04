package com.yearup.dealership.db;

import com.yearup.dealership.models.LeaseContract;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LeaseDao {
    private DataSource dataSource;

    public LeaseDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addLeaseContract(LeaseContract leaseContract) {
        // TODO: Implement the logic to add a lease contract
        try (Connection connect = dataSource.getConnection();
        PreparedStatement statement = connect.prepareStatement(
                "INSERT INTO lease_contracts ( lease_start, lease_end, monthly_payment) VALUES (?,?,?)")){
            statement.setDate(1, Date.valueOf(leaseContract.getLeaseStart()));
            statement.setDate(2,Date.valueOf(leaseContract.getLeaseEnd()));
            statement.setDouble(3,leaseContract.getMonthlyPayment());
            statement.executeUpdate();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
