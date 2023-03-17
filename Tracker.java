import java.util.ArrayList;

// Singleton pattern implemented with lazy instantiation
public class Tracker extends FNCD {
    public int vehiclesSold;
    public int vehiclesInInventory;

    private static Tracker instance = null;
    private Tracker(){
        this.vehiclesSold = 0;
        this.vehiclesInInventory = 0;
    }

    public static Tracker getInstance(){
        if(instance == null){
            instance = new Tracker();
        }
        return instance;
    }

    public static void dailySummary(Enums.DayOfWeek simDay, ArrayList<Tracker> dealerTracker){
        System.out.println(" ");

        System.out.println("Tracker -- Day: " + simDay);
        for(Tracker t : dealerTracker){
            System.out.println("Vehicles sold: "+ t.vehiclesSold);
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
