/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author manh nguyenvan
 */
public class Point {
    
    private int i;
    private int j;

    private int cellId;
    
    
    public Point(int i, int j, int cellId) {
        this.i = i;
        this.j = j;
        
        this.cellId = cellId;
    }

    
    
    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public int getCellId() {
        return cellId;
    }

    public void setCellId(int cellId) {
        this.cellId = cellId;
    }
    
    
    
    
    
    
    public boolean checkDifferent(Point douprev, Point prev, Point curr){
        /*switch(prev.cellId){
            case 1:
                if (((douprev.getI() != prev.getI()) && (prev.getJ() != curr.getJ()))
                        || ((douprev.getJ() != prev.getJ()) && (prev.getI() != curr.getI()))) {
                    return true;
                } 
                break;
            case 2:
                if ((douprev.getI() != prev.getI())
                        && (prev.getJ() != curr.getJ())) {
                    return true;
                } 
                break;
            case 3:
                if ((douprev.getI() != prev.getI())
                        && (prev.getJ() != curr.getJ())) {
                    return true;
                } 
                break;
            case 4:
                if ((douprev.getI() != prev.getI())
                        && (prev.getJ() != curr.getJ())) {
                    return true;
                } 
                break;
            
        }*/
        if (((douprev.getI() != prev.getI()) && (prev.getJ() != curr.getJ()))
                        || ((douprev.getJ() != prev.getJ()) && (prev.getI() != curr.getI()))) {
                    return true;
                } 
        return false;
    }
    
    
    
    
    
    
    
    public int getCellLocationX(Point currentPoint){
        
        int x = 0, y = 0;
        
        switch (currentPoint.getCellId()) {
            case 1:
                x = currentPoint.getI() * 2;
                y = currentPoint.getJ() * 2;
                break;
            case 2:
                x = currentPoint.getI() * 2;
                y = currentPoint.getJ() * 2 + 1;
                break;
            case 3:
                x = currentPoint.getI() * 2 + 1;
                y = currentPoint.getJ() * 2;
                break;
            default:
                x = currentPoint.getI() * 2 + 1;
                y = currentPoint.getJ() * 2 + 1;
                break;
        }
        
        return x;
        
    }
    
    public int getCellLocationY(Point currentPoint){
        
        int y = 0;
        
        switch (currentPoint.getCellId()) {
            case 1:
                y = currentPoint.getJ() * 2;
                break;
            case 2:
                y = currentPoint.getJ() * 2 + 1;
                break;
            case 3:
                y = currentPoint.getJ() * 2;
                break;
            default:
                y = currentPoint.getJ() * 2 + 1;
                break; 
        }
        
        return y;
    }
    
    
    public int getMegaCellLocationX(int x, int y){
        return (x / 2);
        
    }
    
    public int getMegaCellLocationY(int x, int y){
        return (y / 2);
        
    }
    
    
    
    
    public int convertPointToKeyId(Point point, int size){
        
        return (point.getI() * size + point.getJ());
        
    }
    
    
    
    public void display(){
        System.out.println("point: (" + getI() + ", " + getJ() + ", " + getCellId() + ")");
    }
    
    
    
}
