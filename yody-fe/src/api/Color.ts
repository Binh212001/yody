import httpRequest from "@/config/AxiosConfig";
const baseURL = "/api/colors";
interface Color {
  name: string;
  id?: Number;
}

const colorApi = {
  getAll: async () => {
    return await httpRequest.get(baseURL);
  },
  create: async (data: Color) => {
    return await httpRequest.post(baseURL, data);
  },
  update: async (id: number, data: Color) => {
    return await httpRequest.put(`${baseURL}/${id}`, data);
  },
  delete: async (id: number) => {
    return await httpRequest.delete(`${baseURL}/${id}`);
  },
  getById: async (id: number) => {
    return await httpRequest.get(`${baseURL}/${id}`);
  },
};

export default colorApi;
