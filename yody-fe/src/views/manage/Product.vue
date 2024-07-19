<template lang="">
  <div>
    <div class="mt-7">
      <button
        @click="handleShowFormSubmit"
        class="px-4 py-2 bg-indigo-600 text-white rounded-md shadow-sm hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
      >
        Thêm sản phẩm
      </button>
    </div>
    <form class="space-y-4" v-if="state.showForm">
      <div>
        <label for="name" class="block text-sm font-medium text-gray-700"
          >Name</label
        >
        <input
          type="text"
          id="name"
          class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
        />
      </div>
      <div>
        <label for="description" class="block text-sm font-medium text-gray-700"
          >Description</label
        >
        <textarea
          id="description"
          class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
        ></textarea>
      </div>
      <div>
        <label for="price" class="block text-sm font-medium text-gray-700"
          >Price</label
        >
        <input
          type="number"
          id="price"
          class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
        />
      </div>

      <div>
        <label for="category" class="block text-sm font-medium text-gray-700"
          >Category</label
        >
        <select
          id="category"
          class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
        >
          <option></option>
        </select>
      </div>

      <div>
        <label for="colors" class="block text-sm font-medium text-gray-700"
          >Colors</label
        >
        <select
          id="colors"
          multiple
          class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
        ></select>
      </div>

      <div>
        <label for="sizes" class="block text-sm font-medium text-gray-700"
          >Sizes</label
        >
        <select
          id="sizes"
          multiple
          class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
        >
          <option>1</option>
          <option>1</option>
        </select>
      </div>
      <div>
        <label for="images" class="block text-sm font-medium text-gray-700"
          >Images</label
        >
        <input type="file" multiple />
      </div>

      <div>
        <button
          class="px-4 py-2 mr-3 bg-indigo-600 text-white rounded-md shadow-sm hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
        >
          Lưu
        </button>
        <button
          type="button"
          @click="handlehideFormSubmit"
          class="px-4 py-2 bg-red-400 text-white rounded-md shadow-sm hover:bg-red-500 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
        >
          Đóng
        </button>
      </div>
    </form>

    <h2 class="text-2xl font-semibold mb-4">Products</h2>
    <table class="min-w-full divide-y divide-gray-200">
      <thead class="bg-gray-50">
        <tr>
          <th
            class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider"
          >
            Name
          </th>
          <th
            class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider"
          >
            Description
          </th>
          <th
            class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider"
          >
            Price
          </th>
          <th
            class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider"
          >
            Category
          </th>
          <th
            class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider"
          >
            Colors
          </th>
          <th
            class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider"
          >
            Sizes
          </th>
        </tr>
      </thead>
      <tbody class="bg-white divide-y divide-gray-200"></tbody>
    </table>
  </div>
</template>
<script lang="ts">
import { defineComponent, reactive } from "vue";
import { Icon } from "@iconify/vue";

export default defineComponent({
  name: "MyComponent",
  setup() {
    const state = reactive({
      showForm: false,
      mode: false,
    });

    const handleShowFormSubmit = () => {
      state.showForm = true;
    };

    const handleHideFormSubmit = () => {
      state.showForm = false;
    };

    return {
      state,
      handleShowFormSubmit,
      handleHideFormSubmit,
    };
  },
});
</script>
<style></style>
