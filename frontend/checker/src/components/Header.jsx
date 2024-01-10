const Header = () => {
  return (
    <div>
    <header>
      <team>
        <a href="/team">
          <button title="Meet The Team!">About Normal Team Members</button>
        </a>
        <a href="/jacob">
          <button title="Learn his Dark Secrets... (In French!)">
            About Jacob Specifically
          </button>
        </a>
      </team>
      <h1>Space Checkers</h1>
      <div className="player" id="p1">
              <img src="./src/assets/images/tardigrade1.jpg" alt="This" />
              </div>
            
            
              <score className="score" >VS.</score>
            
            
              <div className="player" id="p2">
                <img src="./src/assets/images/DeltaGlobeOrbit.png" alt="Space Force!" />
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
