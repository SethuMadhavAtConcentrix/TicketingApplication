import axios from "axios";
import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate, useParams } from "react-router-dom";
import { toast } from "react-toastify";
import Base from "../components/Base";
import { fetchTickets } from "../store";

export default function BuyTicket() {
  let navigate = useNavigate();
  const { userId, eventId } = useParams();
  //console.log(userId, eventId);
  const [placeOrder, setPlaceOrder] = useState([]);

  const onInputChange = (e) => {
    setPlaceOrder({ ...placeOrder, [e.target.name]: e.target.value });
  };
  const dispatch = useDispatch();
  const { isLoading, tickets, error } = useSelector((state) => {
    return state.tickets;
  });
  // if (isLoading) {
  //   return <div>Loading...</div>;
  // }
  // if (error) {
  //   return <div>Error fetching data...</div>;
  // }

  // useEffect(() => {
  //   }, [dispatch]);

  const onSubmit = async (e) => {
    e.preventDefault();
    dispatch(fetchTickets({ userId, eventId, placeOrder }));

    // await axios.post(
    //   `http://localhost:8096/api/users/${userId}/events/${eventId}/order`,
    //   placeOrder
    // );
    toast.success("Order has been Placed");
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
            <h1
              className="text-center"
              style={{ fontFamily: "lucida handwriting" }}
            >
              Order Tickets
            </h1>
          </div>
          <div className="card-body text-center bg-white">
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
