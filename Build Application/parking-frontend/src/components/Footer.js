import React from "react";

const Footer = () => {
  return (
    <footer className="bg-white shadow-inner py-4 text-center text-sm text-gray-500">
      &copy; {new Date().getFullYear()} Parkee. All rights reserved.
    </footer>
  );
};

export default Footer;
