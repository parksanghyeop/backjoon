import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        StringBuilder builder = new StringBuilder();

        while ((str = br.readLine()) != null) {
            int n = Integer.parseInt(str);

            int base = 1;
            int count = 1;
            while((base=base%n) != 0) {
                count++;
                base = base*10+1;
            }
            builder.append(count + "\n");
        }
        System.out.println(builder);

    }

}