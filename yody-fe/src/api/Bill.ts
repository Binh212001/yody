import httpRequest from "@/config/AxiosConfig";
const baseURL = "/api/bills";

const billApi = {
  getAll: async () => {
    return await httpRequest.get(baseURL);
  },
  create: async (data: any) => {
    return await httpRequest.post(baseURL, data);
  },
  update: async (id: number, data: any) => {
    return await httpRequest.put(`${baseURL}/${id}`, data);
  },
  delete: async (id: number) => {
    return await httpRequest.delete(`${baseURL}/${id}`);
  },
  getById: async (id: number) => {
    return await httpRequest.get(`${baseURL}/${id}`);
  },
};

export default billApi;
