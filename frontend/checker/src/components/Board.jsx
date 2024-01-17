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

    function showMoves(matchingPiece) {
    const tileId = matchingPiece.tile.id;
    const moveL = tileId - 9;
    const moveR = tileId - 7;

    const leftElements = document.getElementsByClassName('black ' + moveL);
    const rightElements = document.getElementsByClassName('black ' + moveR);
  
    Array.from(leftElements).forEach(element => {
      element.style.backgroundColor = 'green';
      element.addEventListener('click', movePiece(matchingPiece.id, moveL))
    });
  
    Array.from(rightElements).forEach(element => {
      element.style.backgroundColor = 'green';
      element.addEventListener('click', movePiece(matchingPiece.id, moveR))
    });
  }

  async function movePiece(pieceId, tileId) {
    const piece = pieceId;
    const newTile = tileId;
    const both = [piece, newTile]
    console.log(both)
    try {
      await axios.put(`/pieces/${piece}`, both)
      console.log("did it work?") // TODO remove before deploy
    } catch {
      console.error("bad times")
    }
    document.location.reload();
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
