import axios from "axios";
import React, { useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import Base from "../components/Base";

export default function BuyTicket() {
  let navigate = useNavigate();
  const { userId, eventId } = useParams();
  const [placeOrder, setPlaceOrder] = useState([]);
  const onInputChange = (e) => {
    setPlaceOrder({ ...placeOrder, [e.target.name]: e.target.value });
    console.log(setPlaceOrder);
  };
  const onSubmit = async (e) => {
    e.preventDefault();
    await axios.post(
      `http://localhost:8096/api/users/${userId}/events/${eventId}/order`,
      placeOrder
    );
    navigate(`/users/${userId}/myOrders`);
  };

  return (
    <div>
      <Base>
        <div
          className="card w-50"
          style={{
            margin: "auto",
            width: "60%",
            border: "3px solid blue",
            padding: "10px",
          }}
        >
          <div className="card-head bg-success">
            <h1 className="text-center">Order Tickets</h1>
          </div>
          <div className="card-body text-center bg-secondary">
            <form onSubmit={(e) => onSubmit(e)}>
              <div className="mb-3">
                <label className="form-label">No of Tickets</label>
                <input
                  name="noOfTickets"
                  onChange={(e) => onInputChange(e)}
                  type="text"
                  className="form-control text-center"
                  id="noOfTickets"
                />
              </div>
              <div className="mb-3">
                <label className="form-label">Amount</label>
                <input
                  name="amount"
                  onChange={(e) => onInputChange(e)}
                  type="number"
                  className="form-control text-center"
                  id="amount"
                />
              </div>
              <button type="submit" className="btn btn-success">
                Submit
              </button>
            </form>
          </div>
        </div>
      </Base>
    </div>
  );
}
