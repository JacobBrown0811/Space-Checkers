import { useState, useEffect, useCallback, useRef } from "react";
import axios from "axios";

const Board = () => {
  const [tiles, setTiles] = useState([]);
  const [pieces, setPieces] = useState([]);
  const [selectedPiece, setSelectedPiece] = useState(null);
  const [turn, setTurn] = useState(1);
  const tileRefs = useRef([]);
  const [currentPlayer, setCurrentPlayer] = useState('blue');

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

  const switchPlayer = () => {
    setCurrentPlayer(currentPlayer === 'blue' ? 'red' : 'blue');
};

  
const showMoves = (event, matchingPiece) => {
  event.stopPropagation();  // This should be inside the function

  if (!matchingPiece || matchingPiece.color !== currentPlayer) {
      console.log("Not your turn");
      return;
  }

  document.addEventListener("click", resetSelection);
  const allTiles = document.getElementsByClassName("black");

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
        removeAllListeners(element);
        if (!element.dataset) {
          element.addEventListener("click", () => {
            setSelectedPiece(null);
          });
        }
      });
    }
    showCaptures(matchingPiece)
  };

  const showCaptures = (matchingPiece) => {

    document.addEventListener("click", resetSelection);
    const allTiles = document.getElementsByClassName("black");

    const removeAllListeners = (element) => {
      const clonedElement = element.cloneNode(true);
      element.parentNode.replaceChild(clonedElement, element);
    };

    const tileId = matchingPiece.tile.id;

    let capL = tileId - 18;
    let capR = tileId - 14;

    if (matchingPiece.color === "red") {
      capL = tileId + 18;
      capR = tileId + 14;
    }

    const leftElements = document.getElementsByClassName(
      "black false " + capL
    );
    const rightElements = document.getElementsByClassName(
      "black false " + capR
    );

      let capUL = tileId - 18;
      let capUR = tileId - 14;
      let capDR = tileId + 18;
      let capDL = tileId + 14;
    

    const upRightElements = document.getElementsByClassName(
      "black false " + capUR
    );
    const upLeftElements = document.getElementsByClassName(
      "black false " + capUL
    );
    const downRightElements = document.getElementsByClassName(
      "black false " + capDR
    );
    const downLeftElements = document.getElementsByClassName(
      "black false " + capDL
    );


    const captureHandler = (event) =>
      capturePiece(matchingPiece.id, event.target.dataset.move);

    if (matchingPiece.king){
      Array.from(upRightElements).forEach((element) => {
        element.style.boxShadow = "inset 0px 0px 16px 8px green";
        element.addEventListener("click", captureHandler);
        element.dataset.move = capUR;
      });
      Array.from(upLeftElements).forEach((element) => {
        element.style.boxShadow = "inset 0px 0px 16px 8px green";
        element.addEventListener("click", captureHandler);
        element.dataset.move = capUL;
      });
      Array.from(downRightElements).forEach((element) => {
        element.style.boxShadow = "inset 0px 0px 16px 8px green";
        element.addEventListener("click", captureHandler);
        element.dataset.move = capDR;
      });
      Array.from(downLeftElements).forEach((element) => {
        element.style.boxShadow = "inset 0px 0px 16px 8px green";
        element.addEventListener("click", captureHandler);
        element.dataset.move = capDL;
      });
    } else {
    Array.from(leftElements).forEach((element) => {
      element.style.boxShadow = "inset 0px 0px 16px 8px green";
      element.addEventListener("click", captureHandler);
      element.dataset.move = capL;
    });

    Array.from(rightElements).forEach((element) => {
      element.style.boxShadow = "inset 0px 0px 16px 8px green";
      element.addEventListener("click", captureHandler);
      element.dataset.move = capR;
    });
  }
    // original
    if (rightElements.length == 0 && leftElements.length == 0 && upRightElements.length == 0 && upLeftElements.length == 0 && downRightElements.length == 0 && downLeftElements.length == 0) {
      Array.from(allTiles).forEach(element => {
        removeAllListeners(element);
        if (!element.dataset) {
          element.addEventListener("click", () => {
            setSelectedPiece(null);
          });
        }
      });
    }

  };

  const movePiece = useCallback(async (pieceId, targetTileId) => {
    const payload = {
        newTileId: Number(targetTileId),
    };
    console.log("Sending payload:", payload);

    try {
        const response = await axios.put(`/pieces/${pieceId}`, payload);
        console.log("Move successful", response.data);
        setSelectedPiece(null);
        setTurn((prevTurn) => prevTurn + 1);
        switchPlayer();
    } catch (error) {
        console.error("Error occurred during move:", error.response ? error.response.data : error.message);
    }
}, [setSelectedPiece, setTurn, switchPlayer]);

    const capturePiece = useCallback(async (pieceId, targetTileId) => {
      const piece = pieces.find(p => p.id === pieceId);
      const targetTile = tiles.find(t => t.id === Number(targetTileId));
      if (!piece || !targetTile) {
          console.log("Invalid piece or target tile");
          return;
      }
      
      let capturedPieceTileId = 0;
      if (piece.tile.id - targetTile.id === 18) capturedPieceTileId = piece.tile.id - 9;
      if (piece.tile.id - targetTile.id === 14) capturedPieceTileId = piece.tile.id - 7;
      if (piece.tile.id - targetTile.id === -14) capturedPieceTileId = piece.tile.id + 7;
      if (piece.tile.id - targetTile.id === -18) capturedPieceTileId = piece.tile.id + 9;
  
      const capturedPiece = pieces.find(p => p.tile.id === capturedPieceTileId);
      const capturedPieceId = capturedPiece ? capturedPiece.id : null;
  
      if (!capturedPieceId) {
          console.log(`No piece to capture at tile ${capturedPieceTileId}`);
          return;
      }
  
      const payload = {
          newTileId: Number(targetTileId),
          capturedPieceId: capturedPieceId
      };
  
      try {
          await axios.put(`/pieces/${pieceId}`, payload);
          console.log("Capture successful");
          setSelectedPiece(null);
          setTurn((prevTurn) => prevTurn + 1);
          switchPlayer();
      } catch (error) {
          console.error("Error occurred during capture: ", error.response ? error.response.data : error.message);
      }
  }, [pieces, tiles, setSelectedPiece, setTurn, switchPlayer]);


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
