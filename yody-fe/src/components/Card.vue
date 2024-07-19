<template>
  <div :class="['max-w-sm rounded overflow-hidden shadow-lg p-4']">
    <a href="">
      <img :src="cartEmpty" alt="Product Image" class="w-full" />
      <div class="py-4">
        <div class="font-bold text-xl mb-2">{{ displayName }}</div>
        <p class="text-base text-red-500 font-bold">{{ price }}vnd</p>
      </div>
      <div class="pt-4 pb-2">
        <span
          v-for="color in props.colors"
          :class="['inline-block w-6 h-6 mr-2 rounded-full', colorClass(color)]"
        >
        </span>
      </div>
    </a>
  </div>
</template>

<script setup lang="ts">
import { computed } from "vue";
import cartEmpty from "@/assets/img/tsn6176-tr1-7.jpg";

const props = defineProps({
  image: {
    type: String,
    required: true,
  },
  displayName: {
    type: String,
    required: true,
  },
  price: {
    type: Number,
    required: true,
  },
  colors: {
    type: [String],
    required: true,
  },
});

const colorClass = (color: string) =>
  computed(() => {
    return `bg-${color}-500`;
  });
</script>

<style></style>
