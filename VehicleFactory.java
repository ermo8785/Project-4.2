import java.util.ArrayList;

// Implementing factory pattern
public class VehicleFactory {
    public Enums.DayOfWeek simDay;
    ArrayList<Vehicle> vehicle;

    VehicleFactory(Enums.DayOfWeek day, ArrayList<Vehicle> vehicleInv){
        simDay = day;
        vehicle = vehicleInv;
    }

    public newVehicle createNewVehicle(String vehicleType){
        if (vehicleType == null){
            return null;
        }
        
        if (vehicleType.equalsIgnoreCase("Car")){
            return new newCar(simDay, vehicle);
        }
        else if(vehicleType.equalsIgnoreCase("PerfCar")){
            return new newPerfCar(simDay, vehicle);
        }
        else if(vehicleType.equalsIgnoreCase("ElectricCar")){
            return new newElectricCar(simDay, vehicle);
        }
        else if(vehicleType.equalsIgnoreCase("MonsterTruck")){
            return new newMonsterTruck(simDay, vehicle);
        }
        else if(vehicleType.equalsIgnoreCase("Pickup")){
            return new newPickup(simDay, vehicle);
        }
        else if(vehicleType.equalsIgnoreCase("Motorcycle")){
            return new newMotorcycle(simDay, vehicle);
        }
        return null;
    }

}
interface newVehicle{
    void makeCar();    
}

abstract class newVehicleType implements newVehicle {
    public Enums.DayOfWeek simDay;
    ArrayList<Vehicle> vehicle;

    newVehicleType(Enums.DayOfWeek day, ArrayList<Vehicle> vehicleInventory){
        simDay = day;
        vehicle = vehicleInventory;
    }
}

class newCar extends newVehicleType{
    newCar(Enums.DayOfWeek day, ArrayList<Vehicle> vehicleInventory){
        super(day, vehicleInventory);
    }

    @Override
    public void makeCar(){
        for(int i = 0; i < 6; i++){
            Car newCar = new Car();

            vehicle.add(newCar);
        }
    }
}

class newPerfCar extends newVehicleType{
    newPerfCar(Enums.DayOfWeek day, ArrayList<Vehicle> vehicleInventory){
        super(day, vehicleInventory);
    }

    @Override
    public void makeCar(){
        for(int i = 0; i < 6; i++){
            PerfCar newCar = new PerfCar();

            vehicle.add(newCar);
        }
    }
}

class newPickup extends newVehicleType{
    newPickup(Enums.DayOfWeek day, ArrayList<Vehicle> vehicleInventory){
        super(day, vehicleInventory);
    }

    @Override
    public void makeCar(){
        for(int i = 0; i < 6; i++){
            Pickup newCar = new Pickup();

            vehicle.add(newCar);
        }
    }
}

class newElectricCar extends newVehicleType{
    newElectricCar(Enums.DayOfWeek day, ArrayList<Vehicle> vehicleInventory){
        super(day, vehicleInventory);
    }

    @Override
    public void makeCar(){
        for(int i = 0; i < 6; i++){
            ElectricCar newCar = new ElectricCar();

            vehicle.add(newCar);
        }
    }
}

class newMonsterTruck extends newVehicleType{
    newMonsterTruck(Enums.DayOfWeek day, ArrayList<Vehicle> vehicleInventory){
        super(day, vehicleInventory);
    }

    @Override
    public void makeCar(){
        for(int i = 0; i < 6; i++){
            MonsterTruck newCar = new MonsterTruck();

            vehicle.add(newCar);
        }
    }
}
class newMotorcycle extends newVehicleType{
    newMotorcycle(Enums.DayOfWeek day, ArrayList<Vehicle> vehicleInventory){
        super(day, vehicleInventory);
    }

    @Override
    public void makeCar(){
        for(int i = 0; i < 6; i++){
            Motorcycle newCar = new Motorcycle();

            vehicle.add(newCar);
        }
    }
}