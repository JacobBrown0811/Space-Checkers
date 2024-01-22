const Header = () => {
  /**
   * build header nav bar
   */
  return (
    <div>
      <header>
        <div id="mid">
          <h1>Space Checkers</h1>
          <div id="icons">
            <div className="player" id="p1">
              {/* <img
                src="./src/assets/images/DeltaGlobeOrbit.png"
                alt="Space Force!"
              /> */}
            </div>

            {/* <div className="player" id="p2">
              <img src="./src/assets/images/tardigrade1.jpg" alt="This" />
            </div> */}
          </div>
        </div>
        <nav>
        <a href="./rules"><button title="Rules of Checkers">Space Rules</button></a>
          <button title="Start new Game">New Game</button>
          <a href="./team"><button title="About Team">About</button></a>
        </nav>
      </header>
    </div>
  );
};

export default Header;
