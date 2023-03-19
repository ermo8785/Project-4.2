import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.ArrayList;

interface BuyerInterface {
    String execute(FNCD Choice);
}

class SalespersonName implements BuyerInterface, SysOut{  //Returns the name of the salesperson 
        public String execute(FNCD Choice){
            ArrayList <Staff> salespeople = Staff.getStaffByType(Choice.staff, Enums.StaffType.Salesperson); //Fills Array list with salespeople 
            int index_sales = Utility.rndFromRange(0,2); //Sets an index within the range 
            Staff salesName = salespeople.get(index_sales); //Sets the name of the salesmen whos helping the user
            return(salesName.name);
        }
}

class Time implements BuyerInterface, SysOut { //Returns the time 
    public String execute(FNCD Choice){
        DateTimeFormatter Format = DateTimeFormatter.ofPattern("HH:MM:SS"); //Format for time used: https://www.javatpoint.com/java-get-current-date
        LocalDateTime CurrentTime = LocalDateTime.now();
        return(Format.format(CurrentTime));
    }
}

class CurrentInventory implements BuyerInterface, SysOut{
    public ArrayList<Vehicle> vehicleInventory = new ArrayList<>(); //Array List for vehicles as Vehicles
    public ArrayList<String> StringInventory = new ArrayList<>();//Array List for vehicles as Strings
    StringBuilder CarInventory = new StringBuilder(); //builds new string 
    public String execute(FNCD Choice){ 
        for (Vehicle i: Choice.inventory){
            CarInventory.append(i.name + "\n"); //adds to new string
            vehicleInventory.add(i); //adds to array lost as vehicle 
            StringInventory.add(i.name); //adds to array list as string 
        }

        return CarInventory.toString(); //Returns string 

    }
}

class DisplaySelectedCar implements BuyerInterface, SysOut { //Uses function above code to fill arrays and display a selceted car.
    public ArrayList<Vehicle> vehicleInventory = new ArrayList<>();
    public ArrayList<String> StringInventory = new ArrayList<>();
    StringBuilder CarInventory = new StringBuilder();
    StringBuilder CarDetails = new StringBuilder();
    public String execute(FNCD Choice) {
        for (Vehicle i: Choice.inventory){
            CarInventory.append(i.name + "\n");
            vehicleInventory.add(i);
            StringInventory.add(i.name);
        }
        boolean carFound = true;
        Scanner CarInput = new Scanner(System.in);
        System.out.println("Here's the list of this FNCD's inventory. Enter the name of the vehicle to see its details. Or enter 0 to quit.");
        while (carFound){
            String Car = CarInput.nextLine();
            if(Car.equals("0")){
                carFound = false;
                CarInput.close();
                String Bye = "Thank you for looking at the details";
                return Bye;
            }
            else if(StringInventory.contains(Car)){
                for(int i = 0; i < vehicleInventory.size(); i++){
                    if(Car.equalsIgnoreCase(vehicleInventory.get(i).name)){
                        CarDetails.append(vehicleInventory.get(i).cleanliness.toString() + "\n");
                        CarDetails.append(vehicleInventory.get(i).condition.toString()+ "\n");
                        CarDetails.append(vehicleInventory.get(i).price.toString()+ "\n");
                        CarDetails.append(vehicleInventory.get(i).type.toString()+ "\n");
                        carFound = false;
                        CarInput.close();
                        return CarDetails.toString();
                    }
                }
            }
            else {
                System.out.println("The car you are looking for is not here please make sure the name is typed correctly.");
            }
        }
        CarInput.close();
        return CarDetails.toString();
    }
}

class BuyCar implements BuyerInterface,SysOut { //Uses the same formatted code as aboce to Buy a car 
    public ArrayList<Vehicle> vehicleInventory = new ArrayList<>();
    public ArrayList<String> StringInventory = new ArrayList<>();
    StringBuilder CarInventory = new StringBuilder();
    StringBuilder CarDetails = new StringBuilder();
    public String execute(FNCD Choice) {
        for (Vehicle i: Choice.inventory){
            CarInventory.append(i.name + "\n");
            vehicleInventory.add(i);
            StringInventory.add(i.name);
        }
        boolean carFound = true;
        Scanner CarInput = new Scanner(System.in);
        while (CarInput.hasNextLine()){
            System.out.println("Here's the list of this FNCD's inventory. Enter the name of the vehicle you wish to buy. Or enter 0 to quit.");
            String Car = CarInput.nextLine();
            if(Car.equals("0")){
                carFound = false;
                CarInput.close();
                String Bye = "Thank you for looking at vehicles if you change your mind we're always here!";
                return Bye;
            }
            else if(StringInventory.contains(Car)){
                for(int i = 0; i < vehicleInventory.size(); i++){
                    if(Car.equalsIgnoreCase(vehicleInventory.get(i).name)){
                        String SelectedCar = vehicleInventory.get(i).name;
                        carFound = false;
                        CarInput.close();
                        return SelectedCar.toString();
                    }
                }
                break;
            }
            else {
                System.out.println("The car you are looking for is not here please make sure the name is typed correctly.");
            }
        }
        CarInput.close();
        return CarDetails.toString();
    }
}

 
class CarDetails implements SysOut{ //Displays car details 
    StringBuilder Details = new StringBuilder();
    public void execute(FNCD Choice, Vehicle CarChoice){ //Appends all the details of the car to the string 
        Details.append(CarChoice.type).append("\n");
        Details.append(CarChoice.cleanliness).append("\n");
        Details.append(CarChoice.condition).append("\n");
        Details.append(CarChoice.cost).append("\n");
        out(Details.toString()); //returns string 
    }
}



class UserMenu implements SysOut { //User menu command pattern
    public void Menu(FNCD Choice){
        Scanner UserInput = new Scanner(System.in);
        SalespersonName Name = new SalespersonName();
        boolean Loop = true;
        while(Loop){

            out("Please enter one of the follow options.");
            out("Enter '1' if you wish to ask the salesperson their name.");  
            out("Enter '2' if you wish to ask the salesperson the time.");  
            out("Enter '3' if you wish to have a new salesperson help you."); 
            out("Enter '4' if you wish to ask for the inventory."); 
            out("Enter '5' if you wish to buy a car");
            out("Enter '6' if you wish to buy a car."); 
            out("Enter '7' if you wish to leave the FNCD.");
            int Decision = Integer.parseInt(UserInput.nextLine());


            if(Decision == 1){ //First Option a customer can choose
                SalespersonName NewName = new SalespersonName();
                out("The current salesperson you're talking to is " + NewName.execute(Choice));
            }
            else if (Decision == 2){
                Time TimeAtMomement = new Time();
                out("The current time is " + TimeAtMomement.execute(Choice));
            } 
            else if (Decision == 3){
                SalespersonName NewName = new SalespersonName();
                if (NewName == Name){
                    while (NewName != Name){
                        NewName = new SalespersonName();
                    }
                }  
            }
            else if (Decision == 4){
                CurrentInventory Display = new CurrentInventory();
                out("The current inventory of this FNCD location is ");
                out(Display.execute(Choice) + "\n");
                DisplaySelectedCar NDisplay = new DisplaySelectedCar();
                out(NDisplay.execute(Choice));
            }
            
            else if(Decision == 5){
                CurrentInventory Display = new CurrentInventory();
                out(Display.execute(Choice) + "\n");
                BuyCar CarToBuy = new BuyCar();
                out("You wish to buy the car " +CarToBuy.execute(Choice) + "?");
            }
            
            /*else if (Decision == 6){
                //Buy the car function goes here
            }
            */
    
            else if(Decision == 7) {
                out("Thank you! Have a great rest of your day!");
                Loop = false;
            }
    
            else{
                out("Oops input is invalid try typing something else!");
            }

            if(!UserInput.hasNextLine()){
                break;
            }
        }

        UserInput.close();
    }
}

