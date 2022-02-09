import java.util.Scanner;

public class Launcher
{
    public static void main(String[] args)
    {
        System.out.println("Bienvenue !");
        Scanner scanner = new Scanner(System.in);
        while (!scanner.next().equals("quit"))
        {
            System.out.println("Unknown command");
        }
    }
}
