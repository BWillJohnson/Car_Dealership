package com.yearup.dealership.Main;

import com.yearup.dealership.db.InventoryDao;
import com.yearup.dealership.db.LeaseDao;
import com.yearup.dealership.db.SalesDao;
import com.yearup.dealership.db.VehicleDao;
import com.yearup.dealership.models.LeaseContract;
import com.yearup.dealership.models.SalesContract;
import com.yearup.dealership.models.Vehicle;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        String username = args[0];
        String password = args[1];

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/car_dealership");

        dataSource.setUsername(username);
        dataSource.setPassword(password);

        // Create instances of required DAO classes
        VehicleDao vehicleDao = new VehicleDao(dataSource);
        InventoryDao inventoryDao = new InventoryDao(dataSource);
        SalesDao salesDao = new SalesDao(dataSource);
        LeaseDao leaseDao = new LeaseDao(dataSource);

        // Create a Scanner object for user input
        Scanner scanner = new Scanner(System.in);

        boolean exit = false;
        while (!exit) {
            System.out.println("Main Menu:");
            System.out.println("1. Search vehicles");
            System.out.println("2. Add a vehicle");
            System.out.println("3. Add a contract");
            System.out.println("4. Remove a vehicle");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    searchVehiclesMenu(vehicleDao, scanner);
                    break;
                case 2:
                    addVehicleMenu(vehicleDao, inventoryDao, scanner);
                    break;
                case 3:
                    addContractMenu(salesDao, leaseDao, scanner);
                    break;
                case 4:
                    removeVehicleMenu(vehicleDao, inventoryDao, scanner);
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        scanner.close();
    }

    private static void addContractMenu(SalesDao salesDao, LeaseDao leaseDao, Scanner scanner) {
        System.out.print("Enter the VIN of the vehicle to add a contract: ");
        String vin = scanner.nextLine();

        System.out.println("\nSelect a contract type:");
        System.out.println("1. Sales Contract");
        System.out.println("2. Lease Contract");
        System.out.print("Enter your choice: ");
        int contractTypeChoice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        switch (contractTypeChoice) {
            case 1:
                addSalesContract(salesDao, vin, scanner);
                break;
            case 2:
                addLeaseContract(leaseDao, vin, scanner);
                break;
            default:
                System.out.println("Invalid choice. Contract not added.");
                break;
        }
    }

    private static void addSalesContract(SalesDao salesDao, String vin, Scanner scanner) {

        System.out.print("Enter the sale date (YYYY-MM-DD): ");
        String saleDateStr = scanner.nextLine();
        LocalDate saleDate = LocalDate.parse(saleDateStr);

        System.out.print("Enter the price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character

        SalesContract salesContract = new SalesContract(vin, saleDate, price);
        salesDao.addSalesContract(salesContract);

        System.out.println("Sales contract added successfully.");
    }

    private static void addLeaseContract(LeaseDao leaseDao, String vin, Scanner scanner) {

        System.out.print("Enter the lease start date (YYYY-MM-DD): ");
        String leaseStartDateStr = scanner.nextLine();
        LocalDate leaseStartDate = LocalDate.parse(leaseStartDateStr);

        System.out.print("Enter the lease end date (YYYY-MM-DD): ");
        String leaseEndDateStr = scanner.nextLine();
        LocalDate leaseEndDate = LocalDate.parse(leaseEndDateStr);

        System.out.print("Enter the monthly payment: ");
        double monthlyPayment = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character

        LeaseContract leaseContract = new LeaseContract(vin, leaseStartDate, leaseEndDate, monthlyPayment);
        leaseDao.addLeaseContract(leaseContract);

        System.out.println("Lease contract added successfully.");
    }


    private static void searchVehiclesMenu(VehicleDao vehicleDao, Scanner scanner) {
        boolean back = false;
        while (!back) {
            System.out.println("\nSearch Vehicles:");
            System.out.println("1. By price range");
            System.out.println("2. By make/model");
            System.out.println("3. By year range");
            System.out.println("4. By color");
            System.out.println("5. By mileage range");
            System.out.println("6. By type");
            System.out.println("7. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    searchByPriceRange(vehicleDao, scanner);
                    break;
                case 2:
                    searchByMakeAndModel(vehicleDao, scanner);
                    break;
                case 3:
                    searchByYearRange(vehicleDao, scanner);
                    break;
                case 4:
                    searchByColor(vehicleDao, scanner);
                    break;
                case 5:
                    searchByMileageRange(vehicleDao, scanner);
                    break;
                case 6:
                    searchByType(vehicleDao, scanner);
                    break;
                case 7:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void searchByPriceRange(VehicleDao vehicleDao, Scanner scanner) {
        System.out.print("Enter the minimum price: ");
        double minPrice = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character

        System.out.print("Enter the maximum price: ");
        double maxPrice = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character

        List<Vehicle> vehicles = vehicleDao.searchByPriceRange(minPrice, maxPrice);
        displaySearchResults(vehicles);
    }

    private static void searchByMakeAndModel(VehicleDao vehicleDao, Scanner scanner) {
        System.out.print("Enter the make: ");
        String make = scanner.nextLine();

        System.out.print("Enter the model: ");
        String model = scanner.nextLine();

        List<Vehicle> vehicles = vehicleDao.searchByMakeModel(make, model);
        displaySearchResults(vehicles);
    }

    private static void searchByYearRange(VehicleDao vehicleDao, Scanner scanner) {
        System.out.print("Enter the minimum year: ");
        int minYear = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        System.out.print("Enter the maximum year: ");
        int maxYear = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        List<Vehicle> vehicles = vehicleDao.searchByYearRange(minYear, maxYear);
        displaySearchResults(vehicles);
    }

    private static void searchByColor(VehicleDao vehicleDao, Scanner scanner) {
        System.out.print("Enter the color: ");
        String color = scanner.nextLine();

        List<Vehicle> vehicles = vehicleDao.searchByColor(color);
        displaySearchResults(vehicles);
    }

    private static void searchByMileageRange(VehicleDao vehicleDao, Scanner scanner) {
        System.out.print("Enter the minimum mileage: ");
        int minMileage = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        System.out.print("Enter the maximum mileage: ");
        int maxMileage = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        List<Vehicle> vehicles = vehicleDao.searchByMileageRange(minMileage, maxMileage);
        displaySearchResults(vehicles);
    }

    private static void searchByType(VehicleDao vehicleDao, Scanner scanner) {
        System.out.print("Enter the vehicle type: ");
        String type = scanner.nextLine();

        List<Vehicle> vehicles = vehicleDao.searchByType(type);
        displaySearchResults(vehicles);
    }

    private static void displaySearchResults(List<Vehicle> vehicles) {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found.");
        } else {
            System.out.println("\nSearch Results:");
            for (Vehicle vehicle : vehicles) {
                System.out.println(vehicle);
            }
        }
    }

    private static void addVehicleMenu(VehicleDao vehicleDao, InventoryDao inventoryDao, Scanner scanner) {

        String vin = generateRandomVin();

        System.out.print("Enter the make: ");
        String make = scanner.nextLine();

        System.out.print("Enter the model: ");
        String model = scanner.nextLine();

        System.out.print("Enter the year: ");
        int year = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        System.out.print("Enter the color: ");
        String color = scanner.nextLine();

        System.out.print("Enter the mileage: ");
        int mileage = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        System.out.print("Enter the price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character

        System.out.print("Enter the type: ");
        String type = scanner.nextLine();

        System.out.print("Enter the dealership ID: ");
        int dealershipId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Vehicle vehicle = new Vehicle(vin, make, model, year, false, color, type, mileage, price);
        vehicleDao.addVehicle(vehicle);
        inventoryDao.addVehicleToInventory(vin, dealershipId);

        System.out.println("Vehicle added successfully.");
    }

    private static void removeVehicleMenu(VehicleDao vehicleDao, InventoryDao inventoryDao, Scanner scanner) {
        System.out.print("Enter the VIN of the vehicle to remove: (Vehicle should not be already sold or leased) ");
        String vin = scanner.nextLine();

        inventoryDao.removeVehicleFromInventory(vin);
        vehicleDao.removeVehicle(vin);
        System.out.println("Vehicle removed successfully.");

    }

    public static String generateRandomVin() {
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString().toUpperCase().replaceAll("-", "");
        // Assuming VIN length is 17 characters, you can adjust this if needed
        String vin = randomUUIDString.substring(0, 17);
        return vin;
    }

}
