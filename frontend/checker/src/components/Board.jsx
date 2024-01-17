import { useState, useEffect } from "react";
import axios from "axios";

const Board = () => {
  const [tiles, setTiles] = useState([]);
  const [pieces, setPieces] = useState([]);

/**
 * Place tiles on the board
 */
  const fetchTiles = async () => {
    try {
      const response = await axios.get("/boards/1/tiles");
      setTiles(response.data);
    } catch (error) {
      console.error("Failure getting tiles!", error);
    }
  };

/**
 * Place pieces on the board
 */
  const fetchPieces = async () => {
    try {
      const response = await axios.get("/boards/1/pieces");
      setPieces(response.data);
    } catch (error) {
      console.error("Failure getting pieces!", error);
    }
  };

  useEffect(() => {
    const fetchData = async () => { //grab pieces and tiles
      await fetchTiles();
      await fetchPieces();
    };
    fetchData();
  }, []);

  
  const selectedPiece = document.getElementById("13");
  // selectedPiece.addEventListener("click", movePiece()); 

    function showMoves(matchingPiece) {
    const piece = matchingPiece.tile.id;
    const moveL = piece - 9;
    const moveR = piece - 7;

    const leftElements = document.getElementsByClassName('black ' + moveL);
    const rightElements = document.getElementsByClassName('black ' + moveR);
  
    Array.from(leftElements).forEach(element => {
      element.style.backgroundColor = 'green';
    });
  
    Array.from(rightElements).forEach(element => {
      element.style.backgroundColor = 'green';
    });

    // const left = document.('.black.' + moveL);
    // const right = document.querySelector('.black.' + moveR);
    // left.style.backgroundColor = 'green';
    // right.style.backgroundColor = 'green';
  }

  async function movePiece(matchingPiece, tileId) {
    const piece = matchingPiece;
    const newTile = tileId;
    const both = [piece, newTile]
    console.log(both)
    try {
      

      console.log("did it work?")
    } catch {
      console.error("bad times")
    }
  }

  return (
    <>
    {/**
     * build game board
     */}
      <game-board>
        {Object.values(
          tiles.reduce((rows, tile) => {
            if (!rows[tile.boardRow]) { 
              rows[tile.boardRow] = [];
            }
            rows[tile.boardRow].push(tile);
            return rows;
          }, {})
        ).map((row, index) => (
          <div key={index}>
            {row.map((tile) => {
              const matchingPiece = pieces.find((piece) => piece.tile.id === tile.id); 

              return (
                <div key={tile.id} className={`${tile.color} ${tile.id} `}>
                  {tile.isOccupied && matchingPiece && (
                    <div className={`piece ${matchingPiece.color} ${matchingPiece.id}`} onClick={()=> showMoves(matchingPiece)}></div> // Populate the board with pieces
                  )}
                </div>
              );
            })}
          </div>
        ))}
      </game-board>
    </>
  );
};

export default Board;
