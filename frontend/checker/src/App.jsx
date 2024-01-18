
import { BrowserRouter as Router, Routes, Route} from "react-router-dom";

import Board from "./components/Board";

import "./App.css";
import Team from "./components/Team";
import Header from "./components/Header";

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
                    <Board/> 
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
            </Routes>
          </Router>
        </>
      </div>
    </>
  );
}

export default App;
