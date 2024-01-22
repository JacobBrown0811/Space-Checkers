
import { BrowserRouter as Router, Routes, Route} from "react-router-dom";

import Board from "./components/Board";

import "./App.css";
import Team from "./components/Team";
import Header from "./components/Header";
import Player1 from "./components/Player1";
import Player2 from "./components/Player2";
import Rules from "./components/Rules";

function App() {
  return (
    <>
      
      <div>
        <>
          <Router>
            <Routes>
              
              <Route
                path="/"
                element={
                  <>
                  <span>
                    <Header/> 
                  </span>
                  <main>
                    <Player1/>
                    <Board/> 
                    <Player2/>
                  </main>
                  </>
                }
              />
              <Route
                path="/team"
                element={
                  <>
                  <span>
                    <Header/> 
                  </span>
                    <Team/> 
                  </>
                }
              />
                            <Route
                path="/rules"
                element={
                  <>
                  <span>
                    <Header/> 
                  </span>
                    <Rules/> 
                  </>
                }
              />
            </Routes>
          </Router>
        </>
      </div>
    </>
  );
}

export default App;
