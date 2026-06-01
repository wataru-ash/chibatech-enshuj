package enshu4;

import processing.core.PApplet;

public class Cell3 extends PApplet {
    int N = 101;
    int[] cell = new int[N];
    int[] next = new int[N];
    int gen = 0;
    int size = 8;


    int[] rule = {0, 1, 1, 0, 1, 1, 1, 0}; 

    public void settings() {
        size(800, 800);
    }

    public void setup() {
        background(255);
        noStroke();
        
    
        cell[N / 2] = 1;
    }

    public void draw() {
    
        if (gen * 10 < height) {
            drawCell(gen);
            updateNext();
            copyToCell();
            gen++;
        }
    }

    
    void updateNext() {
        for (int i = 1; i < N - 1; i++) { 
        
            int d = cell[i - 1] * 4 + cell[i] * 2 + cell[i + 1] * 1;
        
            next[i] = rule[d];
        }
    }


    void copyToCell() {
        for (int i = 0; i < N; i++) {
            cell[i] = next[i];
        }
    }

    
    void drawCell(int g) {
        int y = g * 10;
        for (int i = 0; i < N; i++) {
            if (cell[i] == 1) {
                fill(30, 80, 150); 
            } else {
                fill(0);
            }
            rect(i * 10, y, 8, 8);
        }
    }

    public static void main(String[] args) {
        PApplet.main("enshu4.Cell3");
    }
}