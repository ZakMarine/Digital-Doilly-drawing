/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;


/**
 *
 * @author Zak Marine
 */
public class FrameSection extends JFrame implements ActionListener{
    
    //set of global attibutes to be accessed by multiple methods
    private JTextField setSecNo;
    private JTextField sizeBox;
    private JTextField rFld;
    private JTextField gFld;
    private JTextField bFld;
    private JCheckBox showSec;
    private JCheckBox reflect;
    private GallerySection gallery = new GallerySection(this );
    private JButton saveBtn;
    JButton remBtn;
    private DrawingSection drawing = new DrawingSection(this);
    
    //constructor that passes the name of the frame
    public FrameSection(String nameIn){
        super(nameIn);
    }
    
    //initialisation method that sets up the frame
    public void init(){
        
        this.setLayout(new BorderLayout()); // sets the layout as a border layout
        this.setSize(625, 650); // sets the size of the frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // sets the close operation to end the program

        setUpTopPanel();
        setUpBotPanel();
        
        this.add(drawing);

        setResizable(false); // locks the resizing
        setVisible(true);  
    }
    
    //method that sets up the top utility panel.
    private void setUpTopPanel(){
        JPanel utilTopPanel = new JPanel();
        utilTopPanel.setLayout(new FlowLayout());
        JLabel rLbl = new JLabel("R");
        JLabel gLbl = new JLabel("G");
        JLabel bLbl = new JLabel("B");
        rFld = new JTextField(2);
        rFld.setText("0");
        gFld = new JTextField(2);
        gFld.setText("0");
        bFld = new JTextField(2);
        bFld.setText("0");
        JLabel colLbl = new JLabel("Colour: ");
        sizeBox = new JTextField(3);
        sizeBox.setText("5");
        JLabel sizeLbl = new JLabel("Size");
        JLabel secNoLbl = new JLabel("No. of Sectors");
        setSecNo = new JTextField(5);
        setSecNo.setText("12");
        showSec = new JCheckBox("Show Sector lines");
        showSec.setSelected(true);
        reflect = new JCheckBox("Reflect");
        utilTopPanel.add(colLbl);
        utilTopPanel.add(rLbl);
        utilTopPanel.add(rFld);
        utilTopPanel.add(gLbl);
        utilTopPanel.add(gFld);
        utilTopPanel.add(bLbl);
        utilTopPanel.add(bFld);
        utilTopPanel.add(sizeLbl);
        utilTopPanel.add(sizeBox);
        utilTopPanel.add(secNoLbl);
        utilTopPanel.add(setSecNo);
        utilTopPanel.add(showSec);
        utilTopPanel.add(reflect);
        
        
        this.add(utilTopPanel, BorderLayout.NORTH);
    }
    
    //method that sets up the bottom utility panel
    private void setUpBotPanel(){
        JPanel wholeBotPanel = new JPanel(new GridLayout(2,0)); // whole panel includes the button panel and the lower gallery
        JPanel utilBotPanel = new JPanel();
        utilBotPanel.setLayout(new FlowLayout());
        saveBtn = new JButton("Save");
        remBtn = new JButton("Remove");
        JButton clrBtn = new JButton("Clear");
        JButton undoBtn = new JButton("Undo");
        JButton resetBtn = new JButton("Reset Doilly");
        remBtn.setEnabled(false);
        utilBotPanel.add(saveBtn);
        utilBotPanel.add(remBtn);
        utilBotPanel.add(clrBtn);
        utilBotPanel.add(undoBtn);
        utilBotPanel.add(resetBtn);
        
        wholeBotPanel.add(gallery);// adds the gallery class to the bottom panel

        saveBtn.addActionListener(this);
        remBtn.addActionListener(this);
        clrBtn.addActionListener(this);
        undoBtn.addActionListener(this);
        resetBtn.addActionListener(this);
        
        wholeBotPanel.add(utilBotPanel);
        this.add(wholeBotPanel, BorderLayout.SOUTH); 
    }

    // action perfromed method to control what happens when a button is clicked
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        Graphics g = this.getGraphics();
        if (source.getText().equals("Reset Doilly")){ // reset Doilly is useds when either sector number of the or check boxes are changed to update drawing with new settings
            paint(g);
        }
        else if (source.getText().equals("Undo")){
            if (drawing.lineList.isEmpty()){}// if nothing has been drawn or all is deleted then do nothing
            else{// removes the last set of dots that was drawn, a set being all dots drawn drom clicking and then depressing the mouse button
                drawing.lineList.remove(drawing.lineList.size() - 1);
            }
            paint(g);//repaint the frame
        }
        else if (source.getText().equals("Clear")){
            drawing.lineList.removeAll(drawing.lineList);//removes everything that has been drawn
            paint(g);//repaint the frame
        }
        else if (source.getText().equals("Remove")){
            if(gallery.getImage().equals(null)){}//if nothing is selected do nothing
            else{
                gallery.remove(gallery.getImage());// removes the saved image
                gallery.remLbl();
            }
            repaint();
        }
        else if (source.getText().equals("Save")){
            gallery.setFirst();
            addToGallery(); //calls the add to galerry function
        }
    }   
        
    // function that creates the image that is passed to the galaery class
    public void addToGallery(){
        // creates a new buffered image to the whole drawing panel
        BufferedImage im = new BufferedImage(drawing.getWidth(), drawing.getWidth(), BufferedImage.TYPE_INT_RGB);
        Graphics g = im.createGraphics();
        drawing.paintComponent(g);
        gallery.addDoily(im);// calls the method that adds the doilly to the gallery panel
        g.dispose();
    }
    
    //method that gets the sector number
    public String getSecNo(){
        try{
            if (Integer.parseInt(setSecNo.getText()) < 4){// if the user tries to input less then 4 sections it defaults to 4
                return "4";
            }
            else if (Integer.parseInt(setSecNo.getText()) > 32){ // enters more then 32 section then defaults to 32
                return "32";
            }
            else{
                return setSecNo.getText(); // else return the value
            }
        }
        catch (Exception e){
            return "12"; // if the user puts letters into the text field it defautls to 12 sectors
        }
        
    }
    
    // method to get whether or not show sec is ticked
    public Boolean getShowSec(){
        return showSec.isSelected();
    }
    
    // method to get whether or not reflect is ticked
    public Boolean getReflect(){
        return reflect.isSelected();
    }
    
    //method to get the value in the pen size text field
    public Double getPenSize(){
        try{
            return Double.parseDouble(sizeBox.getText());
        }
        catch (Exception e){
            return Double.parseDouble("5"); // if the user puts letters into the text field the pen size is defaulted to 5
        }
        
    }
    
    // method that gets the color using the rgb text fields
    public Color getColor(){
        try{
            return new Color(Integer.parseInt(rFld.getText()), Integer.parseInt(gFld.getText()), Integer.parseInt(bFld.getText()));
        }
        catch (Exception e){
            return new Color(0,0,0); // if they place a number less than 0 or greater then 255 or places a leter in any field it defualts to black as the colour
        }
        
    }
}

    