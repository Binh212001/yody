<template lang="">
  <div>
    <h3 class="text-xl font-medium">Bộ lọc</h3>
    <div>
      <a-collapse
        ghost
        v-model:activeKey="activeKey"
        class="border-none bg-white rounded-none shadow-none"
      >
        <a-collapse-panel
          key="1"
          header=" Giới tính"
          class="bg-white rounded-none shadow-none font-bold"
        >
          <div>
            <div class="py-2 hover:bg-yd-grey-light-2 flex items-center">
              <div
                class="inline-flex flex-row items-center cursor-pointer select-none"
              >
                <input
                  name="gender"
                  v-model="filter.gender"
                  class="w-5 h-5 rounded border border-yd-line-normal"
                  type="radio"
                  value="true"
                />
                <div class="pl-3 text-yd-label-4 text-yd-typo-label">
                  <div
                    class="font-medium text-yd-label-3 pl-[0.375rem] xlg:pl-1"
                  >
                    Nam
                  </div>
                </div>
              </div>
            </div>
            <div class="py-2 hover:bg-yd-grey-light-2 flex items-center">
              <div
                class="inline-flex flex-row items-center cursor-pointer select-none"
              >
                <input
                  name="gender"
                  v-model="filter.gender"
                  class="w-5 h-5 rounded border border-yd-line-normal"
                  type="radio"
                  value="false"
                />
                <div class="pl-3 text-yd-label-4 text-yd-typo-label">
                  <div
                    class="font-medium text-yd-label-3 pl-[0.375rem] xlg:pl-1"
                  >
                    Nu
                  </div>
                </div>
              </div>
            </div>
          </div>
        </a-collapse-panel>
        <a-collapse-panel
          key="2"
          header="Màu sắc"
          class="border-none bg-white rounded-none shadow-none font-bold"
        >
          <div class="flex gap-2">
            <div
              class="col-span-1 flex-wrap flex pt-2 gap-2 xlg:gap-1 flex-grow cursor-pointer hover:bg-yd-grey-light-2 rounded-lg"
            >
              <div
                v-for="(item, index) in colorStore.color"
                :key="index"
                :class="[
                  'w-[2.375rem] h-[2.375rem] xlg:w-6 xlg:h-6 rounded-full flex justify-center items-center bg-cover bg-no-repeat',
                  { active: item.id === filter.color },
                ]"
                :style="`background-color: ${item.name}`"
                @click="selectColor(item.id)"
              ></div>
            </div>
          </div>
        </a-collapse-panel>
        <a-collapse-panel
          key="3"
          header="Kích thước"
          class="border-none bg-white rounded-none shadow-none font-bold"
        >
          <div class="flex gap-3">
            <div
              v-for="(item, index) in sizeStore.size"
              @click="selectSize(item.id)"
              :class="[
                'col-span-1 flex items-center justify-center p-3 xlg:p-2 cursor-pointer rounded-lg hover:bg-yd-grey-light-2 text-yd-typo-label hover:text-yd-typo-title border border-yd-line-normal',
                { active: item.id === filter.size },
              ]"
            >
              <div class="font-medium text-yd-label-3">{{ item.name }}</div>
            </div>
          </div>
        </a-collapse-panel>
        <a-collapse-panel
          key="4"
          header="Theo giá"
          class="border-none bg-white rounded-none shadow-none font-bold"
        >
          <div>
            <div class="py-2 hover:bg-yd-grey-light-2 flex items-center">
              <div
                class="inline-flex flex-row items-center cursor-pointer select-none"
              >
                <input
                  type="checkbox"
                  class="w-5 h-5 rounded border border-yd-line-normal"
                />
                <div class="pl-3 text-yd-label-4 text-yd-typo-label">
                  <div
                    class="font-medium text-yd-label-3 pl-[0.375rem] xlg:pl-1"
                  >
                    Dưới 350.000đ
                  </div>
                </div>
              </div>
            </div>
            <div class="py-2 hover:bg-yd-grey-light-2 flex items-center">
              <div
                class="inline-flex flex-row items-center cursor-pointer select-none"
              >
                <input
                  type="checkbox"
                  class="w-5 h-5 rounded border border-yd-line-normal"
                />
                <div class="pl-3 text-yd-label-4 text-yd-typo-label">
                  <div
                    class="font-medium text-yd-label-3 pl-[0.375rem] xlg:pl-1"
                  >
                    Dưới 350.000đ
                  </div>
                </div>
              </div>
            </div>
          </div>
        </a-collapse-panel>
      </a-collapse>
    </div>
  </div>
</template>
<script lang="ts">
import { defineComponent, onMounted, reactive, ref } from "vue";
import { useSizeStore } from "@/stores/SizeStore";
import { useColorStore } from "@/stores/ColorStore";
import { useProductStore } from "@/stores/ProductStore";
export default defineComponent({
  name: "Filter",
  setup() {
    const activeKey = ref(["1", "2", "3", "4"]);
    const filter = reactive({
      color: 0,
      size: 0,
      gender: null,
    });
    const short = ref("0");
    const sizeStore = useSizeStore();
    const colorStore = useColorStore();
    const productStore = useProductStore();
    onMounted(() => {
      sizeStore.fetchAllsize().catch((error) => {
        console.error("Error fetching sizes on mount:", error);
      });
      colorStore.fetchAllColor().catch((error) => {
        console.error("Error fetching sizes on mount:", error);
      });
    });

    const selectColor = (id: number) => {
      filter.color = id;
      let prams: any = {
        gender: filter.gender,
        colorId: filter.color,
        sizeId: filter.size,
      };
      productStore.fetchProductByFilter(prams).catch((error) => {
        console.error("Error fetching products on mount:", error);
      });
    };
    const selectSize = (id: number) => {
      filter.size = id;
      let prams: any = {
        gender: filter.gender,
        colorId: filter.color,
        sizeId: filter.size,
      };
      productStore.fetchProductByFilter(prams).catch((error) => {
        console.error("Error fetching products on mount:", error);
      });
    };
    return {
      short,
      sizeStore,
      colorStore,
      activeKey,
      selectColor,
      selectSize,
      filter,
      productStore,
    };
  },
});
</script>
<style>
.active {
  border: 1px solid black !important;
}
</style>
