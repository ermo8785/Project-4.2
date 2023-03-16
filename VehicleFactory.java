import java.util.ArrayList;

// Implementing factory pattern
public class VehicleFactory {
    ArrayList<Vehicle> vehicle;

    VehicleFactory(ArrayList<Vehicle> vehicleInv){
        vehicle = vehicleInv;
    }

    public Vehicle createNewVehicle(String vehicleType){
        if (vehicleType == null){
            return null;
        }
        
        if (vehicleType.equalsIgnoreCase("Car")){
            return new newCar(vehicle);
        }
        else if(vehicleType.equalsIgnoreCase("PerfCar")){
            return new newPerfCar(vehicle);
        }
        else if(vehicleType.equalsIgnoreCase("ElectricCar")){
            return new newElectricCar(vehicle);
        }
        else if(vehicleType.equalsIgnoreCase("MonsterTruck")){
            return new newMonsterTruck(vehicle);
        }
        else if(vehicleType.equalsIgnoreCase("Pickup")){
            return new newPickup(vehicle);
        }
        else if(vehicleType.equalsIgnoreCase("Motorcycle")){
            return new newMotorcycle(vehicle);
        }
        return null;
    }

}
interface newVehicle{
    void importCar();   
}
abstract class newVehicleType implements newVehicle {
    ArrayList<Vehicle> vehicle;

    newVehicleType(ArrayList<Vehicle> vehicleInventory){
        vehicle = vehicleInventory;
    }
}
class newCar extends newVehicleType{
    newCar(ArrayList<Vehicle> vehicleInv){
        super(vehicleInv);
    }
    @Override
    public void importCar(){
        Car newCar = new Car();
        vehicle.add(newCar);
    }
}

/*abstract class newVehicleType implements newVehicle {
    ArrayList<Vehicle> vehicle;

    newVehicleType(ArrayList<Vehicle> vehicleInventory){
        vehicle = vehicleInventory;
    }
}

class newCar extends newVehicleType {
    newCar(ArrayList<Vehicle> vehicleInventory){
        super(vehicleInventory);
    }

    @Override
    public void importCar(){
            Car newCar = new Car();
            vehicle.add(newCar);
    }
}

class newPerfCar extends newVehicleType {
    newPerfCar(ArrayList<Vehicle> vehicleInventory){
        super(vehicleInventory);
    }

    @Override
    public void importCar(){
            PerfCar newCar = new PerfCar();

            vehicle.add(newCar);
    }
}

class newPickup extends newVehicleType{
    newPickup(ArrayList<Vehicle> vehicleInventory){
        super(vehicleInventory);
    }

    @Override
    public void importCar(){
            Pickup newCar = new Pickup();

            vehicle.add(newCar);
        }

}

class newElectricCar extends newVehicleType{
    newElectricCar(ArrayList<Vehicle> vehicleInventory){
        super(vehicleInventory);
    }

    @Override
    public void importCar(){
            ElectricCar newCar = new ElectricCar();

            vehicle.add(newCar);

    }
}

class newMonsterTruck extends newVehicleType{
    newMonsterTruck(ArrayList<Vehicle> vehicleInventory){
        super(vehicleInventory);
    }

    @Override
    public void importCar(){
            MonsterTruck newCar = new MonsterTruck();

            vehicle.add(newCar);

    }
}
class newMotorcycle extends newVehicleType{
    newMotorcycle(ArrayList<Vehicle> vehicleInventory){
        super(vehicleInventory);
    }

    @Override
    public void importCar(){
            Motorcycle newCar = new Motorcycle();

            vehicle.add(newCar);
    }
}*/