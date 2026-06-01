package enshu5;

import processing.core.PApplet;

public class Cell3 extends PApplet {
    
    int H = 120; 
    int W = 120;
    int cellSize = 4; 
    int[][] cell = new int[H][W];

    int[][] gliderGun = {
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1,1},
        {0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1,1},
        {1,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {1,1,0,0,0,0,0,0,0,0,1,0,0,0,1,0,1,1,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
    };

    public void settings() {
        size(480, 480); 
    }

    public void setup() {
        frameRate(15); // 
        colorMode(HSB, 360, 100, 100); 
        
        pattern(gliderGun, 10, 10);
        
        int[][] glider = {
            {0, 1, 0},
            {0, 0, 1},
            {1, 1, 1}
        };
        pattern(glider, 60, 30);
        pattern(glider, 20, 80);
    }

    public void draw() {
        background(0, 0, 8); 
        drawCell();          
        change();            
    }

    void pattern(int[][] p, int x, int y) {
        int pHeight = p.length;
        int pWidth = p[0].length;
        int cHeight = cell.length;
        int cWidth = cell[0].length;
        
        for (int py = 0; py < pHeight; py++) {
            for (int px = 0; px < pWidth; px++) {
                int cy = y + py;
                int cx = x + px;
                if (cy >= 0 && cy < cHeight && cx >= 0 && cx < cWidth) {
                    cell[cy][cx] = p[py][px];
                }
            }
        }
    }

    void drawCell() {
        noStroke(); 
        for (int y = 0; y < H; y++) {
            for (int x = 0; x < W; x++) {
                if (cell[y][x] == 1) {
                
                    float hue = (x * 1.5f + y * 1.5f + frameCount * 3) % 360;
                    fill(hue, 85, 100); 
                    rect(x * cellSize, y * cellSize, cellSize, cellSize);
                }
            }
        }
    }

    int nextState(int x, int y) {
        int count = 0;
        
        // 周囲8マスのチェック
        if (cell[y - 1][x - 1] == 1) count++;
        if (cell[y - 1][x]     == 1) count++;
        if (cell[y - 1][x + 1] == 1) count++;
        if (cell[y][x - 1]     == 1) count++;
        if (cell[y][x + 1]     == 1) count++;
        if (cell[y + 1][x - 1] == 1) count++;
        if (cell[y + 1][x]     == 1) count++;
        if (cell[y + 1][x + 1] == 1) count++;

        int next = 0;
        if (cell[y][x] == 0) {
            if (count == 3) {
                next = 1; 
            }
        } else {
            if (count == 2 || count == 3) {
                next = 1; 
            }
        }
        return next;
    }

    void change() {
        int[][] next = new int[H][W];
        
    
        for (int y = 1; y < H - 1; y++) {
            for (int x = 1; x < W - 1; x++) {
                next[y][x] = nextState(x, y);
            }
        }
        
    
        for (int y = 1; y < H - 1; y++) {
            for (int x = 1; x < W - 1; x++) {
                cell[y][x] = next[y][x];
            }
        }
    }

    public static void main(String[] args) {
        PApplet.main("enshu5.Cell3");
    }
}