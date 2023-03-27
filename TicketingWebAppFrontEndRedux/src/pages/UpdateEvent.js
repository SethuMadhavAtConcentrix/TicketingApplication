import axios from "axios";
import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate, useParams } from "react-router-dom";
import { toast } from "react-toastify";
import Base from "../components/Base";
import { fetchEventDetail, fetchUpdateEvents } from "../store";

export default function UpdateEvent() {
  let navigate = useNavigate();
  const { userId, eventId } = useParams();
  const [updateEvent, setUpdateEvent] = useState([]);
  const onInputChange = (e) => {
    setUpdateEvent({ ...updateEvent, [e.target.name]: e.target.value });
  };

  const dispatch = useDispatch();
  const { isLoading, updateEvents, error } = useSelector((state) => {
    return state.updateEvents;
  });

  const onSubmit = async (e) => {
    e.preventDefault();
    dispatch(fetchUpdateEvents({ userId, eventId, updateEvent }));
    // await axios.put(
    //   `http://localhost:8096/api/users/${userId}/events/${eventId}/update`,
    //   updateEvent
    // );
    toast.success("Event has been Updated");
    navigate(`/users/${userId}/events`);
  };

  // const eventDetails = async () => {
  //   // const eventData = await axios.get(
  //   //   `http://localhost:8096/api/users/${userId}/events/${eventId}`
  //   // );
  //   setUpdateEvent(eventData.data);
  // };
  // useEffect(() => {
  //   eventDetails();
  //   //eslint-disable-next-line  react-hooks/exhaustive-deps
  // }, []);

  useEffect(() => {
    dispatch(fetchEventDetail({ userId, eventId })).then((response) => {
      console.log(response.payload);
      setUpdateEvent(response.payload);
    });
  }, []);

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
          <div className="card-head bg-danger">
            <h1
              className="text-center"
              style={{ fontFamily: "lucida handwriting" }}
            >
              Update Event
            </h1>
          </div>
          <div className="card-body text-center bg-secondary">
            <form onSubmit={(e) => onSubmit(e)}>
              <div className="mb-3">
                <label className="form-label">Event Title</label>
                <input
                  name="eventTitle"
                  value={updateEvent.eventTitle}
                  onChange={(e) => onInputChange(e)}
                  type="text"
                  className="form-control text-center"
                  // id="updateEventTitle"
                />
              </div>
              <div className="mb-3">
                <label className="form-label">Price</label>
                <input
                  name="price"
                  value={updateEvent.price}
                  onChange={(e) => onInputChange(e)}
                  type="text"
                  className="form-control text-center"
                  // id="updateEventPrice"
                />
              </div>
              <div className="mb-3">
                <label className="form-label">Quantity</label>
                <input
                  name="quantity"
                  onChange={(e) => onInputChange(e)}
                  value={updateEvent.quantity}
                  type="text"
                  className="form-control text-center"
                  // id="updateEventQuantity"
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
