import React from "react";
import { Link } from "react-router-dom";
import parkeeLogo from "../assets/parkee_logo.png";

const Header = () => {
  return (
    <header className="flex items-center justify-between px-6 py-6 bg-white shadow-sm">
      <div className="flex items-center space-x-4">
        <button className="text-2xl font-bold">≡</button>
        <img src={parkeeLogo} alt="Parkee Logo" className="h-10" />
      </div>
      <div className="flex items-center space-x-6">
        <div className="space-x-4">
          <Link to="/" className="text-black hover:text-red-600 font-bold">
            Check In
          </Link>
          <Link
            to="/checkout"
            className="text-black hover:text-red-600 font-bold"
          >
            Check Out
          </Link>
        </div>
        <div className="flex items-center space-x-2 bg-gray-100 rounded-full px-4 py-2">
          <span role="img" aria-label="user">
            🧑‍💼
          </span>
          <span className="text-sm font-medium">
            CONVENTIONAL / PARKEE Office Agent
          </span>
          <span>▼</span>
        </div>
      </div>
    </header>
  );
};

export default Header;
