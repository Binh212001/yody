<template>
  <div :class="['max-w-sm rounded overflow-hidden shadow-lg p-4']">
    <RouterLink :to="`/product/${props.item.id}`">
      <img
        :src="`${URL_IMAGE}${props.item.images[0].imageUrl}`"
        alt="Product Image"
        class="w-full"
      />
      <div class="py-4">
        <div class="font-bold text-xl mb-2">{{ props.item.name }}</div>
        <p class="text-base text-red-500 font-bold">{{ props.item.price }}d</p>
      </div>
      <div class="pt-4 pb-2">
        <span
          v-for="color in props.item.colors"
          :class="['inline-block w-6 h-6 mr-2 rounded-full', colorClass(color)]"
        >
        </span>
      </div>
    </RouterLink>
  </div>
</template>

<script setup lang="ts">
import { computed } from "vue";
import cartEmpty from "@/assets/img/tsn6176-tr1-7.jpg";
import { RouterLink } from "vue-router";
import { URL_IMAGE } from "@/constant/constant";
interface Image {
  id: number;
  imageUrl: string;
}

interface Size {
  id: number;
  name: string;
}

interface Product {
  id: number;
  name: string;
  description: string;
  price: number;
  categories: any; // Replace 'any' with the actual type if available
  images: Image[];
  colors: any[]; // Replace 'any' with the actual type if available
  sizes: Size[];
  collection: any; // Replace 'any' with the actual type if available
}

const props = defineProps<{
  item: Product;
}>();

console.log(props.item);

const colorClass = (color: string) =>
  computed(() => {
    return `bg-${color}-500`;
  });
</script>

<style></style>
