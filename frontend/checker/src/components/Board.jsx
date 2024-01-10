import { useState, useEffect } from "react";

import axios from "axios";


const Board = () => {

  const [board, setBoard] = useState([]);

  const fetchBoard = async () => {


    try {

        const response = await axios.get("/boards/1/tiles");
        setBoard(response.data);

    } catch {

        console.error("Failure getting board!");

    }

}

  useEffect(() => {

  const fetchData = async () => await fetchBoard();
  fetchData();
},[])


return (
  <>
    <game-board>
      {
        Object.values(
          board.reduce((rows, tile) => {
            if (!rows[tile.boardRow]) {
              rows[tile.boardRow] = [];
            }
            rows[tile.boardRow].push(tile);
            return rows;
          }, {})
        ).map((row, index) => (
          <div key={index}>
            {
              row.map(Tile => (
                <div key={Tile.id} className={Tile.color}>Row: {Tile.boardRow + 1} Col: {Tile.boardColumn +1} {Tile.isPlayable.toString()}</div>
              ))
            }
          </div>
        ))
      }
    </game-board>
  </>
 );
}

export default Board;
