import React from "react";
import parkingImage from '../assets/parking.png';

const CameraCard = ({ label }) => (
  <div className="border rounded-xl overflow-hidden shadow-sm">
    <img src={parkingImage} alt={label} className="w-full h-auto" />
    <p className="text-center py-2 font-medium">{label}</p>
  </div>
);

const CameraSection = ({ mode }) => {
  const isCheckout = mode === "checkout";

  const cameraLabels = isCheckout
    ? ["Entry Camera", "Exit Camera", "Face Entry Camera", "Face Exit Camera"]
    : ["Entry Camera", "Face Entry Camera"];

  return (
    <div className={`grid ${isCheckout ? "grid-cols-2" : "grid-cols-2"} gap-4`}>
      {cameraLabels.map((label, index) => (
        <CameraCard key={index} label={label} />
      ))}
    </div>
  );
};

export default CameraSection;
