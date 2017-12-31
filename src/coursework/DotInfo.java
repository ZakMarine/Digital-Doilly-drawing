/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Zak Marine
 */
public class DotInfo {
    private double penSize;
    private Color colour;
    private Boolean reflect;
    ArrayList<Point> pointList = new ArrayList<Point>();
    
    //method that adds a point to the list
    public void addPoint(Point pointIn){
        pointList.add(pointIn);
    }
    
    //method that returns whether or not a line is to be reflected
    public Boolean getReflect(){
        return reflect;
    }
    
    //sets the reflect value
    public void setReflect(Boolean reflectIn){
        reflect = reflectIn;
    }
    
    // gets the pen size of the line
    public double getPenSize(){
        return penSize;
    }
    
    // gets the colour of the line
    public Color getColor(){
        return colour;
    }
    
    // sets the pen size of the line
    public void setPenSize(double penSizeIn){
        penSize = penSizeIn;
    }
    
    // sets the colour of the line  
    public void setColor(Color colourIn){
        colour = colourIn;
    }
}
