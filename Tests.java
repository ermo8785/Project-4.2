//import java.beans.Transient;
import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;

//import Enums.DayOfWeek;

public class Tests{
    @Test
    public void initVehicles(){
        FNCD testFNCD = new FNCD();

        // there should at least be six of each vehicle type
        assert testFNCD.inventory.size() == 54 ; // 6 of each vehicle type, there are 9 types
    }
    @Test
    // there should at least be three of each staff type
    public void initEmployees(){
        FNCD testFNCD = new FNCD();
        assert testFNCD.staff.size() == 12 ; // 3 of each staff type, there are 4 types
    }
    @Test
    // 
    public void vehicleFactoryTest(){
        FNCD testFNCD = new FNCD();
        VehicleFactory testFactory = new VehicleFactory(testFNCD.inventory);

        int size = testFNCD.inventory.size();
        testFactory.createNewVehicle("PerfCar").importCar();

        int newSize = testFNCD.inventory.size();
        assert newSize > size;
    }
    @Test
    public void staffFactoryTest(){
        FNCD testFNCD = new FNCD();
        StaffFactory testFactory = new StaffFactory(testFNCD.staff);

        int size = testFNCD.staff.size();
        testFactory.createNewStaff("Salesperson").addNewStaff();

        int newSize = testFNCD.staff.size();
        assert newSize > size;
    }
    @Test
    public void loggerAndSingletonTest(){
        Enums.DayOfWeek simDay1 = Enums.DayOfWeek.Thur;
        Enums.DayOfWeek simDay2 = Enums.DayOfWeek.Thur;
        Logger instanceOne = Logger.getInstance(simDay1);
        Logger instanceTwo = Logger.getInstance(simDay2);
        Assert.assertSame("The two objects are the same, indicating well implemented Singleton pattern", instanceOne, instanceTwo);

    }
    @Test
    public void singletonTrackerTest(){
        Tracker instanceOne = Tracker.getInstance("");
        Tracker instanceTwo = Tracker.getInstance("");
        Assert.assertSame("Passed: two selected objects are the same => Singleton", instanceOne, instanceTwo);
    }
    @Test
    public void vehicleArrayTest(){
        FNCD testFNCD = new FNCD();
        for(Vehicle v : testFNCD.inventory){
            assert v instanceof Vehicle;
        }
    }
    @Test 
    public void staffArrayTest(){
        FNCD testFNCD = new FNCD();
        for(Staff s : testFNCD.staff){
            assert s instanceof Staff;
        }
    }
    @Test
    public void departedStaffArrayTest(){
        FNCD testFNCD = new FNCD();
        for (Staff s : testFNCD.departedStaff){
            assert s instanceof Staff;
        }
    }
    @Test
    public void soldArrayTest(){
        FNCD testFNCD = new FNCD();
        for (Vehicle v : testFNCD.soldVehicles){
            assert v instanceof Vehicle;
        }
    }
    @Test
    public void getBuyerTest(){
        FNCD testFNCD = new FNCD();
        testFNCD.getBuyers(Enums.DayOfWeek.Tue);
        assert testFNCD.getBuyers(Enums.DayOfWeek.Tue) instanceof ArrayList<Buyer>;
    }
    @Test
    public void getRandomVehicleTypeTest(){
        FNCD testFNCD = new FNCD();
        String vehicleType = testFNCD.getRandomVehicleType();

        assert vehicleType == "Car" || vehicleType == "PerfCar" || vehicleType == "ElectricCar"
        || vehicleType == "Pickup" || vehicleType == "MonsterTruck" || vehicleType == "Luxury"
        || vehicleType == "Semi" || vehicleType == "Offroad" || vehicleType == "Motorcycle";
    }
    @Test
    public void addVehicleTest(){
        FNCD testFNCD = new FNCD();
        int size = testFNCD.inventory.size();
        testFNCD.addVehicle(Enums.VehicleType.Car);

        int newSize = testFNCD.inventory.size();

        assert newSize > size;
        
    }
    @Test 
    public void updateInventoryTest(){
        FNCD testFNCD = new FNCD();
        testFNCD.inventory.remove(0);
        testFNCD.inventory.remove(3);

        testFNCD.updateInventory();
        int newSize = testFNCD.inventory.size();
        assert newSize == 54;
    }
    @Test
    public void hireStaffTest(){
        FNCD testFNCD = new FNCD();

        testFNCD.staff.remove(0);

        testFNCD.hireNewStaff();
        int size = testFNCD.staff.size();
        assert size >= 13;
    }
}