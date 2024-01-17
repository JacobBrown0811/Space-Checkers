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
          <button title="Set Player Name">Set Name</button>
          <button title="Start new Game">New Game</button>
          <button title="About Game">About</button>
        </nav>
      </header>
    </div>
  );
};

export default Header;
