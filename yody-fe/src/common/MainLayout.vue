<script setup lang="ts">
import { Icon } from "@iconify/vue";
import logo from "@/assets/img/logo.svg";
</script>

<template lang="">
  <header class="border-b-2 relative">
    <nav class="flex justify-between items-center container m-auto">
      <div class="nav-left flex">
        <img :src="logo" alt="logo" />
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
                <a
                  href="#"
                  class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100"
                  role="menuitem"
                  tabindex="-1"
                  id="menu-item-0"
                  >Account settings</a
                >
                <a
                  href="#"
                  class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100"
                  role="menuitem"
                  tabindex="-1"
                  id="menu-item-1"
                  >Support</a
                >
                <a
                  href="#"
                  class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100"
                  role="menuitem"
                  tabindex="-1"
                  id="menu-item-2"
                  >License</a
                >
              </div>
              <div class="sub-menu">
                <h4>Áo Nam</h4>
                <a
                  href="#"
                  class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100"
                  role="menuitem"
                  tabindex="-1"
                  id="menu-item-0"
                  >Account settings</a
                >
                <a
                  href="#"
                  class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100"
                  role="menuitem"
                  tabindex="-1"
                  id="menu-item-1"
                  >Support</a
                >
                <a
                  href="#"
                  class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100"
                  role="menuitem"
                  tabindex="-1"
                  id="menu-item-2"
                  >License</a
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
          <a href="/cart" class="p-2">
            <Icon icon="solar:cart-5-outline" class="size-6" />
          </a>
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
