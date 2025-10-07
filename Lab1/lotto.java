import java.util.*;

public class lotto {
    public static void main(String[] args) {
        Random rand = new Random();
        Set<Integer> numbers = new HashSet<>();
        while (numbers.size() < 6) {
            numbers.add(rand.nextInt(49) + 1);
        }
        System.out.println(numbers);
    }
}
