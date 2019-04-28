import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class NavPanel extends JPanel {
    public NavPanel(){
        setBackground(Color.PINK);
        setPreferredSize(new Dimension(1000,50));
        setLayout(new GridLayout());

        ArrayList<AbstractButton> buttons=new ArrayList<>();

        JButton clear=new JButton("Clear");
        clear.addActionListener(e -> {
            Main.dp.repaint();
            DrawPanel.polyDraw=false;
            DrawPanel.polyPoints.clear();
        });
        buttons.add(clear);

        JButton color=new JButton("Change Color");
        color.setBackground(DrawPanel.color);
        color.setForeground(Color.WHITE);
        color.addActionListener(e ->{
            DrawPanel.color = JColorChooser.showDialog(this, "Color chooser", DrawPanel.color);
            color.setBackground(DrawPanel.color);
            double y=(299*DrawPanel.color.getRed()+587*DrawPanel.color.getGreen()+114*DrawPanel.color.getBlue())/1000.0;
            color.setForeground(y>=128?Color.BLACK:Color.WHITE);
        });
        buttons.add(color);

        JToggleButton fill=new JToggleButton("Filled");
        fill.addActionListener(e->{
            DrawPanel.fill=!DrawPanel.fill;

        });
        buttons.add(fill);

        JButton stroke=new JButton("Width of line");
        stroke.addActionListener(e->{
            String width=JOptionPane.showInputDialog("Input line width",DrawPanel.stroke.getLineWidth());
            if(width!=null){
                try{
                    DrawPanel.stroke=new BasicStroke(Integer.valueOf(width));
                }catch (NumberFormatException ex){
                    System.err.println(ex);
                }
            }
        });
        buttons.add(stroke);

        JButton oval=new JButton("Oval");
        oval.addActionListener(e -> {
            DrawPanel.ovalMode=true;
            DrawPanel.rectMode=false;
            DrawPanel.lineMode=false;
            DrawPanel.polyMode=false;
        });
        buttons.add(oval);

        JButton rectangle=new JButton("Rectangle");
        rectangle.addActionListener(e -> {
            DrawPanel.ovalMode=false;
            DrawPanel.rectMode=true;
            DrawPanel.lineMode=false;
            DrawPanel.polyMode=false;
        });
        buttons.add(rectangle);

        JButton line=new JButton("Line");
        line.addActionListener(e -> {
            DrawPanel.ovalMode=false;
            DrawPanel.rectMode=false;
            DrawPanel.lineMode=true;
            DrawPanel.polyMode=false;
        });
        buttons.add(line);

        JButton polygon=new JButton("Polygon");
        polygon.addActionListener(e -> {
            DrawPanel.ovalMode=false;
            DrawPanel.rectMode=false;
            DrawPanel.lineMode=false;
            DrawPanel.polyMode=true;
        });
        buttons.add(polygon);

        JButton save=new JButton("Save");
        save.addActionListener(e->FileOperations.saveToFile());
        buttons.add(save);

        for(AbstractButton but:buttons){
            add(but);
        }


    }
}
