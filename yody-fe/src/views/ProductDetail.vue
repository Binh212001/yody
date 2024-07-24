<script lang="ts">
import { defineComponent, onMounted, reactive, ref } from "vue";
import star from "@/assets/img/yellow-circle-star-icon-vector-30066148-1719762106.png";
import oclock from "@/assets/img/oclock.svg";
import arrowTwoWay from "@/assets/img/arrow-two-way.svg";
import productRiskReduce from "@/assets/img/product-risk-reduce.svg";
import security from "@/assets/img/security.svg";
import Card from "@/components/Card.vue";
import { Carousel, Navigation, Slide } from "vue3-carousel";
import { useRoute } from "vue-router";
import "vue3-carousel/dist/carousel.css";
import productApi from "@/api/Product";
import { URL_IMAGE } from "@/constant/constant";
import { useProductStore } from "@/stores/ProductStore";
import user from "@/assets/img/user.svg";
import email from "@/assets/img/email.svg";
import phone from "@/assets/img/phone.svg";
import note from "@/assets/img/note.svg";
import checkout1 from "@/assets/img/checkout1.svg";
import checkout2 from "@/assets/img/checkout2.svg";
import checkout3 from "@/assets/img/checkout3.svg";
import checkout4 from "@/assets/img/checkout4.svg";
import checkout5 from "@/assets/img/checkout5.svg";
import { Form, Field, ErrorMessage } from "vee-validate";
import billApi from "@/api/Bill";

export default defineComponent({
  name: "MyComponent",
  components: {
    Card,
    Carousel,
    Navigation,
    Slide,
    Form,
    Field,
    ErrorMessage,
  },
  setup() {
    const open = ref<boolean>(false);
    const quantityRef = ref(1);
    const refTotalPrice = ref(0);
    const imageSelectRef = ref(0);
    const route = useRoute();
    const obj: any = reactive({
      color: 1,
      size: 1,
      product: null,
      settings: {
        itemsToShow: 2,
        snapAlign: "center",
        wrapAround: "true",
      },
      breakpoints: {
        320: {
          itemsToShow: 1.5,
          snapAlign: "center",
          wrapAround: "true",
        },
        700: {
          itemsToShow: 2,
          snapAlign: "start",
          wrapAround: "true",
        },
        1024: {
          itemsToShow: 5,
          snapAlign: "start",
          wrapAround: "true",
        },
      },
    });
    const productStore = useProductStore();
    const handleIncrement = () => {
      quantityRef.value++;
    };

    const handleDecrement = () => {
      if (quantityRef.value > 1) {
        quantityRef.value--;
      }
    };
    const selectImage = (index: number) => {
      imageSelectRef.value = index;
    };
    const validateName = (value: string) => {
      return !value ? "This field is required" : true;
    };
    const handleAddToCart = (index: number) => {
      let cart: any = {
        cartId: Math.random(),
        ...obj.product,
        colorId: obj.color,
        sizeId: obj.size,
        quantity: quantityRef.value,
      };
      let carts: any = localStorage.getItem("carts");
      if (!carts) {
        carts = [];
        carts.push(cart);
        localStorage.setItem("carts", JSON.stringify(carts));
      } else {
        carts = JSON.parse(carts);
        carts.push(cart);
        localStorage.setItem("carts", JSON.stringify(carts));
      }
    };

    const handlePayment = async (val: any) => {
      let bill = {
        ...val,
        totalPrice: refTotalPrice.value,
        productBillForm: [
          {
            productId: obj.product.id,
            quantity: quantityRef.value,
            price: obj.product.price,
            colorId: obj.color,
            sizeId: obj.size,
          },
        ],
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

    onMounted(async () => {
      const productId = route.params.id;
      const { data, status } = await productApi.getById(Number(productId));
      obj.product = data;
      productStore.fetchAllProduct().catch((error) => {
        console.error("Error fetching sizes on mount:", error);
      });
    });
    const showModal = () => {
      refTotalPrice.value = quantityRef.value * obj.product.price;
      open.value = true;
    };

    const selectSize = (id: number) => {
      obj.size = Number(id);
    };
    const selectColor = (id: number) => {
      obj.color = Number(id);
    };
    return {
      star,
      oclock,
      selectImage,
      imageSelectRef,
      arrowTwoWay,
      productRiskReduce,
      security,
      quantityRef,
      selectSize,
      obj,
      selectColor,
      route,
      validateName,
      URL_IMAGE,
      showModal,
      open,
      productStore,
      handleIncrement,
      handleDecrement,
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
      handleAddToCart,
    };
  },
});
</script>

<template lang="">
  <div>
    <p class="text-gray-600">tab</p>
    <div
      class="grid grid-cols-1 sm:grid-cols-1 md:grid-cols-1 xl:grid-cols-2 xxl:grid-cols-2 gap-3"
    >
      <div class="product-right grid xl:grid-cols-10 xxl:grid-cols-10 gap-4">
        <div
          class="hidden xl:flex xxl:flex flex-col list-image col-span-2 text-white p-4"
        >
          <img
            v-for="(item, index) in obj.product?.images"
            :src="`${URL_IMAGE}${item.imageUrl}`"
            :alt="item.imageUrl"
            :key="index"
            @click="selectImage(index)"
            class="w-100%"
          />
        </div>
        <div class="slide col-span-8 text-white p-4">
          <img
            :src="`${URL_IMAGE}${obj.product?.images[imageSelectRef]?.imageUrl}`"
            :alt="obj.product?.images[imageSelectRef]?.imageUrl"
            class="w-100%"
          />
        </div>
      </div>
      <div class="product-left">
        <h4 class="text-gray-900 font-bold">
          {{ obj.product?.name }}
        </h4>
        <div class="flex">
          <p class="pr-2 border-r-2 border-gray-400">STM7067-XTH-L</p>
          <span class="pl-2">Đã bán 159</span>
        </div>
        <h2 class="font-bold">{{ obj.product?.price }}đ</h2>
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
            v-for="(item, index) in obj.product?.colors"
            :key="index"
            :style="{ backgroundColor: item.name }"
            :class="[
              'btnColor',
              'w-10',
              'h-10',
              'rounded-md',
              'border',
              'mr-2',
              { active: obj.color === item.id },
            ]"
            @click="selectColor(item.id)"
          ></button>
        </div>
        <div class="color">
          <h3 class="font-bold">Kích thước:</h3>
          <button
            v-for="(item, index) in obj.product?.sizes"
            :class="[
              'btnSize',
              'w-10',
              'h-10',
              'rounded-md',
              'border',
              'mr-2',
              { active: obj.size === item.id },
            ]"
            @click="selectSize(item.id)"
          >
            {{ item.name }}
          </button>
        </div>
        <div class="amount">
          <h3 class="font-bold">Số lượng:</h3>
          <div class="product-right grid grid-cols-10 gap-2">
            <div
              class="col-span-10 xl:col-span-2 xxl:col-span-2 flex border rounded-lg items-center"
            >
              <button
                class="btnMinus w-10 h-10 rounded-md"
                @click="handleDecrement"
              >
                -
              </button>
              <span class="flex-1 text-center">{{ quantityRef }}</span>
              <button
                class="btnPlus w-10 h-10 rounded-md"
                @click="handleIncrement"
              >
                +
              </button>
            </div>
            <div class="col-span-10 xl:col-span-8 xxl:col-span-8">
              <button
                class="btnMinus h-10 rounded-md w-full border border-gray-800 hover:shadow-lg"
                @click="handleAddToCart"
              >
                Thêm vào giỏ
              </button>
            </div>
          </div>
          <button
            class="w-full mt-2 rounded-lg py-2 bg-yellow-500 shadow-sm hover:shadow-lg mb-2"
            @click="showModal()"
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
              ><span class="font-regular text-yd-subtitle-3 text-yd-typo-label"
                >Đơn hàng từ 498k</span
              >
            </div>
            <div class="">
              <div class="flex">
                <div class="product-risk-reduce-line_icon__PPQoo">
                  <img :src="oclock" />
                </div>
                <div class="product-risk-reduce-line_content__KG_2F">
                  <span class="product-risk-reduce-line_label__i_9_u"
                    ><span
                      class="font-semibold text-yd-heading-8 text-yd-typo-label"
                      >Giao hàng:</span
                    ></span
                  ><span
                    class="font-regular text-yd-subtitle-3 text-yd-typo-label"
                    >Từ 3 - 5 ngày trên cả nước</span
                  >
                </div>
              </div>
              <div>
                <div class="product-risk-reduce-line_wrapper__mDXz2 flex">
                  <div class="product-risk-reduce-line_icon__PPQoo">
                    <img :src="arrowTwoWay" alt="" />
                  </div>
                  <div class="product-risk-reduce-line_content__KG_2F">
                    <span class="product-risk-reduce-line_label__i_9_u"
                      ><span
                        class="font-semibold text-yd-heading-8 text-yd-typo-label"
                        >Miễn phí đổi trả:</span
                      ></span
                    ><span
                      class="font-regular text-yd-subtitle-3 text-yd-typo-label"
                      >Tại 267+ cửa hàng trong 15 ngày</span
                    >
                  </div>
                </div>
              </div>
              <div class="product-risk-reduce-policies_line-item__nadGz">
                <div class="product-risk-reduce-line_wrapper__mDXz2 flex">
                  <div class="product-risk-reduce-line_icon__PPQoo">
                    <img :src="productRiskReduce" />
                  </div>
                  <div class="product-risk-reduce-line_content__KG_2F">
                    <span
                      class="font-regular text-yd-subtitle-3 text-yd-typo-label"
                      >Sử dụng mã giảm giá ở bước thanh toán</span
                    >
                  </div>
                </div>
              </div>
              <div class="product-risk-reduce-policies_line-item__nadGz">
                <div class="product-risk-reduce-line_wrapper__mDXz2 flex">
                  <div class="product-risk-reduce-line_icon__PPQoo">
                    <img :src="security" />
                  </div>
                  <div class="product-risk-reduce-line_content__KG_2F">
                    <span
                      class="font-regular text-yd-subtitle-3 text-yd-typo-label"
                      >Thông tin bảo mật và mã hoá</span
                    >
                  </div>
                </div>
              </div>
            </div>
            <li>
              Trải nghiệm mới từ chất liệu siêu co giãn, bề mặt vải hồi phục tốt
              ngay sau khi co giãn. Chất liệu thân thiện với môi trường. Thiết
              kế suông phù hợp với mọi vóc dáng, hình in Gấu nổi bật.
            </li>
          </div>
        </div>
      </div>
    </div>
    <div>
      <h2 class="font-bold text-2xl">Có thể bạn sẽ thích</h2>
      <div>
        <Carousel :breakpoints="obj.breakpoints" id="thumbnails" ref="carousel">
          <Slide v-for="(item, index) in productStore.product" :key="item.id">
            <Card :item="item" />
          </Slide>
          <template #addons>
            <Navigation />
          </template>
        </Carousel>
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

<style>
.carousel__slide {
  display: block;
}
.active {
  border: 2px solid black;
}
</style>
