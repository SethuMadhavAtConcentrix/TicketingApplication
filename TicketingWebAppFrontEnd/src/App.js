import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Home from "./pages/Home";
import Events from "./pages/Events";
import Login from "./pages/Login";
import Signup from "./pages/Signup";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import UserDashboard from "./pages/UserDashboard";
import PrivateRoute from "./components/PrivateRoute";
import BuyTicket from "./pages/BuyTicket";
import CreateEvent from "./pages/CreateEvent";
import UpdateEvent from "./pages/UpdateEvent";
import MyOrders from "./pages/MyOrders";
import MakePayment from "./pages/MakePayment";

function App() {
  return (
    <BrowserRouter>
      <ToastContainer position="top-right" />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/users/:userId/events" element={<Events />} />
        <Route path="/users/:userId/events/add" element={<CreateEvent />} />
        <Route
          path="/users/:userId/events/:eventId/update"
          element={<UpdateEvent />}
        />
        <Route
          path="/users/:userId/events/:eventId/order"
          element={<BuyTicket />}
        />
        <Route path="/users/:userId/myOrders" element={<MyOrders />} />
        <Route
          path="/users/:userId/orders/:orderId/payment"
          element={<MakePayment />}
        />

        <Route path="/user" element={<PrivateRoute />}>
          <Route path="dashboard" element={<UserDashboard />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
