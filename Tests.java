//import java.beans.Transient;
import org.junit.Assert;
import org.junit.Test;

//import Enums.DayOfWeek;

public class Tests{
    //1
    @Test
    public void initVehicles(){
        FNCD testFNCD = new FNCD();

        // there should be six of each vehicle type
        assert testFNCD.inventory.size() == 6 * 9;
    }

    //2
    @Test
    // there should be three of each staff type
    public void initEmployees(){
        FNCD testFNCD = new FNCD();
        assert testFNCD.inventory.size() == 3 * 4;
    }

    // 3
    @Test
    // 
    public void vehicleFactoryTest(){
        FNCD testFNCD = new FNCD();
        VehicleFactory testFactory = new VehicleFactory(testFNCD.inventory);

        int size = testFNCD.inventory.size();
        testFactory.createNewVehicle("PerfCar");

        int newSize = testFNCD.inventory.size();
        assert newSize == size + 1;
    }

    // 4
    @Test
    public void staffFactoryTest(){
        FNCD testFNCD = new FNCD();
        StaffFactory testFactory = new StaffFactory(testFNCD.staff);

        int size = testFNCD.staff.size();
        testFactory.createNewStaff("Salesperson");

        int newSize = testFNCD.inventory.size();
        assert newSize == size + 1;
    }
    
    // 5
    @Test
    public void loggerAndSingletonTest(){
        Enums.DayOfWeek simDay1 = Enums.DayOfWeek.Thur;
        Enums.DayOfWeek simDay2 = Enums.DayOfWeek.Thur;
        Logger instanceOne = Logger.getInstance(simDay1);
        Logger instanceTwo = Logger.getInstance(simDay2);
        Assert.assertSame("The two objects are the same, indicating well implemented Singleton pattern", instanceOne, instanceTwo);

    }
    // 6
    @Test
    public void singletonTrackerTest(){
        Tracker instanceOne = Tracker.getInstance("");
        Tracker instanceTwo = Tracker.getInstance("");
        Assert.assertSame("Passed: two selected objects are the same => Singleton", instanceOne, instanceTwo);
    }
    // 7
    @Test
    public void vehicleArrayTest(){
        FNCD testFNCD = new FNCD();
        for(Vehicle v : testFNCD.inventory){
            assert v instanceof Vehicle;
        }
    }
    // 8
    @Test public void staffArrayTest(){
        FNCD testFNCD = new FNCD();
        for(Staff s : testFNCD.staff){
            assert s instanceof Staff;
        }
    }
    // 9
    
    // 10

    // 11

    // 12

    // 13

    // 14

    // 15
}