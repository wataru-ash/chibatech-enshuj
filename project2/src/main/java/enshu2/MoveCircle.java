package enshu2;
import processing.core.PApplet;

public class MoveCircle extends PApplet {
    int x, y;
    int vx, vy;

    public void settings() {
        size(400, 300); 
    }

    public void setup() {
        x = 100;
        y = 100;
        vx = 2;
        vy = 2;
    }

    public void draw() {
        background(192); 

        x += vx;
        y += vy; 

        if (x >= width || x <= 0) {
            vx = vx * -1;
        }
        if (y >= height || y <= 0) {
            vy = vy * -1;
        }

        ellipse(x, y, 30, 30);
    }

    public static void main(String args[]) {
        PApplet.main(MoveCircle.class.getName());
    }
}
