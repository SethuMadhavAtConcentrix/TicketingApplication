import { myAxios } from "./helper";

export const getEvents = async ({ userId }) => {
  const response = await myAxios.get(`/api/users/${userId}/events`);
  return response.data;
};
