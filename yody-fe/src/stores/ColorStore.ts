import { defineStore } from "pinia";
import colorApi from "@/api/Color";

export const useColorStore = defineStore("colors", {
  state: () => ({
    color: [],
  }),
  actions: {
    async fetchAllColor() {
      try {
        const { data } = await colorApi.getAll();
        console.log(data);

        this.color = data;
      } catch (error) {
        console.log("🚀 ~ fetchUserPreferences ~ error:", error);
      }
    },
  },
});
