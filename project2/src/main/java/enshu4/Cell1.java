package enshu4;

import processing.core.PApplet;

public class Cell1 extends PApplet {
    int N = 101;
    int[] cell = new int[N];

    public void settings() {
        size(800, 800);
    }

    public void draw() {
        noLoop();   // draw()を1回だけ実行する(静止画になる)
        noStroke(); // 輪郭線を描かない
        background(192);

        cell[20] = 1; // cell[20]だけを1にする 他のセルは0

        drawCell(0);

        // 第1世代から第4世代までを描く
        for (int gen = 1; gen < 5; gen++) {
            // 次世代のセルをnext[]に作るつもり(今は単に1にしている)
            int[] next = new int[N];
            for (int i = 1; i < N - 1; i++) {
                int v = cell[i]; // 現世代のi番目のセルの値
                next[i] = 1;     // 次世代のi番目のセルを1にする
            }
            // drawCell()で描画するためにnext[]をcell[]にコピーする
            for (int i = 0; i < N; i++) {
                cell[i] = next[i];
            }
            drawCell(gen);
        }
    }

    void drawCell(int gen) {
        int y = gen * 10;
        for (int i = 0; i < N; i++) {
            if (cell[i] == 1) {
                fill(255);
            } else {
                fill(0);
            }
            rect(i * 10, y, 8, 8);
        }
    }

    public static void main(String[] args) {
        PApplet.main("enshu4.Cell1");
    }
}
