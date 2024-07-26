<template>
  <div class="p-6 max-w-4xl mx-auto bg-white rounded-xl shadow-md">
    <h2 class="text-xl font-semibold mb-4">Customer Information</h2>
    <table class="min-w-full divide-y divide-gray-200">
      <thead class="bg-gray-50">
        <tr>
          <th
            class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider"
          >
            ID
          </th>
          <th
            class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider"
          >
            Customer Name
          </th>
          <th
            class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider"
          >
            Address Detail
          </th>
          <th
            class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider"
          >
            Phone
          </th>
          <th
            class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider"
          >
            Total Price
          </th>
          <th
            class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider"
          >
            Created At
          </th>
        </tr>
      </thead>
      <tbody class="bg-white divide-y divide-gray-200">
        <tr
          v-for="data in billStore.bill"
          :key="data.id"
          @click="redirectToBill(data.id)"
          class="cursor-pointer hover:bg-gray-100"
        >
          <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
            {{ data.id }}
          </td>
          <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
            {{ data.customerName }}
          </td>
          <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
            {{ data.addressDetail }}
          </td>
          <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
            {{ data.phone }}
          </td>
          <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
            {{ formatPrice(data.totalPrice) }}
          </td>
          <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
            {{ formatDate(data.createdAt) }}
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script lang="ts">
import { defineComponent, onMounted } from "vue";
import { useBillStore } from "@/stores/BillStore";
import { useRouter } from "vue-router";

export default defineComponent({
  name: "bill",
  setup() {
    const router = useRouter();
    const billStore = useBillStore();

    const redirectToBill = (billId: number) => {
      router.push(`/management/bill/${billId}`);
    };

    const formatPrice = (price: number) => {
      return (price / 100).toLocaleString("en-US", {
        style: "currency",
        currency: "USD",
      });
    };

    const formatDate = (date: string) => {
      return new Date(date).toLocaleString();
    };

    onMounted(() => {
      billStore.fetchAllBill().catch((error) => {
        console.error("Error fetching bills on mount:", error);
      });
    });

    return {
      billStore,
      formatPrice,
      formatDate,
      redirectToBill,
    };
  },
});
</script>
