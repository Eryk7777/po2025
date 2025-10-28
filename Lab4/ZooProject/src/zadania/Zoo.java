package zadania;

import animals.*;
import java.util.Random;

public class Zoo {
    private Animal[] animals = new Animal[100];

    public void fillZoo() {
        Random random = new Random();
        String[] names = {"Alex", "Bella", "Charlie", "Daisy", "Eddy", "Fiona"};

        for (int i = 0; i < animals.length; i++) {
            int choice = random.nextInt(3); // 0 = Parrot, 1 = Dog, 2 = Snake
            String name = names[random.nextInt(names.length)];

            switch (choice) {
                case 0 -> animals[i] = new Parrot(name);
                case 1 -> animals[i] = new Dog(name);
                case 2 -> animals[i] = new Snake(name);
            }
        }
    }

    public int totalLegs() {
        int sum = 0;
        for (Animal animal : animals) {
            if (animal != null) {
                sum += animal.getLegs();
            }
        }
        return sum;
    }

    public void makeAllSounds() {
        for (Animal animal : animals) {
            if (animal != null) {
                animal.makeSound();
            }
        }
    }

    public void printDescriptions() {
        for (Animal animal : animals) {
            if (animal != null) {
                System.out.println(animal.getDescription());
            }
        }
    }

    public Animal[] getAnimals() {
        return animals;
    }

    public static void main(String[] args) {
        Zoo zoo = new Zoo();
        zoo.fillZoo();
        System.out.println("Total legs: " + zoo.totalLegs());
        zoo.makeAllSounds();
        zoo.printDescriptions();
    }
}
