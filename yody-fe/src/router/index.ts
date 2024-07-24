import { createRouter, createWebHistory } from "vue-router";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "home",
      component: () => import("@/views/Home.vue"),
    },
    {
      path: "/product/:id",
      name: "product-deatil",
      component: () => import("@/views/ProductDetail.vue"),
    },
    {
      path: "/cart",
      name: "cart",
      component: () => import("@/views/cart.vue"),
    },
    {
      path: "/category/:categoryName",
      name: "category",
      component: () => import("@/views/Category.vue"),
    },
    {
      path: "/checkout/:code",
      name: "checkout",
      component: () => import("@/views/Checkout.vue"),
    },
    {
      path: "/management/home",
      name: "management-home",
      component: () => import("@/views/manage/Home.vue"),
    },
    {
      path: "/management/product",
      name: "management-product",
      component: () => import("@/views/manage/Product.vue"),
    },
    {
      path: "/management/product/:slug",
      name: "management-product-deatil",
      component: () => import("@/views/manage/ProductDetail.vue"),
    },
    {
      path: "/management/config/:property",
      name: "management-config",
      component: () => import("@/views/manage/ConfigProperties.vue"),
    },
  ],
});

export default router;
