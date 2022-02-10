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

    private void freq(String file_content)
    {
        List<String> content_list = Arrays.asList(file_content.split("\\s+"));
        Map<Object, Integer> frequencyMap = content_list.stream()
                .collect(toMap(
                        s -> s,
                        s -> 1,
                        Integer::sum));
        List<String> list_3 = content_list.stream()
                .sorted(comparing(frequencyMap::get).reversed()).distinct().limit(3).collect(toList());
        System.out.println(Arrays.toString(list_3.toArray()));
    }

    @Override
    public boolean run(Scanner scanner) {
        try
        {
            freq(java.nio.file.Files.readString(Paths.get(scanner.nextLine())));
        }
        catch (Exception e)
        {
            System.err.println("Unreadable file: " + e.getClass().toString() + " " + e.getMessage());
        }
        return false;
    }
}
