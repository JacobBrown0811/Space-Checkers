const Team = () => {

    return (
    <article className="team">
    
                  <div className="member" id="ryan">
                  <img src="./src/assets/images/CS-heathers.jpg" alt="Probably the same guy" />
                  <h2>
                  Ryan brings a high level of professionalism to yelling at inanimate objects, and he&apos;s probably not a sociopath!
                  </h2>
                  </div>

                  <div className="member" id="ronald">
                  <img src="./src/assets/images/hannibal.jpg" alt="Rons real job" />
                  <h2>
                  Ronald brings integrity and commitment to the team, and is never afraid to speak up in his own defense!
                  </h2>
                  </div>

                  <div className="member" id="josh">
                  <img src="./src/assets/images/sammy.jpg" alt="Josh IRL" />
                  <h2>
                  Josh just kind of stares at the code like this until it works right.
                  </h2>
                  </div>
                  <a href="/">
                  <button >Close</button>
                  </a>
            </article>
    )
    }
    
    export default Team;