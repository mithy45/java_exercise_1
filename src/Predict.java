import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Predict implements Command
{

    private String findBestNextWordOf(String content, String word)
    {
        String words = new String();
        Pattern p = Pattern.compile(word + "\\W+(\\w+)");
        Matcher m = p.matcher(content);
        while (m.find())
        {
            if (words.isEmpty())
                words = m.group(1);
            else
                words = words + " " + m.group(1);
        }
        List<String> list_freq = Freq.freq(words, 1);
        if (list_freq.isEmpty())
            return "";
        return list_freq.get(0);
    }

    @Override
    public String name() {
        return "predict";
    }

    @Override
    public boolean run(Scanner scanner) {
        System.out.println("Veuillez saisir un fichier : ");
        String content = new String();
        try
        {
            content = Files.readString(Paths.get(scanner.nextLine())).toLowerCase(Locale.ROOT);
        }
        catch (Exception e) {
            System.err.println("Unreadable file: " + e.getClass().toString() + " " + e.getMessage());
        }
        System.out.println("Veuillez saisir un mot : ");
        String mot = new String();
        try
        {
            if (!content.contains(mot = scanner.nextLine()))
            {
                System.err.println("Le mot n'est pas dans le texte");
                return false;
            }
        }
        catch (Exception e)
        {
            System.err.println("Erreur lors de l'écriture: " + e.getClass().toString() + " " + e.getMessage());
        }
        String sentence = new String();
        String current_word = new String(mot);
        int count = 0;
        while (count != 20)
        {
            if (!current_word.isEmpty())
            {
                if (sentence.isEmpty())
                    sentence = current_word;
                else
                    sentence = sentence + " " + current_word;
            }
            current_word = findBestNextWordOf(content, current_word);
            count++;
        }
        System.out.println(sentence);
        return false;
    }
}
