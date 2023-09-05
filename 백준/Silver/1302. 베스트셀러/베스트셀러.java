import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Map<String, Integer> map = new HashMap<>();

        while (N-- > 0) {
            String book = br.readLine();
            map.put(book, map.getOrDefault(book, 0) + 1);
        }

        System.out.println(findMax(map));


    }

    private static String findMax(Map<String, Integer> map) {
        int maxValue = Integer.MIN_VALUE;
        String result = "";

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (maxValue < entry.getValue()) {
                result = entry.getKey();
                maxValue = entry.getValue();
            } else if (maxValue == entry.getValue()) {
                if (entry.getKey().compareTo(result) < 0) {
                    result = entry.getKey();
                }
            }
        }

        return result;
    }
}