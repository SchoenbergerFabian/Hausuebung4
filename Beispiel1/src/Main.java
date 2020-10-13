import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        try {
            ArrayList<Integer> numbers = FileReader.readNumbers();
            int chunks = getIntInput("chunks> ");
            int divider = getIntInput("divider> ");

            ExecutorService executor =
                    Executors.newCachedThreadPool();

            List<DivideableCheckCallable> tasks = new ArrayList<>();

            int begin = 0;
            for (int chunk = 1; chunk<=chunks; chunk++){
                int end = (int)(Math.ceil(1.0 * numbers.size() / chunks) * chunk);
                if(end < numbers.size()){
                    tasks.add(new DivideableCheckCallable(numbers.subList(begin,end),
                            divider));
                }else{
                    tasks.add(new DivideableCheckCallable(numbers.subList(begin,numbers.size()),
                            divider));
                }
                begin = end;
            }

            try {
                List<Future<Integer[]>> arrays = executor.invokeAll(tasks);

                executor.shutdown();

                arrays.stream()
                        .forEach(array -> {
                            try {
                                Arrays.stream(array.get())
                                        .forEach(System.out::println);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            }
                        });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            System.out.println("ERROR: Could not read the file!");
        }
    }

    private static int getIntInput(String text){
        System.out.print(text);
        Scanner scanner = new Scanner(System.in,"Windows-1252");
        try {
            return Integer.parseInt(scanner.nextLine());
        }catch (NumberFormatException ex){
            return getIntInput(text);
        }
    }
}
