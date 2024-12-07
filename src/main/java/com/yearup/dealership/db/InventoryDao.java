package com.yearup.dealership.db;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InventoryDao {
    private DataSource dataSource;

    public InventoryDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addVehicleToInventory(String VIN, int dealershipId) {
        // TODO: Implement the logic to add a vehicle to the inventory
        try (Connection connect = dataSource.getConnection();
        PreparedStatement statement = connect.prepareStatement(
                "INSERT INTO vehicles (VIN, dealership_id) values (?,?)")) {
            statement.setString(1,VIN);
            statement.setInt(2, dealershipId);

            statement.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void removeVehicleFromInventory(String vin) {
        // TODO: Implement the logic to remove a vehicle from the inventory

        try (Connection connect = dataSource.getConnection();
             PreparedStatement statement = connect.prepareStatement(
                     "DELETE FROM vehicles WHERE VIN = ?")) {
            statement.setString(1, vin);

            int rows = statement.executeUpdate();
            System.out.println("Rows Deleted: " + rows);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
