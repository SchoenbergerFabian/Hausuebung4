import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

public class DivideableCheckCallable implements Callable<Integer[]> {
    private final List<Integer> numbers;

    private final Integer divider;

    public DivideableCheckCallable(List<Integer> numbers, Integer divider) {
        this.numbers = numbers;
        this.divider = divider;
    }

    @Override
    public Integer[] call() throws Exception {
        return numbers.stream()
                .filter(number -> number % divider == 0)
                .toArray(Integer[]::new);
    }
}
