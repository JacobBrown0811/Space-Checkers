package org.wcci.checkers.models;

import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class BoardModel  {

    @OneToMany
    ArrayList <TileModel> board = new ArrayList<TileModel>();

    @Id
    @GeneratedValue
    long id;

    public void drawBoard(){
        for(int row = 0; row < 8; row++){
            for(int col = 0; col < 8; col++){
                TileModel tile = new TileModel();
                tile.row = row;
                tile.column = col;
                if(row %2 != col % 2){
                    tile.color ="black";
                }
                System.out.println(tile.row + " " + tile.column + tile.color);
                board.add(tile);
            }

        }
    }
}