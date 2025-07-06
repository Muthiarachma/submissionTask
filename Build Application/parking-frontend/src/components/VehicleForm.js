import React, { useEffect, useState } from "react";

const VehicleForm = ({ formData, setFormData, type = "checkin" }) => {
  const isCheckout = type === "checkout";
  const [error, setError] = useState("");
  const [vehicleTypes, setVehicleTypes] = useState([]);
  const [paymentTypes, setPaymentTypes] = useState([]);
  const [vehicleTypeId, setVehicleTypeId] = useState("");
  const [parkingSlipId, setParkingSlipId] = useState("");
  const [platNomor, setPlatNomor] = useState("");
  const [disabledFields, setDisabledFields] = useState(true);

  const isValidPlatNomor = (value) => {
    const regex = /^[A-Z]{1,2} ?\d{1,4} ?[A-Z]{0,3}$/;
    return regex.test(value.trim().toUpperCase());
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));

    if (name === "plateNumber" && value && !isValidPlatNomor(value)) {
      setError("Format tidak valid. Contoh: B 1234 ABC");
    } else {
      setError("");
    }
  };

  const handlePlatNomorChange = async (e) => {
    const value = e.target.value.toUpperCase();
    setPlatNomor(value);

    if (!isValidPlatNomor(value)) {
      setError("Format tidak valid. Contoh: B 1234 ABC");
      setDisabledFields(true);
      setVehicleTypeId("");
      setParkingSlipId("");
    } else {
      setError("");

      try {
        const res = await fetch(
          `http://localhost:9090/api/v1/tickets/detail?plateNumber=${value}`
        );
        const result = await res.json();

        if (result.data) {
          setVehicleTypeId(result.data.vehicleTypeId);
          setParkingSlipId(result.data.parkingSlipId);
          setDisabledFields(false);
        } else {
          setVehicleTypeId("");
          setParkingSlipId("");
          setDisabledFields(true);
        }
      } catch (err) {
        console.error("Gagal ambil data:", err);
        setDisabledFields(true);
      }
    }
  };

  useEffect(() => {
    const fetchVehicleTypes = async () => {
      const res = await fetch("http://localhost:9090/api/v1/vehicle/list");
      const data = await res.json();
      setVehicleTypes(data.data || []);
    };

    const fetchPaymentTypes = async () => {
      const res = await fetch("http://localhost:9090/api/v1/payment/list");
      const data = await res.json();
      setPaymentTypes(data.data || []);
    };

    fetchVehicleTypes();
    fetchPaymentTypes();
  }, []);

  return (
    <div className="space-y-4 mt-6">
      {!isCheckout && (
        <>
          <div>
            <label className="text-sm font-medium block mb-1">
              Plat Nomor Kendaraan
            </label>
            <input
              type="text"
              name="plateNumber"
              className={`w-full px-3 py-2 border rounded-md ${
                error ? "border-red-500" : "bg-gray-100"
              }`}
              placeholder="B 1234 ABC"
              value={formData.plateNumber || ""}
              onChange={handleChange}
            />
            {error && <p className="text-red-500 text-sm mt-1">{error}</p>}
          </div>

          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="text-sm font-medium block mb-1">
                Jenis Kendaraan
              </label>
              <select
                name="vehicleTypeId"
                value={formData.vehicleTypeId || ""}
                onChange={handleChange}
                className="w-full px-3 py-2 border rounded-md bg-gray-100"
              >
                <option value="">Pilih Jenis</option>
                {vehicleTypes.map((type) => (
                  <option key={type.id} value={type.id}>
                    {type.name}
                  </option>
                ))}
              </select>
            </div>

            <div>
              <label className="text-sm font-medium block mb-1">
                Metode Pembayaran
              </label>
              <select
                name="paymentMethodId"
                value={formData.paymentMethodId || ""}
                onChange={handleChange}
                className="w-full px-3 py-2 border rounded-md bg-gray-100"
              >
                <option value="">Pilih Metode Pembayaran</option>
                {paymentTypes.map((pay) => (
                  <option key={pay.id} value={pay.id}>
                    {pay.name}
                  </option>
                ))}
              </select>
            </div>
          </div>
        </>
      )}

      {isCheckout && (
        <>
          <div>
            <label className="text-sm font-medium block mb-1">
              Plat Nomor Kendaraan
            </label>
            <input
              type="text"
              name="plateNumber"
              className={`w-full px-3 py-2 border rounded-md ${
                error ? "border-red-500" : "bg-gray-100"
              }`}
              placeholder="B 1234 ABC"
              value={formData.plateNumber || ""}
              onChange={handleChange}
            />
            {error && <p className="text-red-500 text-sm mt-1">{error}</p>}
          </div>

          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="text-sm font-medium block mb-1">
                Jenis Kendaraan
              </label>
              <select
                name="vehicleTypeId"
                value={formData.vehicleTypeId || ""}
                onChange={handleChange}
                disabled
                className="w-full px-3 py-2 border rounded-md bg-gray-100 text-gray-500"
              >
                <option value="">Pilih Jenis</option>
                {vehicleTypes.map((type) => (
                  <option key={type.id} value={type.id}>
                    {type.name}
                  </option>
                ))}
              </select>
            </div>

            <div>
              <label className="text-sm font-medium block mb-1">
                Metode Pembayaran
              </label>
              <select
                name="paymentMethodId"
                value={formData.paymentMethodId || ""}
                onChange={handleChange}
                className="w-full px-3 py-2 border rounded-md bg-gray-100"
              >
                <option value="">Pilih Metode Pembayaran</option>
                {paymentTypes.map((pay) => (
                  <option key={pay.id} value={pay.id}>
                    {pay.name}
                  </option>
                ))}
              </select>
            </div>
          </div>

          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="text-sm font-medium block mb-1">
                Nomor Parking
              </label>
              <input
                type="text"
                name="parkingSlipId"
                value={formData.parkingSlipId || ""}
                onChange={handleChange}
                className="w-full px-3 py-2 border rounded-md bg-gray-100"
                placeholder="12343213231"
                disabled
              />
            </div>

            <div>
              <label className="text-sm font-medium block mb-1">
                Kode Voucher
              </label>
              <input
                type="text"
                name="voucherCode"
                value={formData.voucherCode || ""}
                onChange={handleChange}
                className="w-full px-3 py-2 border rounded-md bg-gray-100"
                placeholder="PA543J534"
              />
            </div>
          </div>
        </>
      )}
    </div>
  );
};

export default VehicleForm;
