import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class Freq implements Command
{

    @Override
    public String name() {
        return "freq";
    }

    public static List<String> freq(String file_content, long limit)
    {
        List<String> content_list = Arrays.asList(file_content.split("\\s+"));
        Map<Object, Integer> frequencyMap = content_list.stream()
                .collect(toMap(
                        s -> s,
                        s -> 1,
                        Integer::sum));
        List<String> list_freq;
        if (limit == 0)
            list_freq = content_list.stream()
                .sorted(comparing(frequencyMap::get).reversed()).distinct().collect(toList());
        else
            list_freq = content_list.stream()
                    .sorted(comparing(frequencyMap::get).reversed()).distinct().limit(limit).collect(toList());
        return list_freq;
    }

    @Override
    public boolean run(Scanner scanner) {
        System.out.println("Veuillez saisir un fichier : ");
        try
        {
            List<String> list_freq = freq(java.nio.file.Files.readString(Paths.get(scanner.nextLine())), 3);
            String s = new String();
            int i;
            for (i = 0; i < list_freq.size() - 1; i = i + 1)
            {
                s = s.concat(list_freq.get(i) + " ");
            }
            s = s.concat(list_freq.get(i));
            System.out.println(s);
        }
        catch (Exception e)
        {
            System.err.println("Unreadable file: " + e.getClass().toString() + " " + e.getMessage());
        }
        return false;
    }
}
