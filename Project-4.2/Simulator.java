// Simulator to cycle for select number of days
public class Simulator implements SysOut {
    final int numDays;
    Enums.DayOfWeek dayOfWeek;
    Simulator() {
        numDays = 30;  // magic number for days to run here
        dayOfWeek = Utility.randomEnum(Enums.DayOfWeek.class);  // we'll start on a random day (for fun)
    }

    // cycling endlessly through enum values
    // https://stackoverflow.com/questions/34159413/java-get-next-enum-value-or-start-from-first
    public Enums.DayOfWeek getNextDay(Enums.DayOfWeek e)
    {
        int index = e.ordinal();
        int nextIndex = index + 1;
        Enums.DayOfWeek[] days = Enums.DayOfWeek.values();
        nextIndex %= days.length;
        return days[nextIndex];
    }

    void run() {
        FNCD North = new FNCD();
        FNCD South = new FNCD();
        for (int day = 1; day <= numDays; ++day) {
            out(">>> Start Simulation Day for North FNCD "+day+" "+dayOfWeek);
            if (dayOfWeek == Enums.DayOfWeek.Sun || dayOfWeek == Enums.DayOfWeek.Wed) North.raceDay(dayOfWeek);  // race days wed and sun
            else North.normalDay(dayOfWeek);  // normal stuff on other days
            out(">>> End Simulation Day for North FNCD "+day+" "+dayOfWeek+"\n");
            dayOfWeek = getNextDay(dayOfWeek);  // increment to the next day
            out("\n");
            out(">>> Start Simulation Day for South FNCD "+day+" "+dayOfWeek);
            if (dayOfWeek == Enums.DayOfWeek.Sun || dayOfWeek == Enums.DayOfWeek.Wed) South.raceDay(dayOfWeek);  // race days wed and sun
            else South.normalDay(dayOfWeek);  // normal stuff on other days
            out(">>> End Simulation Day for South FNCD "+day+" "+dayOfWeek+"\n");
            dayOfWeek = getNextDay(dayOfWeek);  // increment to the next day

        }   
    }
}
