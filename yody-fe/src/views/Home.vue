<script lang="ts">
import { defineComponent, onMounted, ref } from "vue";
import CustomButton from "@/custom/customButton.vue";
import Card from "@/components/Card.vue";
import Slider from "@/components/Slider.vue";
import sp from "@/assets/img/spnoibat.webp";
import { useProductStore } from "@/stores/ProductStore";

export default defineComponent({
  name: "HomePage",

  setup() {
    const page = ref(0);
    const productStore = useProductStore();
    const loadPage = () => {
      page.value += 1;
      productStore.fetchProductsByPage(page.value).catch((error) => {
        console.error("Error fetching products on page change:", error);
      });
    };
    onMounted(() => {
      productStore.fetchAllProduct().catch((error) => {
        console.error("Error fetching sizes on mount:", error);
      });
    });
    return {
      sp,
      productStore,
      loadPage,
    };
  },
  components: {
    CustomButton,
    Card,
    Slider,
  },
});
</script>
<template lang="">
  <div class="mt-7">
    <div class="mb-7">
      <Slider />
    </div>
    <h2 class="font-bold text-center text-3xl mb-6">Bộ sưu tập nổi bật</h2>
    <div
      class="grid grid-cols-2 sm:grid-cols-2 md:grid-cols-4 lg:grid-cols-4 gap-2"
    >
      <div
        :style="{ backgroundImage: 'url(' + sp + ')' }"
        class="h-[500px] centered-background flex flex-col justify-center text-white font-bold"
      >
        <h3 class="text-center mb-7 text-2xl">YODY SPORT</h3>
        <a class="text-center underline font-bold" href="">Kham Pha</a>
      </div>
      <div
        :style="{ backgroundImage: 'url(' + sp + ')' }"
        class="h-[500px] centered-background"
      ></div>
      <div
        :style="{ backgroundImage: 'url(' + sp + ')' }"
        class="h-[500px] centered-background"
      ></div>
      <div
        :style="{ backgroundImage: 'url(' + sp + ')' }"
        class="h-[500px] centered-background"
      ></div>
    </div>
    <div class="pupular mt-7">
      <h2 class="font-bold text-center text-3xl">Sản phẩm ưa chuộng</h2>
      <div
        class="hidden sm:flex md:flex xl:flex xxl:flex justify-center gap-5 mb-5"
      >
        <CustomButton text="Jeans Flex" />
        <CustomButton text="Jeans Flex" />
        <CustomButton text="Jeans Flex" />
        <CustomButton text="Jeans Flex" />
        <CustomButton text="Jeans Flex" />
      </div>
      <div
        class="grid grid-cols-2 sm:grid-cols-2 md:grid-cols-3 xl:grid-cols-5 xxl:grid-cols-5 gap-4"
      >
        <Card
          v-for="(item, index) in productStore.product"
          :item="item"
          :key="index"
        />
      </div>
      <div class="text-center my-5">
        <button class="px-4 py-2 rounded w-[200px] border" @click="loadPage">
          Xem Them
        </button>
      </div>
    </div>
    <!-- <div class="pupular">
      <h2 class="font-bold text-center text-3xl">Gợi ý sản phẩm</h2>
      <div
        class="grid grid-cols-2 sm:grid-cols-1 md:grid-cols-3 xl:grid-cols-5 xxl:grid-cols-5 gap-4"
      >
        <Card
          v-for="(item, index) in productStore.product"
          :item="item"
          :key="index"
        />
      </div>
      <div class="text-center my-5">
        <CustomButton
          text="Xem Them"
          width="w-[200px]"
          border="border border-black"
        />
      </div>
    </div> -->
  </div>
</template>

<style>
.centered-background {
  background-position: center;
  background-size: cover;
}
</style>
