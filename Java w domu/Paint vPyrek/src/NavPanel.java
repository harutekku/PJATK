import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class NavPanel extends JPanel {
    NavPanel(){
        setBackground(Color.GREEN);
        setPreferredSize(new Dimension(1000,50));
        setLayout(new GridLayout());

        ArrayList<AbstractButton> buttons=new ArrayList<>();

        JButton clear=new JButton("Clear");
        clear.addActionListener(e -> {
            Main.dp.repaint();
            Main.dp.polyDraw=false;
            if(Main.dp.polyPoints!=null)
                Main.dp.polyPoints.clear();
        });
        buttons.add(clear);

        JButton color=new JButton("Change Color");
        color.setBackground(Main.dp.color);
        color.setForeground(Color.WHITE);
        color.addActionListener(e ->{
            Color choose = JColorChooser.showDialog(this, "Color chooser", Main.dp.color);
            if(choose!=null){
                Main.dp.color = choose;
                color.setBackground(Main.dp.color);
                double y=(299*Main.dp.color.getRed()+587*Main.dp.color.getGreen()+114*Main.dp.color.getBlue())/1000.0;
                color.setForeground(y>=128?Color.BLACK:Color.WHITE);
            }
        });
        buttons.add(color);

        JToggleButton fill=new JToggleButton("Filled");
        fill.addActionListener(e->{
            Main.dp.fill=!Main.dp.fill;

        });
        buttons.add(fill);

        JButton stroke=new JButton("Width of line");
        stroke.addActionListener(e->{
            String width=JOptionPane.showInputDialog("Input line width",Main.dp.stroke.getLineWidth());
            if(width!=null){
                try{
                    Main.dp.stroke=new BasicStroke(Integer.valueOf(width));
                }catch (NumberFormatException ex){
                    System.err.println(ex);
                }
            }
        });
        buttons.add(stroke);

        JButton oval=new JButton("Oval");
        oval.addActionListener(e -> {
            Main.dp.ovalMode=true;
            Main.dp.rectMode=false;
            Main.dp.lineMode=false;
            Main.dp.polyMode=false;
        });
        buttons.add(oval);

        JButton rectangle=new JButton("Rectangle");
        rectangle.addActionListener(e -> {
            Main.dp.ovalMode=false;
            Main.dp.rectMode=true;
            Main.dp.lineMode=false;
            Main.dp.polyMode=false;
        });
        buttons.add(rectangle);

        JButton line=new JButton("Line");
        line.addActionListener(e -> {
            Main.dp.ovalMode=false;
            Main.dp.rectMode=false;
            Main.dp.lineMode=true;
            Main.dp.polyMode=false;
        });
        buttons.add(line);

        JButton polygon=new JButton("Polygon");
        polygon.addActionListener(e -> {
            Main.dp.ovalMode=false;
            Main.dp.rectMode=false;
            Main.dp.lineMode=false;
            Main.dp.polyMode=true;
        });
        buttons.add(polygon);

        JButton save=new JButton("Save");
        save.addActionListener(e->FileOperations.saveToFile());
        buttons.add(save);

        JButton read=new JButton("Read");
        read.addActionListener(e->FileOperations.readFromFile());
        buttons.add(read);

        for(AbstractButton but:buttons){
            add(but);
        }


    }
}
