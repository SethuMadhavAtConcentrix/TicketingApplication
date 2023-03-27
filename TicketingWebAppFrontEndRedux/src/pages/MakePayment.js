import axios from "axios";
import React, { useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate, useParams } from "react-router-dom";
import { toast } from "react-toastify";
import Base from "../components/Base";
import { fetchPayments } from "../store/thunks/fetchOrders";

export default function MakePayment() {
  let navigate = useNavigate();
  const { userId, orderId } = useParams();
  const [makePayment, setMakePayment] = useState([]);
  const onInputChange = (e) => {
    setMakePayment({ ...makePayment, [e.target.name]: e.target.value });
  };

  const dispatch = useDispatch();
  const { isLoading, doPayment, error } = useSelector((state) => {
    return state.doPayment;
  });
  if (isLoading) {
    return <div>Loading...</div>;
  }
  if (error) {
    return <div>Error fetching data...</div>;
  }

  const onSubmit = async (e) => {
    e.preventDefault();
    dispatch(fetchPayments({ userId, orderId, makePayment }));
    // await axios.post(
    //   `http://localhost:8096/api/users/${userId}/orders/${orderId}/payment`,
    //   makePayment
    // );

    toast.success("Payment Done Successfully");
    navigate(`/users/${userId}/events`);
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
          <div className="card-head bg-info">
            <h1
              className="text-center"
              style={{ fontFamily: "lucida handwriting" }}
            >
              Make Payment
            </h1>
          </div>
          <div className="card-body text-center bg-secondary">
            <form onSubmit={(e) => onSubmit(e)}>
              <div className="mb-3">
                <label className="form-label">Card Number</label>
                <input
                  name="cardno"
                  onChange={(e) => onInputChange(e)}
                  type="number"
                  className="form-control text-center"
                  id="cardno"
                />
              </div>
              <div className="mb-3">
                <label className="form-label">Expiry Month</label>
                <input
                  name="expiryMonth"
                  onChange={(e) => onInputChange(e)}
                  type="number"
                  className="form-control text-center"
                  id="expiryMonth"
                />
              </div>
              <div className="mb-3">
                <label className="form-label">Expiry Year</label>
                <input
                  name="expiryYear"
                  onChange={(e) => onInputChange(e)}
                  type="number"
                  className="form-control text-center"
                  id="expiryYear"
                />
              </div>
              <div className="mb-3">
                <label className="form-label">Status</label>
                <input
                  name="status"
                  onChange={(e) => onInputChange(e)}
                  type="text"
                  className="form-control text-center"
                  id="status"
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
