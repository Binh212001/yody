import { defineStore } from "pinia";
import productApi from "@/api/Product";
import type { ProductForm } from "@/views/manage/Product.vue";

export const useProductStore = defineStore("products", {
  state: () => ({
    product: [],
  }),

  getters: {
    getSize: (state) => state.product,
  },
  actions: {
    async fetchAllProduct() {
      try {
        const { data } = await productApi.getAll();
        this.product = data;
      } catch (error) {
        console.log("🚀 ~ fetchUserPreferences ~ error:", error);
      }
    },
    async fetchProductByFilter(params: any) {
      try {
        const { data } = await productApi.filter(params);
        this.product = data;
      } catch (error) {
        console.log("🚀 ~ fetchUserPreferences ~ error:", error);
      }
    },
    async fetchAllProductByCate(cacategoryId: number) {
      try {
        const { data } = await productApi.getByCat(cacategoryId);
        console.log("🚀 ~ fetchAllProductByCate ~ data:", data);
        this.product = data;
      } catch (error) {
        console.log("🚀 ~ fetchUserPreferences ~ error:", error);
      }
    },
    async fetchProductsByPage(page: number) {
      try {
        const { data } = await productApi.loadPage(page);
        this.product.concat(data);
      } catch (error) {
        console.log("🚀 ~ fetchUserPreferences ~ error:", error);
      }
    },
    async createProduct(product: ProductForm) {
      try {
        const { data } = await productApi.create(product);
        this.product.concat(data);
        const data2 = await productApi.getAll();
        this.product = data2.data;
      } catch (error) {
        console.log("🚀 ~ fetchUserPreferences ~ error:", error);
      }
    },
  },
});
