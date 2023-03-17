import java.util.Scanner;
import java.util.Date;

public class Interface implements SysOut{
    
    public void Command(){
        out("Enter '1' if you wish to ask the salesperson their name."); //Return name 
        out("Enter '2' if you wish to ask the salesperson the time."); //return time 
        out("Enter '3' if you wish to have a new salesperson help you."); //Have new salesperson help them 
        out("Enter '4' if you wish to ask for the invetory."); //Allow user to slect item, then ask the sales person for detials over the selected item, if the item is in the inventory user can buy it, + add ons, end the user interactions 
    }

    public static String GetSalesmen(){ //Set a string name in order to get the name of the salesperson, if they wish to change then redue this function as long as the name of the salesperson isn't the same
        ArrayList <Staff> salespeople = Staff.getStaffByType(staff, Enums.StaffType.Salesperson); //Fills Array list with salespeople 
        int index_sales = Utility.rndFromRange(0,2); //Sets an index within the range 
        Salesperson SalesName = (Salesperson) salespeople.get(index_sales); //Sets the name of the salesmen whos wuitting 
        return SalesName;
    }

    public static Salesperson NewSalesPerson(Salesperson name){
        Salesperson NewName = name;
        while (NewName != name){
            NewName = GetSalesmen();
        }
        return NewName;
    }

    public void Details(Vehicle CarName){
        //Takes car name as a parameter and returns the details of the car 
        out("Here are the details about the car you selected");
        out(CarName.enums.VehicleType);
        out(CarName.enums.condition);
        out(CarName.enums.Cleanliness);
    }

    public void CurrentInventory(){
        //Return everything we have our in our inventory 
        for (int i = 0; i < Enums.VehicleType.values(); i++){
            out(Enums.VehicleType.values()[i].toString());
        }
    }

    public void BuyVehicle(){
        //The user will buy the vehicle and be offered some of the add ons if you want them 
    }

}