import { NavLink as ReactLink, useNavigate, useParams } from "react-router-dom";
import React, { useEffect, useState } from "react";
import {
  Collapse,
  Navbar,
  NavbarToggler,
  NavbarBrand,
  Nav,
  NavItem,
  NavLink,
} from "reactstrap";
import { doLogout, getCurrentUserDetail, isLoggedIn } from "../auth";

const CustomNavbar = () => {
  const { userId } = useParams();
  let navigate = useNavigate();
  const [isOpen, setIsOpen] = useState(false);

  const [login, setLogin] = useState(false);
  const [user, setUser] = useState(undefined);

  const logout = () => {
    doLogout(() => {
      setLogin(false);
      navigate("/");
    });
  };

  useEffect(() => {
    setLogin(isLoggedIn());
    setUser(getCurrentUserDetail());
  }, []);

  const toggle = () => setIsOpen(!isOpen);

  return (
    <div>
      <Navbar color="white" expand="md" className="px-5">
        <NavbarBrand
          className="w3-bar-item w3-button"
          tag={ReactLink}
          style={{
            fontFamily: "lucida handwriting",
            textShadow: "2px 2px 5px violet",
          }}
        >
          Ticketing App
        </NavbarBrand>
        <NavbarToggler onClick={toggle} />
        <Collapse isOpen={isOpen} navbar>
          <Nav className="me-auto" navbar>
            <NavItem>
              <NavLink tag={ReactLink} to="/">
                Home
              </NavLink>
            </NavItem>
          </Nav>
          <Nav navbar>
            {login && (
              <>
                <NavItem>
                  <NavLink tag={ReactLink} to={`/users/${userId}/events/add`}>
                    Create Event
                  </NavLink>
                </NavItem>
                <NavItem>
                  <NavLink tag={ReactLink} to={`/users/${userId}/events`}>
                    Events
                  </NavLink>
                </NavItem>
                <NavItem>
                  <NavLink tag={ReactLink} to={`/users/${userId}/myOrders`}>
                    My Orders
                  </NavLink>
                </NavItem>

                {/* <NavItem>
                  <NavLink tag={ReactLink}>Id: {userId}</NavLink>
                </NavItem> */}
                <NavItem>
                  <NavLink tag={ReactLink} onClick={logout} to="/">
                    LogOut
                  </NavLink>
                </NavItem>
              </>
            )}
            {!login && (
              <>
                <NavItem>
                  <NavLink tag={ReactLink} to="/login">
                    LogIn
                  </NavLink>
                </NavItem>
                <NavItem>
                  <NavLink tag={ReactLink} to="/signup">
                    SignUp
                  </NavLink>
                </NavItem>
              </>
            )}
          </Nav>
        </Collapse>
      </Navbar>
    </div>
  );
};

export default CustomNavbar;
