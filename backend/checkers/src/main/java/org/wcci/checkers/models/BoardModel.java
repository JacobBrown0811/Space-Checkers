package org.wcci.checkers.models;

import java.util.ArrayList;

public class BoardModel  {
    ArrayList <TileModel> board;


    public static void drawBoard(){
        for(int row = 0; row < 8; row++){
            for(int col = 0; col < 8; col++){
                TileModel tile = new TileModel();
                tile.row = row;
                tile.column = col;
                System.out.println(tile.row + " " + tile.column);
            }
        }
    }
}