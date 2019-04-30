import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class DrawPanel extends JPanel implements MouseListener, MouseMotionListener {
    static Point pointStart;
    static ArrayList<Point> polyPoints;
    static Color color=Color.BLACK;
    public static boolean ovalMode=false, rectMode=false, lineMode=false, polyMode=false;
    public static boolean fill=false;
    public static BasicStroke stroke=new BasicStroke(1);
    static boolean polyDraw=false;


    public DrawPanel(){
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(1000,200));
        addMouseListener(this);
    }
    public static void drawOval(Graphics2D g,int x, int y){
        g.setColor(color);
        g.setStroke(stroke);
        int px,py;
        int width,height;
        if(x-pointStart.x>0){
            px=pointStart.x;
            width=x-pointStart.x;
        }
        else{
            px=x;
            width=pointStart.x-x;
        }
        if(y-pointStart.y>0){
            py=pointStart.y;
            height=y-pointStart.y;
        }
        else{
            py=y;
            height=pointStart.y-y;
        }
        if(fill){
            g.fillOval(px,py,width,height);
        }
        else{
            g.drawOval(px,py,width,height);
        }
    }
    public static void drawRect(Graphics2D g,int x, int y){
        g.setColor(color);
        g.setStroke(stroke);
        int px,py;
        int width,height;
        if(x-pointStart.x>0){
            px=pointStart.x;
            width=x-pointStart.x;
        }
        else{
            px=x;
            width=pointStart.x-x;
        }
        if(y-pointStart.y>0){
            py=pointStart.y;
            height=y-pointStart.y;
        }
        else{
            py=y;
            height=pointStart.y-y;
        }
        if(fill){
            g.fillRect(px,py,width,height);
        }
        else{
            g.drawRect(px,py,width,height);
        }
    }
    public static void drawLine(Graphics2D g,int x, int y) {
        g.setColor(color);
        g.setStroke(stroke);
        g.drawLine(pointStart.x,pointStart.y,x,y);
    }
    public static void drawPoly(Graphics2D g){
        g.setColor(color);
        g.setStroke(stroke);
        Polygon p=new Polygon();
        for(Point poi:polyPoints){
            p.addPoint(poi.x,poi.y);
        }
        if(fill){
            g.fillPolygon(p);
        }
        else{
            g.drawPolygon(p);
        }
        polyDraw=false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(polyMode){
            if(!polyDraw){
                getGraphics().drawOval(e.getX(),e.getY(),1,1);
                polyPoints=new ArrayList<>();
                pointStart=new Point(e.getX(),e.getY());
                polyPoints.add(new Point(e.getX(),e.getY()));
                polyDraw=true;
            }
            else if(Math.abs(pointStart.x-e.getX())<15&&Math.abs(pointStart.y-e.getY())<15){
                drawPoly(((Graphics2D)getGraphics()));
            }
            else polyPoints.add(new Point(e.getX(),e.getY()));
        }
        else{
            pointStart=new Point(e.getX(),e.getY());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(ovalMode)drawOval(((Graphics2D)getGraphics()),e.getX(),e.getY());
        else if(rectMode)drawRect(((Graphics2D)getGraphics()),e.getX(),e.getY());
        else if(lineMode)drawLine(((Graphics2D)getGraphics()),e.getX(),e.getY());
        else if(polyMode){
            polyPoints.add(new Point(e.getX(),e.getY()));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
