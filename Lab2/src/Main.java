import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random losuj = new Random(); // Tworzymy losowy generator liczb
        ArrayList<Integer> liczby = new ArrayList<>(); // Lista na nasze liczby

        while (liczby.size() < 6) { // Dopóki mamy mniej niż 6 liczb
            int liczba = losuj.nextInt(49) + 1; // Losujemy liczbę od 1 do 49

            if (!liczby.contains(liczba)) { // Jeśli jeszcze nie mamy tej liczby
                liczby.add(liczba); // Dodajemy ją do listy
            }
        }

        System.out.println(liczby); // Wyświetlamy wylosowane liczby
    }
}
