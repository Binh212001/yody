<script lang="ts">
import { computed, defineComponent, reactive, ref } from "vue";
import cartEmpty from "@/assets/img/cart-empty.svg";
import CardItem from "@/components/CartItem.vue";
import momo from "@/assets/img/momo.webp";
import visacard from "@/assets/img/visa-card.webp";
import vnpay from "@/assets/img/vnpay-qr.webp";
import zalopay from "@/assets/img/zalopay.webp";
import { Form, Field, ErrorMessage } from "vee-validate";
import user from "@/assets/img/user.svg";
import email from "@/assets/img/email.svg";
import phone from "@/assets/img/phone.svg";
import note from "@/assets/img/note.svg";
import checkout1 from "@/assets/img/checkout1.svg";
import checkout2 from "@/assets/img/checkout2.svg";
import checkout3 from "@/assets/img/checkout3.svg";
import checkout4 from "@/assets/img/checkout4.svg";
import checkout5 from "@/assets/img/checkout5.svg";
import billApi from "@/api/Bill";

export default defineComponent({
  name: "MyComponent",
  components: {
    CardItem,
    Form,
    Field,
    ErrorMessage,
  },
  setup() {
    const open = ref<boolean>(false);
    const itemModalShow = reactive({});
    const cartSelect = reactive<any>({
      carts: [],
    });

    const showModal = () => {
      open.value = true;
    };
    let carts = JSON.parse(localStorage.getItem("carts"));
    const handleCancel = () => {
      console.log("Modal Cancel button clicked");
      open.value = false;
    };
    const validateName = (value: string) => {
      return !value ? "This field is required" : true;
    };

    const handleOk = (e: MouseEvent) => {
      console.log("Ok button clicked");
      open.value = false;
    };
    const priceCompute = computed(() => {
      let total = 0;
      cartSelect.carts.forEach((c: any) => {
        total += c.price * c.quantity;
      });
      return total;
    });
    const handlePayment = async (val: any) => {
      let item: any = [];
      let sum = 0;
      cartSelect.carts.forEach((c: any) => {
        sum += c.price * c.quantity;
        item.push({
          productId: c.id,
          quantity: c.quantity,
          price: c.price,
          colorId: c.colorId,
          sizeId: c.sizeId,
        });
      });
      let bill: any = {
        ...val,
        totalPrice: sum,
        productBillForm: item,
      };
      try {
        let { data } = await billApi.create(bill);
      } catch (e) {
        console.error("Error creating bill:", e);
        alert("Error creating bill. Please try again.");
        return;
      }

      open.value = false;
    };

    return {
      validateName,
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
      carts,
      cartSelect,
      priceCompute,
      user,
      email,
      phone,
      note,
      checkout1,
      checkout2,
      checkout3,
      checkout4,
      checkout5,
      handlePayment,
    };
  },
});
</script>

<template lang="">
  <h3 class="font-bold text-3xl text-gray-800 text-center mt-7">Giỏ hàng</h3>
  <div>
    <div class="card-box-empty mt-7 text-center" v-if="false">
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
          <CardItem
            v-for="(item, index) in carts"
            :cartId="item.cartId"
            :cartItem="item"
            :key="index"
            :showModal="showModal"
            :handleOk="handleOk"
            :cartSelect="cartSelect.carts"
          />
        </div>
      </div>

      <div
        class="payment card-box-empty mt-7 md:col-span-1 xl:col-span-1 bg-slate-50 p-4"
      >
        <h3 class="font-bold text-xl mb-2">Chi tiết đơn hàng</h3>
        <div class="border-b">
          <div class="flex justify-between items-end mb-2">
            <p class="text-gray-600">Tổng giá trị sản phẩm</p>
            <p class="text-gray-400">{{ priceCompute }}</p>
          </div>
          <div class="flex justify-between items-end mb-2">
            <p class="text-gray-600">Giảm giá</p>
            <p class="text-red-500">0 đ</p>
          </div>
          <div class="flex justify-between items-end mb-2">
            <p class="text-gray-600">Vận chuyển</p>
            <p class="text-gray-400">0 đ</p>
          </div>
          <div class="flex justify-between items-end mb-2">
            <p class="text-gray-600">Giảm giá vận chuyển</p>
            <p class="text-red-500">0 đ</p>
          </div>
        </div>
        <div class="flex justify-between items-end mb-2">
          <p class="text-gray-600 text-2xl">Tổng thanh toán</p>
          <p class="text-gray-400 text-2xl">{{ priceCompute }}</p>
        </div>

        <button
          @click="showModal"
          class="w-full bg-yellow-500 py-2 rounded-lg shadow-sm hover:shadow-xl text-white font-bold hover:bg-yellow-400"
        >
          Thanh toan
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

    <a-modal
      v-model:open="open"
      title="Mua Hàng"
      style="width: 80vw"
      :footer="null"
    >
      <Form
        @submit="handlePayment"
        class="container m-auto grid grid-col-1 sm:grid-cols-1 md:grid-cols-2 lg:grid-cols-2 gap-3"
      >
        <div>
          <div class="items-center py-5">
            <div
              class="font-semibold xlg:font-semibold text-yd-heading-7 xlg:text-yd-heading-6 text-yd-typo-title mb-7"
            >
              Người nhận
            </div>
            <div>
              <div class="input-box mb-5 border rounded-md flex px-3 py-2">
                <img :src="user" alt="User Icon" class="w-7 h-7 mr-2" />
                <Field
                  :rules="validateName"
                  name="customerName"
                  type="text"
                  placeholder="Nhập họ tên"
                  class="input-text w-full outline-none border-none"
                />
                <ErrorMessage name="name" class="text-red-400" />
              </div>
            </div>
            <div>
              <div class="input-box mb-5 border rounded-md flex px-3 py-2">
                <img :src="phone" alt="User Icon" class="w-7 h-7 mr-2" />
                <Field
                  :rules="validateName"
                  type="text"
                  name="phone"
                  placeholder="Nhập số điện thoại"
                  class="input-text w-full outline-none border-none"
                />
                <ErrorMessage name="name" class="text-red-400" />
              </div>
            </div>
            <div>
              <div class="input-box mb-5 border rounded-md flex px-3 py-2">
                <img :src="email" alt="User Icon" class="w-7 h-7 mr-2" />
                <Field
                  :rules="validateName"
                  name="email"
                  type="text"
                  placeholder="Nhập hoặc email"
                  class="input-text w-full outline-none border-none"
                />
                <ErrorMessage name="name" class="text-red-400" />
              </div>
            </div>
            <div>
              <div class="input-box mb-5 border rounded-md flex px-3 py-2">
                <img :src="note" alt="User Icon" class="w-7 h-7 mr-2" />
                <Field
                  type="text"
                  name="note"
                  placeholder="Ghi chú"
                  class="input-text w-full outline-none border-none"
                />
              </div>
            </div>
            <div class="font-semibold text-yd-heading-8 text-[#1C2430]">
              Địa chỉ của bạn
            </div>
            <div>
              <div class="input-box mb-5 border rounded-md flex px-3 py-2">
                <Field
                  :rules="validateName"
                  name="addressDetail"
                  type="text"
                  placeholder="Nhập địa chỉ (ví dụ 90 Nguyễn Tuân) "
                  class="input-text w-full outline-none border-none"
                />
                <ErrorMessage name="name" class="text-red-400" />
              </div>
            </div>
            <div class="flex flex-col py-5 xlg:pb-4">
              <div
                class="font-semibold xlg:font-semibold text-yd-heading-7 xlg:text-yd-heading-6 text-yd-typo-title"
              >
                Phương thức thanh toán
              </div>
              <div class="font-regular text-yd-subtitle-3 text-yd-gray-80 mb-5">
                Lựa chọn phương thức thanh toán phù hợp nhất cho bạn
              </div>
              <div class="border rounded-md">
                <div class="border p-3">
                  <input
                    type="radio"
                    id="payment-method-1"
                    name="payment-method"
                    value="Thanh toán khi nhận hàng"
                    class="mr-3"
                  />
                  <label
                    for="payment-method-1"
                    class="font-medium text-yd-gray-80"
                  >
                    Thanh toán khi nhận hàng
                  </label>
                </div>
                <div class="border p-3">
                  <input
                    type="radio"
                    id="payment-method-1"
                    name="payment-method"
                    value="Thanh toán khi nhận hàng"
                    class="mr-3"
                  />
                  <label
                    for="payment-method-1"
                    class="font-medium text-yd-gray-80"
                  >
                    Thanh toán Momo Wallet
                  </label>
                </div>
              </div>
            </div>
            <button
              class="bg-yellow-500 w-full py-3 rounded-md hover:shadow-lg hover:bg-yellow-600 text-white font-medium"
              type="submit"
            >
              Thanh toán ngay
            </button>
          </div>
        </div>
        <div>
          <div
            class="font-medium mt-6 mb-5 left-[50%] translate-x-[-50%] text-yd-subtitle-4 text-yd-gray-80 bg-white xlg:bg-yd-gray-10 z-10 w-fit mx-auto px-[10px]"
          >
            Vì sao bạn nên mua hàng tại YODY
          </div>
          <div class="flex mb-7">
            <img :src="checkout2" />
            <div>
              <h4 class="font-medium">Hơn 1.000.000+ khách hàng tin tưởng</h4>
              <p class="text-gray-400">
                Khách hàng sử dụng và đánh giá cao về chất lượng sản phẩm
              </p>
            </div>
          </div>
          <div class="flex mb-7">
            <img :src="checkout2" />
            <div>
              <h4 class="font-medium">Miễn phí đổi trả trong 15 ngày</h4>
              <p class="text-gray-400">
                Đổi trả sản phẩm tại hệ thống hơn 267 cửa hàng
              </p>
            </div>
          </div>
          <div class="flex mb-7">
            <img :src="checkout2" />
            <div>
              <h4 class="font-medium">Hơn 30 triệu đơn giao hàng thành công</h4>
              <p class="text-gray-400">
                Giao hàng nhanh chóng và đảm bảo chất lượng cam kết
              </p>
            </div>
          </div>
          <div class="flex mb-7">
            <img :src="checkout2" />
            <div>
              <h4 class="font-medium">Cam kết chất lượng</h4>
              <p class="text-gray-400">
                100% sản phẩm được kiểm tra và bảo quản để đạt chất lượng tối đa
                khi giao đến tay khách hàng
              </p>
            </div>
          </div>
        </div>
      </Form>
    </a-modal>
  </div>
</template>
<style lang=""></style>
