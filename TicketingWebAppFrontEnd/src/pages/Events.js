import axios from "axios";
import React, { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import Base from "../components/Base";

const Events = () => {
  const { userId } = useParams();
  const [events, setEvents] = useState([]);
  const eventDetails = async () => {
    const eventData = await axios.get(
      `http://localhost:8096/api/users/${userId}/events`
    );
    setEvents(eventData.data);
  };
  useEffect(() => {
    eventDetails();
  }, []);
  return (
    <div className="card">
      <Base>
        <div className="card-head bg-primary">
          <h1 className="text-center">Events List</h1>
        </div>
        <div className="card-body">
          <table className="table table-primary table-hover table-bordered text-center">
            <thead>
              <tr>
                <td>Event Id</td>
                <td>Event Name</td>
                <td>Price</td>
                <td>Capacity</td>
                <td>Purchase</td>
                <td>Update</td>
              </tr>
            </thead>
            <tbody>
              {events.map((event) => (
                <tr key={event.eventId}>
                  <td>{event.eventId}</td>
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
