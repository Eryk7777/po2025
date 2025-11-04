package symulator;

public class SkrzyniaBiegów extends Komponent {
    private int aktualnyBieg;
    private int iloscBiegow;

    public SkrzyniaBiegów(String nazwa, double waga, double cena, int iloscBiegow, int  aktualnyBieg) {
       super(nazwa, waga, cena);
        this.aktualnyBieg = 0; // 0 = luz
        this.iloscBiegow = iloscBiegow;
    }

    public void zwiększBieg() {
        if (aktualnyBieg < iloscBiegow) {
            aktualnyBieg++;
        } else {
            System.out.println("Nie mozna zwiekszyc biegu.");
        }
    }

    public void zmniejszBieg() {
        if (aktualnyBieg > 0) {
            aktualnyBieg--;
        } else {
            System.out.println("Nie mozna zmniejszyc biegu.");
        }
    }

    public void zerujBieg() {
        aktualnyBieg = 0;
    }
}
