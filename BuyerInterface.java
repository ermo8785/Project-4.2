import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.ArrayList;

interface BuyerInterface {
    String execute(FNCD Choice);
}

class SalespersonName implements BuyerInterface, SysOut{ 
        public String execute(FNCD Choice){
            ArrayList <Staff> salespeople = Staff.getStaffByType(Choice.staff, Enums.StaffType.Salesperson); //Fills Array list with salespeople 
            int index_sales = Utility.rndFromRange(0,2); //Sets an index within the range 
            Staff salesName = salespeople.get(index_sales); //Sets the name of the salesmen whos helping the user
            return(salesName.name);
        }
}

class Time implements BuyerInterface, SysOut {
    public String execute(FNCD Choice){
        DateTimeFormatter Format = DateTimeFormatter.ofPattern("HH:MM:SS");
        LocalDateTime CurrentTime = LocalDateTime.now();
        return(Format.format(CurrentTime));
    }
}

class CurrentInventory implements BuyerInterface, SysOut{
    public ArrayList<Vehicle> vehicleInventory = new ArrayList<>();
    public ArrayList<String> StringInventory = new ArrayList<>();
    StringBuilder CarInventory = new StringBuilder();
    public String execute(FNCD Choice){
        for (Vehicle i: Choice.inventory){
            CarInventory.append(i.name + "\n");
            vehicleInventory.add(i);
            StringInventory.add(i.name);
        }
        /*
        for(int i = 0; i < StringInventory.size(); i++){
            out(StringInventory.get(i));
        }
        //out(CarInventory.toString());
        */
        return CarInventory.toString();

    }

    public void selectedCar() {
        boolean carFound = true;
        Scanner CarInput = new Scanner(System.in);
        while (CarInput.hasNext() & carFound){
            System.out.println("Here's the list of this FNCD's inventory. Enter the name of the vehicle to see its details. Or enter 0 to quit.");
            String Car = CarInput.nextLine();
            if(Car.equals("0")){
                break;
            }
            else if(Car != "0" & vehicleInventory.contains(Car)){
                for(int i = 0; i < vehicleInventory.size(); i++){
                    if(Car.equalsIgnoreCase(vehicleInventory.get(i).name)){
                        out("Here are the details for the selected car:");
                        out("Cleanliness: " + vehicleInventory.get(i).cleanliness.toString());
                        out("Condition: " + vehicleInventory.get(i).condition.toString());
                        out("Price: " + vehicleInventory.get(i).price.toString());
                        out("Type: " + vehicleInventory.get(i).type.toString());
                        carFound = false;
                        break;
                    }
                }
                break;
            }
            else {
                System.out.println("The car you are looking for is not here please make sure the name is typed correctly.");
            }
        }
        CarInput.close();
    }
}
class CarDetails implements SysOut{
    StringBuilder Details = new StringBuilder();
    public void execute(FNCD Choice, Vehicle CarChoice){
        Details.append(CarChoice.type).append("\n");
        Details.append(CarChoice.cleanliness).append("\n");
        Details.append(CarChoice.condition).append("\n");
        Details.append(CarChoice.cost).append("\n");
        out(Details.toString());
    }
}


class BuyCar{
    
}

class UserMenu implements SysOut {
    public void Menu(FNCD Choice){
        boolean Loop = true;
        Scanner UserInput = new Scanner(System.in);
        SalespersonName Name = new SalespersonName();
        while(Loop){
            out("Please enter one of the follow options.");
            out("Enter '1' if you wish to ask the salesperson their name.");  
            out("Enter '2' if you wish to ask the salesperson the time.");  
            out("Enter '3' if you wish to have a new salesperson help you."); 
            out("Enter '4' if you wish to ask for the inventory."); 
            out("Enter '5' if you wish to see the details for a user Selected Item.");
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
                Display.selectedCar();
            }

            
            /*else if(Decision == 5){
                Vehicle UserCar;
                CarDetails ShowCar = new CarDetails();
                out("The Details on the chosen car are");
                out(ShowCar.execute(Choice,UserCar));
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
        }

        UserInput.close();
    }
}

