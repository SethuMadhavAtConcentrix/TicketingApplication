import { useState } from "react";
import { toast } from "react-toastify";
import {
  Button,
  Card,
  CardBody,
  CardHeader,
  Col,
  Container,
  Form,
  FormGroup,
  Input,
  Label,
  Row,
} from "reactstrap";
import Base from "../components/Base";
import { loginUser } from "../services/userService";
import { doLogin } from "../auth";
import { useNavigate } from "react-router-dom";
const Login = () => {
  const navigate = useNavigate();

  const [loginDetail, setLoginDetail] = useState({
    username: "",
    password: "",
  });

  const handleChange = (event, field) => {
    let actualValue = event.target.value;
    setLoginDetail({
      ...loginDetail,
      [field]: actualValue,
    });
  };

  const handleReset = () => {
    setLoginDetail({
      username: "",
      password: "",
    });
  };

  const handleFormSubmit = (event) => {
    event.preventDefault();
    console.log(loginDetail);
    if (
      loginDetail.username.trim() === "" ||
      loginDetail.password.trim() === ""
    ) {
      toast.error("Details are required");
      return;
    }

    loginUser(loginDetail)
      .then((data) => {
        console.log("user login: ");
        console.log(data);
        doLogin(data, () => {
          console.log("data is saved to localstorage");
          console.log(data.data.id, data.data.username);
          const name = data.data.username;
          console.log(name);

          navigate(`/users/${data.data.id}/events`);
        });
        toast.success("Login Successful");
      })
      .catch((error) => {
        console.log(error);
        toast.error("Wrong Credentials");
      });
  };

  return (
    <Base>
      <Container>
        <Row className="mt-4">
          <Col sm={{ size: 6, offset: 3 }}>
            <Card color="dark" inverse>
              <CardHeader>
                <h3>Provide Details to Login</h3>
              </CardHeader>
              <CardBody>
                <Form onSubmit={handleFormSubmit}>
                  <FormGroup>
                    <Label for="username">Enter Username</Label>
                    <Input
                      type="text"
                      placeholder="Provide Username"
                      id="username"
                      value={loginDetail.username}
                      onChange={(e) => handleChange(e, "username")}
                    />
                  </FormGroup>
                  <FormGroup>
                    <Label for="password">Enter Password</Label>
                    <Input
                      type="password"
                      placeholder="Provide Password"
                      id="password"
                      value={loginDetail.password}
                      onChange={(e) => handleChange(e, "password")}
                    />
                  </FormGroup>
                  <Container className="text-center">
                    <Button outline color="success">
                      Login
                    </Button>
                    <Button
                      outline
                      color="danger"
                      type="reset"
                      className="ms-2"
                      onClick={handleReset}
                    >
                      Reset
                    </Button>
                  </Container>
                </Form>
              </CardBody>
            </Card>
          </Col>
        </Row>
      </Container>
    </Base>
  );
};
export default Login;
