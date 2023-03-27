import { configureStore } from "@reduxjs/toolkit";
import { reducerEvents } from "./slices/sliceEvents";
import { reducerTickets } from "./slices/sliceTickets";
import { reducerOrders } from "./slices/sliceOrders";
import { reducerUpdateEvents } from "./slices/sliceUpdateEvents";
import { reducerCreateEvent } from "./slices/sliceCreateEvent";
import { reducerEventDetail } from "./slices/sliceEventDetail";
import { reducerMakePayment } from "./slices/sliceMakePayment";
export const store = configureStore({
  reducer: {
    events: reducerEvents,
    tickets: reducerTickets,
    updateEvents: reducerUpdateEvents,
    orders: reducerOrders,
    createEvent: reducerCreateEvent,
    eventDetail: reducerEventDetail,
    doPayment: reducerMakePayment,
  },
});
export * from "./thunks/fetchEvents";
