import React from "react";

const VehicleForm_ = () => {
  return (
    <div className="space-y-4 mt-6">
      <div>
        <label className="text-sm font-medium block mb-1">Plat Nomor Kendaraan</label>
        <input
          type="text"
          className="w-full px-3 py-2 border rounded-md bg-gray-100"
          placeholder="B 1234 ABC"
          disabled
        />
      </div>
      <div className="grid grid-cols-2 gap-4">
        <div>
          <label className="text-sm font-medium block mb-1">Jenis Kendaraan</label>
          <select className="w-full px-3 py-2 border rounded-md bg-gray-100">
            <option>Mobil</option>
            <option>Motor</option>
          </select>
        </div>
        <div>
          <label className="text-sm font-medium block mb-1">Metode Pembayaran</label>
          <select className="w-full px-3 py-2 border rounded-md bg-gray-100">
            <option>emoney</option>
            <option>cash</option>
          </select>
        </div>
      </div>
    </div>
  );
};

export default VehicleForm_;
