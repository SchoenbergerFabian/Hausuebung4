import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {
        int limit = getIntInput("n> ");

        ExecutorService executor =
                Executors.newCachedThreadPool();

        List<AdderCallable> tasks = new ArrayList<>();

        int number = 1;
        while (number<=limit){
            if(number+99>=limit){
                tasks.add(new AdderCallable(number,limit));
            }else{
                tasks.add(new AdderCallable(number,number+99));
            }
            number+=100;
        }

        try {
            List<Future<Integer>> numbers = executor.invokeAll(tasks);

            executor.shutdown();

            numbers.stream()
                    .map(numberFuture -> {
                        try {
                            return numberFuture.get();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }
                        return null;
                    })
            .reduce(Integer::sum)
            .ifPresent(n -> System.out.println("sum with ThreadPool: "+n));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int sum = (int)((Math.pow(limit,2)+limit)/2);
        System.out.println("sum with Gauß: "+sum);
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
