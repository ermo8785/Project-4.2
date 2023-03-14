import java.util.Scanner;
import java.util.Date;

public class Inteface implements SysOut{
    
    public void Command(){
        out("Enter '1' if you wish to ask the salesperson their name."); //Return name 
        out("Enter '2' if you wish to ask the salesperson the time."); //return time 
        out("Enter '3' if you wish to have a new salesperson help you."); //Have new salesperson help them 
        out("Enter '4' if you wish to ask for the invetory."); //Allow user to slect item, then ask the sales person for detials over the selected item, if the item is in the inventory user can buy it, + add ons, end the user interactions 
    }

    public static GetSalesmen(){ //Set a string name in order to get the name of the salesperson, if they wish to change then redue this function as long as the name of the salesperson isn't the same
        ArrayList <Staff> salespeople = Staff.getStaffByType(staff, Enums.StaffType.Salesperson); //Fills Array list with salespeople 
        int index_sales = Utility.rndFromRange(0,2); //Sets an index within the range 
        Salesperson SalesName = (Salesperson) salespeople.get(index_sales); //Sets the name of the salesmen whos wuitting 
        return SalesName;
    }

    public static NewSalesPerson(Salesperson name){
        Salesperson NewName = name;
        while (NewName != name){
            NewName = GetSalesmen();
        }
        return NewName;
    }
}