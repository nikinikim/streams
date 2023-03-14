import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 13, 25);
        Stream<Integer> stream = numbers.stream();
        findMinMax(stream, Integer::compareTo, (x, y) -> System.out.printf("min: %s, max: %s%n", x, y));
        countEvenNumbers(numbers);
        stream.close();

    }

    public static <T> void findMinMax(Stream<? extends T> stream,
                                      Comparator<? super T> order,
                                      BiConsumer<? super T, ? super T> minMaxConsumer) {
        List<T> items = stream.sorted(order).collect(Collectors.toList());
        if (!items.isEmpty()){
            minMaxConsumer.accept((items.get(0)), items.get(items.size() - 1));
        }else{
            minMaxConsumer.accept(null,null);
        }
    }

    public static void countEvenNumbers(List<Integer> numbers) {
        long count = numbers.stream().filter(n -> n % 2 == 0).count();
        System.out.println("Количество четных чисел: " + count);
    }
}