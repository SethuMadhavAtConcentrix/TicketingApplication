import { createAsyncThunk } from "@reduxjs/toolkit";
import { myAxios } from "../../services/helper";

const fetchOrders = createAsyncThunk("orders/fetch", async ({ userId }) => {
  const response = await myAxios.post(`/api/users/${userId}/orders`);
  return response.data;
});
export { fetchOrders };

const fetchPayments = createAsyncThunk(
  "payments/fetch",
  async ({ userId, orderId, makePayment }) => {
    const response = await myAxios.post(
      `api/users/${userId}/orders/${orderId}/payment`,
      makePayment
    );
    return response.data;
  }
);
export { fetchPayments };
