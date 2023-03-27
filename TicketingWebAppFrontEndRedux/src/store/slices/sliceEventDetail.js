import { createSlice } from "@reduxjs/toolkit";
import { fetchEventDetail } from "../thunks/fetchEvents";

const sliceEventDetail = createSlice({
  name: "eventDetail",
  initialState: {
    eventDetail: [],
    isLoading: false,
    error: null,
  },
  extraReducers(builder) {
    builder.addCase(fetchEventDetail.pending, (state, action) => {
      state.isLoading = true;
    });
    builder.addCase(fetchEventDetail.fulfilled, (state, action) => {
      state.isLoading = false;
      state.eventDetail = action.payload;
    });
    builder.addCase(fetchEventDetail.rejected, (state, action) => {
      state.isLoading = false;
      state.error = action.error;
    });
  },
});
export const reducerEventDetail = sliceEventDetail.reducer;
