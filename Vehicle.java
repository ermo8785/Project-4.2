import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public abstract class Vehicle {
    String name;
    Enums.VehicleType type;
    Enums.Condition condition;
    Enums.Cleanliness cleanliness;
    double cost;
    double price;
    double repair_bonus;
    double wash_bonus;
    double sale_bonus;
    double win_bonus;
    int racesWon;
    int range; // Electric cars have unique range attribute, randomly set between 60 and 400 miles
    int engineSizeRate; // generated from truncated normal distribution with mean 700 and std. dev. 300.
    public Vehicle () {
        // all vehicles have the same cleanliness arrival chance
        double chance = Utility.rnd();
        if (chance <= .05) cleanliness = Enums.Cleanliness.Sparkling;
        else if (chance>.05 && chance<=.4) cleanliness = Enums.Cleanliness.Clean;
        else cleanliness = Enums.Cleanliness.Dirty;
        // all vehicles have the same condition arrival chance (even chance of any)
        condition = Utility.randomEnum(Enums.Condition.class);
    }

    // utility for getting adjusted cost by condition
    double getCost(int low,int high) {
        double cost = Utility.rndFromRange(low, high);
        if (condition== Enums.Condition.Used) cost = cost*.8;
        if (condition== Enums.Condition.Broken) cost = cost*.5;
        return cost;
    }
    int getRange(int low, int high){
        int range = Utility.rndFromRange(low, high);
        return range;
    }

    // utility for getting Vehicles by Type
    // You could do this with getClass instead of Type, but I use the enum
    // because it's clearer to me (less Java-y)
    static ArrayList<Vehicle> getVehiclesByType(ArrayList<Vehicle> vehicleList, Enums.VehicleType t) {
        ArrayList<Vehicle> subclassInstances = new ArrayList<>();
        for (Vehicle v : vehicleList) {
            if (v.type == t) subclassInstances.add(v);
        }
        return subclassInstances;
    }

    // Utility for finding out how many of a Vehicle there are
    static int howManyVehiclesByType(ArrayList<Vehicle> vehicleList, Enums.VehicleType t) {
        int n = 0;
        for (Vehicle v: vehicleList) {
            if (v.type == t) n++;
        }
        return n;
    }
}

class Car extends Vehicle {
    // could make the name list longer to avoid as many duplicates if you like...
    static List<String> names = Arrays.asList("Probe","Escort","Taurus","Fiesta");
    static Namer namer = new Namer(names);
    Car() {
        super();
        type = Enums.VehicleType.Car;
        name = namer.getNext();  // every new car gets a new name
        cost = getCost(10000,20000);
        price = cost * 2;
        repair_bonus = 100;
        wash_bonus = 20;
        sale_bonus = 500;
    }
    
}

class PerfCar extends Vehicle {
    static List<String> names = Arrays.asList("Europa","Cayman","Corvette","Mustang");
    static Namer namer = new Namer(names);
    PerfCar() {
        super();
        type = Enums.VehicleType.PerfCar;
        name = namer.getNext();  // every new perf car gets a unique new name
        cost = getCost(20000,40000);
        price = cost * 2;
        repair_bonus = 300;
        wash_bonus = 100;
        sale_bonus = 1000;
        win_bonus = 500;
        racesWon = 0;
    }
}

class Pickup extends Vehicle {
    static List<String> names = Arrays.asList("Ranger","F-250","Colorado","Tundra");
    static Namer namer = new Namer(names);
    Pickup() {
        super();
        type = Enums.VehicleType.Pickup;
        name = namer.getNext();  // every new truck gets a unique new name
        cost = getCost(10000,40000);
        price = cost * 2;
        repair_bonus = 200;
        wash_bonus = 75;
        sale_bonus = 750;
        win_bonus = 500;
        racesWon = 0;
    }
}

class ElectricCar extends Vehicle{
    static List<String> names = Arrays.asList("Tesla", "Mustang", "Prius", "Sorrento", "Charger");
    static Namer namer = new Namer(names);

    ElectricCar(){
        super();
        type = Enums.VehicleType.ElectricCar;
        name = namer.getNext();
        cost = getCost(10000, 40000);
        price = cost * 2;
        repair_bonus = 200;
        wash_bonus = 75;
        sale_bonus = 750;
        range = getRange(40, 600);
    }
}

class Motorcycle extends Vehicle{
    static List<String> names = Arrays.asList("Harley Davidson", "Kawasaki", "Suzuki", "Mitsubishi", "Ninja");
    static Namer namer = new Namer(names);
    Motorcycle(){
        super();
        type = Enums.VehicleType.Motorcycle;
        name = namer.getNext();
        cost = getCost(10000, 40000);
        price = cost * 2;
        repair_bonus = 200;
        wash_bonus = 75;
        sale_bonus = 750;
        win_bonus = 500;
        racesWon = 0;
        //engineSizeRate = 
    }
}

class MonsterTruck extends Vehicle{
    static List<String> names = Arrays.asList("Avenger", "Grave Digger", "Zombie", "War Wizard", "Sudden Impact");
    static Namer namer = new Namer(names);

    MonsterTruck(){
        super();
        type = Enums.VehicleType.MonsterTruck;
        name = namer.getNext();
        cost = getCost(10000, 40000);
        price = (cost * 2);
        repair_bonus = 200;
        wash_bonus = 75;
        sale_bonus = 750;
        win_bonus = 500;
        racesWon = 0;
    }
}

class Luxury extends Vehicle {
    static List<String>names = Arrays.asList("Mercedez, BMW, Acura, Roles Royce, Tesla");
    static Namer namer = new Namer(names);

    Luxury(){
        super();
        type = Enums.VehicleType.Luxury;
        name = namer.getNext();
        cost = getCost(30000, 80000);
        price = (cost * 2);
        repair_bonus = 400;
        wash_bonus = 125;
        sale_bonus = 950;
    }
}

class Offroad extends Vehicle {
    static List<String>names = Arrays.asList("Mercedez, BMW, Acura, Roles Royce, Tesla");
    static Namer namer = new Namer(names);

    Offroad(){
        super();
        type = Enums.VehicleType.Offroad;
        name = namer.getNext();
        cost = getCost(25000, 60000);
        price = (cost * 2);
        repair_bonus = 800;
        wash_bonus = 100;
        sale_bonus = 800;
    }
}

class Semi extends Vehicle {
    static List<String>names = Arrays.asList("Mercedez, BMW, Acura, Roles Royce, Tesla");
    static Namer namer = new Namer(names);

    Semi(){
        super();
        type = Enums.VehicleType.Semi;
        name = namer.getNext();
        cost = getCost(85000, 175000);
        price = (cost * 2);
        repair_bonus = 2000;
        wash_bonus = 1500;
    }
}
