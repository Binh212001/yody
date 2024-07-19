<script lang="ts">
import { defineComponent, reactive, ref } from "vue";
import cartEmpty from "@/assets/img/cart-empty.svg";
import CardItem from "@/components/CartItem.vue";
import momo from "@/assets/img/momo.webp";
import visacard from "@/assets/img/visa-card.webp";
import vnpay from "@/assets/img/vnpay-qr.webp";
import zalopay from "@/assets/img/zalopay.webp";
import ProductModal from "@/components/ProductModal.vue";

export default defineComponent({
  name: "MyComponent",
  components: {
    CardItem,
    ProductModal,
  },
  setup() {
    const open = ref<boolean>(false);
    const itemModalShow = reactive({});

    const showModal = (cartId: string) => {
      open.value = true;
    };

    const handleCancel = () => {
      console.log("Modal Cancel button clicked");
      open.value = false;
    };

    const handleOk = (e: MouseEvent) => {
      console.log("Ok button clicked");
      open.value = false;
    };

    return {
      open,
      itemModalShow,
      showModal,
      handleCancel,
      handleOk,
      cartEmpty,
      momo,
      visacard,
      vnpay,
      zalopay,
    };
  },
});
</script>

<template lang="">
  <h3 class="font-bold text-3xl text-gray-800 text-center mt-7">Giỏ hàng</h3>
  <div>
    <div class="card-box-empty mt-7 text-center">
      <p class="text-center text-gray-500">Không có sản phẩm trong giỏ hàng.</p>
      <div class="flex justify-center mt-5">
        <img :src="cartEmpty" alt="" srcset="" />
      </div>
      <button
        class="bg-yellow-400 text-white font-bold rounded-lg hover:shadow-lg w-[300px] p-2"
      >
        Mua sắm ngay
      </button>
    </div>

    <div
      class="grid xl:grid-cols-3 xxl:grid-cols-3 md:grid-cols-3 grid-cols-1 gap-5 rounded-sm p-4 shadow-md"
    >
      <div
        class="card-box-empty mt-7 md:col-span-2 xl:col-span-2 bg-slate-50 p-4"
      >
        <div class="flex gap-5 border-b-2 py-3">
          <input
            type="checkbox"
            name="selectAll"
            id=""
            class="size-6 rounded-lg"
          />
          <h4 class="font-bold">Sản phẩm nguyên giá</h4>
        </div>
        <div class="py-3">
          <CardItem :showModal="showModal" :handleOk="handleOk" />
          <CardItem :showModal="showModal" :handleOk="handleOk" />
          <CardItem :showModal="showModal" :handleOk="handleOk" />
        </div>
      </div>

      <div
        class="payment card-box-empty mt-7 md:col-span-1 xl:col-span-1 bg-slate-50 p-4"
      >
        <h3 class="font-bold text-xl mb-2">Chi tiết đơn hàng</h3>
        <div class="border-b">
          <div class="flex justify-between items-end mb-2">
            <p class="text-gray-600">Tổng giá trị sản phẩm</p>
            <p class="text-gray-400">798.000 đ</p>
          </div>
          <div class="flex justify-between items-end mb-2">
            <p class="text-gray-600">Giảm giá</p>
            <p class="text-red-500">798.000 đ</p>
          </div>
          <div class="flex justify-between items-end mb-2">
            <p class="text-gray-600">Vận chuyển</p>
            <p class="text-gray-400">20.000 đ</p>
          </div>
          <div class="flex justify-between items-end mb-2">
            <p class="text-gray-600">Giảm giá vận chuyển</p>
            <p class="text-red-500">20.000 đ</p>
          </div>
        </div>
        <div class="flex justify-between items-end mb-2">
          <p class="text-gray-600 text-2xl">Tổng thanh toán</p>
          <p class="text-gray-400 text-2xl">758.100 đ</p>
        </div>

        <button
          class="w-full bg-yellow-500 py-2 rounded-lg shadow-sm hover:shadow-xl text-white font-bold hover:bg-yellow-400"
        >
          Mua Hang
        </button>
        <div class="p-4 text-center">
          <div class="flex gap-3 items-center justify-center mb-3">
            <img
              alt="visa"
              loading="lazy"
              width="64"
              height="34"
              decoding="async"
              data-nimg="1"
              class="w-auto h-[1.75rem] object-contain"
              :src="zalopay"
              style="color: transparent"
            /><img
              alt="visa"
              loading="lazy"
              width="40"
              height="28"
              decoding="async"
              data-nimg="1"
              class="w-auto h-[1.75rem] object-contain"
              :src="visacard"
              style="color: transparent"
            /><img
              alt="vnpay"
              loading="lazy"
              width="63"
              height="28"
              decoding="async"
              data-nimg="1"
              class="w-auto h-[1.75rem] object-contain"
              :src="vnpay"
              style="color: transparent"
            /><img
              alt="momo"
              loading="lazy"
              width="28"
              height="28"
              decoding="async"
              data-nimg="1"
              class="w-auto h-[1.75rem] object-contain"
              :src="momo"
              style="color: transparent"
            />
          </div>
          <div class="font-medium text-yd-typo-label text-yd-label-5">
            Đảm bảo thanh toán an toàn và bảo mật
          </div>
        </div>
      </div>
    </div>
    <div>
      <ProductModal
        :open="open"
        :handleOk="handleOk"
        :handleCancel="handleCancel"
      />
    </div>
  </div>
</template>
<style lang=""></style>
