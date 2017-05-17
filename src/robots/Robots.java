/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robots;

import models.Cell;
import models.Map;
import models.MegaCell;
import models.Point;
import utils.SpiralSTC;

/**
 *
 * @author manh nguyenvan
 */
public class Robots {   // drafts

    private static MegaCell[][] megaCell;
    private static Map map;
    private static SpiralSTC spiralSTC;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        
        map = new Map(5);
        Point startPoint = new Point(1, 2, 3);
        
        spiralSTC = new SpiralSTC(startPoint, map);
//        spiralSTC.BFS(5, 0);
//        spiralSTC.BFSAlgorithm(15);
//        spiralSTC.isLowBattery(new Point(1, 2, 3), new Point(0, 0, 1));
        
        /*
        map = new Map(5);
        
        String str = "";
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 25; j++) {
                str += map.getAdjacencyMatrix()[i][j] + ",  ";
            }
            System.out.println(str);
            str = "";
        }
        */
        
        
        /*
        megaCell = new MegaCell[5][5];
        
        megaCell[0][0] = new MegaCell(new Cell(1), true);
        megaCell[0][1] = new MegaCell(new Cell(1), false);
        megaCell[0][2] = new MegaCell(new Cell(1), false);
        megaCell[0][3] = new MegaCell(new Cell(1), true);
        megaCell[0][4] = new MegaCell(new Cell(1), false);
        
        megaCell[1][0] = new MegaCell(new Cell(1), false);
        megaCell[1][1] = new MegaCell(new Cell(1), false);
        megaCell[1][2] = new MegaCell(new Cell(1), true);
        megaCell[1][3] = new MegaCell(new Cell(1), true);
        megaCell[1][4] = new MegaCell(new Cell(1), false);
        
        megaCell[2][0] = new MegaCell(new Cell(1), false);
        megaCell[2][1] = new MegaCell(new Cell(1), false);
        megaCell[2][2] = new MegaCell(new Cell(1), true);
        megaCell[2][3] = new MegaCell(new Cell(1), false);
        megaCell[2][4] = new MegaCell(new Cell(1), false);
        
        megaCell[3][0] = new MegaCell(new Cell(1), false);
        megaCell[3][1] = new MegaCell(new Cell(1), false);
        megaCell[3][2] = new MegaCell(new Cell(1), false);
        megaCell[3][3] = new MegaCell(new Cell(1), false);
        megaCell[3][4] = new MegaCell(new Cell(1), false);
        
        megaCell[4][0] = new MegaCell(new Cell(1), false);
        megaCell[4][1] = new MegaCell(new Cell(1), false);
        megaCell[4][2] = new MegaCell(new Cell(1), false);
        megaCell[4][3] = new MegaCell(new Cell(1), true);
        megaCell[4][4] = new MegaCell(new Cell(1), false);
        
        
        
        
        
        megaCell[0][1].setRight(true);
        megaCell[0][2].setLeft(true);
        
        megaCell[1][1].setUp(true);
        megaCell[0][1].setDown(true);
        
        megaCell[1][0].setRight(true);
        megaCell[1][1].setLeft(true);
        
        megaCell[1][0].setDown(true);
        megaCell[2][0].setUp(true);
        
        megaCell[2][0].setDown(true);
        megaCell[3][0].setUp(true);
        
        megaCell[3][0].setDown(true);
        megaCell[4][0].setUp(true);
        
        megaCell[4][0].setRight(true);
        megaCell[4][1].setLeft(true);
        
        megaCell[4][1].setRight(true);
        megaCell[4][2].setLeft(true);
        
        megaCell[3][0].setRight(true);
        megaCell[3][1].setLeft(true);
        
        megaCell[3][1].setUp(true);
        megaCell[2][1].setDown(true);
        
        megaCell[3][1].setRight(true);
        megaCell[3][2].setLeft(true);
        
        megaCell[3][2].setRight(true);
        megaCell[3][3].setLeft(true);
        
        megaCell[3][3].setUp(true);
        megaCell[2][3].setDown(true);
        
        megaCell[2][3].setRight(true);
        megaCell[2][4].setLeft(true);
        
        megaCell[2][4].setUp(true);
        megaCell[1][4].setDown(true);
        
        megaCell[1][4].setUp(true);
        megaCell[0][4].setDown(true);
        
        megaCell[2][4].setDown(true);
        megaCell[3][4].setUp(true);
        
        megaCell[3][4].setDown(true);
        megaCell[4][4].setUp(true);
        
        
        
        
        
        
        
        map = new Map(5, megaCell);
        
        
        
        spiralSTC = new SpiralSTC(new Point(1, 0, 1), map);
        
                
        
        
        spiralSTC.spiralSTC();
        */
        
    }
    
}
