<template lang="">
  <div>
    <h5>Tab</h5>
    <div class="flex justify-between items-center">
      <h3 class="text-xl font-bold">Category</h3>
      <a-select v-model:value="short">
        <a-select-option value="0">Sắp xếp theo</a-select-option>
        <a-select-option value="asc">ASC</a-select-option>
        <a-select-option value="dasc">DASC</a-select-option>
      </a-select>
    </div>
    <p class="text-gray-500">332 sản phẩm</p>
    <main class="grid xl:grid-cols-5 md:grid-cols-3 sm:grid-cols-1 grid-cols-1">
      <div class="xl:col-span-1 sm:hidden md:hidden hidden xl:block">
        <Filter />
      </div>
      <div
        class="xl:col-span-4 grid xl:grid-cols-4 md:col-span-3 md:grid-cols-3 sm:grid-cols-1"
      >
        <Card
          v-for="(item, index) in productStore.product"
          :item="item"
          :key="index"
        />
      </div>
    </main>
  </div>
</template>
<script lang="ts">
import { defineComponent, onMounted, ref } from "vue";
import Filter from "@/components/Filter.vue";
import Card from "@/components/Card.vue";
import { useSizeStore } from "@/stores/SizeStore";
import { useColorStore } from "@/stores/ColorStore";
import { useProductStore } from "@/stores/ProductStore";
import { useRoute } from "vue-router";
export default defineComponent({
  name: "MyComponent",
  components: {
    Filter,
    Card,
  },
  setup() {
    const router = useRoute();
    let { categoryId } = router.params;
    const short = ref("0");
    const sizeStore = useSizeStore();
    const colorStore = useColorStore();
    const productStore = useProductStore();
    onMounted(() => {
      sizeStore.fetchAllsize().catch((error) => {
        console.error("Error fetching sizes on mount:", error);
      });
      colorStore.fetchAllColor().catch((error) => {
        console.error("Error fetching sizes on mount:", error);
      });
      productStore.fetchAllProductByCate(Number(categoryId)).catch((error) => {
        console.error("Error fetching sizes on mount:", error);
      });
    });
    return {
      short,
      sizeStore,
      colorStore,
      productStore,
    };
  },
});
</script>
<style></style>
