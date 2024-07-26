import billApi from "@/api/Bill";
import { defineStore } from "pinia";

export const useBillStore = defineStore("sizes", {
  state: () => ({
    bill: [],
  }),

  getters: {
    getBill: (state) => state.bill,
  },
  actions: {
    async fetchAllBill() {
      try {
        const { data } = await billApi.getAll();
        this.bill = data;
      } catch (error) {
        console.log("🚀 ~ fetchUserPreferences ~ error:", error);
      }
    },
    async fetchBillById(billId: number) {
      try {
        const { data } = await billApi.getById(billId);
        this.bill = data;
      } catch (error) {
        console.log("🚀 ~ fetchUserPreferences ~ error:", error);
      }
    },
  },
});
