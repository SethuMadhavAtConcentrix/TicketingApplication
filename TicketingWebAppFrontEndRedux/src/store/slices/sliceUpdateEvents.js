import { createSlice } from "@reduxjs/toolkit";
import { fetchUpdateEvents } from "../thunks/fetchEvents";

const sliceUpdateEvents = createSlice({
  name: "updateEvents",
  initialState: {
    updateEvents: [],
    isLoading: false,
    error: null,
  },
  extraReducers(builder) {
    builder.addCase(fetchUpdateEvents.pending, (state, action) => {
      state.isLoading = true;
    });
    builder.addCase(fetchUpdateEvents.fulfilled, (state, action) => {
      state.isLoading = false;
      state.updateEvents = action.payload;
    });
    builder.addCase(fetchUpdateEvents.rejected, (state, action) => {
      state.isLoading = false;
      state.error = action.error;
    });
  },
});
export const reducerUpdateEvents = sliceUpdateEvents.reducer;
