<script lang="ts">
import { Icon } from "@iconify/vue";
import logo from "@/assets/img/logo.svg";
import { useCategoryStore } from "@/stores/CategoryStore";
import { defineComponent, onMounted } from "vue";
import menuMan from "@/assets/img/menu_man.webp";
import menuWoman from "@/assets/img/menu_woman.webp";
import menuChilren from "@/assets/img/menu_children.webp";

export default defineComponent({
  name: "MainLayout",
  setup() {
    const categoryStore = useCategoryStore();
    onMounted(() => {
      categoryStore.fetchAllCategory().catch((error) => {
        console.error("Error fetching sizes on mount:", error);
      });
    });
    return {
      categoryStore,
      logo,
      menuMan,
      menuWoman,
      menuChilren,
    };
  },
  components: {
    Icon,
  },
});
</script>

<template lang="">
  <header class="border-b-2 relative">
    <nav class="flex justify-between items-center container m-auto py-2">
      <div class="nav-left flex">
        <RouterLink to="/">
          <img :src="logo" alt="logo" />
        </RouterLink>
        <div class="inline-block text-left dropdown">
          <span
            class="inline-flex justify-center w-full px-4 py-2 text-sm font-medium text-gray-700 focus:outline-none hover:border-b-2"
            id="menu-button"
            aria-expanded="true"
            aria-haspopup="true"
          >
            Nam
          </span>
          <div
            class="hidden z-20 w-[100vw] origin-top-right absolute right-0 rounded-md shadow-lg bg-white ring-1 ring-black ring-opacity-5 focus:outline-none dropdown-menu"
            role="menu"
            aria-orientation="vertical"
            aria-labelledby="menu-button"
            tabindex="-1"
          >
            <div class="py-1 flex container m-auto gap-5" role="none">
              <div class="sub-menu">
                <h4 class="font-bold">Áo Nam</h4>
                <RouterLink
                  v-for="(item, index) in categoryStore.male.ao"
                  :key="index"
                  :to="`/category/${item.id}`"
                  class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100"
                  role="menuitem"
                  tabindex="-1"
                  id="menu-item-0"
                  >{{ item.name }}</RouterLink
                >
              </div>
              <div class="sub-menu">
                <h4 class="font-bold">Quần Nam</h4>
                <RouterLink
                  v-for="(item, index) in categoryStore.male.quan"
                  :key="index"
                  :to="`/category/${item.id}`"
                  class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100"
                  role="menuitem"
                  tabindex="-1"
                  id="menu-item-0"
                  >{{ item.name }}</RouterLink
                >
              </div>
              <div class="sub-menu">
                <h4 class="font-bold">Đồ thể thao Nam</h4>
                <RouterLink
                  v-for="(item, index) in categoryStore.male.sport"
                  :key="index"
                  :to="`/category/${item.id}`"
                  class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100"
                  role="menuitem"
                  tabindex="-1"
                  id="menu-item-0"
                  >{{ item.name }}</RouterLink
                >
              </div>
              <div class="sub-menu">
                <h4 class="font-bold">Khác</h4>
                <RouterLink
                  v-for="(item, index) in categoryStore.male.orther"
                  :key="index"
                  :to="`/category/${item.id}`"
                  class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100"
                  role="menuitem"
                  tabindex="-1"
                  id="menu-item-0"
                  >{{ item.name }}</RouterLink
                >
              </div>
              <div class="sub-menu">
                <img :src="menuMan" alt="menu-man" class="w-[400px]" />
              </div>
            </div>
          </div>
        </div>
        <!-- //Male -->

        <div class="inline-block text-left dropdown">
          <span
            class="inline-flex justify-center w-full px-4 py-2 text-sm font-medium text-gray-700 focus:outline-none hover:border-b-2"
            id="menu-button"
            aria-expanded="true"
            aria-haspopup="true"
          >
            Nữ
          </span>
          <div
            class="hidden z-20 w-[100vw] origin-top-right absolute right-0 rounded-md shadow-lg bg-white ring-1 ring-black ring-opacity-5 focus:outline-none dropdown-menu"
            role="menu"
            aria-orientation="vertical"
            aria-labelledby="menu-button"
            tabindex="-1"
          >
            <div class="py-1 flex container m-auto gap-5" role="none">
              <div class="sub-menu">
                <h4 class="font-bold">Áo Nữ</h4>
                <RouterLink
                  v-for="(item, index) in categoryStore.female.ao"
                  :key="index"
                  :to="`/category/${item.id}`"
                  class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100"
                  role="menuitem"
                  tabindex="-1"
                  id="menu-item-0"
                  >{{ item.name }}</RouterLink
                >
              </div>
              <div class="sub-menu">
                <h4 class="font-bold">Quần Nữ</h4>
                <RouterLink
                  v-for="(item, index) in categoryStore.female.quan"
                  :key="index"
                  :to="`/category/${item.id}`"
                  class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100"
                  role="menuitem"
                  tabindex="-1"
                  id="menu-item-0"
                  >{{ item.name }}</RouterLink
                >
              </div>
              <div class="sub-menu">
                <h4 class="font-bold">Đồ thể thao Nữ</h4>
                <RouterLink
                  v-for="(item, index) in categoryStore.female.sport"
                  :key="index"
                  :to="`/category/${item.id}`"
                  class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100"
                  role="menuitem"
                  tabindex="-1"
                  id="menu-item-0"
                  >{{ item.name }}</RouterLink
                >
              </div>
              <div class="sub-menu">
                <h4 class="font-bold">Khác</h4>
                <RouterLink
                  v-for="(item, index) in categoryStore.female.orther"
                  :key="index"
                  :to="`/category/${item.id}`"
                  class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100"
                  role="menuitem"
                  tabindex="-1"
                  id="menu-item-0"
                  >{{ item.name }}</RouterLink
                >
              </div>
              <div class="sub-menu">
                <img :src="menuWoman" alt="menu-man" class="w-[400px]" />
              </div>
            </div>
          </div>
        </div>
        <!-- BABY -->
        <div class="inline-block text-left dropdown">
          <span
            class="inline-flex justify-center w-full px-4 py-2 text-sm font-medium text-gray-700 focus:outline-none hover:border-b-2"
            id="menu-button"
            aria-expanded="true"
            aria-haspopup="true"
          >
            Trẻ em
          </span>
          <div
            class="hidden z-20 w-[100vw] origin-top-right absolute right-0 rounded-md shadow-lg bg-white ring-1 ring-black ring-opacity-5 focus:outline-none dropdown-menu"
            role="menu"
            aria-orientation="vertical"
            aria-labelledby="menu-button"
            tabindex="-1"
          >
            <div class="py-1 flex container m-auto gap-5" role="none">
              <div class="sub-menu">
                <h4 class="font-bold">Áo Trẻ em</h4>
                <RouterLink
                  v-for="(item, index) in categoryStore.baby.ao"
                  :key="index"
                  :to="`/category/${item.id}`"
                  class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100"
                  role="menuitem"
                  tabindex="-1"
                  id="menu-item-0"
                  >{{ item.name }}</RouterLink
                >
              </div>
              <div class="sub-menu">
                <h4 class="font-bold">Quần Trẻ em</h4>
                <RouterLink
                  v-for="(item, index) in categoryStore.baby.quan"
                  :key="index"
                  :to="`/category/${item.id}`"
                  class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100"
                  role="menuitem"
                  tabindex="-1"
                  id="menu-item-0"
                  >{{ item.name }}</RouterLink
                >
              </div>
              <div class="sub-menu">
                <img :src="menuChilren" alt="menu-man" class="w-[400px]" />
              </div>
            </div>
          </div>
        </div>
        <!-- Collection -->
        <div class="inline-block text-left dropdown">
          <span
            class="inline-flex justify-center w-full px-4 py-2 text-sm font-medium text-gray-700 focus:outline-none hover:border-b-2"
            id="menu-button"
            aria-expanded="true"
            aria-haspopup="true"
          >
            Bộ sưu tập
          </span>
          <div
            class="hidden z-20 w-[100vw] origin-top-right absolute right-0 rounded-md shadow-lg bg-white ring-1 ring-black ring-opacity-5 focus:outline-none dropdown-menu"
            role="menu"
            aria-orientation="vertical"
            aria-labelledby="menu-button"
            tabindex="-1"
          >
            <div class="py-1 flex container m-auto gap-5" role="none">
              <div class="sub-menu">
                <h4 class="font-bold">Áo Trẻ em</h4>
                <RouterLink
                  v-for="(item, index) in categoryStore.baby.ao"
                  :key="index"
                  :to="`/category/${item.id}`"
                  class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100"
                  role="menuitem"
                  tabindex="-1"
                  id="menu-item-0"
                  >{{ item.name }}</RouterLink
                >
              </div>
            </div>
          </div>
        </div>
      </div>
      <!-- Right header -->
      <div class="nav-right flex items-center gap-5">
        <div
          class="box-search rounded-[30px] bg-slate-200 flex p-2 items-center"
        >
          <input
            type="text"
            placeholder="Tìm kiếm"
            class="outline-none border-none bg-slate-200"
          />
          <Icon icon="iconamoon:search-thin" />
        </div>
        <div>
          <a-badge count="25" class="flex">
            <RouterLink to="/cart">
              <Icon icon="solar:cart-5-outline" class="size-6" />
            </RouterLink>
          </a-badge>
        </div>
      </div>
    </nav>
  </header>
  <main class="container m-auto">
    <slot></slot>
  </main>
  <footer></footer>
</template>

<style lang="css">
.dropdown:hover .dropdown-menu,
.dropdown-submenu:hover .dropdown-menu {
  display: block;
}
</style>
