import httpRequest from "@/config/AxiosConfig";
import { createSlug } from "@/utils/helper";
import type { ProductForm } from "@/views/manage/Product.vue";
const baseURL = "/api/products";
export interface Product {
  name: string;
  id?: Number;
  slug?: string;
}

const productApi = {
  getAll: async () => {
    return await httpRequest.get(baseURL);
  },
  loadPage: async (page: number) => {
    return await httpRequest.get(baseURL + "?page=" + page);
  },
  create: async (data: any) => {
    console.log(data);

    const formData = new FormData();
    formData.append("name", data.name);
    formData.append("description", data.description);
    formData.append("price", data.price);
    formData.append("category", data.category);
    formData.append("colors", data.colors);
    formData.append("sizes", data.sizes);
    data.images.forEach((file: any) => {
      formData.append("images", file);
    });
    return await httpRequest.post(baseURL, formData, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    });
  },
  update: async (id: number, data: Product) => {
    return await httpRequest.put(`${baseURL}/${id}`, data);
  },
  delete: async (id: number) => {
    return await httpRequest.delete(`${baseURL}/${id}`);
  },
  getById: async (id: number) => {
    return await httpRequest.get(`${baseURL}/${id}`);
  },
};

export default productApi;
