import java.util.ArrayList;

public class Tracker {
    public int vehiclesSold;
    public int vehiclesInInventory;
    public Double moneyEarnedByStaff;

    public Tracker(){
        // Publish these events
        /* Keep track of budget
            Money earned by each staff member
            Vehicles sold
            Vehicles in inventory
         */
        this.vehiclesSold = 0;
        this.vehiclesInInventory = 0;
    }

    public static void dailySummary(Enums.DayOfWeek simDay, ArrayList<Tracker> dealerTracker){
        System.out.println(" ");

        System.out.println("Tracker -- Day: " + simDay);
        for(Tracker t : dealerTracker){
            System.out.println("Vehicles sold: "+t.vehiclesSold);
            System.out.println("Vehicles in inventory: " + t.vehiclesInInventory);
        }
        System.out.println(" ");

    }

    public static void addDataFNCD(ArrayList<Tracker> dealerTracker, int vehiclesSold, int vehiclesInInventory){
        for(int i = 0; i < dealerTracker.size(); i++){
            dealerTracker.get(i).vehiclesSold = vehiclesSold;
            dealerTracker.get(i).vehiclesInInventory = vehiclesInInventory;
        }
    }
}
