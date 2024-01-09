import { useState, useEffect } from "react";

import axios from "axios";


const Board = () => {

  const [Board, setBoard] = useState([]);

  const fetchBoard = async () => {


    try {

        const response = await axios.get(`/boards/1/tiles`);
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
        Board.map(
        Tile => (
          <div key={Tile.id} className={Tile.color}>{Tile.boardRow}sdukfhgosudhfslodi</div>
        )
      )
      }

      </game-board>
    </>
  );
}

export default Board;
