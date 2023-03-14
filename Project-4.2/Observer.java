import java.util.*;
public class Observer implements SysOut{
    ArrayList<String> Subscriber = new ArrayList<>();

    public Observer(){
        addObserver("Observer 1");
    }
    
    public void addObserver(String name){
        Subscriber.add(name);
    }

    public void removeObserver(String name){
        Subscriber.remove(name);
    }

    public void dailySummary(Enums.DayOfWeek simDay, ArrayList<Tracker> trackFNCD){
        for (int i = 0; i < Subscriber.size(); i++){
            out(" ");
            out(Subscriber.get(i) + "'s daily tracker update: ");
            Tracker.dailySummary(simDay, trackFNCD);
        }
    }
}
