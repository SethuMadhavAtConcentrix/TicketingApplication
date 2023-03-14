import { myAxios } from "./helper";

export const events = async () => {
  const response = await myAxios.get("/api/events");
  return response.data;
};
