import httpRequest from "@/config/AxiosConfig";
import { createSlug } from "@/utils/helper";
const baseURL = "/api/categories";
export interface Category {
  name: string;
  id?: Number;
  slug?: string;
}

const categoryApi = {
  getAll: async () => {
    return await httpRequest.get(baseURL);
  },
  create: async (data: Category) => {
    data["slug"] = createSlug(data.name);
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
