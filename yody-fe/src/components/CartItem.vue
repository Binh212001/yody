<template lang="">
  <div class="grid grid-cols-10">
    <div class="col-span-1">
      <input type="checkbox" name="card" id="" class="size-6 rounded-lg" />
    </div>
    <div class="col-span-9 grid grid-cols-6">
      <div class="col-span-2">
        <img :src="cartEmpty" alt="Error" srcset="" />
      </div>
      <div class="col-span-4">
        <h3 class="font-bold">Quần Jeans Nữ Baggy Co Giãn Cạp Liền</h3>
        <h4 class="font-bold">399.000 đ</h4>
        <div class="flex justify-between items-center mt-9">
          <div
            class="p-2 bg-gray-200 rounded-md font-bold flex items-center gap-2 hover:shadow-md"
          >
            <span @click="props.showModal('111')"> Xanh nhat, LG </span>
            <Icon icon="mingcute:down-line" />
          </div>
          <div
            class="col-span-10 xl:col-span-2 xxl:col-span-2 flex border rounded-lg items-center"
          >
            <button class="btnMinus w-8 h-8 rounded-md">-</button>
            <span class="flex-1 text-center">1</span>
            <button class="btnPlus w-8 h-8 rounded-md">+</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script setup lang="ts">
import cartEmpty from "@/assets/img/cart-empty.svg";
import { Icon } from "@iconify/vue";
const props = defineProps({
  showModal: {
    type: Function,
    required: true,
  },
  handleOk: {
    type: Function,
    required: true,
  },
});
</script>
<style lang=""></style>
