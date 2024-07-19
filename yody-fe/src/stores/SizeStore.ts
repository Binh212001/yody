import { defineStore } from "pinia";
import sizeApi from "@/api/Size";

export const useSizeStore = defineStore("sizes", {
  state: () => ({
    size: [],
  }),

  getters: {
    getSize: (state) => state.size,
  },
  actions: {
    async fetchAllsize() {
      try {
        const data = await sizeApi.getAll();
        this.size = data.data;
      } catch (error) {
        console.log("🚀 ~ fetchUserPreferences ~ error:", error);
      }
    },
  },
});
