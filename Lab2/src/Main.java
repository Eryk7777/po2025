import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        ArrayList<Integer> twojeTypy = new ArrayList<>();

        for (String arg : args) {
            int liczba = Integer.parseInt(arg);
            twojeTypy.add(liczba);
        }

        Random losuj = new Random();
        ArrayList<Integer> wylosowane = new ArrayList<>();

        while (wylosowane.size() < 6) {
            int liczba = losuj.nextInt(49) + 1;
            if (!wylosowane.contains(liczba)) {
                wylosowane.add(liczba);
            }
        }

        int trafienia = 0;
        for (int typ : twojeTypy) {
            if (wylosowane.contains(typ)) {
                trafienia++;
            }
        }

        System.out.println("Twoje typy: " + twojeTypy);
        System.out.println("Wylosowane liczby: " + wylosowane);
        System.out.println("Liczba trafien: " + trafienia);
    }
}
