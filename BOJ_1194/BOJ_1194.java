package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1194_달이차오른다가자 {

    static int N, M; // 세로, 가로
    static char[][] map; // 미로

    static boolean[][][] visited;
    static int min = Integer.MAX_VALUE;

    static final int[] dx = {-1, 1, 0, 0}; // 상 하 좌 우
    static final int[] dy = {0, 0, -1, 1}; // 상 하 좌 우

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 시작 x, y 좌표
        int cx = 0, cy = 0;
        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            String buffer = in.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = buffer.charAt(j);
                if (map[i][j] == '0') {
                    cx = i;
                    cy = j;
                }
            }
        }

        // x, y, key 3차원 배열을 사용
        // 새로운 key를 가졌을 경우 갔던 길을 다시 돌아올 수 있어야 함
        visited = new boolean[N][M][64];
        bfs(cx, cy);

        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
        in.close();
    }

    private static void bfs(int cx, int cy) {
        Queue<Integer> queueX = new LinkedList<Integer>(); // x좌표
        Queue<Integer> queueY = new LinkedList<Integer>(); // y좌표
        Queue<Integer> queueK = new LinkedList<Integer>(); // key
        Queue<Integer> queueD = new LinkedList<Integer>(); // distance

        queueX.offer(cx);
        queueY.offer(cy);
        queueK.offer(0);
        queueD.offer(0);

        visited[cx][cy][0] = true;

        while (!queueX.isEmpty()) {
            int x = queueX.poll();
            int y = queueY.poll();
            int k = queueK.poll();
            int d = queueD.poll();

            if (map[x][y] == '1') {
                min = Math.min(min, d);
                continue;
            }
            if (map[x][y] >= 'a' && map[x][y] <= 'f') {
                k = k | 1 << (map[x][y] - 97);
            }
            if (map[x][y] >= 'A' && map[x][y] <= 'F') {
                if ((k & 1 << (map[x][y] - 65)) == 0) {
                    continue;
                }
            }

            for (int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];
                if (!outOfRange(nx, ny) && !visited[nx][ny][k] && map[nx][ny] != '#') {
                    queueX.offer(nx);
                    queueY.offer(ny);
                    queueK.offer(k);
                    queueD.offer(d + 1);
                    visited[nx][ny][k] = true;
                }
            }
        }
    }

    private static boolean outOfRange(int x, int y) {
        if (x < 0 || x >= N || y < 0 || y >= M) return true;
        return false;
    }

}
