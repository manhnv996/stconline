/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;


import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Queue;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import models.Cell;
import models.Map;
import models.Point;

/**
 *
 * @author manh nguyenvan
 */
public class SpiralSTC {
    
    private Map map;
    
    private Point startPoint;
    private int i;
    private int j;
    private int cellId;
    private Point currentPoint;
    
    private int iNext;
    private int jNext;
    private int cellIdNext;
    
    
    public SpiralSTC(Point startPoint, Map map){
        this.startPoint = startPoint;
        
        this.currentPoint = startPoint;
        this.i = startPoint.getI();
        this.j = startPoint.getJ();
        this.cellId = startPoint.getCellId();
        
        this.map = map;
    }
    
    
    
    

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }
    
    
    
    
    
    public void updateMegaCellCurrent(){
        this.i = currentPoint.getI();
        this.j = currentPoint.getJ();
        this.cellId = currentPoint.getCellId();
        
        this.map.getMap()[i][j].getCell().setCellId(cellId);
    }
    
    public void updateCellVisited(){
        map.getMap()[i][j].getCell().setIsVisit(true);
    }
    
    
//    public boolean checkVisitedAll(){
//        for (int k = 0; k < map.getN(); k++) {
//            for (int l = 0; l < map.getN(); l++) {
//                if (!map.getMap()[k][l].getCell().isIsVisit()) {
//                    
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
    
    
    
    public void setStartPoint(int x, int y, int cellId){
        startPoint.setI(x);
        startPoint.setJ(x);
        startPoint.setCellId(x);
    }
    
    public void setCurrentPoint(int x, int y, int cellId){
        currentPoint.setI(x);
        currentPoint.setJ(x);
        currentPoint.setCellId(x);
    }
    
    
    public void reset(){
        startPoint.setI(0);
        startPoint.setJ(0);
        startPoint.setCellId(1);
        
        currentPoint.setI(0);
        currentPoint.setJ(0);
        currentPoint.setCellId(1);
    }
    
    
    public void spiralSTC(){
        startPoint = new Point(0, 0, 1);
        currentPoint = startPoint;
        
//        while(!checkVisitedAll()){
//        while(currentPoint.getI() != 1 
//                || currentPoint.getJ() != 0
//                || currentPoint.getCellId() != 1){
        do {    
            getCellNext();
            
        } while(currentPoint.getI() != startPoint.getI()
                    || currentPoint.getJ() != startPoint.getJ()
                    || currentPoint.getCellId() != startPoint.getCellId());
            
    }
    
    
    
    
    public void spiralSTCOnline(){
        startPoint = new Point(0, 0, 1);
        currentPoint = startPoint;
        
//        while(!checkVisitedAll()){
//        while(currentPoint.getI() != 1 
//                || currentPoint.getJ() != 0
//                || currentPoint.getCellId() != 1){
        do {    
            getCellNext();
            
        } while(currentPoint.getI() != startPoint.getI()
                    || currentPoint.getJ() != startPoint.getJ()
                    || currentPoint.getCellId() != startPoint.getCellId());
            
    }
    
    
    
    
    
    public int checkCellCurrent(){
        if (map.getMap()[i][j].getCell().getCellId() == 1) {
            return 1;
        } else if (map.getMap()[i][j].getCell().getCellId() == 2) {
            return 2;
        } else if (map.getMap()[i][j].getCell().getCellId() == 3) {
            return 3;
        } else {
            return 4;
        }
    }
    
    
    
    
    
    
    public boolean checkConflict(List<Point> mobileObjectList){
        
        for (int k = 0; k < mobileObjectList.size(); k++) {
            if (currentPoint.getI() == mobileObjectList.get(k).getI()
                    && currentPoint.getJ() == mobileObjectList.get(k).getJ()
                    && currentPoint.getCellId() == mobileObjectList.get(k).getCellId()) {
                

                return true;
            }
        }
        return false;
    }
    
    
    
    public Point getCellNext(){
        
        int id = checkCellCurrent();
        
        switch (checkCellCurrent()) {
            case 1:
                updateCellVisited();
                if (map.getMap()[i][j].isLeft()) {
                    // do something
                    
                    currentPoint.setI(i);
                    currentPoint.setJ(j - 1);
                    currentPoint.setCellId(2);
                    
//                    updateMegaCellCurrent();
                    
                } else if (map.getMap()[i][j].isDown()) {
                    //
                    currentPoint.setI(i);
                    currentPoint.setJ(j);
                    currentPoint.setCellId(3);
                    
//                    updateMegaCellCurrent();
                    
                } else {
                    // ở góc
                    currentPoint.setI(i);
                    currentPoint.setJ(j);
                    currentPoint.setCellId(3);
                    
//                    updateMegaCellCurrent();
                }   break;
            case 2:
                updateCellVisited();
                if (map.getMap()[i][j].isUp()) {
                    // do something
                    currentPoint.setI(i - 1);
                    currentPoint.setJ(j);
                    currentPoint.setCellId(4);
                    
//                    updateMegaCellCurrent();
                    
                } else if (map.getMap()[i][j].isLeft()) {
                    //
                    currentPoint.setI(i);
                    currentPoint.setJ(j);
                    currentPoint.setCellId(1);
                    
//                    updateMegaCellCurrent();
                    
                } else {
                    currentPoint.setI(i);
                    currentPoint.setJ(j);
                    currentPoint.setCellId(1);
                    
//                    updateMegaCellCurrent();
                    
                }   break;
            case 3:
                updateCellVisited();
                if (map.getMap()[i][j].isDown()) {
                    // do something
                    currentPoint.setI(i + 1);
                    currentPoint.setJ(j);
                    currentPoint.setCellId(1);
                    
//                    updateMegaCellCurrent();
                    
                } else if (map.getMap()[i][j].isRight()) {
                    //
                    currentPoint.setI(i);
                    currentPoint.setJ(j);
                    currentPoint.setCellId(4);
                    
//                    updateMegaCellCurrent();
                    
                } else {
                    currentPoint.setI(i);
                    currentPoint.setJ(j);
                    currentPoint.setCellId(4);
                    
//                    updateMegaCellCurrent();
                    
                }   break;
            case 4:
                updateCellVisited();
                if (map.getMap()[i][j].isRight()) {
                    // do something
                    currentPoint.setI(i);
                    currentPoint.setJ(j + 1);
                    currentPoint.setCellId(3);
                    
//                    updateMegaCellCurrent();
                    
                } else if (map.getMap()[i][j].isUp()) {
                    //
                    currentPoint.setI(i);
                    currentPoint.setJ(j);
                    currentPoint.setCellId(2);
                    
//                    updateMegaCellCurrent();
                    
                } else {
                    currentPoint.setI(i);
                    currentPoint.setJ(j);
                    currentPoint.setCellId(2);
                    
//                    updateMegaCellCurrent();
                    
                }   break;
            default:
                break;
        }
        
        
        //
//        updateMegaCellCurrent();
//
//        currentPoint.display();
//        map.getMap()[i][j].getCell().setIconPassed();
        
        
        if (checkConflict(getMobileObjectPointList())) {
            currentPoint.setI(i);
            currentPoint.setJ(j);
            currentPoint.setCellId(id);

        } else {
            updateMegaCellCurrent();

            currentPoint.display();
            map.getMap()[i][j].getCell().setIconPassed();

        }
        
        
        return currentPoint;
    }
    
    
    
    public Point getCellNextOnline(){
        
        setEdgeOfPathOnline();
        
        int id = checkCellCurrent();
        
        switch (checkCellCurrent()) {
            case 1:
                updateCellVisited();
                if (map.getMap()[i][j].isLeft()) {
                    // do something
                    
                    currentPoint.setI(i);
                    currentPoint.setJ(j - 1);
                    currentPoint.setCellId(2);
                    
//                    updateMegaCellCurrent();
                    
                } else if (map.getMap()[i][j].isDown()) {
                    //
                    currentPoint.setI(i);
                    currentPoint.setJ(j);
                    currentPoint.setCellId(3);
                    
//                    updateMegaCellCurrent();
                    
                } else {
                    // ở góc
                    currentPoint.setI(i);
                    currentPoint.setJ(j);
                    currentPoint.setCellId(3);
                    
//                    updateMegaCellCurrent();
                }   break;
            case 2:
                updateCellVisited();
                if (map.getMap()[i][j].isUp()) {
                    // do something
                    currentPoint.setI(i - 1);
                    currentPoint.setJ(j);
                    currentPoint.setCellId(4);
                    
//                    updateMegaCellCurrent();
                    
                } else if (map.getMap()[i][j].isLeft()) {
                    //
                    currentPoint.setI(i);
                    currentPoint.setJ(j);
                    currentPoint.setCellId(1);
                    
//                    updateMegaCellCurrent();
                    
                } else {
                    currentPoint.setI(i);
                    currentPoint.setJ(j);
                    currentPoint.setCellId(1);
                    
//                    updateMegaCellCurrent();
                    
                }   break;
            case 3:
                updateCellVisited();
                if (map.getMap()[i][j].isDown()) {
                    // do something
                    currentPoint.setI(i + 1);
                    currentPoint.setJ(j);
                    currentPoint.setCellId(1);
                    
//                    updateMegaCellCurrent();
                    
                } else if (map.getMap()[i][j].isRight()) {
                    //
                    currentPoint.setI(i);
                    currentPoint.setJ(j);
                    currentPoint.setCellId(4);
                    
//                    updateMegaCellCurrent();
                    
                } else {
                    currentPoint.setI(i);
                    currentPoint.setJ(j);
                    currentPoint.setCellId(4);
                    
//                    updateMegaCellCurrent();
                    
                }   break;
            case 4:
                updateCellVisited();
                if (map.getMap()[i][j].isRight()) {
                    // do something
                    currentPoint.setI(i);
                    currentPoint.setJ(j + 1);
                    currentPoint.setCellId(3);
                    
//                    updateMegaCellCurrent();
                    
                } else if (map.getMap()[i][j].isUp()) {
                    //
                    currentPoint.setI(i);
                    currentPoint.setJ(j);
                    currentPoint.setCellId(2);
                    
//                    updateMegaCellCurrent();
                    
                } else {
                    currentPoint.setI(i);
                    currentPoint.setJ(j);
                    currentPoint.setCellId(2);
                    
//                    updateMegaCellCurrent();
                    
                }   break;
            default:
                break;
        }
        
        //
//        updateMegaCellCurrent();
//        
//        currentPoint.display();
//        map.getMap()[i][j].getCell().setIconPassed();
        
        
        
        
        if (checkConflict(getMobileObjectPointList())) {
            currentPoint.setI(i);
            currentPoint.setJ(j);
            currentPoint.setCellId(id);

        } else {
            updateMegaCellCurrent();

            currentPoint.display();
            map.getMap()[i][j].getCell().setIconPassed();

        }
        
        
        
        
        return currentPoint;
    }
    
    
    
    
    public void setEdgeOfPathOnline(){
        
        switch(checkCellCurrent()){
            case 1:
                
                if (currentPoint.getJ() > 0) {
                    if (map.getAdjacencyMatrix()[currentPoint.convertPointToKeyId(currentPoint, map.getN())]
                            [currentPoint.convertPointToKeyId(currentPoint, map.getN()) - 1]) {
                        
                        if (checkMegaCellIsSetEdge(currentPoint.getI(), currentPoint.getJ() - 1)) {
                            
                            map.getMap()[currentPoint.getI()][currentPoint.getJ()].setLeft(true);
                            map.getMap()[currentPoint.getI()][currentPoint.getJ() - 1].setRight(true);
                            
                            System.out.println("Set Map: " + "(" + currentPoint.getI() + ", " + currentPoint.getJ() + ") - "
                                    + "(" + currentPoint.getI() + ", " + (currentPoint.getJ() - 1));
                            
                        }
                        
//                        map.getMap()[currentPoint.getI()][currentPoint.getJ()].setLeft(true);
                        
                    }
                }
                break;
                
            case 2:
                if (currentPoint.getI() > 0) {
                    if (map.getAdjacencyMatrix()[currentPoint.convertPointToKeyId(currentPoint, map.getN()) - map.getN()]
                            [currentPoint.convertPointToKeyId(currentPoint, map.getN())]) {
                        
                        if (checkMegaCellIsSetEdge(currentPoint.getI() - 1, currentPoint.getJ())) {
                            
                            map.getMap()[currentPoint.getI()][currentPoint.getJ()].setUp(true);
                            map.getMap()[currentPoint.getI() - 1][currentPoint.getJ()].setDown(true);
                            
                            
                            System.out.println("Set Map: " + "(" + currentPoint.getI() + ", " + currentPoint.getJ() + ") - "
                                    + "(" + (currentPoint.getI() - 1) + ", " + currentPoint.getJ() + ")");
                            
                        }
                        
//                        map.getMap()[currentPoint.getI()][currentPoint.getJ()].setUp(true);
                        
                    }
                }
                break;
                
            case 3:
                if (currentPoint.getI() < (map.getN() - 1)) {
                    if (map.getAdjacencyMatrix()[currentPoint.convertPointToKeyId(currentPoint, map.getN()) + map.getN()]
                            [currentPoint.convertPointToKeyId(currentPoint, map.getN())]) {
                        
                        if (checkMegaCellIsSetEdge(currentPoint.getI() + 1, currentPoint.getJ())) {
                            
                            map.getMap()[currentPoint.getI()][currentPoint.getJ()].setDown(true);
                            map.getMap()[currentPoint.getI() + 1][currentPoint.getJ()].setUp(true);
                            
                            
                            System.out.println("Set Map: " + "(" + currentPoint.getI() + ", " + currentPoint.getJ() + ") - "
                                    + "(" + (currentPoint.getI() + 1) + ", " + currentPoint.getJ() + ")");
                            
                        }
                        
//                        map.getMap()[currentPoint.getI()][currentPoint.getJ()].setDown(true);
                        
                    }
                }
                break;
                
            case 4:
                if (currentPoint.getJ() < (map.getN() - 1)) {
                    if (map.getAdjacencyMatrix()[currentPoint.convertPointToKeyId(currentPoint, map.getN())]
                            [currentPoint.convertPointToKeyId(currentPoint, map.getN()) + 1]) {
                        
                        if (checkMegaCellIsSetEdge(currentPoint.getI(), currentPoint.getJ() + 1)) {
                            
                            map.getMap()[currentPoint.getI()][currentPoint.getJ()].setRight(true);
                            map.getMap()[currentPoint.getI()][currentPoint.getJ() + 1].setLeft(true);
                            
                            
                            System.out.println("Set Map: " + "(" + currentPoint.getI() + ", " + currentPoint.getJ() + ") - "
                                    + "(" + currentPoint.getI() + ", " + (currentPoint.getJ() + 1) + ")");
                            
                        }
                        
//                        map.getMap()[currentPoint.getI()][currentPoint.getJ()].setRight(true);
                        
                    }
                }
                break;
                
        }
        
        
    }
    
    
    private boolean checkMegaCellIsSetEdge(int x, int y){
        if (map.getMap()[x][y].isDown()
                || map.getMap()[x][y].isUp()
                || map.getMap()[x][y].isLeft()
                || map.getMap()[x][y].isRight()) {
            
            return false;
        }
        return true;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    private int size;
    
    private int n = 0; //số đỉnh của đồ thị
    
    private boolean[] notVisited;
    private int[][] resultDFS;  // [prev][curr]
    
    private int prev = -1;
    private int countNote = 0;
    
    
    
    public int DFS(int v){
        
//        System.out.println("v = " + v + ", prev = " + prev);
        
        resultDFS[countNote][0] = prev;
        resultDFS[countNote][1] = v;
        countNote ++;
//        System.out.println("count note " + countNote);
        notVisited[v] = false;
        
        
        for (int u = 0; u < n; u++) {
            if (map.getAdjacencyMatrix()[v][u] == true 
                    && notVisited[u]){ 
                
                DFS(u);
                
            }
//            resultDFS[countNote ++][0] = v;
//            countNote ++;
            prev = v;
        }
        
        return v;
    }
    
    
    public void DFSAlgorithm(int start){
//        init();
        size = map.getN();
        n = size * size;
        
        countNote = 0;
        
        int countObstacle = 0;
        for (int k = 0; k < map.getN(); k++) {
            for (int l = 0; l < map.getN(); l++) {
                if (map.getMap()[k][l].isIsObstacle()) {
                    countObstacle ++;
                    
                }
            }
        }
        
        resultDFS = new int[n - countObstacle][2];
        
        
        notVisited = new boolean[n];
        
        for (int k = 0; k < n; k++) {
            notVisited[k] = true;
        }
//        for (int k = 0; k < n; k++) {
        for (int k = start; k < n; k++) {
            if (notVisited[k] 
                    && !map.getMap()[map.convertAdjacencyMatrixToMegaCellX(k)]
                            [map.convertAdjacencyMatrixToMegaCellY(k)]
                            .isIsObstacle()) {
                
                DFS(k);
                
                map.setGraphPath(resultDFS);
                break;  //chỉ gọi 1 lần (vì cần 1 đường đi liên thông)
            }
        }
    }
    
    
    
    public void setEdgeOfPath(){
        int xCell = 0, yCell = 0;
        int xPrevCell = 0, yPrevCell = 0;
        
        for (int k = 1; k < map.getGraphPath().length; k++) {
            
            xCell = map.convertAdjacencyMatrixToMegaCellX(map.getGraphPath()[k][0]);
            yCell = map.convertAdjacencyMatrixToMegaCellY(map.getGraphPath()[k][0]);
            
            xPrevCell = map.convertAdjacencyMatrixToMegaCellX(map.getGraphPath()[k][1]);
            yPrevCell = map.convertAdjacencyMatrixToMegaCellY(map.getGraphPath()[k][1]);
            
            if (xCell == xPrevCell) {
                if (yCell == (yPrevCell + 1)) {
                    map.getMap()[xCell][yCell].setLeft(true);
                    map.getMap()[xPrevCell][yPrevCell].setRight(true);
                    
                } else if (yCell == (yPrevCell - 1)) {
                    map.getMap()[xCell][yCell].setRight(true);
                    map.getMap()[xPrevCell][yPrevCell].setLeft(true);
                    
                }
            } else if (yCell == yPrevCell) {
                if (xCell == (xPrevCell + 1)) {
                    map.getMap()[xCell][yCell].setUp(true);
                    map.getMap()[xPrevCell][yPrevCell].setDown(true);
                    
                } else if (xCell == (xPrevCell - 1)) {
                    map.getMap()[xCell][yCell].setDown(true);
                    map.getMap()[xPrevCell][yPrevCell].setUp(true);
                    
                }
                
            }
            
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    private List<Integer> queue;
    int[] color;
    int[] back;
    
    public void BFS(int start, int finish){
        
        size = map.getN();
        n = size * size;
        
        
        
//        countIndex = 0;
        int countObstacle = 0;
        for (int k = 0; k < map.getN(); k++) {
            for (int l = 0; l < map.getN(); l++) {
                if (map.getMap()[k][l].isIsObstacle()) {
                    countObstacle ++;
                    
                }
            }
        }
//        
//        resultDFS = new int[n - countObstacle][2];
        
        
        
        
        queue = new ArrayList<Integer>();
        color = new int[n];
        back = new int[n];
        
        for (int k = 0; k < n; k++) {
            color[i] = 0;
            back[i] = -1;
        }
        
        color[start] = 1;
        queue.add(start);
        while(!queue.isEmpty()){
            int u = queue.get(0);
            queue.remove(0);
            
            //System.out.println("u " + u);
            
            for (int k = 0; k < n; k++) {
                if (map.getAdjacencyMatrix()[u][k] == true
                        && color[k] == 0) {
                    color[k] = 1;
                    back[k] = u;
                    queue.add(k);
                }
            }
            color[u] = 2;
        }
        
        BFSPath.clear();
        resultBFS(start, finish, back);
        
    }
    
    private List<Integer> BFSPath = new ArrayList<>();
    private void resultBFS(int u, int v, int[] back){
        if (u == v) {
            BFSPath.add(v);
            //System.out.println(v);
        } else {
            if (back[v] == -1) {
                
            } else {
                resultBFS(u, back[v], back);
                BFSPath.add(v);
                //System.out.println(v);
            }
        }
    }
    
    
    
    public List<Point> isLowBattery(Point current, Point source){
        
        int x = current.getCellLocationX(currentPoint);
        int y = current.getCellLocationY(currentPoint);
        
        int key = map.convertMegaCellToAdjacencyMatrix(map.getMegaCellLocationX(x, y), map.getMegaCellLocationY(x, y));
        
        
        
        int xSrc = source.getCellLocationX(source);
        int ySrc = source.getCellLocationY(source);
        
        int keySrc = map.convertMegaCellToAdjacencyMatrix(map.getMegaCellLocationX(xSrc, ySrc),
                map.getMegaCellLocationY(xSrc, ySrc));
        
        
//        Tìm đường quay về
        BFS(key, keySrc);
        
        
        List<Point> lowBatteryListPoint = new ArrayList<>();
        
        Point curr;
        
//        int curr = 0;
        currentPoint.setI(current.getI());
        currentPoint.setJ(current.getJ());
        currentPoint.setCellId(current.getCellId());
        
        int xPrevCell = 0, yPrevCell = 0;
        int xCell = 0, yCell = 0;
        
        for (int k = 1; k < BFSPath.size(); k++) {
//            curr = BFSPath.get(k);
//            BFSPath.remove(k);

            xPrevCell = map.convertAdjacencyMatrixToMegaCellX(BFSPath.get(k - 1));
            yPrevCell = map.convertAdjacencyMatrixToMegaCellY(BFSPath.get(k - 1));
            
            xCell = map.convertAdjacencyMatrixToMegaCellX(BFSPath.get(k));
            yCell = map.convertAdjacencyMatrixToMegaCellY(BFSPath.get(k));
            
            
            switch (checkCellCurrent()) {
                case 1:
                    updateCellVisited();

                    if (xCell == xPrevCell) {
                        if (yCell == (yPrevCell + 1)) {
                            
                            currentPoint.setI(i);
                            currentPoint.setJ(j);
                            currentPoint.setCellId(2);
                            
                            updateMegaCellCurrent();
                            
                            currentPoint.display();
                            map.getMap()[i][j].getCell().setIconPassed();
                            
                            ////
                            curr = new Point(i, j, 2);
                            lowBatteryListPoint.add(curr);
                            
                            
                            //
                            currentPoint.setI(i);
                            currentPoint.setJ(j + 1);
                            currentPoint.setCellId(1);
                            
                            updateMegaCellCurrent();
                            
                            currentPoint.display();
                            map.getMap()[i][j].getCell().setIconPassed();
                            
                            ////
                            curr = new Point(i, j, 1);
                            lowBatteryListPoint.add(curr);
                            
                            
                            

                        } else if (yCell == (yPrevCell - 1)) {
                            currentPoint.setI(i);
                            currentPoint.setJ(j - 1);
                            currentPoint.setCellId(2);

                            updateMegaCellCurrent();

                            currentPoint.display();
                            map.getMap()[i][j].getCell().setIconPassed();
                            
                            ////
                            curr = new Point(i, j, 2);
                            lowBatteryListPoint.add(curr);
                            
                            
                            //
                            currentPoint.setI(i);
                            currentPoint.setJ(j);
                            currentPoint.setCellId(1);
                            
                            updateMegaCellCurrent();
                            
                            currentPoint.display();
                            map.getMap()[i][j].getCell().setIconPassed();
                            
                            ////
                            curr = new Point(i, j, 1);
                            lowBatteryListPoint.add(curr);
                            
                            
                        }
                    } else if (yCell == yPrevCell) {
                        if (xCell == (xPrevCell + 1)) {
                            currentPoint.setI(i);
                            currentPoint.setJ(j);
                            currentPoint.setCellId(3);

                            updateMegaCellCurrent();

                            currentPoint.display();
                            map.getMap()[i][j].getCell().setIconPassed();
                            
                            ////
                            curr = new Point(i, j, 3);
                            lowBatteryListPoint.add(curr);
                            
                            
                            //
                            currentPoint.setI(i + 1);
                            currentPoint.setJ(j);
                            currentPoint.setCellId(1);
                            
                            updateMegaCellCurrent();
                            
                            currentPoint.display();
                            map.getMap()[i][j].getCell().setIconPassed();
                            
                            ////
                            curr = new Point(i, j, 1);
                            lowBatteryListPoint.add(curr);
                            
                            
                        } else if (xCell == (xPrevCell - 1)) {
                            currentPoint.setI(i - 1);
                            currentPoint.setJ(j);
                            currentPoint.setCellId(3);

                            updateMegaCellCurrent();

                            currentPoint.display();
                            map.getMap()[i][j].getCell().setIconPassed();
                            
                            ////
                            curr = new Point(i, j, 3);
                            lowBatteryListPoint.add(curr);
                            
                            
                            //
                            currentPoint.setI(i);
                            currentPoint.setJ(j);
                            currentPoint.setCellId(1);
                            
                            updateMegaCellCurrent();
                            
                            currentPoint.display();
                            map.getMap()[i][j].getCell().setIconPassed();
                            
                            ////
                            curr = new Point(i, j, 1);
                            lowBatteryListPoint.add(curr);
                            
                            
                        }

                    }
                    break;
                    
                case 2:
                    updateCellVisited();

                    if (xCell == xPrevCell) {
                        if (yCell == (yPrevCell + 1)) {

                            currentPoint.setI(i);
                            currentPoint.setJ(j + 1);
                            currentPoint.setCellId(1);

                            updateMegaCellCurrent();

                            currentPoint.display();
                            map.getMap()[i][j].getCell().setIconPassed();
                            
                            ////
                            curr = new Point(i, j, 1);
                            lowBatteryListPoint.add(curr);
                            
                            
                            //
                            currentPoint.setI(i);
                            currentPoint.setJ(j);
                            currentPoint.setCellId(2);
                            
                            updateMegaCellCurrent();
                            
                            currentPoint.display();
                            map.getMap()[i][j].getCell().setIconPassed();
                            
                            ////
                            curr = new Point(i, j, 2);
                            lowBatteryListPoint.add(curr);
                            
                            
                        } else if (yCell == (yPrevCell - 1)) {
                            currentPoint.setI(i);
                            currentPoint.setJ(j);
                            currentPoint.setCellId(1);

                            updateMegaCellCurrent();

                            currentPoint.display();
                            map.getMap()[i][j].getCell().setIconPassed();
                            
                            ////
                            curr = new Point(i, j, 1);
                            lowBatteryListPoint.add(curr);
                            
                            
                            //
                            currentPoint.setI(i);
                            currentPoint.setJ(j - 1);
                            currentPoint.setCellId(2);
                            
                            updateMegaCellCurrent();
                            
                            currentPoint.display();
                            map.getMap()[i][j].getCell().setIconPassed();
                            
                            ////
                            curr = new Point(i, j, 2);
                            lowBatteryListPoint.add(curr);
                            
                            
                        }
                    } else if (yCell == yPrevCell) {
                        if (xCell == (xPrevCell + 1)) {
                            currentPoint.setI(i);
                            currentPoint.setJ(j);
                            currentPoint.setCellId(4);

                            updateMegaCellCurrent();

                            currentPoint.display();
                            map.getMap()[i][j].getCell().setIconPassed();
                            
                            ////
                            curr = new Point(i, j, 4);
                            lowBatteryListPoint.add(curr);
                            
                            
                            //
                            currentPoint.setI(i + 1);
                            currentPoint.setJ(j);
                            currentPoint.setCellId(2);
                            
                            updateMegaCellCurrent();
                            
                            currentPoint.display();
                            map.getMap()[i][j].getCell().setIconPassed();
                            
                            ////
                            curr = new Point(i, j, 2);
                            lowBatteryListPoint.add(curr);
                            
                            
                        } else if (xCell == (xPrevCell - 1)) {
                            currentPoint.setI(i - 1);
                            currentPoint.setJ(j);
                            currentPoint.setCellId(4);

                            updateMegaCellCurrent();

                            currentPoint.display();
                            map.getMap()[i][j].getCell().setIconPassed();
                            
                            ////
                            curr = new Point(i, j, 4);
                            lowBatteryListPoint.add(curr);
                            
                            
                            //
                            currentPoint.setI(i);
                            currentPoint.setJ(j);
                            currentPoint.setCellId(2);
                            
                            updateMegaCellCurrent();
                            
                            currentPoint.display();
                            map.getMap()[i][j].getCell().setIconPassed();
                            
                            ////
                            curr = new Point(i, j, 2);
                            lowBatteryListPoint.add(curr);
                            
                            
                        }

                    }
                    break;
                    
                case 3:
                    updateCellVisited();

                    if (xCell == xPrevCell) {
                        if (yCell == (yPrevCell + 1)) {

                            currentPoint.setI(i);
                            currentPoint.setJ(j);
                            currentPoint.setCellId(4);

                            updateMegaCellCurrent();

                            currentPoint.display();
                            map.getMap()[i][j].getCell().setIconPassed();
                            
                            ////
                            curr = new Point(i, j, 4);
                            lowBatteryListPoint.add(curr);
                            
                            
                            //
                            currentPoint.setI(i);
                            currentPoint.setJ(j + 1);
                            currentPoint.setCellId(3);
                            
                            updateMegaCellCurrent();
                            
                            currentPoint.display();
                            map.getMap()[i][j].getCell().setIconPassed();
                            
                            ////
                            curr = new Point(i, j, 3);
                            lowBatteryListPoint.add(curr);
                            
                            
                        } else if (yCell == (yPrevCell - 1)) {
                            currentPoint.setI(i);
                            currentPoint.setJ(j - 1);
                            currentPoint.setCellId(4);

                            updateMegaCellCurrent();

                            currentPoint.display();
                            map.getMap()[i][j].getCell().setIconPassed();
                            
                            ////
                            curr = new Point(i, j, 4);
                            lowBatteryListPoint.add(curr);
                            
                            
                            //
                            currentPoint.setI(i);
                            currentPoint.setJ(j);
                            currentPoint.setCellId(3);
                            
                            updateMegaCellCurrent();
                            
                            currentPoint.display();
                            map.getMap()[i][j].getCell().setIconPassed();
                            
                            ////
                            curr = new Point(i, j, 3);
                            lowBatteryListPoint.add(curr);
                            
                            
                        }
                    } else if (yCell == yPrevCell) {
                        if (xCell == (xPrevCell + 1)) {
                            currentPoint.setI(i + 1);
                            currentPoint.setJ(j);
                            currentPoint.setCellId(1);

                            updateMegaCellCurrent();

                            currentPoint.display();
                            map.getMap()[i][j].getCell().setIconPassed();
                            
                            ////
                            curr = new Point(i, j, 1);
                            lowBatteryListPoint.add(curr);
                            
                            
                            //
                            currentPoint.setI(i);
                            currentPoint.setJ(j);
                            currentPoint.setCellId(3);
                            
                            updateMegaCellCurrent();
                            
                            currentPoint.display();
                            map.getMap()[i][j].getCell().setIconPassed();
                            
                            ////
                            curr = new Point(i, j, 3);
                            lowBatteryListPoint.add(curr);
                            
                            
                        } else if (xCell == (xPrevCell - 1)) {
                            currentPoint.setI(i);
                            currentPoint.setJ(j);
                            currentPoint.setCellId(1);

                            updateMegaCellCurrent();

                            currentPoint.display();
                            map.getMap()[i][j].getCell().setIconPassed();
                            
                            ////
                            curr = new Point(i, j, 1);
                            lowBatteryListPoint.add(curr);
                            
                            
                            //
                            currentPoint.setI(i - 1);
                            currentPoint.setJ(j);
                            currentPoint.setCellId(3);
                            
                            updateMegaCellCurrent();
                            
                            currentPoint.display();
                            map.getMap()[i][j].getCell().setIconPassed();
                            
                            ////
                            curr = new Point(i, j, 3);
                            lowBatteryListPoint.add(curr);
                            
                            
                        }

                    }
                    break;
                    
                case 4:
                    updateCellVisited();

                    if (xCell == xPrevCell) {
                        if (yCell == (yPrevCell + 1)) {

                            currentPoint.setI(i);
                            currentPoint.setJ(j + 1);
                            currentPoint.setCellId(3);

                            updateMegaCellCurrent();

                            currentPoint.display();
                            map.getMap()[i][j].getCell().setIconPassed();
                            
                            ////
                            curr = new Point(i, j, 3);
                            lowBatteryListPoint.add(curr);
                            
                            
                            //
                            currentPoint.setI(i);
                            currentPoint.setJ(j);
                            currentPoint.setCellId(4);
                            
                            updateMegaCellCurrent();
                            
                            currentPoint.display();
                            map.getMap()[i][j].getCell().setIconPassed();
                            
                            ////
                            curr = new Point(i, j, 4);
                            lowBatteryListPoint.add(curr);
                            
                            
                        } else if (yCell == (yPrevCell - 1)) {
                            currentPoint.setI(i);
                            currentPoint.setJ(j);
                            currentPoint.setCellId(3);

                            updateMegaCellCurrent();

                            currentPoint.display();
                            map.getMap()[i][j].getCell().setIconPassed();
                            
                            ////
                            curr = new Point(i, j, 3);
                            lowBatteryListPoint.add(curr);
                            
                            
                            //
                            currentPoint.setI(i);
                            currentPoint.setJ(j - 1);
                            currentPoint.setCellId(4);
                            
                            updateMegaCellCurrent();
                            
                            currentPoint.display();
                            map.getMap()[i][j].getCell().setIconPassed();
                            
                            ////
                            curr = new Point(i, j, 4);
                            lowBatteryListPoint.add(curr);
                            
                            
                        }
                    } else if (yCell == yPrevCell) {
                        if (xCell == (xPrevCell + 1)) {
                            currentPoint.setI(i + 1);
                            currentPoint.setJ(j);
                            currentPoint.setCellId(2);

                            updateMegaCellCurrent();

                            currentPoint.display();
                            map.getMap()[i][j].getCell().setIconPassed();
                            
                            ////
                            curr = new Point(i, j, 2);
                            lowBatteryListPoint.add(curr);
                            
                            
                            //
                            currentPoint.setI(i);
                            currentPoint.setJ(j);
                            currentPoint.setCellId(4);
                            
                            updateMegaCellCurrent();
                            
                            currentPoint.display();
                            map.getMap()[i][j].getCell().setIconPassed();
                            
                            ////
                            curr = new Point(i, j, 4);
                            lowBatteryListPoint.add(curr);
                            
                            
                        } else if (xCell == (xPrevCell - 1)) {
                            currentPoint.setI(i);
                            currentPoint.setJ(j);
                            currentPoint.setCellId(2);

                            updateMegaCellCurrent();

                            currentPoint.display();
                            map.getMap()[i][j].getCell().setIconPassed();
                            
                            ////
                            curr = new Point(i, j, 2);
                            lowBatteryListPoint.add(curr);
                            
                            
                            //
                            currentPoint.setI(i - 1);
                            currentPoint.setJ(j);
                            currentPoint.setCellId(4);
                            
                            updateMegaCellCurrent();
                            
                            currentPoint.display();
                            map.getMap()[i][j].getCell().setIconPassed();
                            
                            ////
                            curr = new Point(i, j, 4);
                            lowBatteryListPoint.add(curr);
                            
                            
                        }

                    }
                    break;
                    
                    
            }
            
            
//            currentPoint.display();
//            map.getMap()[i][j].getCell().setIconPassed();

        }
        
        
        
        switch(currentPoint.getCellId()){
            case 1:
                break;
            case 2: 
                currentPoint.setCellId(1);

                updateMegaCellCurrent();

                currentPoint.display();
                map.getMap()[i][j].getCell().setIconPassed();
                
                            ////
                            curr = new Point(i, j, 1);
                            lowBatteryListPoint.add(curr);
                            
                            
                break;
                
            case 3: 
                currentPoint.setCellId(1);
                
                updateMegaCellCurrent();

                currentPoint.display();
                map.getMap()[i][j].getCell().setIconPassed();
                
                            ////
                            curr = new Point(i, j, 1);
                            lowBatteryListPoint.add(curr);
                            
                            
                break;
                
            case 4: 
                
                currentPoint.setCellId(2);
                
                updateMegaCellCurrent();

                currentPoint.display();
                map.getMap()[i][j].getCell().setIconPassed();
                
                            ////
                            curr = new Point(i, j, 2);
                            lowBatteryListPoint.add(curr);
                            
                            
                
                //
                currentPoint.setCellId(1);
                
                updateMegaCellCurrent();

                currentPoint.display();
                map.getMap()[i][j].getCell().setIconPassed();
                
                            ////
                            curr = new Point(i, j, 1);
                            lowBatteryListPoint.add(curr);
                            
                            
                break;
                
        }
        
//        for (int k = 0; k < lowBatteryListPoint.size(); k++) {
//            lowBatteryListPoint.get(k).display();
//        }
        
        return lowBatteryListPoint;
    }
    
    
    
    
    
    
    
//    private boolean[][] G;    // ma trận kề
//    private boolean[] chuaxet;    // đỉnh đã được thăm
//    private int[] intQueue; // queue
//    
//    private int countIndex = 0;
//    private int previous = -1;
//    
//    public void init(){
//        
//        size = map.getN();
//        n = size * size;
//        
//        
//        G = new boolean[n][n];
//        
//        G = map.getAdjacencyMatrix();
//        
//        chuaxet = new boolean[n];
//        intQueue = new int[n];
//        
//        
//        
//        
////        countIndex = 0;
////        int countObstacle = 0;
////        for (int k = 0; k < map.getN(); k++) {
////            for (int l = 0; l < map.getN(); l++) {
////                if (map.getMap()[k][l].isIsObstacle()) {
////                    countObstacle ++;
////                    
////                }
////            }
////        }
////        
////        resultDFS = new int[n - countObstacle][2];
//        
//    }
//    
//    private List<Integer> resultPath = new ArrayList<>();
//    
//    public void BFS(int v) {
//        int u, firstQ, lastQ, j;
//        firstQ = 0;
//        lastQ = 0;
//        intQueue[lastQ] = v;
//        chuaxet[v] = false;
//        
//        // thiết lập hàng đợi với đỉnh đầu là i
//        while (firstQ <= lastQ) {
//            u = intQueue[firstQ];
//            
//            System.out.println(u + ",  " + previous);
//            //
//            resultPath.add(u);
//            
//            firstQ = firstQ + 1;    // duyệt đỉnh đầu hàng đợi
//            for (j = 0; j < n; j++) {
//                if (G[u][j] == true && chuaxet[j]) {
//                    
//                    previous = intQueue[lastQ];
//                    
//                    lastQ = lastQ + 1;
//                    intQueue[lastQ] = j;
//                    chuaxet[j] = false;
//                }
//                
//            }
//        }
//    }
// 
//    public void BFSAlgorithm(int k) {
//        init();
//        for (int i = 0; i < n; i++) {
//            chuaxet[i] = true;
//        }
//        System.out.print("\n");
////        for (int i = 0; i < n; i++){
//        for (int i = k; i < n; i++){
//            if (chuaxet[i] 
//                    && !map.getMap()[map.convertAdjacencyMatrixToMegaCellX(i)]
//                            [map.convertAdjacencyMatrixToMegaCellY(i)]
//                            .isIsObstacle()) {
//                BFS(i);
//                
//                
//                break;
//            }
//        }
//    }
    
    
    
    
    
    
    public void makeFile(int sumStep, int countOverlap, int totalMegaCell){
        
        double coverage = 0.0d;
        double overlap = 0.0d;

        /////
        if (totalMegaCell > 0) {
            if (sumStep > (totalMegaCell * 4)) {
                sumStep = totalMegaCell * 4;
            }
            coverage = sumStep * 100.0/ (totalMegaCell * 4);
            overlap = countOverlap * 100.0/ (totalMegaCell * 4);

//            JOptionPane.showMessageDialog(null, "Độ bao phủ: " + sumStep + "/ " + totalMegaCell * 4 + " ô "
//                    + " (" + coverage + "%)" + "\n"
//                    + "Độ chồng lấn: " + countOverlap + "/ " + totalMegaCell * 4 + " ô "
//                    + " (" + overlap + "%)");

        }
            
        
        
        try {
            JFileChooser jfc = new JFileChooser("Sava File");
            if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {

                jfc.setDialogTitle("Sava File");
                //FileOutputStream fos = new FileOutputStream(jfc.getSelectedFile());

                File f = new File(jfc.getSelectedFile().toString());
                FileWriter fwrite= new FileWriter(f);
                PrintWriter pw= new PrintWriter(fwrite);
                
                pw.println("STC chuẩn:");
                pw.print("Độ bao phủ: " + sumStep + "/ " + totalMegaCell * 4 + " ô "
                    + " (" + coverage + "%)" + "\n"
                    + "Độ chồng lấn: " + countOverlap + "/ " + totalMegaCell * 4 + " ô "
                    + " (" + overlap + "%)");
                
                pw.println("\nĐường đi khó xây dựng cho robot");
                
                pw.println("\n------------------------------\n\n");
                pw.println("STC có cài đặt cơ sở tri thức");
                
                pw.print("Độ bao phủ: " + sumStep + "/ " + totalMegaCell * 4 + " ô "
                    + " (" + coverage + "%)" + "\n"
                    + "Độ chồng lấn: " + countOverlap + "/ " + totalMegaCell * 4 + " ô "
                    + " (" + overlap + "%)");
                
                pw.println("\nĐường đi cài đặt và xây dựng đơn giản");
                
                pw.println("\n\n");
                pw.printf("%90s", "------------------------------\n");

                Date today= new Date(System.currentTimeMillis());
                String strToday= today.toString();
                pw.printf("%90s", strToday + "\n\n");

                fwrite.close();
                pw.close();

                JOptionPane.showMessageDialog(null, "Lưu thành công");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    
    
    
    
    
    
    
    
//    //////////
//    private List<Cell> mobileObjectList = new ArrayList<>();
//    
//    public void addMobileObject(Cell cellObject){
//        mobileObjectList.add(cellObject);
//        
//    }
//    
//    public void removeAllMobileObject(){
//        mobileObjectList.clear();
//        
//    }
//    
//    public List<Cell> getMobileObjectList(){
//        return mobileObjectList;
//        
//    }
    
    
    private List<Point> mobileObjectPointList = new ArrayList<>();
    
    public List<Point> getMobileObjectPointList(){
        return mobileObjectPointList;
        
    }
    
    public void addMobileObject(Point cellObject){
        mobileObjectPointList.add(cellObject);
        
    }
    
    public void removeAllMobileObject(){
        mobileObjectPointList.clear();
        
    }
    
    
    
    public Point mobileObjectGetCellNext(int key, Point robotPoint){
        
        int ran = (int) (Math.random() * 4 + 1);
        
        int x = mobileObjectPointList.get(key).getI();
        int y = mobileObjectPointList.get(key).getJ();
        int id = mobileObjectPointList.get(key).getCellId();
        
//        System.out.println("((" + x + ", " + y + "," + id);
        
        switch(ran){
            case 1:
                // up
                switch(id){
                    case 1:
                        if (mobileObjectPointList.get(key).getI() > 0) {
                            if (map.getMap()[x - 1][y].isIsObstacle()) {
                                break;

                            }
                            mobileObjectPointList.get(key).setI(x - 1);
                            mobileObjectPointList.get(key).setJ(y);
                            mobileObjectPointList.get(key).setCellId(3);
                        }
                        break;
                    case 2:
                        if (mobileObjectPointList.get(key).getI() > 0) {
                            if (map.getMap()[x - 1][y].isIsObstacle()) {
                                break;

                            }
                            mobileObjectPointList.get(key).setI(x - 1);
                            mobileObjectPointList.get(key).setJ(y);
                            mobileObjectPointList.get(key).setCellId(4);
                        }
                        break;
                    case 3:
                        mobileObjectPointList.get(key).setI(x);
                        mobileObjectPointList.get(key).setJ(y);
                        mobileObjectPointList.get(key).setCellId(1);

                        break;
                    case 4:
                        mobileObjectPointList.get(key).setI(x);
                        mobileObjectPointList.get(key).setJ(y);
                        mobileObjectPointList.get(key).setCellId(2);

                        break;

                }
                    
                break;
            case 2:
                // left
                switch(id){
                    case 1:
                        if (mobileObjectPointList.get(key).getJ() > 0) {
                            if (map.getMap()[x][y - 1].isIsObstacle()) {
                                break;

                            }
                            mobileObjectPointList.get(key).setI(x);
                            mobileObjectPointList.get(key).setJ(y - 1);
                            mobileObjectPointList.get(key).setCellId(2);
                        }
                        break;
                    case 2:
                        mobileObjectPointList.get(key).setI(x);
                        mobileObjectPointList.get(key).setJ(y);
                        mobileObjectPointList.get(key).setCellId(1);

                        break;
                    case 3:
                        if (mobileObjectPointList.get(key).getJ() > 0) {
                            if (map.getMap()[x][y - 1].isIsObstacle()) {
                                break;

                            }
                            mobileObjectPointList.get(key).setI(x);
                            mobileObjectPointList.get(key).setJ(y - 1);
                            mobileObjectPointList.get(key).setCellId(4);
                        }
                        break;
                    case 4:
                        mobileObjectPointList.get(key).setI(x);
                        mobileObjectPointList.get(key).setJ(y);
                        mobileObjectPointList.get(key).setCellId(3);

                        break;

                }
                    
                break;
            case 3:
                // down
                switch(id){
                    case 1:
                        mobileObjectPointList.get(key).setI(x);
                        mobileObjectPointList.get(key).setJ(y);
                        mobileObjectPointList.get(key).setCellId(3);

                        break;
                    case 2:
                        mobileObjectPointList.get(key).setI(x);
                        mobileObjectPointList.get(key).setJ(y);
                        mobileObjectPointList.get(key).setCellId(4);

                        break;
                    case 3:
                        if (mobileObjectPointList.get(key).getI() < (map.getN() - 1)) {
                            if (map.getMap()[x + 1][y].isIsObstacle()) {
                                break;

                            }
                            mobileObjectPointList.get(key).setI(x + 1);
                            mobileObjectPointList.get(key).setJ(y);
                            mobileObjectPointList.get(key).setCellId(1);
                        }
                        break;
                    case 4:
                        if (mobileObjectPointList.get(key).getI() < (map.getN() - 1)) {
                            if (map.getMap()[x + 1][y].isIsObstacle()) {
                                break;

                            }
                            mobileObjectPointList.get(key).setI(x + 1);
                            mobileObjectPointList.get(key).setJ(y);
                            mobileObjectPointList.get(key).setCellId(2);
                        }
                        break;

                }
                    
                break;
            case 4:
                // right
                switch(id){
                    case 1:
                        mobileObjectPointList.get(key).setI(x);
                        mobileObjectPointList.get(key).setJ(y);
                        mobileObjectPointList.get(key).setCellId(2);

                        break;
                    case 2:
                        if (mobileObjectPointList.get(key).getJ() < (map.getN() - 1)) {
                            if (map.getMap()[x][y + 1].isIsObstacle()) {
                                break;

                            }
                            mobileObjectPointList.get(key).setI(x);
                            mobileObjectPointList.get(key).setJ(y + 1);
                            mobileObjectPointList.get(key).setCellId(1);
                        }
                        break;
                    case 3:
                        mobileObjectPointList.get(key).setI(x);
                        mobileObjectPointList.get(key).setJ(y);
                        mobileObjectPointList.get(key).setCellId(4);

                        break;
                    case 4:
                        if (mobileObjectPointList.get(key).getJ() < (map.getN() - 1)) {
                            if (map.getMap()[x][y + 1].isIsObstacle()) {
                                break;

                            }
                            mobileObjectPointList.get(key).setI(x);
                            mobileObjectPointList.get(key).setJ(y + 1);
                            mobileObjectPointList.get(key).setCellId(3);
                        }
                        break;

                }
                    
                break;
                
        }
        
        
        if (mobileObjectPointList.get(key).getI() == robotPoint.getI()
                && mobileObjectPointList.get(key).getJ() == robotPoint.getJ()
                && mobileObjectPointList.get(key).getCellId() == robotPoint.getCellId()) {
            
            mobileObjectPointList.get(key).setI(x);
            mobileObjectPointList.get(key).setJ(y);
            mobileObjectPointList.get(key).setCellId(id);

            
        }
        
        if (mobileObjectPointList.get(key).getI() == startPoint.getI()
                && mobileObjectPointList.get(key).getJ() == startPoint.getJ()
                && mobileObjectPointList.get(key).getCellId() == startPoint.getCellId()) {
            
            mobileObjectPointList.get(key).setI(x);
            mobileObjectPointList.get(key).setJ(y);
            mobileObjectPointList.get(key).setCellId(id);

            
        }
        
        return mobileObjectPointList.get(key);
        
    }
    
    
    
    
    
    
}
