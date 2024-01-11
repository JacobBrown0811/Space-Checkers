
import { BrowserRouter as Router, Routes, Route} from "react-router-dom";

import Board from "./components/Board";

import "./App.css";
import Team from "./components/Team";
import Jacob from "./components/Jacob";
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
              <Route
                path="/jacob"
                element={
                  <>
                  <span>
                    <Header/> 
                  </span>
                    <Jacob/> 
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
