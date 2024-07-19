<script lang="ts">
import { defineComponent } from "vue";
import user from "@/assets/img/user.svg";
import email from "@/assets/img/email.svg";
import phone from "@/assets/img/phone.svg";
import note from "@/assets/img/note.svg";
import checkout1 from "@/assets/img/checkout1.svg";
import checkout2 from "@/assets/img/checkout2.svg";
import checkout3 from "@/assets/img/checkout3.svg";
import checkout4 from "@/assets/img/checkout4.svg";
import checkout5 from "@/assets/img/checkout5.svg";

export default defineComponent({
  name: "MyComponent",
  setup() {
    // No reactive state or methods defined here
    return {
      user,
      email,
      phone,
      note,
      checkout1,
      checkout2,
      checkout3,
      checkout4,
      checkout5,
    };
  },
});
</script>

<template lang="">
  <div
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
            <input
              type="text"
              placeholder="Nhập họ tên"
              class="input-text w-full outline-none border-none"
            />
          </div>
        </div>
        <div>
          <div class="input-box mb-5 border rounded-md flex px-3 py-2">
            <img :src="phone" alt="User Icon" class="w-7 h-7 mr-2" />
            <input
              type="text"
              placeholder="Nhập số điện thoại"
              class="input-text w-full outline-none border-none"
            />
          </div>
        </div>
        <div>
          <div class="input-box mb-5 border rounded-md flex px-3 py-2">
            <img :src="email" alt="User Icon" class="w-7 h-7 mr-2" />
            <input
              type="text"
              placeholder="Nhập hoặc email"
              class="input-text w-full outline-none border-none"
            />
          </div>
        </div>
        <div>
          <div class="input-box mb-5 border rounded-md flex px-3 py-2">
            <img :src="note" alt="User Icon" class="w-7 h-7 mr-2" />
            <input
              type="text"
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
            <input
              type="text"
              placeholder="Nhập địa chỉ (ví dụ 90 Nguyễn Tuân) "
              class="input-text w-full outline-none border-none"
            />
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
              <label for="payment-method-1" class="font-medium text-yd-gray-80">
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
              <label for="payment-method-1" class="font-medium text-yd-gray-80">
                Thanh toán Momo Wallet
              </label>
            </div>
          </div>
        </div>
        <button
          class="bg-yellow-500 w-full py-3 rounded-md hover:shadow-lg hover:bg-yellow-600 text-white font-medium"
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
            100% sản phẩm được kiểm tra và bảo quản để đạt chất lượng tối đa khi
            giao đến tay khách hàng
          </p>
        </div>
      </div>
    </div>
  </div>
</template>
<style lang=""></style>
