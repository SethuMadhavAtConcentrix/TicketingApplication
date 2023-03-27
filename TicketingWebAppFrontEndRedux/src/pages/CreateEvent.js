import axios from "axios";
import React, { useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate, useParams } from "react-router-dom";
import { toast } from "react-toastify";
import Base from "../components/Base";
import { fetchCreateEvent } from "../store";

export default function CreateEvent() {
  let navigate = useNavigate();
  const { userId } = useParams();
  const [addEvent, setAddEvent] = useState([]);
  const onInputChange = (e) => {
    setAddEvent({ ...addEvent, [e.target.name]: e.target.value });
  };
  const dispatch = useDispatch();
  const { isLoading, createEvent, error } = useSelector((state) => {
    return state.createEvent;
  });
  if (isLoading) {
    return <div>Loading...</div>;
  }
  if (error) {
    return <div>Error fetching data...</div>;
  }
  const onSubmit = async (e) => {
    e.preventDefault();
    dispatch(fetchCreateEvent({ userId, addEvent }));

    // await axios.post(
    //   `http://localhost:8096/api/users/${userId}/events/add`,
    //   addEvent
    // );

    toast.success("Event has been Created");
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
            border: "3px solid black",
            padding: "10px",
          }}
        >
          <div className="card-head bg-success">
            <h1
              className="text-center"
              style={{ fontFamily: "lucida handwriting" }}
            >
              Add Event
            </h1>
          </div>
          <div className="card-body text-center bg-white">
            <form onSubmit={(e) => onSubmit(e)}>
              <div className="mb-3">
                <label className="form-label">Event Title</label>
                <input
                  name="eventTitle"
                  onChange={(e) => onInputChange(e)}
                  type="text"
                  className="form-control text-center"
                  id="addEventTitle"
                />
              </div>
              <div className="mb-3">
                <label className="form-label">Price</label>
                <input
                  name="price"
                  onChange={(e) => onInputChange(e)}
                  type="text"
                  className="form-control text-center"
                  id="addEventPrice"
                />
              </div>
              <div className="mb-3">
                <label className="form-label">Quantity</label>
                <input
                  name="quantity"
                  onChange={(e) => onInputChange(e)}
                  type="text"
                  className="form-control text-center"
                  id="addEventQuantity"
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
