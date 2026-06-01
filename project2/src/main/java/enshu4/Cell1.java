package enshu4;
    public void draw() {
        noLoop();   // draw()を1回だけ実行する(静止画になる)
        noStroke(); // 輪郭線を描かない
        background(192);

        cell[20] = 1; // cell[1]だけを1にする 他のセルは0

    drawCell(0);

        // 第1世代から第4世代までを描く
        for (int gen=1; gen<5; gen++) {
            // 次世代のセルをnext[]に作るつもり(今は単に1にしている)
            int next[] = new int[N];
            for (int i=1; i<N-1; i++) {
                int v = cell[i]; // 現世代のi番目のセルの値(課題のヒント 今は無意味)
                next[i] = 1;     // 次世代のi番目のセルを1にする
            }
            // drawCell()で描画するためにnext[]をcell[]にコピーする
            for (int i=0; i<N; i++) {
                cell[i] = next[i];
            }
             drawCell(gen); 
        }

    }
}