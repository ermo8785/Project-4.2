import java.util.ArrayList;

//import Enums.DayOfWeek;

//import Enums.Condition;
//import Enums.VehicleType;


// This represents the FNCD business and things they would control
public class FNCD implements SysOut {
    ArrayList<Staff> staff;  // folks working
    ArrayList<Staff> departedStaff;   // folks that left
    ArrayList<Vehicle> inventory;   // vehicles at the FNCD
    ArrayList<Vehicle> soldVehicles;   // vehicles the buyers bought
    private double budget;   // big money pile
    Enums.DayOfWeek simDay; 
    String textOut = " ";
    ArrayList<Tracker> dealerTracker;
    FNCD() {
        staff = new ArrayList<>();
        departedStaff = new ArrayList<>();
        inventory = new ArrayList<>();
        soldVehicles = new ArrayList<>();
        dealerTracker = new ArrayList<>();
        budget = 100000;  // I changed this just to see additions to the budget happen
    }
    double getBudget() {
        return budget;    // I'm keeping this private to be on the safe side
    }
    void moneyIn(double cash) {  // Nothing special about taking money in yet
        budget += cash;
    }
    void moneyOut(double cash) {   // I check for budget overruns on every payout
        budget -= cash;
        if (budget<0) {
            budget += 250000;
            out("***Budget overrun*** Added $250K, budget now: " + Utility.asDollar(budget));
        }
    }

    // Here's where I process daily activities
    // I debated about moving the individual activities out to an Activity class
    // It would make the normal day less of a monster maybe, eh...

    /*void closedDay(Enums.DayOfWeek day) {   // Nothing really special about closed days
        out("Sorry, FNCD is closed on "+day);
    }*/
    void raceDay(Enums.DayOfWeek day){
        // racing 

        hireNewStaff();    // hire up to 3 of each staff type
        
        String vehicleType = getRandomVehicleType();
        ArrayList<Vehicle> raceList = new ArrayList<>();
        for (Vehicle v : inventory){
            if (v.type.toString() == vehicleType && raceList.size() != 3 && v.condition != Enums.Condition.Broken){
                raceList.add(v);
            }
        }
        out("The FNCD is looking to prepare for a race...");
        textOut = textOut.concat("The FNCD is looking to prepare for a race...");
        out("Today's race features vehicles of type " +vehicleType);
        textOut = textOut.concat("Today's race features vehicles of type " +vehicleType);
        ArrayList<Staff> drivers = Staff.getStaffByType(staff, Enums.StaffType.Driver);
        for (Staff s:drivers){
            Driver d = (Driver) s;
            d.raceVehicles(raceList);
        }
        payStaff();
        // anyone quitting? replace with an intern (if not an intern)
        checkForQuitters();
        // daily report
        reportOut(day);
    }

    // Helper function to get random selection of vehicle type
    String getRandomVehicleType(){
        int index = (int) (Math.random() * 6);
        if(Enums.VehicleType.values()[index].toString() == "Car" || Enums.VehicleType.values()[index].toString() == "ElectricCar"){
            index = (int) (Math.random() * 6);
        }
        return Enums.VehicleType.values()[index].toString();
    }

    void normalDay(Enums.DayOfWeek day) {  // On a normal day, we do all the activities
        textOut = " ";
        // opening
        out("The FNCD is opening...");
        textOut = textOut.concat("The FNCD is opening... Day " + day+ " \n");

        hireNewStaff();    // hire up to 3 of each staff type
        updateInventory();  // buy up to 4 of each type
        // washing - tell the interns to do the washing up
        out("The FNCD interns are washing...");
        textOut = textOut.concat("The FNCD interns are washing... \n");
        ArrayList<Staff> interns = Staff.getStaffByType(staff, Enums.StaffType.Intern);
        for (Staff s:interns) {
            Intern i = (Intern) s;
            i.washVehicles(inventory);
        }

        // repairing - tell the mechanics to do their repairing
        out("The FNCD mechanics are repairing...");
        textOut = textOut.concat("The FNCD mechanics are repairing... \n");
        ArrayList<Staff> mechanics = Staff.getStaffByType(staff, Enums.StaffType.Mechanic);
        for (Staff s:mechanics) {
            Mechanic m = (Mechanic) s;
            m.repairVehicles(inventory);
        }

        // selling
        out("The FNCD salespeople are selling...");
        textOut = textOut.concat("The FNCD salespeople are selling... \n");
        ArrayList<Buyer> buyers = getBuyers(day);
        ArrayList<Staff> salespeople = Staff.getStaffByType(staff, Enums.StaffType.Salesperson);
        // tell a random salesperson to sell each buyer a car - may get bonus
        for(Buyer b: buyers) {
            out("Buyer "+b.name+" wants a "+b.preference+" ("+b.type+")");
            int randomSeller = Utility.rndFromRange(0,salespeople.size()-1);
            Salesperson seller = (Salesperson) salespeople.get(randomSeller);
            Vehicle vSold = seller.sellVehicle(b, inventory);
            // What the FNCD needs to do if a car is sold - change budget and inventory
            if (vSold != null) {
                soldVehicles.add(vSold);
                moneyIn(vSold.price);
                inventory.removeIf ( n -> n.name == vSold.name);
            }
        }

        // ending
        // pay all the staff their salaries
        payStaff();
        // anyone quitting? replace with an intern (if not an intern)
        checkForQuitters();
        // daily report
        reportOut(day);
    }

    // generate buyers
    ArrayList<Buyer> getBuyers(Enums.DayOfWeek day) {
        // 0 to 5 buyers arrive (2-8 on Fri/Sat)
        int buyerMin = 0;  //normal Mon-Thur
        int buyerMax = 5;
        if (day == Enums.DayOfWeek.Fri || day == Enums.DayOfWeek.Sat) {
            buyerMin = 2;
            buyerMax = 8;
        }
        ArrayList<Buyer> buyers = new ArrayList<Buyer>();
        int buyerCount = Utility.rndFromRange(buyerMin,buyerMax);
        for (int i=1; i<=buyerCount; ++i) buyers.add(new Buyer());
        out("The FNCD has "+buyerCount+" buyers today...");
        textOut = textOut.concat("The FNCD has "+buyerCount+" buyers today... \n");
        return buyers;
    }

    // see if we need any new hires
    void hireNewStaff() {
        final int numberInStaff = 3;
        for (Enums.StaffType t : Enums.StaffType.values()) {
            int typeInList = Staff.howManyStaffByType(staff, t);
            int need = numberInStaff - typeInList;
            for (int i = 1; i<=need; ++i) addStaff(t);
        }
    }

    // adding staff
    // smells like we need a factory or something...
    void addStaff(Enums.StaffType t) {
        Staff newStaff = null;
        if (t == Enums.StaffType.Intern) newStaff = new Intern();
        if (t == Enums.StaffType.Mechanic) newStaff = new Mechanic();
        if (t == Enums.StaffType.Salesperson) newStaff = new Salesperson();
        if (t == Enums.StaffType.Driver) newStaff = new Driver();
        out("Hired a new "+newStaff.type+" named "+ newStaff.name);
        textOut = textOut.concat("Hired a new "+newStaff.type+" named "+ newStaff.name+" \n");
        staff.add(newStaff);
    }

    // see if we need any vehicles
    void updateInventory() {
        final int numberInInventory = 6;
        for (Enums.VehicleType t : Enums.VehicleType.values()) {
            int typeInList = Vehicle.howManyVehiclesByType(inventory, t);
            int need = numberInInventory - typeInList;
            for (int i = 1; i<=need; ++i) addVehicle(t);
        }

    }

    // add a vehicle of a type to the inventory
    void addVehicle(Enums.VehicleType t) {
        Vehicle v = null;
        VehicleFactory vehFactory = new VehicleFactory(simDay, inventory);
        if (t == Enums.VehicleType.Car) v = vehFactory.createNewVehicle("car");
        /*if (t == Enums.VehicleType.PerfCar) v = new PerfCar();
        if (t == Enums.VehicleType.Pickup) v = new Pickup();
        if (t == Enums.VehicleType.MonsterTruck) v = new MonsterTruck();
        if (t == Enums.VehicleType.Motorcycle) v = new Motorcycle();
        if (t == Enums.VehicleType.ElectricCar) v = new ElectricCar();*/
        moneyOut(v.cost);  // pay for the vehicle
        out ("FNCD bought "+v.name+", a "+v.cleanliness+" "+v.condition+" "+v.type+" for "+Utility.asDollar(v.cost));
        textOut = textOut.concat("FNCD bought "+v.name+", a "+v.cleanliness+" "+v.condition+" "+v.type+" for "+Utility.asDollar(v.cost)+" \n");
        inventory.add(v);
    }

    // pay salary to staff and update days worked
    void payStaff() {
        for (Staff s: staff) {
            moneyOut(s.salary);  // money comes from the FNCD
            s.salaryEarned += s.salary;  // they get paid
            s.daysWorked += 1; // they worked another day

        }
    }

    // Huh - no one wants to quit my FNCD!
    // I left this as an exercise to the reader...
    void checkForQuitters() {
        ArrayList <Staff> salespeople = Staff.getStaffByType(staff, Enums.StaffType.Salesperson);
        ArrayList<Staff> interns = Staff.getStaffByType(staff, Enums.StaffType.Intern);
        ArrayList<Staff> mechanics = Staff.getStaffByType(staff, Enums.StaffType.Mechanic);
        double SalesQuit = Math.random(); // Chance of quitting for each employee 
        double MechQuit = Math.random();
        double InternQuit = Math.random();
        int count = 0;
        if(SalesQuit <= .1){ //Salesmen quits 
            int index_sales = Utility.rndFromRange(0,2); //Sets an index within the range 
            Salesperson SalesName = (Salesperson) salespeople.get(index_sales); //Sets the name of the salesmen whos wuitting 
            departedStaff.add(SalesName); //Adds to departed staff array list 
            for (int i = 0; i < staff.size(); i++){ //Traverses the staff array list to remove salesperson 
                if (staff.get(i) == SalesName){
                    staff.remove(i); //Removes the name of employee who is quitting FNCD
                }
            }
            out("Salesperson " + SalesName.name + " has quit the FNCD");
            textOut = textOut.concat("Salesperson " + SalesName.name + " has quit the FNCD \n");
            if (interns.size() > 0){ //Need interns to promote 
                int index_intern = Utility.rndFromRange(0, 2);
                Staff LuckyIntern = (Staff) interns.get(index_intern);
                out("Intern " + LuckyIntern.name + " has been promoted to a salesmen!");
                textOut = textOut.concat("Intern " + LuckyIntern.name + " has been promoted to a salesmen! \n");
                for (int i = 0; i < staff.size(); i++){ //Traverses the staff array list 
                    if (staff.get(i) == LuckyIntern){
                        staff.remove(i); //Removes intern that will be promoted 
                        Staff promote = new Salesperson(); //New salesperson AKA the intern is the new salesperson 
                        promote.name = LuckyIntern.name; //Change name of the employee 
                        staff.add(promote); //Adds new employee to the staff array list 
                    }
                }
                //interns.remove(index_intern);
                //salespeople.add(LuckyIntern);
                count ++;
            }else out("Not enough interns to promote one. We need to hire a new employee!");

        }
        if(MechQuit <= .1){
            int index_mech = Utility.rndFromRange(0,2);
            Mechanic MechName = (Mechanic) mechanics.get(index_mech);
            departedStaff.add(MechName);
            for (int i = 0; i < staff.size(); i++){
                if (staff.get(i) == MechName){
                    staff.remove(i);
                }
            }
            out("Mechanic " + MechName.name + " has quit the FNCD");
            textOut = textOut.concat("Mechanic " + MechName.name + " has quit the FNCD \n");
            if (interns.size() > 0){
                int index_intern = Utility.rndFromRange(0, 2);
                Intern LuckyIntern = (Intern) interns.get(index_intern);
                out("Intern " + LuckyIntern.name + " has been promoted to a mechanic!");
                textOut = textOut.concat("Intern " + LuckyIntern.name + " has been promoted to a mechanic! \n");
                for (int i = 0; i < staff.size(); i++){
                    if (staff.get(i) == LuckyIntern){
                        staff.remove(i);
                        Staff promote = new Mechanic();
                        promote.name = LuckyIntern.name;
                        staff.add(promote);
                    }
                }
                //interns.remove(index_intern);
                //mechanics.add(LuckyIntern);
                count ++;
            }
            }   
        if(InternQuit <= .1 || interns.size() > 0){
            int index_intern = Utility.rndFromRange(0,2);
            Intern InternName = (Intern) interns.get(index_intern);
            departedStaff.add(InternName);
            //mechanics.remove(index_intern);
            for (int i = 0; i < staff.size(); i++){
                if (staff.get(i) == InternName){
                    staff.remove(i);
                }
            }
            out("Intern " + InternName.name + " has quit the FNCD");
            textOut = textOut.concat("Intern " + InternName.name + " has quit the FNCD \n");
            count ++;
        }
        if (count < 0){
            out("No-one on the staff is leaving!");
            textOut = textOut.concat("No-one on the staff is leaving! \n");
        }
        for(int j = 0; j < staff.size(); j++){
            if (staff.get(j).injured == true){
                staff.remove(j);
                out("Driver: " + staff.get(j).name + " has left the FNCD due to injury");
                textOut = textOut.concat("Driver: " + staff.get(j).name + " has left the FNCD due to injury \n");
            }
        }

        
    }
    

        // I would check the percentages here
        // Move quitters to the departedStaff list
        // If an intern quits, you're good
        // If a mechanic or a salesperson quits
        // Remove an intern from the staff and use their properties to
        // create the new mechanic or salesperson

    

    void reportOut(Enums.DayOfWeek day) {
        // We're all good here, how are you?
        // Quick little summary of happenings, you could do better

        out("Vehicles in inventory "+inventory.size());
        textOut = textOut.concat("Vehicles in inventory "+inventory.size()+" \n");
        out("Vehicles sold count "+soldVehicles.size());
        textOut = textOut.concat("Vehicles sold count "+soldVehicles.size()+" \n");
        out("Money in the budget "+ Utility.asDollar(getBudget()));
        textOut = textOut.concat("Money in the budget "+ Utility.asDollar(getBudget())+" \n");
        out("That's it for the day.");
        textOut = textOut.concat("That's it for the day. \n");
        Observer observer = new Observer();
        Tracker.addDataFNCD(dealerTracker, soldVehicles.size(), inventory.size());

        observer.dailySummary(day, dealerTracker);
        writeToFile(day);
    }
    void writeToFile(Enums.DayOfWeek day){
        Logger outFile = new Logger(day);
        outFile.writeLog(textOut);
    }
}