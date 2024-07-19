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
        <Card />
        <Card />
        <Card />
        <Card />
        <Card />
      </div>
    </main>
  </div>
</template>
<script lang="ts">
import { defineComponent, ref } from "vue";
import Filter from "@/components/Filter.vue";
import Card from "@/components/Card.vue";

export default defineComponent({
  name: "MyComponent",
  components: {
    Filter,
    Card,
  },
  setup() {
    const short = ref("0");

    return {
      short,
    };
  },
});
</script>
<style></style>
