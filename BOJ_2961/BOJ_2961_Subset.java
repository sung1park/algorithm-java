package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2961_도영이가만든맛있는음식_Subset {

    static int N;
    static int[] sour, bitter;

    static boolean[] isSelected;

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

        isSelected = new boolean[N];
        getSubset(0);

        System.out.println(min);
        in.close();
    }

    private static void getSubset(int cnt) {
        if (cnt == N) {
            int diff = calcDiff(isSelected);
            min = Math.min(min, diff);
            return;
        }

        isSelected[cnt] = false;
        getSubset(cnt + 1);
        isSelected[cnt] = true;
        getSubset(cnt + 1);

    }

    private static int calcDiff(boolean[] isSelected) {
        int sourTotal = 1;
        int bitterTotal = 0;

        for (int i = 0; i < N; i++) {
            if (isSelected[i]) {
                sourTotal *= sour[i];
                bitterTotal += bitter[i];
            }
        }

        if (sourTotal == 1 && bitterTotal == 0)
            return Integer.MAX_VALUE;
        else
            return Math.abs(sourTotal - bitterTotal);
    }

}
