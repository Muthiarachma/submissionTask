import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import CheckInPage from "./pages/CheckInPage";
import CheckOutPage from "./pages/CheckOutPage";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<CheckInPage />} />
        <Route path="/checkout" element={<CheckOutPage />} />
      </Routes>
    </Router>
  );
}

export default App;
