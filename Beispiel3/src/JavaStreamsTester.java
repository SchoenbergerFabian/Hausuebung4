import java.util.List;
import java.util.stream.Collectors;

public class JavaStreamsTester {

    private static int getCountEmptyString(List<String> strings){
        return strings.stream()
                .filter(string -> string.length()==0)
                .toArray().length;
    }

    private static int getCountLength3(List<String> strings){
        return strings.stream()
                .filter(string -> string.length()==3)
                .toArray().length;
    }

    private static List<String> deleteEmptyStrings(List<String> strings){
        return strings.stream()
                .filter(string -> string.length()==0)
                .collect(Collectors.toList());
    }

    private static String getMergedString(List<String> strings, String separator){
        return strings.stream()
                .reduce((string1,string2) -> string1+string2)
                .orElse(null);
    }

    private static List<Integer> getSquares(List<Integer> numbers){
        return numbers.stream()
                .map(number -> number*number)
                .collect(Collectors.toList());
    }

    private static int getMax(List<Integer> numbers){
        return numbers.stream()
                .max((number1,number2) -> number1-number2)
                .orElse(0);
    }

    private static int getMin(List<Integer> numbers){
        return numbers.stream()
                .min((number1,number2) -> number1-number2)
                .orElse(0);
    }

    private static int getSum(List<Integer> numbers){
        return numbers.stream()
                .reduce(Integer::sum)
                .orElse(0);
    }

    private static int getAverage(List<Integer> numbers){
        return (int) numbers.stream()
                .mapToInt(number -> number)
                .average()
                .orElse(0.0);
    }

}
