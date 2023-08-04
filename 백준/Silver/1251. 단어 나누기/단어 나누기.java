import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;

public class Backjoon1251 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();


        LinkedList<String> list = new LinkedList<>();

        // 반복문을 통해 단어를 나누는 모든 경우의수를 탐색한다.
        for (int i = 2; i < str.length(); i++) {
            for (int j = 1; j < i; j++) {
                // 각 기준 인덱스에 따라 단어를 3개로 나눈다.
                StringBuilder sb1 = new StringBuilder(str.substring(0, j));
                StringBuilder sb2 = new StringBuilder(str.substring(j, i));
                StringBuilder sb3 = new StringBuilder(str.substring(i));

                // 단어를 뒤집고 리스트에 추가한다.
                String str1 = sb1.reverse().toString();
                String str2 = sb2.reverse().toString();
                String str3 = sb3.reverse().toString();
                list.add(str1 + str2 + str3);
            }
        }
        // worst case nlogn 정렬
        // 리스트에 있는 단어들을 사전순으로 정렬한다.
        Collections.sort(list);
        // 가장 앞에있는 사전순 단어 출력
        System.out.print(list.pop());

    }
}
