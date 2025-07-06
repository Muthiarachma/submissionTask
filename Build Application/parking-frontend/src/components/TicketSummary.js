import React, { useEffect, useState } from "react";
import dayjs from "dayjs";
import axios from "axios";
import Swal from "sweetalert2";
import withReactContent from "sweetalert2-react-content";

const MySwal = withReactContent(Swal);

const TicketSummary = ({ type = "checkin", data = {} }) => {
  const [currentTime, setCurrentTime] = useState(dayjs());
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    const interval = setInterval(() => {
      setCurrentTime(dayjs());
    }, 1000);

    return () => clearInterval(interval);
  }, []);
  const isCheckout = type === "checkout";

  const handleSubmit = async () => {
    setLoading(true);

    const endpoint = isCheckout
      ? "http://localhost:9090/api/v1/tickets/check-out"
      : "http://localhost:9090/api/v1/tickets/check-in";

    try {
      // // Payload untuk check-in
      // const checkInPayload = {
      //   plateNumber: "B1236MAX",
      //   vehicleTypeId: "CAR",
      //   parkingLotId: "8d24318a-17a4-448a-9e2d-f351f1244a33",
      // };

      // // Payload untuk check-out
      // const checkOutPayload = {
      //   plateNumber: "B1236MAZ",
      //   vehicleTypeId: "CAR",
      //   parkingLotId: "8d24318a-17a4-448a-9e2d-f351f1244a33",
      //   parkingSlipId: "47f11150-3aa8-434e-873f-3374d4050caa",
      //   paymentMethodId: "FLAZZ",
      //   durationInSeconds: 7200,
      //   price: 14000.0,
      //   discount: 2000.0,
      //   finalPrice: 12000.0,
      //   voucherCode: "V_1000",
      //   checkOutDate: "2025-07-06T:30:00",
      // };

      // const response = await axios.post(
      //   endpoint,
      //   isCheckout ? checkOutPayload : checkInPayload
      // );
      const payload = {
        ...data, // Ambil data dari props
        parkingLotId: "8d24318a-17a4-448a-9e2d-f351f1244a33",
        ...(isCheckout && {
          checkOutDate: currentTime.format("YYYY-MM-DDTHH:mm:ss"),
        }),
      };

      const response = await axios.post(endpoint, payload);
      console.log("Berhasil:", response.data);
      MySwal.fire({
        icon: "success",
        title: "Berhasil!",
        text: "Tiket berhasil diproses.",
        confirmButtonColor: "#dc2626",
      }).then(() => {
        window.location.reload();
      });
    } catch (error) {
      console.error("Gagal:", error);
      MySwal.fire({
        icon: "error",
        title: "Gagal!",
        text: "Terjadi kesalahan saat memproses tiket.",
        confirmButtonColor: "#dc2626",
      });
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="border rounded-xl p-4 space-y-4 shadow-sm text-sm w-full">
      <div className="text-center pb-4">
        <h3 className="text-lg font-bold capitalize">
          {data?.metodePembayaran || "Gedung Parkee"}
        </h3>
        <p className="text-sm text-gray-500">
          Jalan Biak No.31A Cideng, Kecamatan Gambir, Kota Jakarta Pusat
        </p>
      </div>

      <table className="w-full">
        <tbody>
          <tr>
            <td className="text-gray-500">Gate system</td>
            <td className="text-right">
              {data?.gateSystem || "Basement Parking"}
            </td>
          </tr>
          <tr>
            <td className="text-gray-500">Jenis Parkir</td>
            <td className="text-right">{data?.jenisParkir || "Standart"}</td>
          </tr>
          <tr>
            <td className="text-gray-500">Member Expired</td>
            <td className="text-right">{data?.memberExpired || "-"}</td>
          </tr>
          <tr>
            <td className="text-gray-500">Nama Member</td>
            <td className="text-right">{data?.namaMember || "-"}</td>
          </tr>
          <tr>
            <td className="text-gray-500">Unit Member</td>
            <td className="text-right">{data?.unitMember || "-"}</td>
          </tr>
          <tr>
            <td className="text-gray-500">Waktu Masuk</td>
            <td className="text-right font-semibold">
              {isCheckout
                ? data?.waktuMasuk || "-"
                : currentTime.format("DD MMM YYYY HH:mm:ss")}
            </td>
          </tr>

          {isCheckout && (
            <>
              <tr>
                <td className="text-gray-500">Waktu Keluar</td>
                <td className="text-right font-semibold">
                  {currentTime.format("DD MMM YYYY HH:mm:ss")}
                </td>
              </tr>
              <tr>
                <td className="text-gray-500">Sesi</td>
                <td className="text-right font-semibold">
                  {data?.sesi || "Rp 0,00"}
                </td>
              </tr>
              <tr>
                <td className="text-gray-500">Diskon</td>
                <td className="text-right font-semibold">
                  {data?.diskon || "Rp 0,00"}
                </td>
              </tr>
            </>
          )}
        </tbody>
      </table>

      {isCheckout && (
        <div className="border-t pt-3">
          <div className="flex justify-between font-semibold text-sm">
            <span>Total</span>
            <span>{data?.total || "Rp 0,00"}</span>
          </div>
          <div className="text-gray-500 text-sm">
            Duration: {data?.durasi || "0d 00h 00m"}
          </div>
        </div>
      )}

      <div className="space-y-2">
        <button
          onClick={handleSubmit}
          disabled={loading}
          className={`w-full py-2 rounded-md text-white ${
            loading
              ? "bg-red-400 cursor-not-allowed"
              : "bg-red-600 hover:bg-red-700"
          }`}
        >
          {loading ? "Loading..." : isCheckout ? "Pay Tiket" : "Submit Tiket"}
        </button>
        <button
          type="button"
          className="w-full border border-red-600 text-red-600 py-2 rounded-md hover:bg-red-50"
          onClick={() => window.location.reload()}
        >
          Refresh Page
        </button>
      </div>
    </div>
  );
};

export default TicketSummary;
