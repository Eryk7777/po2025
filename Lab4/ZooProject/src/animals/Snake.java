package animals;

public class Snake extends Animal {
    public Snake(String name) {
        super(name, 0);
    }


    public String getDescription() {
        return "Snake named " + name + " with " + legs + " legs";
    }

    @Override
    public void makeSound() {
        System.out.println("Hiss!");
    }
}
