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
                "INSERT INTO lease_contracts (VIN, lease_start, lease_end, monthly_payment) VALUES (?,?,?,?)")){
            statement.setString(1, leaseContract.getVin());
            statement.setDate(2, Date.valueOf(leaseContract.getLeaseStart()));
            statement.setDate(3,Date.valueOf(leaseContract.getLeaseEnd()));
            statement.setDouble(4,leaseContract.getMonthlyPayment());

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
