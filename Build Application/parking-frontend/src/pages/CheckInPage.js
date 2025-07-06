import React, { useState } from "react";
import Header from "../components/Header";
import CameraSection from "../components/CameraSection";
import VehicleForm from "../components/VehicleForm";
import TicketSummary from "../components/TicketSummary";
import Footer from "../components/Footer";

const CheckInPage = () => {
  const [formData, setFormData] = useState({
    plateNumber: "",
    vehicleTypeId: "",
    parkingSlipId: "",
    paymentMethodId: "",
    durationInSeconds: 0,
    price: 0,
    discount: 0,
    finalPrice: 0,
    voucherCode: "",
  });

  return (
    <div className="flex flex-col min-h-screen">
      <Header />
      <main className="flex-grow grid grid-cols-1 md:grid-cols-3 gap-10 px-20 pt-6 pb-20">
        <div className="md:col-span-2 space-y-6">
          <CameraSection mode="checkin" />
          <VehicleForm
            type="checkin"
            formData={formData}
            setFormData={setFormData}
          />
        </div>
        <TicketSummary type="checkin" data={formData} />
      </main>
      <Footer />
    </div>
  );
};

export default CheckInPage;
