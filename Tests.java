//import java.beans.Transient;
import org.junit.Assert;
import org.junit.Test;

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

    // 6

    // 7

    // 8

    // 9

    // 10

    // 11

    // 12

    // 13

    // 14

    // 15
}