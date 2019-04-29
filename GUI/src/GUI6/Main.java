package GUI6;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TreeFrame();
        });
    }
}
class TreeFrame extends JFrame {

    private Random rand=new Random();
    public TreeFrame() {
        setSize(new Dimension(1024, 768));
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2=(Graphics2D) g;
        g2.setBackground(Color.pink);
        g2.clearRect(0,0,getWidth(),getHeight());

        // Punkt startowy
        int xFrom = 512;
        int yFrom = 768;
        // Długość gałęzi
        int radius = 100;
        // Początkowy kąt
        double angle = Math.PI;

        // Rysowanie
        draw(g2, xFrom, yFrom, radius, angle,0,8,new Color(0,0,0));
    }

    private void draw(Graphics2D g, int xFrom, int yFrom, double radius, double angle, int counter, int size, Color color) {

        double deltaAngle = 0.5;

        double leftEndX = xFrom + Math.sin(angle + deltaAngle)*radius;
        double leftEndY = yFrom + Math.cos(angle + deltaAngle)*radius;
        double leftAngle=angle+Math.random()*deltaAngle;

        double RightEndX = xFrom + Math.sin(angle - deltaAngle)*radius;
        double RightEndY = yFrom + Math.cos(angle - deltaAngle)*radius;
        double RightAngle=angle-Math.random()*deltaAngle;
        Color sons=new Color(color.getRed()+30,color.getGreen()+15,color.getBlue()+5);

        g.setColor(color);
        g.setStroke(new BasicStroke(size));
        g.drawLine(xFrom, yFrom, (int)leftEndX, (int)leftEndY);
        g.drawLine(xFrom, yFrom, (int)RightEndX, (int)RightEndY);
        counter++;
        if(counter<6){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            size--;
            radius-=10;
            draw(g,(int)leftEndX, (int)leftEndY,radius,leftAngle,counter,size,sons);
            draw(g,(int)RightEndX, (int)RightEndY,radius,RightAngle,counter,size,sons);
        }
    }
    private Color randomColor(){
        int r = rand.nextInt(256);
        int g = rand.nextInt(256);
        int b = rand.nextInt(256);
        return new Color(r,g,b);
    }
    private void Sierp(Graphics2D g, int[] x3, int[] y3, int counter){
        Color color=randomColor();
        g.setColor(color);
        g.fillPolygon(x3,y3,3);
        for(int i=0;i<3;i++){
            x3[i]/=3;
            y3[i]/=3;
        }

    }
}