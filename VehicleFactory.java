import java.util.ArrayList;

// Implementing factory pattern
public class VehicleFactory {
    public int simDay;
    ArrayList<Vehicle> vehicle;

    VehicleFactory(int day, ArrayList<Vehicle> vehicleInv){
        simDay = day;
        vehicle = vehicleInv;
    }

    public newVehicle createNewVehicle(String vehicleType){
        if (vehicleType == null){
            return null;
        }
        
        if (vehicleType.equalsIgnoreCase("Car")){
            return new newCar(day, vehicleInv);
        }
        else if(vehicleType.equalsIgnoreCase("PerfCar")){
            return new newPerfCar(day, vehicleInv);
        }
        return null;
    }

}
interface newVehicle{
    void makeCar();    
}

abstract class newVehicleType implements newVehicle {
    public int simDay;
    ArrayList<Vehicle> vehicle;

    newVehicleType(int day, ArrayList<Vehicle> vehicleInventory){
        simDay = day;
        vehicle = vehicleInventory;
    }
}

class newCar extends newVehicleType{
    newCar(int day, ArrayList<Vehicle> vehicleInventory){
        super(day, vehicleInventory);
    }

    @Override
    public void makeCar(){
        for(int i = 0; i < 3; i++){
            Car newCar = new Car();

            vehicle.add(newCar);
        }
    }
}

class newPerfCar extends newVehicleType{
    newPerfCar(int day, ArrayList<Vehicle> vehicleInventory){
        super(day, vehicleInventory);
    }

    @Override
    public void makeCar(){
        for(int i = 0; i < 3; i++){
            PerfCar newCar = new PerfCar();

            vehicle.add(newCar);
        }
    }
}

class newPickup extends newVehicleType{
    newPickup(int day, ArrayList<Vehicle> vehicleInventory){
        super(day, vehicleInventory);
    }

    @Override
    public void makeCar(){
        for(int i = 0; i < 3; i++){
            Pickup newCar = new Pickup();

            vehicle.add(newCar);
        }
    }
}

class newElectricCar extends newVehicleType{
    newElectricCar(int day, ArrayList<Vehicle> vehicleInventory){
        super(day, vehicleInventory);
    }

    @Override
    public void makeCar(){
        for(int i = 0; i < 3; i++){
            ElectricCar newCar = new ElectricCar();

            vehicle.add(newCar);
        }
    }
}

class newMonsterTruck extends newVehicleType{
    newMonsterTruck(int day, ArrayList<Vehicle> vehicleInventory){
        super(day, vehicleInventory);
    }

    @Override
    public void makeCar(){
        for(int i = 0; i < 3; i++){
            MonsterTruck newCar = new MonsterTruck();

            vehicle.add(newCar);
        }
    }
}
class newMotorcycle extends newVehicleType{
    newMotorcycle(int day, ArrayList<Vehicle> vehicleInventory){
        super(day, vehicleInventory);
    }

    @Override
    public void makeCar(){
        for(int i = 0; i < 3; i++){
            Motorcycle newCar = new Motorcycle();

            vehicle.add(newCar);
        }
    }
}