/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import models.Map;
import models.MegaCell;
import models.Cell;
import models.Point;
import utils.SpiralSTC;

/**
 *
 * @author manh nguyenvan
 */
public class VMap extends javax.swing.JFrame {

    
    private int size;
    
    private Cell[][] labelCell;
    
    private JPanel panelCell;
    
    
    
    
    
    
    
    private MegaCell[][] megaCell;
    private Map map;
    private SpiralSTC spiralSTC;
    
    private Point startPoint;
    private Point currentPoint;
    
    
    
    /**
     * Creates new form VMap
     */
    public VMap() {
        initComponents();
        
        
        setLocationRelativeTo(null);
//        setResizable(false);
        
        setTitle("STC");
        

        
        
        this.size = 12;
        
        setVMap(size);
        
        
        
        startPoint = new Point(0, 0, 1);
        currentPoint = startPoint;
        
        setMap(size);
        
        
        
        refreshMap();
        refreshCell();
        
        
        toggleBtWall.setSelected(true);
        cbSTC.setSelectedIndex(1);
    }
    
    
    
    
    private void setVMap(int size){
        
        if (size > 12) {
            size = 12;
        }
        
        this.size = size;
        
        labelCell = new Cell[size * 2][size * 2];
        
        
        
//        panelMap.setBounds(400, 50, size * 4 *30+6, size * 4 *30+84);
        
        
        
        
        for (int i = 0; i < (size * 2); i++) {
            for (int j = 0; j < (size * 2); j++) {
                
                final int X, Y;
                X = i;
                Y = j;
                
                labelCell[j][i] = new Cell(1);
                labelCell[j][i].setIconRoad();
//                labelCell[j][i].setBounds(10 + i * 24, 20 + j * 24, 29, 24);
                labelCell[j][i].setBounds(10 + i * 25, 20 + j * 25, 29, 24);
                
                panelMap.add(labelCell[j][i]);
                
                labelCell[j][i].addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        
                        
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        
                        
                        if (toggleBtMobile.isSelected()) {
                            
                            int c = map.getMegaCellLocationX(X, Y);
                            int d = map.getMegaCellLocationY(X, Y);
                            
                            
                            spiralSTC.addMobileObject(new Point(d, c, 1));

                            lbCountMobileObject.setText(String.valueOf(spiralSTC.getMobileObjectPointList().size()));
                            
                            
                            
                            
                            Point pointCurr = spiralSTC.getMobileObjectPointList().get(spiralSTC.getMobileObjectPointList().size() - 1);

                            int xCell = pointCurr.getCellLocationX(pointCurr);
                            int yCell = pointCurr.getCellLocationY(pointCurr);

                //            System.out.println(xCell + ", " + yCell);

                            labelCell[pointCurr.getCellLocationX(pointCurr)]
                                    [pointCurr.getCellLocationY(pointCurr)]
                                    .setMobileObject(true);
                            
                            
                        }
                        
                        
                        
                        if (toggleBtWall.isSelected()) {
                            int c = map.getMegaCellLocationX(X, Y);
                            int d = map.getMegaCellLocationY(X, Y);
                            System.out.println("" + d + ", " + c);
                            
                            if (map.getMegaCellLocationX(X, Y) != startPoint.getJ()
                                    || map.getMegaCellLocationY(X, Y) != startPoint.getI()) {
                                
                                map.getMap()[map.getMegaCellLocationY(X, Y)][map.getMegaCellLocationX(X, Y)]
                                        .setIsObstacle(true);
                                
                                
                                
                                // update adjacency matrix
                                map.updateAdjacencyMatrix(X, Y);
                                
                            }
                            
//                            map.getMap()[map.getMegaCellLocationY(X, Y)][map.getMegaCellLocationX(X, Y)]
//                                    .setIsObstacle(true);

                        }
                        
                        
                        if (toggleBtWall.isSelected()) {
                            
                            if (map.getMegaCellLocationX(X, Y) != startPoint.getJ()
                                    || map.getMegaCellLocationY(X, Y) != startPoint.getI()) {
                                

                                labelCell[Y][X].setIconWall();

                                if (Y % 2 == 0) {
                                    if (X % 2 == 0) {
                                        labelCell[Y + 1][X].setIconWall();
                                        labelCell[Y][X + 1].setIconWall();
                                        labelCell[Y + 1][X + 1].setIconWall();

                                    } else {
                                        labelCell[Y + 1][X].setIconWall();
                                        labelCell[Y][X - 1].setIconWall();
                                        labelCell[Y + 1][X - 1].setIconWall();
                                    }

                                } else {
                                    if (X % 2 == 0) {
                                        labelCell[Y - 1][X].setIconWall();
                                        labelCell[Y][X + 1].setIconWall();
                                        labelCell[Y - 1][X + 1].setIconWall();

                                    } else {
                                        labelCell[Y - 1][X].setIconWall();
                                        labelCell[Y][X - 1].setIconWall();
                                        labelCell[Y - 1][X - 1].setIconWall();
                                    }

                                }


                            }
                        }
                        
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        
                    }
                });
            }
        }
        
        
        
    }
    
    
    private void refreshMap(){
        for (int i = 0; i < (size * 2); i++) {
            for (int j = 0; j < (size * 2); j++) {
                labelCell[i][j].setIconRoad();
            }
        }
        
        labelCell[startPoint.getCellLocationX(startPoint)][startPoint.getCellLocationY(startPoint)].setIconRobot();
        
    }
    
    
    private void refreshCell(){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                map.getMap()[i][j].setIsObstacle(false);
                
                map.getMap()[i][j].setUp(false);
                map.getMap()[i][j].setDown(false);
                map.getMap()[i][j].setRight(false);
                map.getMap()[i][j].setLeft(false);
                
                map.getMap()[i][j].getCell().setCellId(1);
            }
        }
        
    }
    
    
    private void refreshPath(){
        
        int X, Y;
        
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (!map.getMap()[i][j].isIsObstacle()) {
                    
                    Y = i * 2;
                    X = j * 2;

                    
                    labelCell[Y][X].setIconRoad();

                    if (Y % 2 == 0) {
                        if (X % 2 == 0) {
                            labelCell[Y + 1][X].setIconRoad();
                            labelCell[Y][X + 1].setIconRoad();
                            labelCell[Y + 1][X + 1].setIconRoad();

                        } else {
                            labelCell[Y + 1][X].setIconRoad();
                            labelCell[Y][X - 1].setIconRoad();
                            labelCell[Y + 1][X - 1].setIconRoad();
                        }

                    } else {
                        if (X % 2 == 0) {
                            labelCell[Y - 1][X].setIconRoad();
                            labelCell[Y][X + 1].setIconRoad();
                            labelCell[Y - 1][X + 1].setIconRoad();

                        } else {
                            labelCell[Y - 1][X].setIconRoad();
                            labelCell[Y][X - 1].setIconRoad();
                            labelCell[Y - 1][X - 1].setIconRoad();
                        }

                    }


                }
                
            }
        }
        
        
        
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
//                map.getMap()[i][j].setIsObstacle(false);
                
                map.getMap()[i][j].setUp(false);
                map.getMap()[i][j].setDown(false);
                map.getMap()[i][j].setRight(false);
                map.getMap()[i][j].setLeft(false);
                
                map.getMap()[i][j].getCell().setCellId(1);
            }
        }
        
        
        
        labelCell[startPoint.getCellLocationX(startPoint)][startPoint.getCellLocationY(startPoint)].setIconRobot();
        
    }
    
    
    
    private void setMap(int size){
        
        if (size > 12) {
            size = 12;
        }
        
        
        map = new Map(size);
        spiralSTC = new SpiralSTC(new Point(0, 0, 1), map);
        
//        spiralSTC.DFSAlgorithm();
////        spiralSTC.pri();
//        spiralSTC.setEdgeOfPath();
        
        
        
    }
    
    
    
    
    
    
    private int getCellLocationX(Point currentPoint){
        
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
    
    private int getCellLocationY(Point currentPoint){
        
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
    
    
    private int getMegaCellLocationX(int x, int y){
        return (x / 2);
        
    }
    
    private int getMegaCellLocationY(int x, int y){
        return (y / 2);
        
    }
    
    
    
    
    
    
    
    /*
    public void spiralSTC(){
        
//        startPoint = new Point(0, 0, 1);
//        currentPoint = startPoint;
        
//        while(!checkVisitedAll()){
//        while(currentPoint.getI() != 1 
//                || currentPoint.getJ() != 0
//                || currentPoint.getCellId() != 2){
        do {
            currentPoint = spiralSTC.getCellNext();
            
            labelCell[getCellLocationX(currentPoint)][getCellLocationY(currentPoint)].setIconPassed();
            
        } while(currentPoint.getI() != startPoint.getI()
                    || currentPoint.getJ() != startPoint.getJ()
                    || currentPoint.getCellId() != startPoint.getCellId());
            
    }
    */
    
    
    
    private boolean checkExistMobileObject(){
        if (spiralSTC.getMobileObjectPointList().size() <= 0) {
            return false;
        }
        return true;
    }
    
    public void setMobileObjectCurrentPoint(){

        List<Point> mobileObjectPointList = spiralSTC.getMobileObjectPointList();

        int xCell = 0, yCell = 0;
        Point pointCurr;
        for (int i = 0; i < mobileObjectPointList.size(); i++) {
            pointCurr = spiralSTC.getMobileObjectPointList().get(i);

            xCell = pointCurr.getCellLocationX(pointCurr);
            yCell = pointCurr.getCellLocationY(pointCurr);

//            System.out.println(xCell + ", " + yCell);

            labelCell[pointCurr.getCellLocationX(pointCurr)]
                    [pointCurr.getCellLocationY(pointCurr)]
                    .setMobileObject(false);
//                    .setIconRoad();


            pointCurr = spiralSTC.mobileObjectGetCellNext(i, currentPoint);

//            System.out.println("cuurrr " + pointCurr.getCellLocationX(pointCurr) + " | " + pointCurr.getCellLocationY(pointCurr));

            labelCell[pointCurr.getCellLocationX(pointCurr)]
                    [pointCurr.getCellLocationY(pointCurr)]
                    .setMobileObject(true);
//                    .setIconRobot();


        }

        
    }
    
    
    
    
    
    private boolean checkDifferent(Point douprev, Point prev, Point curr){
        
        if (((douprev.getI() != prev.getI()) && (prev.getJ() != curr.getJ()))
                        || ((douprev.getJ() != prev.getJ()) && (prev.getI() != curr.getI()))) {
                    return true;
                } 
        return false;
    }
    
    
    private int checkRoadLine(Point douprev, Point prev, Point curr){
        
        if (prev.getCellId() == 2) {
            if (((douprev.getCellId() == 1) && (curr.getCellId() == 4))
                    || ((douprev.getCellId() == 4) && (curr.getCellId() == 1))) {
                
                if (((douprev.getI() == prev.getI()) && (prev.getI() == curr.getI()))
                        ) {
                    return 5;
                } else if (checkDifferent(douprev, prev, curr)) {
                    return 6;
                } 
                else {
                    return 7;
                }
                
                
            }
        } else if (prev.getCellId() == 3) {
            if (((douprev.getCellId() == 1) && (curr.getCellId() == 4))
                    || ((douprev.getCellId() == 4) && (curr.getCellId() == 1))
                    ) {
                if (((douprev.getI() == prev.getI()) && (prev.getI() == curr.getI()))
                        ) {
                    return 8;
                } else if (checkDifferent(douprev, prev, curr)) {
                    return 9;
                } 
                else {
                    return 10;
                }
                
                
            }
        } else if (prev.getCellId() == 1) {
            if (((douprev.getCellId() == 2) && (curr.getCellId() == 3))
                    || ((douprev.getCellId() == 3) && (curr.getCellId() == 2))) {
                if (((douprev.getI() == prev.getI()) && (prev.getI() == curr.getI()))
                        ) {
                    return 11;
                } else if (checkDifferent(douprev, prev, curr)) {
                    return 12;
                } 
                else {
                    return 13;
                }
                
                
            }
        } else if (prev.getCellId() == 4) {
            if (((douprev.getCellId() == 2) && (curr.getCellId() == 3))
                    || ((douprev.getCellId() == 3) && (curr.getCellId() == 2))) {
                if (((douprev.getI() == prev.getI()) && (prev.getI() == curr.getI()))
                        ) {
                    return 14;
                } else if (checkDifferent(douprev, prev, curr)) {
                    return 15;
                } 
                else {
                    return 16;
                }
                
                
            }
        } 
        
        
        if (prev.getI() == curr.getI()) {
            
            if (prev.getJ() == curr.getJ()) {
                
                switch(prev.getCellId()){
                    case 1:
                        if (curr.getCellId() == 2) {
                            return 4;
                        } else if (curr.getCellId() == 3) {
                            return 1;
                        }
                        break;
                    case 2:
                        if (curr.getCellId() == 1) {
                            return 4;
                        } else if (curr.getCellId() == 4) {
                            return 2;
                        }
                        break;
                    case 3:
                        if (curr.getCellId() == 1) {
                            return 1;
                        } else if (curr.getCellId() == 4) {
                            return 3;
                        }
                        break;
                    case 4:
                        if (curr.getCellId() == 2) {
                            return 2;
                        } else if (curr.getCellId() == 3) {
                            return 3;
                        }
                        break;
                        
                }
                
            }
            else {
                if (prev.getJ() < curr.getJ()) {
                    
                    return 3;
                } else {    //>
                    
                    return 4;
                }
                
            }
            
        } 
        else {
            
            if (prev.getI() < curr.getI()) {
                
                if (prev.getJ() == curr.getJ()) {

                }
                else {

                }
                
                return 1;
                
            } else {    //>
                
                if (prev.getJ() == curr.getJ()) {

                }
                else {

                }
                
                return 2;
                
            }
            
        }
        
        return -1;
    }
    
    
    
    
    
    
    
    
    class Spiral extends Thread {

        private int totalMegaCell = 0;
        private int sumStep = 0;
        private int countOverlap = 0;
        
        public void run(){

            sumStep = 0;
            
//            startPoint = new Point(0, 0, 1);
            currentPoint = startPoint;
            
//            currentPoint = start;
            



            Point tempPoint = new Point(0, 0, 0);
            Point prevTempPoint = new Point(0, 0, 0);

            
            Point temp = new Point(0, 0, 0);


    //        while(!checkVisitedAll()){
//            while(currentPoint.getI() != startPoint.getI()
//                    || currentPoint.getJ() != startPoint.getJ()
//                    || currentPoint.getCellId() != startPoint.getCellId()){
            do {
                
                try {
                    if (checkExistMobileObject()) {
                        Thread.sleep(100);
                        setMobileObjectCurrentPoint();  //vật cản di động
                        
                        
                        ////
                        temp.setI(prevTempPoint.getI());
                        temp.setJ(prevTempPoint.getJ());
                        temp.setCellId(prevTempPoint.getCellId());

                        
                    }
                    
                    
                    
                    ////
                    prevTempPoint.setI(tempPoint.getI());
                    prevTempPoint.setJ(tempPoint.getJ());
                    prevTempPoint.setCellId(tempPoint.getCellId());
                    ////
                    tempPoint.setI(currentPoint.getI());
                    tempPoint.setJ(currentPoint.getJ());
                    tempPoint.setCellId(currentPoint.getCellId());


                    
                    currentPoint = spiralSTC.getCellNext();
                    
                    
                    if (tempPoint.getCellId() == currentPoint.getCellId()) {    //if -> restore
                        
                        ////
                        tempPoint.setI(prevTempPoint.getI());
                        tempPoint.setJ(prevTempPoint.getJ());
                        tempPoint.setCellId(prevTempPoint.getCellId());
                        ////
                        prevTempPoint.setI(temp.getI());
                        prevTempPoint.setJ(temp.getJ());
                        prevTempPoint.setCellId(temp.getCellId());
                        
                    }
                    
                    //
                    sumStep ++;
                    
                    
                    
                    
                    ////
                    int key = checkRoadLine(prevTempPoint, tempPoint, currentPoint);
                    labelCell[getCellLocationX(tempPoint)][getCellLocationY(tempPoint)].setIconPassed(key);
//                    labelCell[getCellLocationX(tempPoint)][getCellLocationY(tempPoint)].setIconPassed();
                    labelCell[getCellLocationX(currentPoint)][getCellLocationY(currentPoint)].setIconRobot();
                    
                    
                    totalMegaCell = sumStep / 4;
                    
                    
                    
                    
//                    labelCell[getCellLocationX(currentPoint)][getCellLocationY(currentPoint)].setIconPassed();
                    
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    Logger.getLogger(VMap.class.getName()).log(Level.SEVERE, null, ex);
                }

            } while(currentPoint.getI() != startPoint.getI()
                    || currentPoint.getJ() != startPoint.getJ()
                    || currentPoint.getCellId() != startPoint.getCellId());

            
            
            
            
            /////
            if (totalMegaCell > 0) {
                if (sumStep > (totalMegaCell * 4)) {
                    sumStep = totalMegaCell * 4;
                }
                double coverage = sumStep * 100.0/ (totalMegaCell * 4);
                double overlap = countOverlap * 100.0/ (totalMegaCell * 4);
                
                JOptionPane.showMessageDialog(null, "Độ bao phủ: " + sumStep + "/ " + totalMegaCell * 4 + " ô "
                        + " (" + coverage + "%)" + "\n"
                        + "Độ chồng lấn: " + countOverlap + "/ " + totalMegaCell * 4 + " ô "
                        + " (" + overlap + "%)");

            }
            
            
            //
            int isSave = JOptionPane.showConfirmDialog(null, "Lưu kết quả so sánh");
            if(isSave == 0){
                spiralSTC.makeFile(sumStep, countOverlap, totalMegaCell);
            }
            
            
            cbSTC.setSelectedIndex(1);
            
            
            
        }
    }
    
    class SpiralOnline extends Thread {
        
        private int totalMegaCell = 0;
        private int sumStep = 0;
        private int countOverlap = 0;
        
        public void run(){

            sumStep = 0;
            
//            startPoint = new Point(0, 0, 1);
            currentPoint = startPoint;
            
//            currentPoint = start;
            


            Point tempPoint = new Point(0, 0, 0);
            Point prevTempPoint = new Point(0, 0, 0);

            
            Point temp = new Point(0, 0, 0);


    //        while(!checkVisitedAll()){
//            while(currentPoint.getI() != startPoint.getI()
//                    || currentPoint.getJ() != startPoint.getJ()
//                    || currentPoint.getCellId() != startPoint.getCellId()){
            do {
                
                try {
                    if (checkExistMobileObject()) {
                        Thread.sleep(100);
                        setMobileObjectCurrentPoint();  //vật cản di động
                        
                        
                        ////
                        temp.setI(prevTempPoint.getI());
                        temp.setJ(prevTempPoint.getJ());
                        temp.setCellId(prevTempPoint.getCellId());

                        
                    }
                    
                    
                    
                    ////
                    prevTempPoint.setI(tempPoint.getI());
                    prevTempPoint.setJ(tempPoint.getJ());
                    prevTempPoint.setCellId(tempPoint.getCellId());
                    ////
                    tempPoint.setI(currentPoint.getI());
                    tempPoint.setJ(currentPoint.getJ());
                    tempPoint.setCellId(currentPoint.getCellId());


                    
                    
                    
                    currentPoint = spiralSTC.getCellNextOnline();
                    
                    if (tempPoint.getCellId() == currentPoint.getCellId()) {    //if -> restore
                        
                        ////
                        tempPoint.setI(prevTempPoint.getI());
                        tempPoint.setJ(prevTempPoint.getJ());
                        tempPoint.setCellId(prevTempPoint.getCellId());
                        ////
                        prevTempPoint.setI(temp.getI());
                        prevTempPoint.setJ(temp.getJ());
                        prevTempPoint.setCellId(temp.getCellId());
                        
                    }
                    
                    
                    
                    //
                    sumStep ++;
                    
                    
                    
                    ////
                    int key = checkRoadLine(prevTempPoint, tempPoint, currentPoint);
                    labelCell[getCellLocationX(tempPoint)][getCellLocationY(tempPoint)].setIconPassed(key);
//                    labelCell[getCellLocationX(tempPoint)][getCellLocationY(tempPoint)].setIconPassed();
                    labelCell[getCellLocationX(currentPoint)][getCellLocationY(currentPoint)].setIconRobot();
                    
                    
                    
                    totalMegaCell = sumStep / 4;
                    
                    
                    
//                    labelCell[getCellLocationX(currentPoint)][getCellLocationY(currentPoint)].setIconPassed();
                    
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    Logger.getLogger(VMap.class.getName()).log(Level.SEVERE, null, ex);
                }

            } while(currentPoint.getI() != startPoint.getI()
                    || currentPoint.getJ() != startPoint.getJ()
                    || currentPoint.getCellId() != startPoint.getCellId());
            
            
            
            
            /////
            if (totalMegaCell > 0) {
                if (sumStep > (totalMegaCell * 4)) {
                    sumStep = totalMegaCell * 4;
                }
                double coverage = sumStep * 100.0/ (totalMegaCell * 4);
                double overlap = countOverlap * 100.0/ (totalMegaCell * 4);
                
                JOptionPane.showMessageDialog(null, "Độ bao phủ: " + sumStep + "/ " + totalMegaCell * 4 + " ô "
                        + " (" + coverage + "%)" + "\n"
                        + "Độ chồng lấn: " + countOverlap + "/ " + totalMegaCell * 4 + " ô "
                        + " (" + overlap + "%)");

            }
            
            
            
            
            //
            int isSave = JOptionPane.showConfirmDialog(null, "Tiến hành so sánh với STC chuẩn");
            if(isSave == 0){
                cbSTC.setSelectedIndex(0);
                btRestart.doClick();
                
                try {
                    Thread.sleep(750);
                } catch (InterruptedException ex) {
                    Logger.getLogger(VMap.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                btStart.doClick();
            }
            
            
            

        }
    }
    
    
    class BatteryLow extends Thread {

        public void run(){

//            startPoint = new Point(0, 0, 1);
//            currentPoint = startPoint;
            
//            currentPoint = start;

            
            
            labelCell[getCellLocationX(currentPoint)][getCellLocationY(currentPoint)].setIconPassedTwice();
    
            
            List<Point> list = new ArrayList<>();
            list.addAll(spiralSTC.isLowBattery(currentPoint, startPoint));
//            for (int i = 0; i < spiralSTC.isLowBattery(currentPoint, startPoint).size(); i++) {
//                
//            }

            
    //        while(!checkVisitedAll()){
//            while(currentPoint.getI() != startPoint.getI()
//                    || currentPoint.getJ() != startPoint.getJ()
//                    || currentPoint.getCellId() != startPoint.getCellId()){
//            do {
                
                try {
                    System.out.println("pin yếu");
                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).display();
//                        labelCell[getCellLocationX(list.get(i))][getCellLocationY(list.get(i))].setIconPassedTwice();
                        
                        if (i > 0) {
                            labelCell[getCellLocationX(list.get(i - 1))][getCellLocationY(list.get(i - 1))].setIconPassedTwice();
                        }
                        labelCell[getCellLocationX(list.get(i))][getCellLocationY(list.get(i))].setIconRobot();
                        
                        Thread.sleep(150);
                    }
                    
                    //labelCell[getCellLocationX(currentPoint)][getCellLocationY(currentPoint)].setIconPassed();
                    
                    //Thread.sleep(25);
                } catch (InterruptedException ex) {
                    Logger.getLogger(VMap.class.getName()).log(Level.SEVERE, null, ex);
                }

//            } while(currentPoint.getI() != startPoint.getI()
//                    || currentPoint.getJ() != startPoint.getJ()
//                    || currentPoint.getCellId() != startPoint.getCellId());

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        panelMap = new javax.swing.JPanel();
        panelControl = new javax.swing.JPanel();
        toggleBtWall = new javax.swing.JToggleButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        btStart = new javax.swing.JButton();
        btRestart = new javax.swing.JButton();
        btPause = new javax.swing.JButton();
        btCancel = new javax.swing.JButton();
        btBatteryLow = new javax.swing.JButton();
        cbSTC = new javax.swing.JComboBox<>();
        jSeparator3 = new javax.swing.JSeparator();
        btRemoveAllMobileObject = new javax.swing.JButton();
        lbCountMobileObject = new javax.swing.JLabel();
        toggleBtMobile = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout panelMapLayout = new javax.swing.GroupLayout(panelMap);
        panelMap.setLayout(panelMapLayout);
        panelMapLayout.setHorizontalGroup(
            panelMapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 630, Short.MAX_VALUE)
        );
        panelMapLayout.setVerticalGroup(
            panelMapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 640, Short.MAX_VALUE)
        );

        toggleBtWall.setText("Đặt vật cản cố định");
        toggleBtWall.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                toggleBtWallStateChanged(evt);
            }
        });
        toggleBtWall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toggleBtWallActionPerformed(evt);
            }
        });

        btStart.setText("Bắt đầu");
        btStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btStartActionPerformed(evt);
            }
        });

        btRestart.setText("Làm mới đường đi");
        btRestart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRestartActionPerformed(evt);
            }
        });

        btPause.setText("Dừng");
        btPause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPauseActionPerformed(evt);
            }
        });

        btCancel.setText("Hủy");
        btCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelActionPerformed(evt);
            }
        });

        btBatteryLow.setText("Pin yếu");
        btBatteryLow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBatteryLowActionPerformed(evt);
            }
        });

        cbSTC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "STC chuẩn", "STC cài đặt cơ sở tri thức" }));

        btRemoveAllMobileObject.setText("Loại bỏ toàn bộ vật cản di động");
        btRemoveAllMobileObject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRemoveAllMobileObjectActionPerformed(evt);
            }
        });

        lbCountMobileObject.setText(" ");

        toggleBtMobile.setText("Đặt vật cản di động");
        toggleBtMobile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toggleBtMobileActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelControlLayout = new javax.swing.GroupLayout(panelControl);
        panelControl.setLayout(panelControlLayout);
        panelControlLayout.setHorizontalGroup(
            panelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelControlLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addComponent(jSeparator2)
                    .addComponent(jSeparator3)
                    .addGroup(panelControlLayout.createSequentialGroup()
                        .addComponent(btStart)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btPause))
                    .addGroup(panelControlLayout.createSequentialGroup()
                        .addGroup(panelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btBatteryLow)
                            .addComponent(cbSTC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(toggleBtWall)
                            .addComponent(btRestart)
                            .addComponent(btCancel)
                            .addGroup(panelControlLayout.createSequentialGroup()
                                .addComponent(toggleBtMobile)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbCountMobileObject))
                            .addComponent(btRemoveAllMobileObject))
                        .addGap(0, 13, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelControlLayout.setVerticalGroup(
            panelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelControlLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbSTC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(toggleBtWall)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCountMobileObject)
                    .addComponent(toggleBtMobile))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btRemoveAllMobileObject)
                .addGap(18, 18, 18)
                .addComponent(btRestart)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btCancel)
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btStart)
                    .addComponent(btPause))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btBatteryLow)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelControl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelMap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelControl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(panelMap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 9, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void toggleBtWallStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_toggleBtWallStateChanged
        // TODO add your handling code here:
        
    }//GEN-LAST:event_toggleBtWallStateChanged

    private Thread spiralThread;
    private Thread spiralOnlineThread;
    
    public void setStartPoint(int x, int y, int cellId){
        startPoint.setI(x);
        startPoint.setJ(y);
        startPoint.setCellId(cellId);
    }
    
    public void setCurrentPoint(int x, int y, int cellId){
        currentPoint.setI(x);
        currentPoint.setJ(y);
        currentPoint.setCellId(cellId);
    }
    
    
    public void reset(){
        startPoint.setI(0);
        startPoint.setJ(0);
        startPoint.setCellId(1);
        
        currentPoint.setI(0);
        currentPoint.setJ(0);
        currentPoint.setCellId(1);
    }
    
    private void btStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btStartActionPerformed
        // TODO add your handling code here:
        
        labelCell[startPoint.getCellLocationX(startPoint)][startPoint.getCellLocationY(startPoint)].setIconRobot();
        
        
        map.refreshAdjacencyMatrix();
        
        //
        
//        STC Offline
//        spiralSTC.DFSAlgorithm(0);
//        spiralSTC.setEdgeOfPath();
//
//        spiralThread = new Spiral();
//        spiralThread.start();
        

        //
//        STC Online
//        spiralOnlineThread = new SpiralOnline();
//        spiralOnlineThread.start();

        //spiralSTC();
        
        
        if (cbSTC.getSelectedIndex() == 0) {
            // STC Offline
            spiralSTC.DFSAlgorithm(0);
            spiralSTC.setEdgeOfPath();

            spiralThread = new Spiral();
            spiralThread.start();
            
        } else {
            // STC Online
            spiralOnlineThread = new SpiralOnline();
            spiralOnlineThread.start();

        }
        
        
        
        
//        
//        toggleBtWall.setSelected(false);
    }//GEN-LAST:event_btStartActionPerformed

    private void btRestartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRestartActionPerformed
        // TODO add your handling code here:
        
//        refreshMap();
//        refreshCell();
        
        refreshPath();
    }//GEN-LAST:event_btRestartActionPerformed

    private void btPauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPauseActionPerformed
        // TODO add your handling code here:
        
        if (spiralThread != null) {
            spiralThread.stop();
            spiralThread = null;
        }
        
        if (spiralOnlineThread != null) {
            spiralOnlineThread.stop();
            spiralOnlineThread = null;
        }
        
    }//GEN-LAST:event_btPauseActionPerformed

    private void btCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelActionPerformed
        // TODO add your handling code here:
        
        if (spiralThread != null) {
            spiralThread.stop();
            spiralThread = null;
        }
        
        if (spiralOnlineThread != null) {
            spiralOnlineThread.stop();
            spiralOnlineThread = null;
        }
        
        if (batteryLowThread != null) {
            batteryLowThread.stop();
            batteryLowThread = null;
        }
        
        //
        removeAllMobileObject();
        
        spiralSTC.reset();
        
        //
//        spiralSTC.setStartPoint(0, 4, 1);
//        spiralSTC.setCurrentPoint(0, 4, 1);
        
        setStartPoint(0, 0, 1);
        setCurrentPoint(0, 0, 1);
        
        
        refreshMap();
        refreshCell();
    }//GEN-LAST:event_btCancelActionPerformed

    
    private Thread batteryLowThread;
    
    private void btBatteryLowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBatteryLowActionPerformed
        // TODO add your handling code here:
        
        if (spiralThread != null) {
            spiralThread.stop();
            spiralThread = null;
        }
        
        if (spiralOnlineThread != null) {
            spiralOnlineThread.stop();
            spiralOnlineThread = null;
        }
        
        JOptionPane.showMessageDialog(null, "Pin yếu!");
        
        batteryLowThread = new BatteryLow();
        batteryLowThread.start();
        
        
//        
//        toggleBtWall.setSelected(false);
    }//GEN-LAST:event_btBatteryLowActionPerformed

    private void toggleBtWallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toggleBtWallActionPerformed
        // TODO add your handling code here:
        if (toggleBtMobile.isSelected()) {
            toggleBtMobile.setSelected(false);
        }
        
    }//GEN-LAST:event_toggleBtWallActionPerformed

    
    private void removeAllMobileObject(){
        
        spiralSTC.removeAllMobileObject();
        
        for (int i = 0; i < size * 2; i++) {
            for (int j = 0; j < size * 2; j++) {
                labelCell[i][j].setMobileObject(false);
                
            }
        }
        
        
        lbCountMobileObject.setText("");
    }
    
    private void btRemoveAllMobileObjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRemoveAllMobileObjectActionPerformed
        // TODO add your handling code here:

//        setMobileObjectCurrentPoint();
        
        removeAllMobileObject();
    
    }//GEN-LAST:event_btRemoveAllMobileObjectActionPerformed

    private void toggleBtMobileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toggleBtMobileActionPerformed
        // TODO add your handling code here:
        if (toggleBtWall.isSelected()) {
            toggleBtWall.setSelected(false);
        }
    }//GEN-LAST:event_toggleBtMobileActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VMap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VMap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VMap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VMap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VMap().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btBatteryLow;
    private javax.swing.JButton btCancel;
    private javax.swing.JButton btPause;
    private javax.swing.JButton btRemoveAllMobileObject;
    private javax.swing.JButton btRestart;
    private javax.swing.JButton btStart;
    private javax.swing.JComboBox<String> cbSTC;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lbCountMobileObject;
    private javax.swing.JPanel panelControl;
    private javax.swing.JPanel panelMap;
    private javax.swing.JToggleButton toggleBtMobile;
    private javax.swing.JToggleButton toggleBtWall;
    // End of variables declaration//GEN-END:variables
}
