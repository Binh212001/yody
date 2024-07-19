<template lang="">
  <div>
    <a-modal
      v-model:open="props.open"
      title="Basic Modal"
      @ok="props.handleOk"
      width="700px"
      :closeable="true"
      @cancel="handleCancel"
    >
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
            <div class="product-right grid grid-cols-10 gap-2">
              <div class="col-span-10 flex border rounded-lg items-center">
                <button
                  class="btnMinus w-10 h-10 rounded-md"
                  @click="handleDecrement"
                >
                  -
                </button>
                <span class="flex-1 text-center">1</span>
                <button
                  class="btnPlus w-10 h-10 rounded-md"
                  @click="handleIncrement"
                >
                  +
                </button>
              </div>
            </div>
            <button
              class="w-full mt-2 rounded-lg py-2 bg-yellow-500 shadow-sm hover:shadow-lg mb-2"
            >
              Mua Ngay
            </button>
            <div class="orther-infomation">
              <div class="product-risk-reduce-line_content__KG_2F">
                <span class="product-risk-reduce-line_label__i_9_u"
                  ><span
                    class="font-semibold text-yd-heading-8 text-yd-typo-label"
                    >Miễn phí vận chuyển:</span
                  ></span
                ><span
                  class="font-regular text-yd-subtitle-3 text-yd-typo-label"
                  >Đơn hàng từ 498k</span
                >
              </div>
            </div>
          </div>
        </div>
      </div>
    </a-modal>
  </div>
</template>
<script setup lang="ts">
const props = defineProps({
  open: {
    type: Boolean,
    required: true,
  },
  handleOk: {
    type: Function,
    required: true,
  },
  handleCancel: {
    type: Function,
    required: true,
  },
});
</script>
<style lang=""></style>
