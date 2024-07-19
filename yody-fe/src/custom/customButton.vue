<template>
  <button
    :class="[
      'px-4 py-2 rounded',
      buttonColor,
      props.width,
      props.border,
      { 'cursor-not-allowed opacity-50': props.disabled },
    ]"
    :disabled="disabled"
  >
    {{ text }}
  </button>
</template>

<script setup lang="ts">
import { computed } from "vue";

const props = defineProps({
  color: {
    type: String,
    default: "gray",
  },
  text: {
    type: String,
    required: true,
  },
  width: {
    type: String,
  },
  border: {
    type: String,
  },
  disabled: {
    type: Boolean,
    default: false,
  },
});

const buttonColor = computed(() => {
  return (
    {
      gray: "bg-[#fafafa] hover:bg-[#fafafa] text-[#949494]",
      blue: "bg-[#007bff] hover:bg-[#0069d9] text-white",
      yellow: "bg-yellow-500 hover:bg-yellow-700 text-white",
    }[props.color] || "bg-[#fafafa] hover:bg-[#fafafa] text-[#949494]"
  );
});
</script>

<style scoped>
/* You can add additional custom styles here if needed */
</style>
