
package enshu6;

import processing.core.PApplet;

public class Mandelbrot extends PApplet {
    int colors[] = new int[64]; 

    public void settings() {
        size(500, 500);
    }

    public void setup() {
    
        int begin = color(10, 0, 50); 
        int middle = color(0, 255, 200); 
        int end = color(255, 255, 255); 
        
        int lerpMax = colors.length - 1;
        int half = lerpMax / 2;
        
        for (int i = 0; i <= half; i++) {
            colors[i] = lerpColor(begin, middle, (float) i / half);
        }
        for (int i = half + 1; i <= lerpMax; i++) {
            colors[i] = lerpColor(middle, end, (float) (i - half) / (lerpMax - half));
        }
    }

    public void draw() {
        noLoop(); 
        
        double x0 = -0.745;
        double y0 = 0.133;
        double w = 0.005;
        double h = 0.005;

        for (int py = 0; py < height; py++) {
            for (int px = 0; px < width; px++) {
                int c = mandelbrot(px, py, x0, y0, w, h); 
                set(px, py, c); 
            }
        }
    }

    int mandelbrot(int px, int py, double x0, double y0, double w, double h) {
        double a = x0 + w * (double)px / width;
        double b = y0 - h * (double)py / height;

    
        int max = 500; 
        int count = mcount(a, b, max);

    
        if (count == max) {
            return color(0, 0, 0); 
        } 
    
        else {
            return colors[count % colors.length];
        }
    }


    int mcount(double a, double b, int max) {
        int count = 0;
        double x = 0, y = 0; 
        
        while (count < max && (x * x + y * y < 4)) {
            count++;
            double xx = x * x - y * y + a; 
            double yy = 2 * x * y + b;     
            x = xx; 
            y = yy; 
        }
        return count;
    }

    public static void main(String args[]) {
        PApplet.main("enshu6.Mandelbrot");
    }
}