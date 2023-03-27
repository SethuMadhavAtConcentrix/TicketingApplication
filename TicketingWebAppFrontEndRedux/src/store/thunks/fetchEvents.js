import { createAsyncThunk } from "@reduxjs/toolkit";
import UpdateEvent from "../../pages/UpdateEvent";
import { myAxios } from "../../services/helper";

const fetchEvents = createAsyncThunk("events/fetch", async ({ userId }) => {
  const response = await myAxios.get(`/api/users/${userId}/events`);
  return response.data;
});
export { fetchEvents };

const fetchEventDetail = createAsyncThunk(
  "eventDetail/fetch",
  async ({ userId, eventId }) => {
    const response = await myAxios.get(
      `/api/users/${userId}/events/${eventId}`
    );
    return response.data;
  }
);
export { fetchEventDetail };

const fetchCreateEvent = createAsyncThunk(
  "createEvent/fetch",
  async ({ userId, addEvent }) => {
    const response = await myAxios.post(
      `api/users/${userId}/events/add`,
      addEvent
    );
    return response.data;
  }
);
export { fetchCreateEvent };

const fetchUpdateEvents = createAsyncThunk(
  "updateEvents/fetch",
  async ({ userId, eventId, updateEvent }) => {
    const response = await myAxios.put(
      `/api/users/${userId}/events/${eventId}/update`,
      updateEvent
    );
    return response.data;
  }
);
export { fetchUpdateEvents };

const fetchTickets = createAsyncThunk(
  "tickets/fetch",
  async ({ userId, eventId, placeOrder }) => {
    const response = await myAxios.post(
      `/api/users/${userId}/events/${eventId}/order`,
      placeOrder
    );
    return response.data;
  }
);
export { fetchTickets };
