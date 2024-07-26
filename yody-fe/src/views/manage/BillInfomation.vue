<template>
  <div class="p-6 max-w-4xl mx-auto bg-white rounded-xl shadow-md">
    <h2 class="text-xl font-semibold mb-4">Bill Details</h2>
    <!-- Bill Information -->
    <div class="mb-6">
      <h3 class="text-lg font-semibold">Customer Information</h3>
      <p><strong>ID:</strong> {{ bill?.id }}</p>
      <p><strong>Customer Name:</strong> {{ bill?.customerName }}</p>
      <p><strong>Address Detail:</strong> {{ bill?.addressDetail }}</p>
      <p><strong>Phone:</strong> {{ bill?.phone }}</p>
      <p><strong>Total Price:</strong> {{ formatPrice(bill?.totalPrice) }}</p>
      <p><strong>Created At:</strong> {{ formatDate(bill?.createdAt) }}</p>
      <p><strong>Updated At:</strong> {{ formatDate(bill?.updatedAt) }}</p>
    </div>

    <!-- Product List -->
    <h3 class="text-lg font-semibold mb-4">Products</h3>
    <div
      v-for="product in bill?.productBills"
      :key="product?.product?.id"
      class="mb-4 p-4 border border-gray-200 rounded-lg"
    >
      <div class="flex items-center mb-2">
        <img
          :src="getProductImage(product?.product.images)"
          alt="Product?.product Image"
          class="w-20 h-20 object-cover rounded-md mr-4"
        />
        <div>
          <h4 class="text-md font-semibold">{{ product?.product?.name }}</h4>
          <p>
            <strong>Description:</strong> {{ product?.product?.description }}
          </p>
          <p>
            <strong>Price:</strong> {{ formatPrice(product?.product?.price) }}
          </p>
          <p>
            <strong>Category:</strong> {{ product?.product?.categories.name }}
          </p>
          <p>
            <strong>Gender:</strong> {{ product?.product?.gender || "N/A" }}
          </p>

          <div class="mt-2">
            <strong>Colors:</strong>
            <div class="flex mt-1">
              <select>
                <option
                  v-for="(item, index) in product?.product.colors"
                  :value="item.id"
                  :selected="`${item.id == product?.product.colorId}`"
                >
                  {{ item.name }}
                </option>
              </select>
            </div>
          </div>

          <div class="mt-2">
            <strong>Sizes:</strong>
            <ul class="list-disc list-inside mt-1">
              <li v-for="size in product?.product.sizes" :key="size.id">
                {{ size.name }}
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, onMounted, ref } from "vue";
import { useRoute } from "vue-router";
import { useBillStore } from "@/stores/BillStore";
import billApi from "@/api/Bill";
import { URL_IMAGE } from "@/constant/constant";

export default defineComponent({
  name: "BillDetail",
  setup() {
    const route = useRoute();
    const billStore = useBillStore();
    const bill = ref<any>(null);
    console.log("🚀 ~ setup ~ bill:", bill);

    const fetchBill = async () => {
      const billId = parseInt(route.params.billId as string, 10);
      try {
        const { data } = await billApi.getById(billId);
        console.log("🚀 ~ fetchBill ~ data:", data);

        bill.value = data;
      } catch (error) {
        console.error("Error fetching bill:", error);
      }
    };

    onMounted(async () => {
      await fetchBill();
    });

    const formatPrice = (price: number) => {
      return (price / 100).toLocaleString("en-US", {
        style: "currency",
        currency: "USD",
      });
    };

    const formatDate = (date: string) => {
      return new Date(date).toLocaleString();
    };

    const getProductImage = (images: { imageUrl: string }[]) => {
      return images.length > 0 ? `${URL_IMAGE}${images[0].imageUrl}` : "";
    };

    return {
      bill,
      formatPrice,
      formatDate,
      getProductImage,
    };
  },
});
</script>

<style>
/* Add additional styles if needed */
</style>
