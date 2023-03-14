public class StaffFactory{
    public Staff createNewStaff(String staffName, String staffType){
        if(staffName == null || staffType == null){
            return null;
        }
        switch (staffType.toUpperCase()){
            case "INTERN":
                return new Intern(staffName);
            case "MECHANIC":
                return new Mechanic(staffName);
            case "SALESPERSON":
                return new Salesperson(staffName);
            case "DRIVER":
                return new Driver(staffName);
        }
        return null;
    }
}