import java.util.ArrayList;
import java.util.Random;

public class Main_2 {
    public static void main(String[] args) {

        // Zamieniamy argumenty na listę Twoich typów
        ArrayList<Integer> twojeTypy = new ArrayList<>();
        for (String arg : args) {
            int liczba = Integer.parseInt(arg);
            twojeTypy.add(liczba);
        }

        Random losuj = new Random();
        int liczbaLosowan = 0; // licznik losowań

        long start = System.currentTimeMillis(); // start czasu

        while (true) {
            liczbaLosowan++;

            ArrayList<Integer> wylosowane = new ArrayList<>();

            // Losujemy 6 unikalnych liczb
            while (wylosowane.size() < 6) {
                int liczba = losuj.nextInt(49) + 1;
                if (!wylosowane.contains(liczba)) {
                    wylosowane.add(liczba);
                }
            }

            // Sprawdzamy trafienia
            int trafienia = 0;
            for (int typ : twojeTypy) {
                if (wylosowane.contains(typ)) {
                    trafienia++;
                }
            }

            // Jeśli pełne trafienie, to koniec
            if (trafienia == 6) {
                long koniec = System.currentTimeMillis();
                long czasDzialania = koniec - start;

                System.out.println("Twoje typy: " + twojeTypy);
                System.out.println("Wylosowane liczby: " + wylosowane);
                System.out.println("Pełne trafienie po " + liczbaLosowan + " losowaniach!");
                System.out.println("Czas działania programu: " + czasDzialania + " ms");
                break;
            }
        }
    }
}
