/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author manh nguyenvan
 */
public class Cell extends JLabel{
    
    private int cellId;

    private boolean isVisit;
    
    
    public Cell(int cellId) {
        this.cellId = cellId;
        this.isVisit = false;
    }

    
    
    public int getCellId() {
        return cellId;
    }

    public void setCellId(int cellId) {
        this.cellId = cellId;
    }

    public boolean isIsVisit() {
        return isVisit;
    }

    public void setIsVisit(boolean isVisit) {
        this.isVisit = isVisit;
    }
    
    
    
    
    
    
    
    
    
    private ImageIcon icRoad = new ImageIcon("image/ic_grey_dark_cell.png");
    private ImageIcon icWall = new ImageIcon("image/ic_black_cell.png");
    private ImageIcon icPassed = new ImageIcon("image/ic_green_cell.png");
//    private ImageIcon icPassed = new ImageIcon("image/ic_grey_dark_cell.png");
    private ImageIcon icPassedTwice = new ImageIcon("image/ic_blue_cell.png");
    private ImageIcon icLight = new ImageIcon("image/ic_white_cell.png");
    
    private ImageIcon icRobot = new ImageIcon("image/ic_red_cell.png");

    
    
    
    private ImageIcon icRoadLineUpDown = new ImageIcon("image/line/doc.png");
    private ImageIcon icRoadLineDownUp = new ImageIcon("image/line/doc.png");
    private ImageIcon icRoadLineLeftRight = new ImageIcon("image/line/ngang.png");
    private ImageIcon icRoadLineRightLeft = new ImageIcon("image/line/ngang.png");
    
    private ImageIcon icRoadLine124 = new ImageIcon("image/line/trai_duoi.png");
    private ImageIcon icRoadLine421 = new ImageIcon("image/line/phai_tren.png");
    private ImageIcon icRoadLine421k = new ImageIcon("image/line/trai_tren.png");
    
    private ImageIcon icRoadLine134 = new ImageIcon("image/line/phai_tren.png");
    private ImageIcon icRoadLine431 = new ImageIcon("image/line/trai_duoi.png");
    private ImageIcon icRoadLine431k = new ImageIcon("image/line/phai_duoi.png");
    
    private ImageIcon icRoadLine213 = new ImageIcon("image/line/phai_duoi.png");
    private ImageIcon icRoadLine312 = new ImageIcon("image/line/trai_tren.png");
    private ImageIcon icRoadLine312k = new ImageIcon("image/line/phai_tren.png");
    
    private ImageIcon icRoadLine243 = new ImageIcon("image/line/trai_tren.png");
    private ImageIcon icRoadLine342 = new ImageIcon("image/line/phai_duoi.png");
    private ImageIcon icRoadLine342k = new ImageIcon("image/line/trai_duoi.png");
    
    
    
    
    public void setIconPassed(int key){
        switch(key){
            case 1:
                setIcon(icRoadLineUpDown);
                break;
            case 2:
                setIcon(icRoadLineDownUp);
                break;
            case 3:
                setIcon(icRoadLineLeftRight);
                break;
            case 4:
                setIcon(icRoadLineRightLeft);
                break;
                
            case 5:
                setIcon(icRoadLine124);
                break;
            case 6:
                setIcon(icRoadLine421);
                break;
            case 7:
                setIcon(icRoadLine421k);
                break;
                
            case 8:
                setIcon(icRoadLine134);
                break;
            case 9:
                setIcon(icRoadLine431);
                break;
            case 10:
                setIcon(icRoadLine431k);
                break;
                
            case 11:
                setIcon(icRoadLine213);
                break;
            case 12:
                setIcon(icRoadLine312);
                break;
            case 13:
                setIcon(icRoadLine312k);
                break;
                
            case 14:
                setIcon(icRoadLine243);
                break;
            case 15:
                setIcon(icRoadLine342);
                break;
            case 16:
                setIcon(icRoadLine342k);
                break;
                
                
            default:
                setIcon(icPassed);
                break;
            
        }
    }
    
    
    
    public void setIconRoad(){
        
        setIcon(icRoad);
    }
    
    public void setIconWall(){
        
        setIcon(icWall);
    }
    
    public void setIconPassed(){
        
        setIcon(icPassed);
    }
    
    public void setIconPassedTwice(){
        
        setIcon(icPassedTwice);
    }
    
    public void setIconLight(){
        
        setIcon(icLight);
    }
    
    public void setIconRobot(){
        
        setIcon(icRobot);
    }
    
    
    
    public void setMobileObject(boolean b){
        
//        setEnabled(!b);
        setVisible(!b);
    }
    
    
    
    
}
