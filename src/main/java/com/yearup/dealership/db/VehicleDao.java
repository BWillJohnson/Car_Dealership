package com.yearup.dealership.db;

import com.yearup.dealership.models.Vehicle;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao {
    private DataSource dataSource;

    public VehicleDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addVehicle(Vehicle vehicle) {

        try (Connection connect = dataSource.getConnection();
             PreparedStatement statement = connect.prepareStatement(
                     "INSERT INTO vehicles (vin, make, model, year, SOLD, color, vehicleType, odometer, price) values (?,?,?,?,?,?,?,?,?)")) {
            statement.setString(1, vehicle.getVin());
            statement.setString(2, vehicle.getMake());
            statement.setString(3, vehicle.getModel());
            statement.setInt(4, vehicle.getYear());
            statement.setBoolean(5, vehicle.isSold());
            statement.setString(6, vehicle.getColor());
            statement.setString(7, vehicle.getVehicleType());
            statement.setInt(8, vehicle.getOdometer());
            statement.setDouble(9, vehicle.getPrice());
            statement.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void removeVehicle(String vin) {
        // TODO: Implement the logic to remove a vehicle
        try (Connection connect = dataSource.getConnection();
             PreparedStatement statement = connect.prepareStatement(
                     "DELETE FROM vehicles WHERE vin = ?")) {
            statement.setString(1, vin);

            int rows = statement.executeUpdate();
            System.out.println("Rows Deleted: " + rows);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<Vehicle> searchByPriceRange(double minPrice, double maxPrice) {
        // TODO: Implement the logic to search vehicles by price range
        List<Vehicle> vehicleList = new ArrayList<>();
        String priceRangeQuery = " SELECT *  FROM vehicles WHERE Price BETWEEN ? AND ?";
        try (Connection connect = dataSource.getConnection();
             PreparedStatement statement = connect.prepareStatement(priceRangeQuery)) {
            statement.setDouble(1, minPrice);
            statement.setDouble(2, maxPrice);

            try (ResultSet results = statement.executeQuery()){
                while (results.next()){
                    vehicleList.add(createVehicleFromResultSet(results));
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vehicleList;
    }

    public List<Vehicle> searchByMakeModel(String make, String model) {
        // TODO: Implement the logic to search vehicles by make and model
        List<Vehicle> vehicleList = new ArrayList<>();
        String makeAndModelQuery = "SELECT * FROM vehicles WHERE make = ? and model = ?";
        try (Connection connect = dataSource.getConnection();
        PreparedStatement statement = connect.prepareStatement(makeAndModelQuery)){
            statement.setString(1,make);
            statement.setString(2,model);
            try (ResultSet results = statement.executeQuery()){
                while (results.next()){
                    vehicleList.add(createVehicleFromResultSet(results));
                }
            }

        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return vehicleList;
    }

    public List<Vehicle> searchByYearRange(int minYear, int maxYear) {
        // TODO: Implement the logic to search vehicles by year range
        List<Vehicle> vehicleList = new ArrayList<>();
        String yearRangeQuery = " SELECT *  FROM vehicles WHERE Year BETWEEN ? AND ?";
        try (Connection connect = dataSource.getConnection();
             PreparedStatement statement = connect.prepareStatement(yearRangeQuery)) {
            statement.setInt(1, minYear);
            statement.setInt(2, maxYear);

            try (ResultSet results = statement.executeQuery()){
                while (results.next()){
                    vehicleList.add(createVehicleFromResultSet(results));
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vehicleList;
    }

    public List<Vehicle> searchByColor(String color) {
        // TODO: Implement the logic to search vehicles by color
        List<Vehicle> vehicleList = new ArrayList<>();
        String colorVehicleQuery = " SELECT *  FROM vehicles WHERE color = ? ";
        try (Connection connect = dataSource.getConnection();
             PreparedStatement statement = connect.prepareStatement(colorVehicleQuery)) {
            statement.setString(1, color);

            try (ResultSet results = statement.executeQuery()){
                while (results.next()){
                    vehicleList.add(createVehicleFromResultSet(results));
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vehicleList;
    }

    public List<Vehicle> searchByMileageRange(int minMileage, int maxMileage) {
        // TODO: Implement the logic to search vehicles by mileage range
        List<Vehicle> vehicleList = new ArrayList<>();
        String odometerRangeQuery = " SELECT *  FROM vehicles WHERE odometer BETWEEN ? AND ?";
        try (Connection connect = dataSource.getConnection();
             PreparedStatement statement = connect.prepareStatement(odometerRangeQuery)) {
            statement.setInt(1, minMileage);
            statement.setInt(2, maxMileage);

            try (ResultSet results = statement.executeQuery()){
                while (results.next()){
                    vehicleList.add(createVehicleFromResultSet(results));
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vehicleList;
    }

    public List<Vehicle> searchByType(String type) {
        // TODO: Implement the logic to search vehicles by type
        List<Vehicle> vehicleList = new ArrayList<>();
        String vehicleTypeQuery = " SELECT *  FROM vehicles WHERE type = ? ";
        try (Connection connect = dataSource.getConnection();
             PreparedStatement statement = connect.prepareStatement(vehicleTypeQuery)) {
            statement.setString(1, type);

            try (ResultSet results = statement.executeQuery()){
                while (results.next()){
                    vehicleList.add(createVehicleFromResultSet(results));
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vehicleList;
    }

    private Vehicle createVehicleFromResultSet(ResultSet resultSet) throws SQLException {
        Vehicle vehicle = new Vehicle();
        vehicle.setVin(resultSet.getString("VIN"));
        vehicle.setMake(resultSet.getString("make"));
        vehicle.setModel(resultSet.getString("model"));
        vehicle.setYear(resultSet.getInt("year"));
        vehicle.setSold(resultSet.getBoolean("SOLD"));
        vehicle.setColor(resultSet.getString("color"));
        vehicle.setVehicleType(resultSet.getString("vehicleType"));
        vehicle.setOdometer(resultSet.getInt("odometer"));
        vehicle.setPrice(resultSet.getDouble("price"));
        return vehicle;
    }
}
