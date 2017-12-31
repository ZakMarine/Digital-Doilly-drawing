/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author Zak Marine
 */
public class GallerySection extends JPanel implements MouseListener{
    
    private int noSaveDoillies;
    private FrameSection frame;
    private JLabel source;
    private Boolean selected  = false;
    private Boolean first = false;
    private ArrayList<JLabel> imLst = new ArrayList<JLabel>();
    private ArrayList<Boolean> emptySpace = new ArrayList<Boolean>();
    private int position = 0;
    
    //constructor the takes the main frame so it can be accessed in the gallery
    public GallerySection(FrameSection frame){
        this.frame = frame;
        init();
    }
    
    // initation method that sets the gallery panel to be agridlayout with 12 sections
    public void init(){
        this.setLayout(new GridLayout(1,12));
        for (int i = 0; i < 12; i++){
            JLabel label = new JLabel();
            label.addMouseListener(this);
            imLst.add(label);
            this.add(imLst.get(i));
            emptySpace.add(true);
        }
    }
    
    // method to get the number of doillies
    public int getNoDoilly(){
        return noSaveDoillies;
    }
    
    public void remLbl(){
        imLst.get(position).setIcon(null);
        emptySpace.set(position, true);
        noSaveDoillies--;
    }
    
    // method to get the lable the the image is attached to
    public JLabel getImage(){
        try{
            return source;
        }
        catch(Exception e){
            return null;
        }
    }
    
    //method that adds the doilly as a lable icon and adds to the gallery panel
    public void addDoily(BufferedImage im){
        if (noSaveDoillies == 12){}
        else{
            imLst.get(noSaveDoillies).setIcon(new ImageIcon(resize(im)));
            //JLabel lbl = new JLabel();
            // method setting the lable icon to a resized version of the image
            //lbl.setIcon(new ImageIcon(resize(im)));
            //lbl.addMouseListener(this);
            //this.add(lbl);
            //this.revalidate();
            noSaveDoillies++;
        }
    }
    
    //method that resizes thethe image to the size of panel
    public BufferedImage resize(BufferedImage im){
            Image tmp = im.getScaledInstance(frame.getWidth()/12, this.getHeight(), Image.SCALE_SMOOTH);
            BufferedImage nimg = new BufferedImage(frame.getWidth()/12, this.getHeight(), BufferedImage.TYPE_INT_RGB);
            
            Graphics g2 = nimg.createGraphics();
            g2.drawImage(tmp, 0,0, null);
            g2.dispose();
            
            return nimg;
    }
    
    public void setFirst(){
        first = true;
    }
    
    // method that selects the image for deletion
    public void mouseClicked(MouseEvent e) {
        if (first){
            if (selected){
            imLst.get(imLst.indexOf(source)).setText(null);
            }
            //assignes the current label that is selected to source variable
            source = (JLabel) e.getSource();
            //enables the remove button once it is deleted button
            frame.remBtn.setEnabled(true);
            //sets the text position to ontop of the image
            source.setHorizontalTextPosition(JLabel.CENTER);
            //produces the selected overlay
            source.setText("Selected");
            selected = true;
            position = imLst.indexOf(source);
            if (noSaveDoillies == 0){
                selected = false;
            }   
        }
        
        
        this.revalidate();
    }
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
