import java.util.ArrayList;
public class StaffFactory{
    //factory pattern
    ArrayList<Staff> staff;

    StaffFactory(ArrayList<Staff> staffLst){
        staff = staffLst;
    }
    public newStaff createNewStaff(String staffType){
        if(staffType == null){
            return null;
        }
        if (staffType.equalsIgnoreCase("Intern")){
            return new newIntern(staff);
        }
        else if (staffType.equalsIgnoreCase("Mechanic")){
            return new newIntern(staff);
        }
        else if (staffType.equalsIgnoreCase("Salesperson")){
            return new newIntern(staff);
        }
        return null;
    }
}

interface newStaff{
    void addNewStaff();
}

abstract class newStaffType implements newStaff{
    ArrayList<Staff> staff;

    newStaffType(ArrayList<Staff> staffLst){
        staff = staffLst;
    }
}

class newIntern extends newStaffType{
    newIntern(ArrayList<Staff> staffLst){
        super(staffLst);
    }
    public void addNewStaff(){
        for(int i = 0; i < 3; i++){
            Intern newStaff = new Intern();
            staff.add(newStaff);
        }
    }
}

class newMechanic extends newStaffType{
    newMechanic(ArrayList<Staff> staffLst){
        super(staffLst);
    }
    public void addNewStaff(){
        for(int i = 0; i < 3; i++){
            Mechanic newStaff = new Mechanic();
            staff.add(newStaff);
        }
    }
}
class newSalesperson extends newStaffType{
    newSalesperson(ArrayList<Staff> staffLst){
        super(staffLst);
    }
    public void addNewStaff(){
        for(int i = 0; i < 3; i++){
            Salesperson newStaff = new Salesperson();
            staff.add(newStaff);
        }
    }
}