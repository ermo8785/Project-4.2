// import javax.lang.model.util.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public abstract class Staff implements SysOut {
    String name;
    double salary;  // daily salary
    double salaryEarned;
    double bonusEarned;
    boolean injured;
    Enums.StaffType type;
    int daysWorked;
    int racesWon;

    public String textOut = " ";
    Staff() {
        salaryEarned = 0;
        bonusEarned = 0;
        daysWorked = 0;
    }

    // utility for getting Staff by Type
    // You could do this with getClass instead of Type, but I use the enum
    // because it's clearer to me - less Java-y
    static ArrayList<Staff> getStaffByType(ArrayList<Staff> staffList, Enums.StaffType t) {
        ArrayList<Staff> subclassInstances = new ArrayList<>();
        for (Staff s : staffList) {
            if (s.type == t) subclassInstances.add(s);
        }
        return subclassInstances;
    }

    //Utility for finding out how many of a Staff type there are
    static int howManyStaffByType(ArrayList<Staff> staffList, Enums.StaffType t) {
        int n = 0;
        for (Staff s: staffList) {
            if (s.type == t) n++;
        }
        return n;
    }
}

class Intern extends Staff {
    static List<String> names = Arrays.asList("Fred","Ethel","Lucy","Desi");
    static Namer namer = new Namer(names);
    static List<String> washingMethods = Arrays.asList("Chemical", "Elbow Grease", "Detailed");
    String washMethod;

    String getWashMethod(){
        int index = (int) (Math.random() * washingMethods.size());
        return washingMethods.get(index);
    }

    Intern() {
        super();
        type = Enums.StaffType.Intern;
        name = namer.getNext();  // every new intern gets a new name
        salary = 60; // daily salary
        washMethod = getWashMethod();
    }

    // How an intern washes cars
    // TODO: There's some duplication in this - it's a little clumsy - refactor me!
    
    void washVehicles(ArrayList<Vehicle> vList) {
        int washCount = 0;
        Enums.Cleanliness startAs;
        for (Vehicle v:vList) {
            // wash the first dirty car I see
            // Strategy pattern implementation on dirty cars
            if (v.cleanliness == Enums.Cleanliness.Dirty && washMethod == "Chemical") {
                washCount += 1;
                startAs = Enums.Cleanliness.Dirty;
                double chance = Utility.rnd();
                if (chance <= .8) v.cleanliness = Enums.Cleanliness.Clean;
                if (chance >.8 && chance <=.9) {
                    v.cleanliness = Enums.Cleanliness.Sparkling;
                    bonusEarned += v.wash_bonus;
                    out("Intern "+name+" got a bonus of "+Utility.asDollar(v.wash_bonus)+"!");
                }
                if(chance <= 0.10){
                    v.condition = Enums.Condition.Broken;
                }
                out("Intern "+name+" washed "+v.name+" "+startAs+" to "+v.cleanliness+ " using the " +washMethod+ " washing method.");
                if (washCount == 2) break;
            }
            else if (v.cleanliness == Enums.Cleanliness.Dirty && washMethod == "Elbow Grease") {
                washCount += 1;
                startAs = Enums.Cleanliness.Dirty;
                double chance = Utility.rnd();
                if (chance <= 0.70) v.cleanliness = Enums.Cleanliness.Clean;
                if (chance >.85 && chance <=.9) {
                    v.cleanliness = Enums.Cleanliness.Sparkling;
                    bonusEarned += v.wash_bonus;
                    out("Intern "+name+" got a bonus of "+Utility.asDollar(v.wash_bonus)+"!");
                }
                if(chance <= 0.10){
                    v.condition = Enums.Condition.Broken;
                }
                out("Intern "+name+" washed "+v.name+" "+startAs+" to "+v.cleanliness+ " using the " +washMethod+ " washing method.");
                if (washCount == 2) break;
            }
            else if (v.cleanliness == Enums.Cleanliness.Dirty && washMethod == "Detailed") {
                washCount += 1;
                startAs = Enums.Cleanliness.Dirty;
                double chance = Utility.rnd();
                if (chance <= .6) v.cleanliness = Enums.Cleanliness.Clean;
                if (chance >.7 && chance <=.9) {
                    v.cleanliness = Enums.Cleanliness.Sparkling;
                    bonusEarned += v.wash_bonus;
                    out("Intern "+name+" got a bonus of "+Utility.asDollar(v.wash_bonus)+"!");
                }
                if(chance <= 0.10){
                    v.condition = Enums.Condition.Broken;
                }
                out("Intern "+name+" washed "+v.name+" "+startAs+" to "+v.cleanliness+ " using the " +washMethod+ " washing method.");
                if (washCount == 2) break;
            }
        }
        /* Strategy pattern implementation on clean cars */
        if (washCount<2) {
            for (Vehicle v:vList) {
                // wash the first clean car I see
                if (v.cleanliness == Enums.Cleanliness.Clean && washMethod == "Chemical") {
                    washCount += 1;
                    startAs = Enums.Cleanliness.Clean;
                    double chance = Utility.rnd();
                    if (chance <= .10) v.cleanliness = Enums.Cleanliness.Dirty;
                    if (chance >.15 && chance <=.35) {
                        v.cleanliness = Enums.Cleanliness.Sparkling;
                        bonusEarned += v.wash_bonus;
                        out("Intern "+name+" got a bonus of "+Utility.asDollar(v.wash_bonus)+"!");
                    }
                    if(chance <= 0.10){
                        v.condition = Enums.Condition.Broken;
                    }
                    out("Intern "+name+" washed "+v.name+" "+startAs+" to "+v.cleanliness+ " using the " +washMethod+ " washing method.");
                    if (washCount == 2) break;
                }
                else if (v.cleanliness == Enums.Cleanliness.Clean && washMethod == "Elbow Grease") {
                    washCount += 1;
                    startAs = Enums.Cleanliness.Clean;
                    double chance = Utility.rnd();
                    if (chance <= .15) v.cleanliness = Enums.Cleanliness.Dirty;
                    if (chance >.20 && chance <=.35) {
                        v.cleanliness = Enums.Cleanliness.Sparkling;
                        bonusEarned += v.wash_bonus;
                        out("Intern "+name+" got a bonus of "+Utility.asDollar(v.wash_bonus)+"!");
                    }
                    if(chance <= 0.10){
                        v.condition = Enums.Condition.LikeNew;
                    }
                    out("Intern "+name+" washed "+v.name+" "+startAs+" to "+v.cleanliness+ " using the " +washMethod+ " washing method.");
                    if (washCount == 2) break;
                }
                else if (v.cleanliness == Enums.Cleanliness.Clean && washMethod == "Detailed") {
                    washCount += 1;
                    startAs = Enums.Cleanliness.Clean;
                    double chance = Utility.rnd();
                    if (chance <= .05) v.cleanliness = Enums.Cleanliness.Dirty;
                    if (chance >.10 && chance <=.50) {
                        v.cleanliness = Enums.Cleanliness.Sparkling;
                        bonusEarned += v.wash_bonus;
                        out("Intern "+name+" got a bonus of "+Utility.asDollar(v.wash_bonus)+"!");
                    }
                    out("Intern "+name+" washed "+v.name+" "+startAs+" to "+v.cleanliness+ " using the " +washMethod+ " washing method.");
                    if (washCount == 2) break;
                }
            }
        }
    }
}

class Mechanic extends Staff {
    static List<String> names = Arrays.asList("James", "Scotty", "Spock", "Uhura");
    static Namer namer = new Namer(names);
    Mechanic() {
        super();
        type = Enums.StaffType.Mechanic;
        name = namer.getNext();  // every new mechanic gets a new name
        salary = 120; // daily salary
    }

    // how Mechanics repair Vehicles - not as complicated as the Wash thing above
    void repairVehicles(ArrayList<Vehicle> vList) {
        int fixCount = 0;
        Enums.Condition startAs;
        // I'm just grabbing the first Vehicle I find - would be easy to randomly pick one
        for (Vehicle v: vList) {
            if (v.condition != Enums.Condition.LikeNew) {
                startAs = v.condition;
                if (v.cleanliness == Enums.Cleanliness.Clean) v.cleanliness = Enums.Cleanliness.Dirty;
                if (v.cleanliness == Enums.Cleanliness.Sparkling) v.cleanliness = Enums.Cleanliness.Clean;
                double chance = Utility.rnd();
                if (chance < .8) {
                    fixCount += 1;
                    if (v.condition == Enums.Condition.Used) {
                        v.condition = Enums.Condition.LikeNew;
                        v.price = v.price * 1.25;  // 25% increase for Used to Like New
                    }
                    if (v.condition == Enums.Condition.Broken) {
                        v.condition = Enums.Condition.Used;
                        v.price = v.price * 1.5;  // 50% increase for Broken to Used
                    }
                    bonusEarned += v.repair_bonus;
                    out("Mechanic "+name+" got a bonus of "+Utility.asDollar(v.repair_bonus)+"!");
                    out("Mechanic "+name+" fixed "+v.name+" "+startAs+" to "+v.condition);
                }
                else {
                    fixCount += 1;   // I'm saying a failed repair still took up a fix attempt
                    out("Mechanic "+name+" did not fix the "+v.condition+" "+v.name);
                }
            }
            if (fixCount==2) break;
        }
    }
}
class Salesperson extends Staff {
    static List<String> names = Arrays.asList("Rachel","Monica","Phoebe","Chandler","Ross","Joey");
    static Namer namer = new Namer(names);
    Salesperson() {
        super();
        type = Enums.StaffType.Salesperson;
        name = namer.getNext();  // every new salesperson gets a new name
        salary = 90; // daily salary
    }

    // Someone is asking this Salesperson to sell to this Buyer
    // We'll return any car we sell for the FNCD to keep track of (null if no sale)
    Vehicle sellVehicle(Buyer b, ArrayList<Vehicle> vList) {
        // buyer type determines initial purchase chance
        double saleChance = .7; // needs one
        if (b.type == Enums.BuyerType.WantsOne) saleChance = .4;
        if (b.type == Enums.BuyerType.JustLooking) saleChance = .1;

        // find the most expensive vehicle of the type the buyer wants that isn't broken
        // sales chance +10% if Like New, + 10% if Sparkling
        // if no vehicles of type, find remaining most expensive vehicle and sell at -20%
        ArrayList<Vehicle> desiredList = Vehicle.getVehiclesByType(vList, b.preference);
        Vehicle v;
        v = getMostExpensiveNotBroken(desiredList);  // could be null
        if (v == null) {
            // no unbroken cars of preferred type
            saleChance -= .2;
            v = getMostExpensiveNotBroken(vList);  // could still be null
        }
        if (v == null) {
            out("Salesperson "+name+" has no car for buyer "+b.name);
            return null;
        }
        else { //sell this car!
            if (v.condition == Enums.Condition.LikeNew) saleChance += .1;
            if (v.cleanliness == Enums.Cleanliness.Sparkling) saleChance += .1;
            double chance = Utility.rnd();
            if (chance<=saleChance) {  // sold
                /* Half implementation of decorator */
                if (chance <= .25){ // buyer may add extended warranty, undercoating, road rescue coverage, and/or satellite radio
                    v.price += v.price * .20;
                    out("Buyer " + b.name + " is adding extended warranty to their vehicle, making the price $" +v.price);
                }
                if(chance <= .10){
                    v.price += v.price * .5;
                    out("Buyer " + b.name + " is adding undercoating to their vehicle, making the price $" +v.price);
                }
                if(chance <= .05){
                    v.price += v.price * .2;
                    out("Buyer " + b.name + " is adding road rescue coverage to their vehicle, making the price $" +v.price);
                }
                if(chance <= .40){
                    v.price += v.price * .5;
                    out("Buyer " + b.name + " is adding satellite radio to their vehicle, making the price $" +v.price);
                }
                bonusEarned += v.sale_bonus;
                out("Buyer "+b.name+" is buying! Salesperson "+name+" gets a bonus of "+Utility.asDollar(v.sale_bonus)+"!");
                out("Buyer "+b.name+" bought "+v.cleanliness+" "+v.condition+" "+v.name+" for "+Utility.asDollar(v.price));
                return v;
            }
            else {  // no sale!
                out("Buyer "+b.name+" decided not to buy.");
                return null;
            }
        }
    }

    // Little helper for finding most expensive and not broken in a list of vehicles
    // Used twice by salespeople
    Vehicle getMostExpensiveNotBroken(ArrayList<Vehicle> vList) {
        double highPrice = 0;
        int selected = -1;
        for (int index=0;index<vList.size();++index) {
            Vehicle v = vList.get(index);
            if (v.price>highPrice) {
                if (v.condition != Enums.Condition.Broken) {
                    selected = index;
                    highPrice = v.price;
                }
            }
        }
        if (selected == -1) return null;
        else return vList.get(selected);
    }
}

class Driver extends Staff {
    static List<String> names = Arrays.asList("Eric", "Luke", "Hunt", "Laura","Chad");
    static Namer namer = new Namer(names);
    Driver() 
    {
        super();
        type = Enums.StaffType.Driver;
        name = namer.getNext();  // every new driver gets a new name
        salary = 300; // daily salary
        racesWon = 0;
        injured = false;
    }
    void raceVehicles(ArrayList <Vehicle> raceList){
        /*String vehicleType = getRandomVehicleType();
        ArrayList<Vehicle> raceList = new ArrayList<>();
        
        for (Vehicle v : inventory){
            if (v.type.toString() == vehicleType && raceList.size() != 3 && v.condition != Enums.Condition.Broken){
                raceList.add(v);
            }
        }*/
        if(raceList.size() < 3){
            out("Sorry, the FNCD does not have enough vehicles to participate in a race");
        }
        else{
            for (Vehicle v : raceList){
                int placement = (int) (Math.random()*20) + 1;
                double chance = Utility.rnd();
                if (placement < 4){
                    out(v.name+ " is a winner thanks to " +name+"!");
                    // increment races won
                    v.racesWon += 1;
                    // if win count 1 or more, sale price goes up 10%
                    v.price += v.price * .1;
                    // driver of the vehicle will increment a count of races won
                    racesWon += 1;
                    // driver receives bonus
                    bonusEarned += v.win_bonus;

                }
                else if (placement > 14){
                    out(name+ " did not win today.");
                    out(v.name+ " is damaged and must be fixed.");
                    v.condition = Enums.Condition.Broken;
                    if (chance <= .3){ // driver has 30% chance of being injured
                        injured = true;// Driver is injured so the boolean is set to true
                        // helper function where driver leaves
                    }
                }
            }
        }
    }
    String getRandomVehicleType(){
        int index = (int) (Math.random() * 6);
        if(Enums.VehicleType.values()[index].toString() == "Car" || Enums.VehicleType.values()[index].toString() == "ElectricCar"){
            index = (int) (Math.random() * 6);
        }
        return Enums.VehicleType.values()[index].toString();
    }

}



