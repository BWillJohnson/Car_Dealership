package com.yearup.dealership.db;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InventoryDao {
    private DataSource dataSource;

    public InventoryDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addVehicleToInventory(String VIN, int dealership_id) {
        // I am checking if the dealership_id exists in the dealership table
        // By doing this I am only specify if the SELECT row 1 has a Dealership_id column exists.
        String checkDealershipSQL = "SELECT 1 FROM dealerships WHERE dealership_id = ?";
        try (Connection connect = dataSource.getConnection();
             PreparedStatement checkStatement = connect.prepareStatement(checkDealershipSQL)) {

            checkStatement.setInt(1, dealership_id);
            try (ResultSet rs = checkStatement.executeQuery()) {
                if (rs.next()) {
                    // Dealership exists, proceed to insert the vehicle
                    String insertSQL = "INSERT INTO inventory (VIN, dealership_id) VALUES (?, ?)";
                    try (PreparedStatement statement = connect.prepareStatement(insertSQL)) {
                        statement.setString(1, VIN);
                        statement.setInt(2, dealership_id);
                        statement.executeUpdate();
                    }
                } else {
                    System.out.println("Error: Dealership with ID " + dealership_id + " does not exist.");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void removeVehicleFromInventory(String vin) {
        // TODO: Implement the logic to remove a vehicle from the inventory

        try (Connection connect = dataSource.getConnection();
             PreparedStatement statement = connect.prepareStatement(
                     "DELETE FROM inventory WHERE VIN = ?")) {
            statement.setString(1, vin);

            int rows = statement.executeUpdate();
            System.out.println("Rows Deleted: " + rows);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
