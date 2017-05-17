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
public class MegaCell {
    
//    private Cell cell1;
//    private Cell cell2;
//    private Cell cell3;
//    private Cell cell4;
    
    
    private Cell cell;
    
    private int megaCellId; // !!!!!!!!!
    
    
    private boolean isObstacle; //ô này là chướng ngại vật

    private boolean right;
    private boolean left;
    private boolean up;
    private boolean down;
    
    
    
    public MegaCell(){
        this.isObstacle = true;
        
        this.left = false;
        this.right = false;
        this.up = false;
        this.down = false;
    }
    
    public MegaCell(Cell cell, boolean isObstacle) {
        
        this.cell = cell;
        
        this.isObstacle = isObstacle;
        
        this.left = false;
        this.right = false;
        this.up = false;
        this.down = false;
    }

    
    
    
    
    
    
    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    
    
    
    
//    public Cell getCell1() {
//        return cell1;
//    }
//
//    public void setCell1(Cell cell1) {
//        this.cell1 = cell1;
//    }
//
//    public Cell getCell2() {
//        return cell2;
//    }
//
//    public void setCell2(Cell cell2) {
//        this.cell2 = cell2;
//    }
//
//    public Cell getCell3() {
//        return cell3;
//    }
//
//    public void setCell3(Cell cell3) {
//        this.cell3 = cell3;
//    }
//
//    public Cell getCell4() {
//        return cell4;
//    }
//
//    public void setCell4(Cell cell4) {
//        this.cell4 = cell4;
//    }

    public boolean isIsObstacle() {
        return isObstacle;
    }

    public void setIsObstacle(boolean isObstacle) {
        this.isObstacle = isObstacle;
    }

    
    
    
    public int getMegaCellId() {
        return megaCellId;
    }

    public void setMegaCellId(int megaCellId) {
        this.megaCellId = megaCellId;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }
    
    
    
    
    
    
}
