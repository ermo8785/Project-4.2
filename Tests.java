//import java.beans.Transient;
import org.junit.Assert;
import org.junit.Test;

public class Tests{
    StaffFactory staffFactory = new StaffFactory();
    Staff[] Intern = {
        (Staff) staffFactory.createNewStaff("Hansel Simpson", "Intern"),
        (Staff) staffFactory.createNewStaff("John Johnson", "Intern"),
        (Staff) staffFactory.createNewStaff("Johnny Cash", "Intern"),
        (Staff) staffFactory.createNewStaff("Raf Simons", "Intern")
    };

    Staff[] Mechanic = {
        (Staff) staffFactory.createNewStaff("Hansel Sims", "Mechanic"),
        (Staff) staffFactory.createNewStaff("John Johns", "Mechanic"),
        (Staff) staffFactory.createNewStaff("Johnny Cas", "Mechanic"),
        (Staff) staffFactory.createNewStaff("Raf Simon", "Mechanic")
    };

    Staff[] Salesperson ={
        (Staff) staffFactory.createNewStaff("Han Simpson", "Salesperson"),
        (Staff) staffFactory.createNewStaff("Jo Johnson", "Salesperson"),
        (Staff) staffFactory.createNewStaff("John Cash", "Salesperson"),
        (Staff) staffFactory.createNewStaff("Rafael Simons", "Salesperson")
    };

    //1
    @Test
    public void initVehicles(){
        FNCD testFNCD = new FNCD();

        // there should be six of each vehicle type
        assert testFNCD.inventory.size() == 6 * 6;
    }

    //2
    @Test
    // there should be three of each staff type
    public void initEmployees(){
        FNCD testFNCD = new FNCD();
        assert testFNCD.inventory.size() == 3 * 3;
    }

    // 3
    @Test
    // 
    public void vehicleFactoryTest(){
        FNCD testFNCD = new FNCD();
        VehicleFactory testFactory = new VehicleFactory(testFNCD.simDay, testFNCD.inventory);

        int size = testFNCD.inventory.size();
        testFactory.createNewVehicle("PerfCar");

        int newSize = testFNCD.inventory.size();
        assert newSize == size + 6;
    }

    
    
}