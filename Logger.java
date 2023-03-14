import java.io.IOException;
import java.io.FileWriter;

public class Logger implements SysOut{
    Enums.DayOfWeek day;

    public Logger(Enums.DayOfWeek simDay){
        day = simDay;
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
