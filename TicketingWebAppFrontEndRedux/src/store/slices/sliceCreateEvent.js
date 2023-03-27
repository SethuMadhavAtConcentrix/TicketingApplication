import { createSlice } from "@reduxjs/toolkit";
import { fetchCreateEvent } from "../thunks/fetchEvents";

const sliceCreateEvent = createSlice({
  name: "createEvent",
  initialState: {
    createEvent: [],
    isLoading: false,
    error: null,
  },
  extraReducers(builder) {
    builder.addCase(fetchCreateEvent.pending, (state, action) => {
      state.isLoading = true;
    });
    builder.addCase(fetchCreateEvent.fulfilled, (state, action) => {
      state.isLoading = false;
      state.createEvent = action.payload;
    });
    builder.addCase(fetchCreateEvent.rejected, (state, action) => {
      state.isLoading = false;
      state.error = action.error;
    });
  },
});
export const reducerCreateEvent = sliceCreateEvent.reducer;
