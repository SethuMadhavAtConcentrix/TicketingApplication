import { createSlice } from "@reduxjs/toolkit";
import { fetchTickets } from "../thunks/fetchEvents";

const sliceTickets = createSlice({
  name: "tickets",
  initialState: {
    ticktes: [],
    isLoading: false,
    error: null,
  },
  extraReducers(builder) {
    builder.addCase(fetchTickets.pending, (state, action) => {
      state.isLoading = true;
    });
    builder.addCase(fetchTickets.fulfilled, (state, action) => {
      state.isLoading = false;
      state.events = action.payload;
    });
    builder.addCase(fetchTickets.rejected, (state, action) => {
      state.isLoading = false;
      state.error = action.error;
    });
  },
});
export const reducerTickets = sliceTickets.reducer;
