/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author manh nguyenvan
 */
public class Map {
    
    private int n;
    private MegaCell[][] map;

    private boolean[][] adjacencyMatrix; // adjacency matrix
    
    private int[][] graphPath;  // [curr][prev]
    
    
    
    public Map(int n) {
        this.n = n;
        this.map = new MegaCell[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = new MegaCell(new Cell(1), false);
            }
        }
        
        this.adjacencyMatrix = new boolean[n * n][n * n];
        
        for (int i = 0; i < (n * n); i++) {
            for (int j = 0; j < (n * n); j++) {
                
                this.adjacencyMatrix[i][j] = false;
                
            }
        }
        
        
        for (int i = 0; i < n; i++) {
            for (int j = (n * i); j < (n * (i + 1)); j++) {
                if ((j + 1) < (n * i + n)) {
                    this.adjacencyMatrix[j][j + 1] = true;
                    this.adjacencyMatrix[j + 1][j] = true;
                }
                if ((j + n) < ((n * (i + 1)) + n)) {
                    if ((j + n) < n * n) {
                        this.adjacencyMatrix[j][j + n] = true;
                        this.adjacencyMatrix[j + n][j] = true;
                        
                    }
                }
            }
        }
        
        
//        map[0][2].setIsObstacle(true);
//        map[1][1].setIsObstacle(true);
        
//        map[0][0].setIsObstacle(true);
//        map[0][3].setIsObstacle(true);
//        map[1][3].setIsObstacle(true);
//        map[1][2].setIsObstacle(true);
//        map[2][2].setIsObstacle(true);
//        map[3][4].setIsObstacle(true);
        
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (this.map[i][j].isIsObstacle()) {
                    int key = convertMegaCellToAdjacencyMatrix(i, j);
                    
                    for (int k = 0; k < n * n; k++) {
                        
                        this.adjacencyMatrix[key][k] = false;
                        this.adjacencyMatrix[k][key] = false;
                        
                    }
                    
                }
            }
        }
        
//        for (int i = (n * line); i < (n * (line + 1)); i++) {
//            if ((i + 1) < (n)) {
//                this.adjacencyMatrix[i][i + 1] = true;
//            }
//            
//        }
        


        
        int countObstacle = 0;
        for (int i = 0; i < getN(); i++) {
            for (int j = 0; j < getN(); j++) {
                if (getMap()[i][j].isIsObstacle()) {
                    countObstacle ++;
                    
                }
            }
        }
        graphPath = new int[n * n - countObstacle][2];
        
        
        

        
    }
    
    public Map(int n, MegaCell[][] map) {
        this.n = n;
        this.map = map;
    }

    
    
    
    
    
    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public MegaCell[][] getMap() {
        return map;
    }

    public void setMap(MegaCell[][] map) {
        this.map = map;
    }

    public boolean[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    public void setAdjacencyMatrix(boolean[][] adjacencyMatrix) {
        this.adjacencyMatrix = adjacencyMatrix;
    }

    public int[][] getGraphPath() {
        return graphPath;
    }

    public void setGraphPath(int[][] graphPath) {
        this.graphPath = graphPath;
    }
    
    
    
    
    
    
    
    public void updateAdjacencyMatrix(int xCellId, int yCellId){

        if (map[getMegaCellLocationY(xCellId, yCellId)][getMegaCellLocationX(xCellId, yCellId)]
                .isIsObstacle()) {
            
            int key = convertMegaCellToAdjacencyMatrix(getMegaCellLocationY(xCellId, yCellId), 
                    getMegaCellLocationX(xCellId, yCellId));

            for (int k = 0; k < getN() * getN(); k++) {

                getAdjacencyMatrix()[key][k] = false;
                getAdjacencyMatrix()[k][key] = false;

            }

        }

    }
    
    public void refreshAdjacencyMatrix(){
        
//        this.adjacencyMatrix = new boolean[n * n][n * n];
        
        for (int i = 0; i < (n * n); i++) {
            for (int j = 0; j < (n * n); j++) {
                
                this.adjacencyMatrix[i][j] = false;
                
            }
        }
        
        
        for (int i = 0; i < n; i++) {
            for (int j = (n * i); j < (n * (i + 1)); j++) {
                if ((j + 1) < (n * i + n)) {
                    this.adjacencyMatrix[j][j + 1] = true;
                    this.adjacencyMatrix[j + 1][j] = true;
                }
                if ((j + n) < ((n * (i + 1)) + n)) {
                    if ((j + n) < n * n) {
                        this.adjacencyMatrix[j][j + n] = true;
                        this.adjacencyMatrix[j + n][j] = true;
                        
                    }
                }
            }
        }
        
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (this.map[i][j].isIsObstacle()) {
                    int key = convertMegaCellToAdjacencyMatrix(i, j);
                    
                    for (int k = 0; k < n * n; k++) {
                        
                        this.adjacencyMatrix[key][k] = false;
                        this.adjacencyMatrix[k][key] = false;
                        
                    }
                    
                }
            }
        }
        
//        for (int i = (n * line); i < (n * (line + 1)); i++) {
//            if ((i + 1) < (n)) {
//                this.adjacencyMatrix[i][i + 1] = true;
//            }
//            
//        }
        


        
        int countObstacle = 0;
        for (int i = 0; i < getN(); i++) {
            for (int j = 0; j < getN(); j++) {
                if (getMap()[i][j].isIsObstacle()) {
                    countObstacle ++;
                    
                }
            }
        }
        graphPath = null;
        graphPath = new int[n * n - countObstacle][2];
        
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public int getMegaCellLocationX(int x, int y){
        return (x / 2);
        
    }
    
    public int getMegaCellLocationY(int x, int y){
        return (y / 2);
        
    }
    
    
    
    public int convertAdjacencyMatrixToMegaCellX(int k){
        int x = k / n;
        int y = k % n;
        
        return x;
    }
    
    public int convertAdjacencyMatrixToMegaCellY(int k){
        
        return (k % n);
    }
    
    public int convertMegaCellToAdjacencyMatrix(int i, int j){
        return (i * n + j);
    }
    
    
    
    
}
