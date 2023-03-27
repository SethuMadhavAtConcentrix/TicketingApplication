import { createSlice } from "@reduxjs/toolkit";
import { fetchEvents } from "../thunks/fetchEvents";

const sliceEvents = createSlice({
  name: "events",
  initialState: {
    events: [],
    isLoading: false,
    error: null,
  },
  extraReducers(builder) {
    builder.addCase(fetchEvents.pending, (state, action) => {
      state.isLoading = true;
    });
    builder.addCase(fetchEvents.fulfilled, (state, action) => {
      state.isLoading = false;
      state.events = action.payload;
    });
    builder.addCase(fetchEvents.rejected, (state, action) => {
      state.isLoading = false;
      state.error = action.error;
    });
  },
});
export const reducerEvents = sliceEvents.reducer;
