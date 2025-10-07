public class choinka {
    public static void main(String[] args) {
        int w = 20;  // domyślna liczba wierszy

        if (args.length > 0) {
            w = Integer.parseInt(args[0]);  // pobierz z argumentu
        }

        for (int i = 1; i <= w; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}