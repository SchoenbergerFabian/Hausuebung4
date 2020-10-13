import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;

public class FileReader {
    public static ArrayList<Integer> readNumbers() throws IOException{
        ArrayList<Integer> numbers = new ArrayList();

        Files.lines(new File("numbers.csv").toPath())
                .map(line -> line.split(":"))
                .forEach(stringArray ->
                        Arrays.stream(stringArray).forEach(string -> {
                            try{
                                numbers.add(Integer.parseInt(string));
                            }catch(NumberFormatException ex){}
                        }));
        return numbers;
    }
}
