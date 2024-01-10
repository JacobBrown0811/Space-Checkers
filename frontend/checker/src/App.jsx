import { useState } from "react";
// import { AuthProvider } from './contexts/AuthContext'
import { BrowserRouter as Router, Routes, Route} from "react-router-dom";
import { Navigate, useNavigate} from "react-router-dom";
import { Link } from "react-router-dom";

import Board from "./components/Board";

import "./App.css";
import Score from "./components/Score";
import Team from "./components/Team";
import Jacob from "./components/Jacob";
import Header from "./components/Header";

function App() {
  return (
    <>
      
      <div>
        <main>
          <Router>
            <Routes>
              
              <Route
                path="/game"
                element={
                  <>
                    <Header/>  <Board/> 
                  </>
                }
              />
              <Route
                path="/team"
                element={
                  <>
                    <Team/> 
                  </>
                }
              />
              <Route
                path="/jacob"
                element={
                  <>
                    <Jacob/> 
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
