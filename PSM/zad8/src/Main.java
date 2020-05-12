import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
        FractalPlant x = new FractalPlant();
        x.setResizable(false);
        x.setVisible(true);
    }
}

class FractalPlant extends JFrame {
    List<Point> list;
    String word;
    public FractalPlant() {
        list = new ArrayList<>();
        word = makeWord("X", 7);
        //System.out.println(word);
        setSize(1000, 1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public String makeWord(String prev, int n) {
        if (n == 0) return prev;
        else {
            StringBuilder next = new StringBuilder("");
            for (int i = 0; i < prev.length(); i++) {
                switch (prev.charAt(i)) {
                    case 'X':
                        next.append("F+[[X]-X]-F[-FX]+X");
                        break;
                    case 'F':
                        next.append("FF");
                        break;
                    default:
                        next.append(prev.charAt(i));
                }
            }
            return makeWord(next.toString(), n - 1);
        }
    }

    @Override
    public void paint(Graphics g) {
        draw((Graphics2D)g, 500, 900, -90, 3);
    }

    public void draw(Graphics2D g, int x, int y, double angle, int lineLength) {
        int curX = x, curY = y;
        double curAngle = angle;
        for (char c : word.toCharArray()) {
            switch (c) {
                case 'F':
                    int x2 = curX + (int) (Math.cos(Math.toRadians(curAngle)) * lineLength);
                    int y2 = curY + (int) (Math.sin(Math.toRadians(curAngle)) * lineLength);
                    g.drawLine(curX, curY, x2, y2);
                    curX=x2;
                    curY=y2;
                    break;
                case '-':
                    curAngle-=25;
                    break;
                case '+':
                    curAngle+=25;
                    break;
                case '[':
                    list.add(new Point(curX, curY, curAngle));
                    break;
                case ']':
                    Point tmp = list.remove(list.size() - 1);
                    curAngle = tmp.angle;
                    curX = tmp.x;
                    curY = tmp.y;
                    break;
            }
        }
    }
}
class Point {
    int x, y;
    double angle;
    public Point(int x, int y, double angle) {
        this.x = x;
        this.y = y;
        this.angle = angle;
    }
}