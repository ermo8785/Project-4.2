import java.util.ArrayList;
// Factory pattern
public class VehicleFactory {
    ArrayList<Vehicle> vehicle;

    VehicleFactory(ArrayList<Vehicle> inventory){
        vehicle = inventory;
    }
    public newVehicle createNewVehicle(String vehicleType){
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
        else if(vehicleType.equalsIgnoreCase("Semi")){
            return new newSemi(vehicle);
        }
        else if(vehicleType.equalsIgnoreCase("Offroad")){
            return new newOffroad(vehicle);
        }
        else if(vehicleType.equalsIgnoreCase("Luxury")){
            return new newLuxury(vehicle);
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

class newCar extends newVehicleType {
    newCar(ArrayList<Vehicle> vehicleInventory){
        super(vehicleInventory);
    }

    @Override
    public void importCar(){

        for(int i = 0; i < 6; i++){
            Car newCar = new Car();
            vehicle.add(newCar);
        }
    }
}

class newPerfCar extends newVehicleType {
    newPerfCar(ArrayList<Vehicle> vehicleInventory){
        super(vehicleInventory);
    }

    @Override
    public void importCar(){
        for(int i = 0; i < 6; i++){
            PerfCar newCar = new PerfCar();
            vehicle.add(newCar);
        }
    }
}

class newPickup extends newVehicleType{
    newPickup(ArrayList<Vehicle> vehicleInventory){
        super(vehicleInventory);
    }

    @Override
    public void importCar(){
        for(int i = 0; i < 6; i++){
            Pickup newCar = new Pickup();
            vehicle.add(newCar);
        }
    }

}

class newElectricCar extends newVehicleType{
    newElectricCar(ArrayList<Vehicle> vehicleInventory){
        super(vehicleInventory);
    }

    @Override
    public void importCar(){
        for(int i = 0; i < 6; i++){
            ElectricCar newCar = new ElectricCar();
            vehicle.add(newCar);
        }

    }
}

class newMonsterTruck extends newVehicleType{
    newMonsterTruck(ArrayList<Vehicle> vehicleInventory){
        super(vehicleInventory);
    }

    @Override
    public void importCar(){
        for(int i = 0; i < 6; i++){
            MonsterTruck newCar = new MonsterTruck();
            vehicle.add(newCar);
        }
    }
}
class newMotorcycle extends newVehicleType{
    newMotorcycle(ArrayList<Vehicle> vehicleInventory){
        super(vehicleInventory);
    }

    @Override
    public void importCar(){
        for(int i = 0; i < 6; i++){
            Motorcycle newCar = new Motorcycle();
            vehicle.add(newCar);
        }
    }
}

class newSemi extends newVehicleType{
    newSemi(ArrayList<Vehicle> vehicleInventory){
        super(vehicleInventory);
    }

    @Override
    public void importCar(){
        for(int i = 0; i < 6; i++){
            Semi newCar = new Semi();
            vehicle.add(newCar);
        }
    }
}

class newOffroad extends newVehicleType{
    newOffroad(ArrayList<Vehicle> vehicleInventory){
        super(vehicleInventory);
    }

    @Override
    public void importCar(){
        for(int i = 0; i < 6; i++){
            Offroad newCar = new Offroad();
            vehicle.add(newCar);
        }
    }
}

class newLuxury extends newVehicleType{
    newLuxury(ArrayList<Vehicle> vehicleInventory){
        super(vehicleInventory);
    }

    @Override
    public void importCar(){
        for(int i = 0; i < 6; i++){
            Luxury newCar = new Luxury();
            vehicle.add(newCar);
        }
    }
}
