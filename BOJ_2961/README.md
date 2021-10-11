# BOJ 2961 도영이가 만든 맛있는 음식

[바로가기](https://www.acmicpc.net/problem/2961)



### 문제

- 완전탐색(조합, 부분집합)을 이용해 모든 경우의 수를 고려하는 문제이다
- 처음엔 조합밖에 사용할 줄 몰라서 조합으로 풀었지만, 나중에 부분집합, 비트마스킹, 바이너리카운팅을 이용해 풀이하였다
- 완전탐색을 연습하기 좋은 문제라고 생각하여 기록하였다



### 풀이

##### 조합

```java
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
```



##### 부분집합

```java
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
```



##### 비트마스킹

```java
private static void combination(int cnt, int start, int num, int ingredients) {
    if (cnt == num) {
        int diff = calcDiff(ingredients);
        min = Math.min(min, diff);
        return;
    }

    for (int i = start; i < N; i++) {
        int nextIngredients = ingredients | 1 << i;
        combination(cnt + 1, i + 1, num, nextIngredients);
    }
}
```



##### 바이너리 카운팅

```java
for (int i = 1; i < Math.pow(2, N); i++) {
        int diff = calcDiff(i);
        min = Math.min(min, diff);
    }
```

