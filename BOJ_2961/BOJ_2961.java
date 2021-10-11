package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2961_도영이가만든맛있는음식 {

    static int N;
    static int[] sour, bitter;

    static int min = Integer.MAX_VALUE;
    static int[] ingredients;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(in.readLine());
        sour = new int[N];
        bitter = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            sour[i] = Integer.parseInt(st.nextToken());
            bitter[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            ingredients = new int[i];
            combination(0, 0, i);
        }
        System.out.println(min);

        in.close();
    }

    private static void combination(int cnt, int start, int num) {
        if (cnt == num) {
            int diff = calcDiff(ingredients, num);
            if (diff < min) min = diff;
            return;
        }

        for (int i = start; i < N; i++) {
            ingredients[cnt] = i;
            combination(cnt + 1, i + 1, num);
        }
    }

    private static int calcDiff(int[] ingredients, int num) {
        int sourTotal = 1;
        int bitterTotal = 0;

        for (int i = 0; i < num; i++) {
            sourTotal *= sour[ingredients[i]];
            bitterTotal += bitter[ingredients[i]];
        }

        return Math.abs(sourTotal - bitterTotal);
    }

}
