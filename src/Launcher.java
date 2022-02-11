import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Launcher
{
    public static void main(String[] args) {
        System.out.println("Bienvenue !");
        boolean found, run = true;
        String res;
        Scanner scanner = new Scanner(System.in);
        ArrayList<Command> command_list = new ArrayList<Command>(Arrays.asList(new Fibo(), new Freq(), new Quit(), new Predict()));
        do
        {
            found = false;
            System.out.println("Veuillez saisir une commande : ");
            res = scanner.nextLine();
            for (Command cmd : command_list)
            {
                if (cmd.name().equals(res))
                {
                    found = true;
                    if (cmd.run(scanner))
                        run = false;
                    break;
                }
            }
            if (!found)
                System.out.println("Unknown command");
        } while (run);
    }
}
