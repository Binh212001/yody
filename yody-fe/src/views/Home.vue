<script lang="ts">
import { defineComponent, onMounted, ref } from "vue";
import CustomButton from "@/custom/customButton.vue";
import Card from "@/components/Card.vue";
import Slider from "@/components/Slider.vue";
import sp from "@/assets/img/spnoibat.webp";
import { useProductStore } from "@/stores/ProductStore";
import cl1 from "@/assets/img/cl1.webp";
import cl2 from "@/assets/img/cl2.webp";
import cl3 from "@/assets/img/cl3.webp";

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
      cl1,
      cl2,
      cl3,
      page,
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
        <router-link to="category/3">
          <h3 class="text-center mb-7 text-2xl">YODY SPORT</h3>
          <p class="text-center underline font-bold">Kham Pha</p>
        </router-link>
      </div>
      <div
        :style="{ backgroundImage: 'url(' + cl1 + ')' }"
        class="h-[500px] centered-background flex flex-col justify-center text-white font-bold"
      >
        <router-link to="category/3">
          <h3 class="text-center mb-7 text-2xl">YODY SPORT</h3>
          <p class="text-center underline font-bold">Kham Pha</p>
        </router-link>
      </div>
      <div
        :style="{ backgroundImage: 'url(' + cl2 + ')' }"
        class="h-[500px] centered-background flex flex-col justify-center text-white font-bold"
      >
        <router-link to="category/3">
          <h3 class="text-center mb-7 text-2xl">YODY SPORT</h3>
          <p class="text-center underline font-bold">Kham Pha</p>
        </router-link>
      </div>
      <div
        :style="{ backgroundImage: 'url(' + cl3 + ')' }"
        class="h-[500px] centered-background flex flex-col justify-center text-white font-bold"
      >
        <router-link to="category/3">
          <h3 class="text-center mb-7 text-2xl">YODY SPORT</h3>
          <p class="text-center underline font-bold">Kham Pha</p>
        </router-link>
      </div>
    </div>
    <div class="pupular mt-7">
      <h2 class="font-bold text-center text-3xl">Sản phẩm ưa chuộng</h2>

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
  </div>
</template>

<style>
.centered-background {
  background-position: center;
  background-size: cover;
}
</style>
