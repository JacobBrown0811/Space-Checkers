import { useState } from "react";
// import { AuthProvider } from './contexts/AuthContext'
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

import Board from "./components/Board";

import "./App.css";
import Score from "./components/Score";

function App() {
  return (
    <>
      <header>
        <team>
          <button title="Meet The Team!">About Normal Team Members</button>
          <button title="Learn his Dark Secrets... (In French!)">
            About Jacob Specifically
          </button>
        </team>
        <h1>Space Checkers</h1>
        <nav>
          <button title="Set Player Name">Set Name</button>
          <button title="Start new Game">New Game</button>
          <button title="Hunt an actual person">The Most Dangerous Game</button>
        </nav>
      </header>
      <div>
        <main>
          <Router>
            randy
            <Routes>
              <Route
                path="/*"
                element={
                  <>
                    <Board /><Score /> 
                  </>
                }
              />
            </Routes>
          </Router>
        </main>
      </div>
    </>
  );
}

export default App;
