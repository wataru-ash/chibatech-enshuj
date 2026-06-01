package enshu2;
import processing.core.PApplet;

public class MovingFourLines extends PApplet {

    public void settings() {
        size(200, 200);
    }

    public void draw() {
        background(255);
        
        int x = frameCount % 200; 

        line(x, 0, x, 200);
        line(200 - x, 0, 200 - x, 200);
        line(0, x, 200, x);
        line(0, 200 - x, 200, 200 - x);
    }

    public static void main(String args[]) {
        PApplet.main(MovingFourLines.class.getName());
    }
}