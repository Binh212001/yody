import { defineStore } from "pinia";
import httpRequest from "../config/AxiosConfig";

export const useEmployeeStore = defineStore("employees", {
  state: () => ({
    employee: [],
    // ...
  }),

  getters: {
    getEmployee: (state) => state.employee,
  },
  actions: {
    async fetchAllEmployee() {
      try {
        const data = await httpRequest.get("/employee");
        console.log(data.data);

        this.employee = data.data;
      } catch (error) {
        console.log("🚀 ~ fetchUserPreferences ~ error:", error);
      }
    },
  },
});
