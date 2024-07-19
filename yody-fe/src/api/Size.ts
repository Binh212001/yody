import httpRequest from "@/config/AxiosConfig";
const baseURL = "/api/sizes";
interface Size {
  name: string;
  id?: Number;
}

const sizeApi = {
  getAll: async () => {
    return await httpRequest.get(baseURL);
  },
  create: async (data: Size) => {
    return await httpRequest.post(baseURL, data);
  },
  update: async (id: number, data: Size) => {
    return await httpRequest.put(`${baseURL}/${id}`, data);
  },
  delete: async (id: number) => {
    return await httpRequest.delete(`${baseURL}/${id}`);
  },
  getById: async (id: number) => {
    return await httpRequest.get(`${baseURL}/${id}`);
  },
};

export default sizeApi;