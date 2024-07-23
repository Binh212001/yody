import httpRequest from "@/config/AxiosConfig";
const baseURL = "/api/colors";
interface Color {
  name: string;
  id?: Number;
  cssClass: string;
}

const colorApi = {
  getAll: async () => {
    return await httpRequest.get(baseURL);
  },
  create: async (data: Color) => {
    data.cssClass = `bg-${data.name.trim().toLowerCase()}-500`;
    return await httpRequest.post(baseURL, data);
  },
  update: async (id: number, data: Color) => {
    data.cssClass = `bg-${data.name}-500`;
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
