import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");

        int N = Integer.parseInt(str[0]);

        str = br.readLine().split(" ");
        HashMap<Integer, Integer> list = new LinkedHashMap<Integer, Integer>();


        for (int i = 0; i < N; i++) {
            // 키 존재하면 value +1
            if (list.containsKey(Integer.parseInt(str[i]))) {
                list.replace(Integer.parseInt(str[i]), list.get(Integer.parseInt(str[i])) + 1);
            } else {
                // 키없으면 value 1
                list.put(Integer.parseInt(str[i]), 1);
            }
        }

        ArrayList<Integer> v = new ArrayList<Integer>(list.keySet());

        // 리스트에 젖아된 key value순으로 내림차순
        Collections.sort(v, new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {

                return Integer.compare(list.get(b), list.get(a));
            }
        });

        Iterator<Integer> it = v.iterator();
        while (it.hasNext()) {
            Integer element = it.next();
            for (int i = 0; i < list.get(element); i++) {
                System.out.print(element + " ");
            }

        }
    }
}