/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author Zak Marine
 */
public class DrawingSection extends JPanel implements MouseListener, MouseMotionListener{
    
    DotInfo dotList;
    FrameSection frame;
    ArrayList<DotInfo> lineList = new ArrayList<DotInfo>();
    
    //constructor that takes the frame in and to be used
    public DrawingSection(FrameSection frameIn){
        frame = frameIn;
        init();
    }
    
    // initalistion method that adds the listeners to the panel for dot drawing
    public void init(){
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    //method that draws dots and sectgor lines on the panel
    public void draw(Graphics g, Integer sectorNoIn){
        Graphics2D g2 = (Graphics2D) g;
        Integer sectorNo = sectorNoIn; 
        double angle = ((360/sectorNo)*Math.PI/180);
        //for loop that tracks the number of times all drawn elements need to be rotated by
        for (int k = 0; k < Integer.parseInt(frame.getSecNo()); k++){
            // rotate function that rotates around the center of the panel
            g2.rotate(angle, this.getWidth()/2, this.getHeight()/2);
            //setting the colour to black to draw sector lines
            g2.setColor(Color.BLACK);
            // statement to draw lines if show sectore lines check box is ticked
            if (frame.getShowSec()){g2.drawLine(this.getWidth()/2, this.getHeight()/2, this.getWidth()/2, 100);}
            // for loop that loops around the number of lines that are present
            for (int i = 0; i < lineList.size(); i++){
                // for loop that loops around the number of dots that are in a line
                for (int j = 0; j < lineList.get(i).pointList.size(); j++){
                    // setting the colour of the dots to the inputted RGB values when the dot is drawn
                    g2.setColor(lineList.get(i).getColor());
                    // drawing the dots based off the the x and y co-ordinate stored and the size based of the pen value inputted
                    g2.fillOval((int) lineList.get(i).pointList.get(j).getX(), (int)lineList.get(i).pointList.get(j).getY() , (int) lineList.get(i).getPenSize(), (int) lineList.get(i).getPenSize() );
                    if (lineList.get(i).getReflect()){
                        // if the dot needs to be reflected the x distance from the point to the middle of the pane is calculated
                        int dx = (int) lineList.get(i).pointList.get(j).getX() - this.getWidth()/2;
                        // a dot is drawn at the same distance from middle of the panel but on the other side to achieve a reflection
                        g2.fillOval(this.getWidth()/2 - dx, (int)lineList.get(i).pointList.get(j).getY(), (int) lineList.get(i).getPenSize(), (int) lineList.get(i).getPenSize());
                    }
                }
            }
        }
    }

    //method that paints all compoenets
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //call the draw method
        draw(g, Integer.parseInt(frame.getSecNo()));
    }
    
    // when the mouse is dragged the current point of the mouse is added to the line
    public void mouseDragged(MouseEvent e) {
        dotList.addPoint(e.getPoint());
        repaint();
    }
    
    // when the mouse is first pressed a new instance of a 'line' is created and the colour and size is stored
    public void mousePressed(MouseEvent e) {
        dotList = new DotInfo();
        dotList.setPenSize(frame.getPenSize());
        dotList.addPoint(e.getPoint());
        dotList.setColor(frame.getColor());
        dotList.setReflect(frame.getReflect());
        lineList.add(dotList);
        repaint();

    }
    public void mouseMoved(MouseEvent e) {} 
    public void mouseClicked(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

}