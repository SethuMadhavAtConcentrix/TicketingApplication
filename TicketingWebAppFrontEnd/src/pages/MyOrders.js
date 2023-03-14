import axios from "axios";
import React, { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import Base from "../components/Base";

export default function MyOrders() {
  const { userId } = useParams();
  const [orders, setOrders] = useState([]);
  const orderDetails = async () => {
    const orderData = await axios.post(
      `http://localhost:8096/api/users/${userId}/orders`
    );
    setOrders(orderData.data);
  };
  useEffect(() => {
    orderDetails();
  });
  return (
    <div>
      <Base>
        <div className="card">
          <div className="card-head  bg-warning">
            <h1 className="text-center">My Orders</h1>
          </div>
          <div className="card-body">
            <table className="table table-hover borderless text-center">
              <thead>
                <tr>
                  <td>
                    <b>Order Id</b>
                  </td>
                  <td>
                    <b>Concert Id</b>
                  </td>
                  <td>
                    <b>Concert Title</b>
                  </td>
                  <td>
                    <b>No of Tickets</b>
                  </td>
                  <td>
                    <b>Amount</b>
                  </td>
                  <td>
                    <b>Make Payment</b>
                  </td>
                </tr>
              </thead>
              <tbody>
                {orders.map((order) => (
                  <tr key={order.orderId}>
                    <td>{order.orderId}</td>
                    <td>{order.event.eventId}</td>
                    <td>{order.event.eventTitle}</td>
                    <td>{order.noOfTickets}</td>
                    <td>{order.amount}</td>
                    <td>
                      <Link
                        to={`/users/${userId}/orders/${order.orderId}/payment`}
                        type="button"
                        className="btn btn-outline-primary btn-sm"
                      >
                        Payment
                      </Link>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </div>
      </Base>
    </div>
  );
}
