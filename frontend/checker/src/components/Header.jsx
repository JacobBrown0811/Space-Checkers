const Header = () => {
  return (
    <div>
    <header>
      <div id="team">
        <a href="/team">
          <button title="Meet The Team!">About Normal Team Members</button>
        </a>
        <a href="/jacob">
          <button title="Learn his Dark Secrets... (In French!)">
            About Jacob Specifically
          </button>
        </a>
      </div>
      <div id="mid">
      <h1>Space Checkers</h1>
      <div id="icons">
      <div className="player" id="p1">
              <img src="./src/assets/images/tardigrade1.jpg" alt="This" />
              </div>
            
            
              <div id="score" className="score" >VS.</div>
            
            
              <div className="player" id="p2">
                <img src="./src/assets/images/DeltaGlobeOrbit.png" alt="Space Force!" />
              </div>
              </div>
              </div>
      <nav>
        <button title="Set Player Name">Set Name</button>
        <button title="Start new Game">New Game</button>
        <button title="Hunt an actual person">The Most Dangerous Game</button>
      </nav>
      </header>
    </div>
  );
};

export default Header;
