package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2961_도영이가만든맛있는음식_BitMask {

    static int N;
    static int[] sour, bitter;

    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());

        sour = new int[N];
        bitter = new int[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            sour[i] = Integer.parseInt(st.nextToken());
            bitter[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            combination(0, 0, i, 0);
        }

        System.out.println(min);
        in.close();
    }

    private static void combination(int cnt, int start, int num, int ingredients) {

        if (cnt == num) {
            int diff = calcDiff(ingredients);
            min = Math.min(min, diff);
            return;
        }

        for (int i = start; i < N; i++) {
            int temp = ingredients;
            ingredients = ingredients | 1 << i;
            combination(cnt + 1, i + 1, num, ingredients);
            ingredients = temp;
        }

    }

    private static int calcDiff(int ingredients) {
        int sourTotal = 1;
        int bitterTotal = 0;

        for (int i = 0; i < N; i++) {
            if ((ingredients & 1 << i) != 0) {
                sourTotal *= sour[i];
                bitterTotal += bitter[i];
            }
        }

        return Math.abs(sourTotal - bitterTotal);
    }

}
