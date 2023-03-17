import java.io.IOException;
import java.io.FileWriter;

// Singleton pattern & eager instantiation
public class Logger implements SysOut{
    static Enums.DayOfWeek day;
    private static Logger instance = new Logger(day);

    Logger(Enums.DayOfWeek simDay){
        day = simDay; 
    }

    public static Logger getInstance(Enums.DayOfWeek simDay){
        if (day != simDay){
            Logger.instance = new Logger(simDay);
        }
        return Logger.instance; // returns singleton instance
    }

    public void writeLog(String input){
        try {
            FileWriter writer = new FileWriter("Logger-" + day + ".txt");
            writer.write(input);
            writer.close();
            out("Success: written to the file");
        }
        catch(IOException e){
            out("Error: could not write to a new daily logger file.");
            e.printStackTrace();
        }
    }
}
