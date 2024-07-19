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
    const quantityRef = ref(0);
    const obj = reactive({
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

    const handleIncrement = () => {
      quantityRef.value++;
    };

    const handleDecrement = () => {
      if (quantityRef.value > 0) {
        quantityRef.value--;
      }
    };

    return {
      star,
      oclock,
      arrowTwoWay,
      productRiskReduce,
      security,
      quantityRef,
      obj,
      handleIncrement,
      handleDecrement,
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
              >
                Thêm vào giỏ
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
        <Carousel
          :breakpoints="obj.breakpoints"
          id="thumbnails"
          v-model="currentSlide"
          ref="carousel"
        >
          <Slide v-for="slide in 10" :key="slide">
            <Card
              image="../assets/img/avatar.jpg"
              displayName="Iphone12"
              :price="19.99"
              :colors="['red', 'blue', 'white']"
            />
          </Slide>
          <template #addons>
            <Navigation />
            <Pagination />
          </template>
        </Carousel>
      </div>
    </div>
  </div>
</template>

<style>
.carousel__slide {
  display: block;
}
</style>
