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
    const allTiles = document.getElementsByClassName('black');
  
    const removeAllListeners = (element) => {
      const clonedElement = element.cloneNode(true);
      element.parentNode.replaceChild(clonedElement, element);
    };
  
    Array.from(allTiles).forEach(element => {
      element.style.boxShadow = '';
      removeAllListeners(element);
    });
  
    const tileId = matchingPiece.tile.id;
    const moveL = tileId - 9;
    const moveR = tileId - 7;
  
    const leftElements = document.getElementsByClassName('black ' + moveL);
    const rightElements = document.getElementsByClassName('black ' + moveR);
  
    const moveHandler = (event) => movePiece(matchingPiece.id, event.target.dataset.move);
  
    Array.from(leftElements).forEach(element => {
      element.style.boxShadow = 'inset 0px 0px 16px 8px silver';
      element.addEventListener('click', moveHandler);
      element.dataset.move = moveL;
    });
  
    Array.from(rightElements).forEach(element => {
      element.style.boxShadow = 'inset 0px 0px 16px 8px silver';
      element.addEventListener('click', moveHandler);
      element.dataset.move = moveR;
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
                    <div className={`piece ${matchingPiece.color} ${matchingPiece.id}`} onClick={()=> showMoves(matchingPiece)}></div> // show possible moves for clicked piece
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
