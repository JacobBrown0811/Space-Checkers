import { useState, useEffect, useCallback, useRef } from "react";
import axios from "axios";

const Board = () => {
  const [tiles, setTiles] = useState([]);
  const [pieces, setPieces] = useState([]);
  const [selectedPiece, setSelectedPiece] = useState(null);
  const [turn, setTurn] = useState(1);
  const tileRefs = useRef([]);

  const fetchTiles = async () => {
    try {
      const response = await axios.get("/boards/1/tiles");
      setTiles(response.data);
    } catch (error) {
      console.error("Failure getting tiles!", error);
    }
  };

  const fetchPieces = async () => {
    try {
      const response = await axios.get("/boards/1/pieces");
      setPieces(response.data);
    } catch (error) {
      console.error("Failure getting pieces!", error);
    }
  };

  useEffect(() => {
    const fetchData = async () => {
      await fetchTiles();
      await fetchPieces();
    };
    fetchData();
  }, [turn]);

  const resetSelection = () => {
    setSelectedPiece(null);
  };

  useEffect(() => {
    document.addEventListener("click", resetSelection);

    return () => {
      document.removeEventListener("click", resetSelection);
    };
  }, []);

  const showMoves = (event, matchingPiece) => {
    event.stopPropagation();
    document.addEventListener("click", resetSelection);
    const allTiles = document.getElementsByClassName("black");
    //  const indicatedTiles = document.getElementsByClassName('black.false.'+ moveL) + document.getElementsByClassName('black.false.'+ moveR);

    const removeAllListeners = (element) => {
      const clonedElement = element.cloneNode(true);
      element.parentNode.replaceChild(clonedElement, element);
    };

    Array.from(allTiles).forEach((element) => {
      element.style.boxShadow = "";
      // removeAllListeners(element);
    });

    const tileId = matchingPiece.tile.id;

    let moveL = tileId - 9;
    let moveR = tileId - 7;

    if (matchingPiece.color === "red") {
      moveL = tileId + 9;
      moveR = tileId + 7;
    }

    const leftElements = document.getElementsByClassName(
      "black false " + moveL
    );
    const rightElements = document.getElementsByClassName(
      "black false " + moveR
    );

      let moveUL = tileId - 9;
      let moveUR = tileId - 7;
      let moveDR = tileId + 9;
      let moveDL = tileId + 7;
    

    const upRightElements = document.getElementsByClassName(
      "black false " + moveUR
    );
    const upLeftElements = document.getElementsByClassName(
      "black false " + moveUL
    );
    const downRightElements = document.getElementsByClassName(
      "black false " + moveDR
    );
    const downLeftElements = document.getElementsByClassName(
      "black false " + moveDL
    );


    const moveHandler = (event) =>
      movePiece(matchingPiece.id, event.target.dataset.move);

    if (matchingPiece.king){
      Array.from(upRightElements).forEach((element) => {
        element.style.boxShadow = "inset 0px 0px 16px 8px silver";
        element.addEventListener("click", moveHandler);
        element.dataset.move = moveUR;
      });
      Array.from(upLeftElements).forEach((element) => {
        element.style.boxShadow = "inset 0px 0px 16px 8px silver";
        element.addEventListener("click", moveHandler);
        element.dataset.move = moveUL;
      });
      Array.from(downRightElements).forEach((element) => {
        element.style.boxShadow = "inset 0px 0px 16px 8px silver";
        element.addEventListener("click", moveHandler);
        element.dataset.move = moveDR;
      });
      Array.from(downLeftElements).forEach((element) => {
        element.style.boxShadow = "inset 0px 0px 16px 8px silver";
        element.addEventListener("click", moveHandler);
        element.dataset.move = moveDL;
      });
    } else {
    Array.from(leftElements).forEach((element) => {
      element.style.boxShadow = "inset 0px 0px 16px 8px silver";
      element.addEventListener("click", moveHandler);
      element.dataset.move = moveL;
    });

    Array.from(rightElements).forEach((element) => {
      element.style.boxShadow = "inset 0px 0px 16px 8px silver";
      element.addEventListener("click", moveHandler);
      element.dataset.move = moveR;
    });
  }
    // original
    if (rightElements.length == 0 && leftElements.length == 0 && upRightElements.length == 0 && upLeftElements.length == 0 && downRightElements.length == 0 && downLeftElements.length == 0) {
      Array.from(allTiles).forEach(element => {
      // Array.from(indicatedTiles).forEach((element) => {
        removeAllListeners(element);
        if (!element.dataset) {
          element.addEventListener("click", () => {
            setSelectedPiece(null);
          });
        }
      });
    }

    // if (rightElements.length == 0 && leftElements.length == 0) {
    //   Array.from(allTiles).forEach(element => {
    //   // Array.from(indicatedTiles).forEach((element) => {

    //     removeAllListeners(element);
    //     if (!element.dataset) {
    //       element.addEventListener("click", () => {
    //         setSelectedPiece(null);
    //       });
    //     }
    //   });
    // }
  };

  const movePiece = useCallback(async (pieceId, tileId) => {
    const piece = pieceId;
    const newTile = tileId;
    const both = [piece, newTile];
    console.log(both);
    try {
      await axios.put(`/pieces/${piece}`, both);
      console.log("did it work?");
      setSelectedPiece(null);
      setTurn((prevTurn) => prevTurn + 1);
    } catch {
      console.error("bad times");
    }
  }, []);

  useEffect(() => {
    fetchTiles();
    fetchPieces();
  }, [turn]);

  return (
    <>
      <game-board key={turn}>
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
              const matchingPiece = pieces.find(
                (piece) => piece.tile.id === tile.id
              );

              return (
                <div
                  ref={(el) => (tileRefs.current[tile.id] = el)}
                  key={tile.id}
                  className={`${tile.color} ${tile.isOccupied} ${tile.id}`}
                  onClick={(event) => showMoves(event, matchingPiece)}
                >
                  {tile.isOccupied && matchingPiece && (
                    <div
                      className={`piece ${matchingPiece.color} ${matchingPiece.king} ${matchingPiece.id}`}
                    ></div>
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
