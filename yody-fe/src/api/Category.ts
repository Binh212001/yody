import httpRequest from "@/config/AxiosConfig";
const baseURL = "/api/categories";
interface Category {
  name: string;
  id?: Number;
}

const categoryApi = {
  getAll: async () => {
    return await httpRequest.get(baseURL);
  },
  create: async (data: Category) => {
    return await httpRequest.post(baseURL, data);
  },
  update: async (id: number, data: Category) => {
    return await httpRequest.put(`${baseURL}/${id}`, data);
  },
  delete: async (id: number) => {
    return await httpRequest.delete(`${baseURL}/${id}`);
  },
  getById: async (id: number) => {
    return await httpRequest.get(`${baseURL}/${id}`);
  },
};

export default categoryApi;
