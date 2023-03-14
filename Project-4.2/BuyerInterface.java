
import java.util.Scanner;
import java.util.Date;

public class BuyerInterface implements SysOut{
    public Staff staff;

    public void run(Simulator North, Simulator South){
        Simulator FNCD_Location = null;
        Scanner scanner = new Scanner(System.in);
        out("Select which FNCD you would like to check out, type 'North' for North or 'South' for South.");
        String location = scanner.nextLine();
        boolean Valid = false;
        while (Valid == false)
        {
            if (location.toLowerCase() == "north"){
                FNCD_Location = North;
                Valid = true;
            }else if (location.toLowerCase() == "south"){
                FNCD_Location = South;
                Valid = true;
            }else {
                out("Invalid Input.");
                Valid = false;
            }
        }

    }
}
