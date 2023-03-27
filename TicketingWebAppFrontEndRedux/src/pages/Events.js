//import axios from "axios";
//import { useState } from "react";
import React, { useEffect } from "react";
import { Link, useParams } from "react-router-dom";
import Base from "../components/Base";
import { useDispatch, useSelector } from "react-redux";
import { fetchEvents } from "../store";

const Events = () => {
  const { userId } = useParams();
  // const [events, setEvents] = useState([]);
  // const eventDetails = async () => {
  //   const eventData = await axios.get(
  //     `http://localhost:8096/api/users/${userId}/events`
  //   );
  //   setEvents(eventData.data);
  // };
  // useEffect(() => {
  //   eventDetails();
  // }, []);

  const dispatch = useDispatch();
  const { isLoading, events, error } = useSelector((state) => {
    return state.events;
  });
  useEffect(() => {
    dispatch(fetchEvents({ userId }));
  }, [dispatch]);

  if (isLoading) {
    return <div>Loading...</div>;
  }
  if (error) {
    return <div>Error fetching data...</div>;
  }

  return (
    <div className="card">
      <Base>
        <div className="card-head bg-primary">
          <h1
            className="text-center"
            style={{ fontFamily: "lucida handwriting" }}
          >
            Events List
          </h1>
        </div>
        <div className="card-body">
          <table className="table table-primary table-hover table-borderless text-center">
            <thead>
              <tr style={{ fontFamily: "cursive" }}>
                <td>
                  <b>Event Name</b>
                </td>
                <td>
                  <b>Price</b>
                </td>
                <td>
                  <b>Capacity</b>
                </td>
                <td>
                  <b>Purchase</b>
                </td>
                <td>
                  <b>Update</b>
                </td>
              </tr>
            </thead>
            <tbody>
              {events.map((event) => (
                <tr key={event.eventId}>
                  <td>{event.eventTitle}</td>
                  <td>{event.price}</td>
                  <td>{event.quantity}</td>
                  <td>
                    <Link
                      to={`/users/${userId}/events/${event.eventId}/order`}
                      type="button"
                      className="btn btn-primary btn-sm"
                    >
                      Buy Tickets
                    </Link>
                  </td>
                  <td>
                    <Link
                      type="button"
                      to={`/users/${userId}/events/${event.eventId}/update`}
                      className="btn btn-warning btn-sm"
                    >
                      Update
                    </Link>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </Base>
    </div>
  );
};
export default Events;
