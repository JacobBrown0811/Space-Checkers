const Team = () => {
  /**
   * Team profiles
   */
  return (
    <article className="team">
      <div className="member" id="ryan">
        <img
          src="./src/assets/images/CS-heathers.jpg"
          alt="Probably the same guy"
        />
        <div>
          Ryan brings a high level of professionalism to yelling at inanimate
          objects, and he&apos;s probably not a sociopath!
        </div>
      </div>

      <div className="member" id="ronald">
        <img src="./src/assets/images/hannibal.jpg" alt="Rons real job" />
        <div>
          Ronald brings integrity and commitment to the team, and is never
          afraid to speak up in his own defense!
        </div>
      </div>

      <div className="member" id="jacob">
        <img
          src="./src/assets/images/paladin.jpg"
          alt="Probably the same guy"
        />
        <div>Laissez le bon temps rouler!</div>
      </div>

      <div className="member" id="josh">
        <img src="./src/assets/images/sammy.jpg" alt="Josh IRL" />
        <div>
          Josh just kind of stares at the code like this until it works right.
        </div>
      </div>
      <a href="/">
        <button>Close</button>
      </a>
    </article>
  );
};

export default Team;
