import { useEffect, useState } from "react";
import { signUp } from "../services/userService";
import { toast } from "react-toastify";
import {
  Card,
  Form,
  CardBody,
  CardHeader,
  Container,
  FormGroup,
  Input,
  Label,
  Button,
  Row,
  Col,
} from "reactstrap";
import Base from "../components/Base";
import { useNavigate } from "react-router-dom";

const Signup = () => {
  const [data, setData] = useState({
    username: "",
    email: "",
    password: "",
    role: ["USER"],
  });

  const [error, setError] = useState({
    errors: {},
    isError: false,
  });

  const handleChange = (event, property) => {
    setData({ ...data, [property]: event.target.value });
  };

  const resetData = () => {
    setData({
      username: "",
      email: "",
      password: "",
      role: ["USER"],
    });
  };

  useEffect(() => {}, []);

  const navigate = useNavigate();

  const submitForm = (event) => {
    event.preventDefault();
    if (error.isError) {
      toast.error("Username must be unique of 5 - 10 Characters");
      toast.error("Email must be unique of 20 Characters or below");
      toast.error("Password of 8 - 15 Characters");
      return;
    }
    signUp({
      username: data.username,
      email: data.email,
      password: data.password,
      role: data.role,
    })
      .then((resp) => {
        console.log("success log");
        navigate("/login");
        toast.success("User Registered Successfully");
        setData({
          username: "",
          email: "",
          password: "",
          role: ["USER"],
        });
      })
      .catch((error) => {
        console.log(error);
        console.log("Error log");
        toast.error("Username must be unique of 5 - 10 Characters");
        toast.error("Email must be unique of 20 Characters or below");
        toast.error("Password of 8 - 15 Characters");
        setError({
          errors: error,
          isError: true,
        });
      });
  };

  return (
    <Base>
      <Container>
        <Row className="mt-4">
          <Col sm={{ size: 6, offset: 3 }}>
            <Card color="dark" inverse>
              <CardHeader
                className="text-center"
                style={{ fontFamily: "lucida handwriting" }}
              >
                <h3>Provide Details to Register</h3>
              </CardHeader>
              <CardBody>
                <Form onSubmit={submitForm}>
                  <FormGroup>
                    <Label for="username">Enter Username</Label>
                    <Input
                      type="text"
                      placeholder="Provide Username"
                      id="username"
                      onChange={(e) => handleChange(e, "username")}
                      value={data.username}
                    />
                  </FormGroup>

                  <FormGroup>
                    <Label for="email">Enter Email</Label>
                    <Input
                      type="email"
                      placeholder="Provide Email"
                      id="email"
                      onChange={(e) => handleChange(e, "email")}
                      value={data.email}
                    />
                  </FormGroup>

                  <FormGroup>
                    <Label for="password">Enter Password</Label>
                    <Input
                      type="password"
                      placeholder="Provide Password"
                      id="password"
                      onChange={(e) => handleChange(e, "password")}
                      value={data.password}
                    />
                  </FormGroup>

                  <Container className="text-center">
                    <Button outline color="success">
                      Register
                    </Button>
                    <Button
                      outline
                      color="danger"
                      type="reset"
                      className="ms-2"
                      onClick={resetData}
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
export default Signup;
