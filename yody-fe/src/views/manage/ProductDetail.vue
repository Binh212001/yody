<script lang="ts">
import { defineComponent, reactive, ref } from "vue";
import star from "@/assets/img/yellow-circle-star-icon-vector-30066148-1719762106.png";
import oclock from "@/assets/img/oclock.svg";
import arrowTwoWay from "@/assets/img/arrow-two-way.svg";
import productRiskReduce from "@/assets/img/product-risk-reduce.svg";
import security from "@/assets/img/security.svg";
import Card from "@/components/Card.vue";
import { Carousel, Navigation, Slide } from "vue3-carousel";
import "vue3-carousel/dist/carousel.css";

export default defineComponent({
  name: "MyComponent",
  components: {
    Card,
    Carousel,
    Navigation,
    Slide,
  },
  setup() {
    const state = reactive({
      showForm: false,
      mode: false,
    });

    const handleShowFormSubmit = () => {
      state.showForm = true;
    };

    const handleHideFormSubmit = () => {
      state.showForm = false;
    };

    return {
      state,
      handleShowFormSubmit,
      handleHideFormSubmit,
      star,
      oclock,
      arrowTwoWay,
      productRiskReduce,
      security,
    };
  },
});
</script>
<template lang="">
  <div>
    <div
      class="grid grid-cols-1 sm:grid-cols-1 md:grid-cols-1 xl:grid-cols-2 xxl:grid-cols-2 gap-3"
    >
      <div class="product-right grid xl:grid-cols-10 xxl:grid-cols-10 gap-4">
        <div
          class="hidden xl:flex xxl:flex flex-col list-image col-span-2 bg-blue-500 text-white p-4"
        ></div>
        <div class="slide col-span-8 bg-green-500 text-white p-4">
          <img src="" alt="" class="w-100%" />
        </div>
      </div>
      <div class="product-left">
        <h4 class="text-gray-900 font-bold">
          Áo Thun Thể Thao Nam Feel The Sport
        </h4>
        <div class="flex">
          <p class="pr-2 border-r-2 border-gray-400">STM7067-XTH-L</p>
          <span class="pl-2">Đã bán 159</span>
        </div>
        <h2 class="font-bold">249.000 đ</h2>
        <div class="voucher">
          <h3 class="font-bold">Khuyến mãi</h3>
          <p class="flex items-center">
            <img :src="star" class="w-12 h-12" />
            <a href="">
              Đăng ký Email nhận Voucher 100K cho đơn từ 400K
              <span class="text-yellow-500">(Nhận ngay)</span>
            </a>
          </p>
          <p class="flex items-center">
            <img :src="star" class="w-12 h-12" />
            <a href="">
              Voucher giảm 100K cho đơn từ 499K
              <span class="text-yellow-500">(Nhận ngay)</span>
            </a>
          </p>
          <p class="flex items-center">
            <img :src="star" class="w-12 h-12" />
            <a href="">
              Đăng ký Email nhận Voucher 100K cho đơn từ 400K
              <span class="text-yellow-500">(Nhận ngay)</span>
            </a>
          </p>
          <p class="flex items-center">
            <img :src="star" class="w-12 h-12" />
            <a href="">
              Đăng ký Email nhận Voucher 100K cho đơn từ 400K
              <span class="text-yellow-500">(Nhận ngay)</span>
            </a>
          </p>
        </div>
        <div class="color">
          <h3 class="font-bold">Màu sắc:</h3>
          <button
            class="btnColor w-10 h-10 rounded-full bg-black mr-3"
          ></button>
          <button
            class="btnColor w-10 h-10 rounded-full bg-black mr-3"
          ></button>
          <button
            class="btnColor w-10 h-10 rounded-full bg-black mr-3"
          ></button>
        </div>
        <div class="color">
          <h3 class="font-bold">Kích thước:</h3>
          <button class="btnSize w-10 h-10 rounded-md border mr-2">SM</button>
          <button class="btnSize w-10 h-10 rounded-md border mr-2">LG</button>
          <button class="btnSize w-10 h-10 rounded-md border mr-2">XL</button>
        </div>
        <div class="amount">
          <h3 class="font-bold">Số lượng:</h3>
        </div>
      </div>
    </div>
  </div>
</template>

<style></style>
