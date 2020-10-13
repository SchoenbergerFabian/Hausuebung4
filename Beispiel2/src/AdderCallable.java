import java.util.concurrent.Callable;

public class AdderCallable implements Callable<Integer> {
    private int start;
    private final int end;

    public AdderCallable(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        while (start<=end){
            sum+=start;
            start++;
        }
        return sum;
    }
}
