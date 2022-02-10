import java.util.Scanner;

public class Fibo implements Command
{

    @Override
    public String name() {
        return "fibo";
    }

    private int fibo(int n)
    {
        if (n <= 1)
            return n;
        return fibo(n - 1) + fibo(n - 2);
    }

    @Override
    public boolean run(Scanner scanner) {
        System.out.println("Veuillez saisir un nombre : ");
        try
        {
            System.out.println(fibo(Integer.parseInt(scanner.nextLine())));
        }
        catch (Exception e)
        {
            System.err.println("Erreur de la donnée : " + e.getMessage());
        }
        return false;
    }

}
