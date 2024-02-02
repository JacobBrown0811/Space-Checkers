import React from 'react';
import axios from 'axios';

const Header = () => {
 function handleButtonClick() {
    axios.get('/boards/wipe/1')
      .then(response => {
        console.log(response.data);
        window.location.reload();
      })
      .catch(error => {
        console.error('Error wiping board:', error);
      });
 }

 return (
    <div>
      <header>
        <div id="mid">
          <h1>Space Checkers</h1>
          <div id="icons">
            <div className="player" id="p1"></div>
          </div>
        </div>
        <nav>
          <a href="./rules"><button title="Rules of Checkers">Space Rules</button></a>
          <button title="Start new Game" onClick={handleButtonClick}>New Game</button>
          <a href="./team"><button title="About Team">About</button></a>
        </nav>
      </header>
    </div>
 );
};

export default Header;
