<template lang="">
  <div class="grid grid-cols-10">
    <div class="col-span-1">
      <input
        name="cartId"
        :value="cartId"
        type="checkbox"
        id=""
        class="size-6 rounded-lg"
        @change="handleSelect"
      />
    </div>
    <div class="col-span-9 grid grid-cols-6">
      <div class="col-span-2">
        <img
          :src="`${URL_IMAGE}${cartItem.images[0]?.imageUrl}`"
          alt="Error"
          srcset=""
        />
      </div>
      <div class="col-span-4">
        <h3 class="font-bold">{{ cartItem?.name }}</h3>
        <h4 class="font-bold">{{ cartItem?.price }}</h4>
        <div class="flex justify-between items-center mt-9">
          <div
            class="p-2 bg-gray-200 rounded-md font-bold flex items-center gap-2 hover:shadow-md"
          >
            <div>
              Màu sắc :
              <select @change="(e) => changeColor(e, cartId)">
                <option
                  v-for="(item, index) in cartItem.colors"
                  :value="item.id"
                  :selected="`${item.id == cartItem.colorId}`"
                >
                  {{ item.name }}
                </option>
              </select>
            </div>
          </div>
          <div
            class="p-2 bg-gray-200 rounded-md font-bold flex items-center gap-2 hover:shadow-md"
          >
            <div>
              Kích cỡ :
              <select @change="(e) => changeSize(e, cartId)">
                <option
                  v-for="(item, index) in cartItem.sizes"
                  :value="item.id"
                  :selected="`${item.id == cartItem.sizeId}`"
                >
                  {{ item.name }}
                </option>
              </select>
            </div>
          </div>
          <div
            class="col-span-10 xl:col-span-2 xxl:col-span-2 flex border rounded-lg items-center"
          >
            <button
              class="btnMinus w-8 h-8 rounded-md"
              @click="handleDecrement(cartId)"
            >
              -
            </button>
            <span class="flex-1 text-center">{{ refQuantity }} </span>
            <button
              class="btnPlus w-8 h-8 rounded-md"
              @click="handleIncrement(cartId)"
            >
              +
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script lang="ts">
import cartEmpty from "@/assets/img/cart-empty.svg";
import { Icon } from "@iconify/vue";
import { computed, defineComponent, onMounted, ref } from "vue";
import { URL_IMAGE } from "@/constant/constant";

export default defineComponent({
  components: {
    Icon,
  },
  props: {
    showModal: {
      type: Function,
      required: true,
    },
    handleOk: {
      type: Function,
      required: true,
    },
    cartItem: {
      type: Object,
      required: true,
    },
    cartId: {
      type: Number,
      required: true,
    },
    cartSelect: {
      type: Array,
      required: true,
    },
  },
  setup(props) {
    let carts = JSON.parse(localStorage.getItem("carts"));
    const index = carts.findIndex((item: any) => item.cartId == props.cartId);
    let refQuantity = ref(carts[index].quantity);
    const changeColor = (e: Event, cartId: Number) => {
      const target = e.target as HTMLSelectElement;
      const index = carts.findIndex((item: any) => item.cartId == cartId);
      carts[index].colorId = parseInt(target.value);
      localStorage.setItem("carts", JSON.stringify(carts));
    };
    const changeSize = (e: Event, cartId: Number) => {
      const target = e.target as HTMLSelectElement;
      const index = carts.findIndex((item: any) => item.cartId == cartId);
      carts[index].sizeId = parseInt(target.value);
      localStorage.setItem("carts", JSON.stringify(carts));
    };

    const handleIncrement = (cartId: Number) => {
      const index = carts.findIndex((item: any) => item.cartId == cartId);
      carts[index].quantity = carts[index].quantity + 1;
      refQuantity.value = carts[index].quantity + 1;
      localStorage.setItem("carts", JSON.stringify(carts));
    };
    const handleDecrement = (cartId: Number) => {
      const index = carts.findIndex((item: any) => item.cartId == cartId);
      if (carts[index].quantity > 1) {
        carts[index].quantity = carts[index].quantity - 1;
        refQuantity.value = carts[index].quantity - 1;
        localStorage.setItem("carts", JSON.stringify(carts));
      }
    };

    const handleSelect = (e: Event) => {
      const target = e.target as HTMLSelectElement;
      if (target.checked) {
        props.cartSelect.push(props.cartItem);
      } else {
        const index = props.cartSelect.findIndex(
          (item: any) => item.cartId == props.cartId
        );
        props.cartSelect.splice(index, 1);
      }
    };
    return {
      changeColor,
      cartEmpty,
      URL_IMAGE,
      handleIncrement,
      handleDecrement,
      changeSize,
      refQuantity,
      handleSelect,
    };
  },
});
</script>
<style lang=""></style>
