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
                <div class="w-5 h-5 rounded border border-yd-line-normal"></div>
                <div class="pl-3 text-yd-label-4 text-yd-typo-label">
                  <div
                    class="font-medium text-yd-label-3 pl-[0.375rem] xlg:pl-1"
                  >
                    Unisex
                  </div>
                </div>
              </div>
              <input class="hidden" type="checkbox" value="false" />
            </div>
            <div class="py-2 hover:bg-yd-grey-light-2 flex items-center">
              <div
                class="inline-flex flex-row items-center cursor-pointer select-none"
              >
                <div class="w-5 h-5 rounded border border-yd-line-normal"></div>
                <div class="pl-3 text-yd-label-4 text-yd-typo-label">
                  <div
                    class="font-medium text-yd-label-3 pl-[0.375rem] xlg:pl-1"
                  >
                    Nam
                  </div>
                </div>
              </div>
              <input class="hidden" type="checkbox" value="false" />
            </div>
          </div>
        </a-collapse-panel>
        <a-collapse-panel
          key="2"
          header="Màu sắc"
          class="border-none bg-white rounded-none shadow-none font-bold"
        >
          <div class="flex gap-2 flex-wrap">
            <div>
              <div
                class="col-span-1 flex flex-col items-center pt-2 gap-2 xlg:gap-1 flex-grow cursor-pointer hover:bg-yd-grey-light-2 rounded-lg"
              >
                <div
                  class="w-[2.375rem] h-[2.375rem] xlg:w-6 xlg:h-6 rounded-full flex justify-center items-center bg-cover bg-no-repeat"
                  style="background-color: rgb(0, 0, 0)"
                ></div>
                <div
                  class="font-medium text-yd-label-5 text-center min-h-[2rem] text-yd-typo-title"
                >
                  Đen
                </div>
              </div>
            </div>
          </div>
        </a-collapse-panel>
        <a-collapse-panel
          key="3"
          header="Kích thước"
          class="border-none bg-white rounded-none shadow-none font-bold"
        >
          <div class="flex">
            <div
              class="col-span-1 flex items-center justify-center p-3 xlg:p-2 cursor-pointer rounded-lg hover:bg-yd-grey-light-2 text-yd-typo-label hover:text-yd-typo-title border border-yd-line-normal"
            >
              <div class="font-medium text-yd-label-3">XL</div>
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
                <div class="w-5 h-5 rounded border border-yd-line-normal"></div>
                <div class="pl-3 text-yd-label-4 text-yd-typo-label">
                  <div
                    class="font-medium text-yd-label-3 pl-[0.375rem] xlg:pl-1"
                  >
                    Dưới 350.000đ
                  </div>
                </div>
              </div>
              <input class="hidden" type="checkbox" value="false" />
            </div>
            <div class="py-2 hover:bg-yd-grey-light-2 flex items-center">
              <div
                class="inline-flex flex-row items-center cursor-pointer select-none"
              >
                <div class="w-5 h-5 rounded border border-yd-line-normal"></div>
                <div class="pl-3 text-yd-label-4 text-yd-typo-label">
                  <div
                    class="font-medium text-yd-label-3 pl-[0.375rem] xlg:pl-1"
                  >
                    Dưới 350.000đ
                  </div>
                </div>
              </div>
              <input class="hidden" type="checkbox" value="false" />
            </div>
          </div>
        </a-collapse-panel>
      </a-collapse>
    </div>
  </div>
</template>
<script lang="ts" setup>
import { ref } from "vue";
const activeKey = ref(["1", "2", "3", "4"]);
</script>
<style lang=""></style>
