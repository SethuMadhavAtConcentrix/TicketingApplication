import { myAxios } from "./helper";

export const signUp = async ({ username, email, password, role }) => {
  const response = await myAxios.post("/api/auth/signup", {
    username,
    email,
    password,
    role,
  });
  return response.data;
};

export const loginUser = async (loginDetail) => {
  return myAxios.post("/api/auth/login", loginDetail);
};
