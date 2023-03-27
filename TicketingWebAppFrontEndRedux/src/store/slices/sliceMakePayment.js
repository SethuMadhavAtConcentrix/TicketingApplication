import { createSlice } from "@reduxjs/toolkit";
import { fetchPayments } from "../thunks/fetchOrders";

const sliceMakePayment = createSlice({
  name: "doPayment",
  initialState: {
    doPayment: [],
    isLoading: false,
    error: null,
  },
  extraReducers(builder) {
    builder.addCase(fetchPayments.pending, (state, action) => {
      state.isLoading = true;
    });
    builder.addCase(fetchPayments.fulfilled, (state, action) => {
      state.isLoading = false;
      state.doPayment = action.payload;
    });
    builder.addCase(fetchPayments.rejected, (state, action) => {
      state.isLoading = false;
      state.error = action.error;
    });
  },
});
export const reducerMakePayment = sliceMakePayment.reducer;
