import { useState, useEffect } from "react";
import axios from "axios";

const Board = () => {
  const [tiles, setTiles] = useState([]);
  const [pieces, setPieces] = useState([]);

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
    const fetchData = async () => { //grab pieces and tiles
      await fetchTiles();
      await fetchPieces();
    };
    fetchData();
  }, []);

  return (
    <>
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
                <div key={tile.id} className={`${tile.color} ${tile.isOccupied ? 'occupied' : ''}`}>
                  {tile.isOccupied && matchingPiece && (
                    <div className={`piece ${matchingPiece.color}`}></div>
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
