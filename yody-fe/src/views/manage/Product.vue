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
    <a-modal
      v-model:open="state.showForm"
      title="Basic Modal"
      :footer="state.footer"
    >
      <Form class="space-y-4" @submit="handleOk">
        <div>
          <label for="name" class="block text-sm font-medium text-gray-700"
            >Name</label
          >
          <Field
            type="text"
            id="name"
            name="name"
            class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
          />
        </div>
        <div>
          <label
            for="description"
            class="block text-sm font-medium text-gray-700"
            >Description</label
          >
          <Field
            as="textarea"
            id="description"
            name="description"
            class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
          ></Field>
        </div>
        <div>
          <label for="price" class="block text-sm font-medium text-gray-700"
            >Price</label
          >
          <Field
            type="number"
            id="price"
            name="price"
            class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
          />
        </div>

        <div>
          <label for="category" class="block text-sm font-medium text-gray-700"
            >Category</label
          >
          <Field
            as="select"
            id="category"
            name="category"
            class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
          >
            <option>1</option>
            <option>1</option>
            <option>1</option>
          </Field>
        </div>

        <div>
          <label for="colors" class="block text-sm font-medium text-gray-700"
            >Colors</label
          >
          <Field
            as="select"
            id="colors"
            name="colors"
            multiple
            class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
          ></Field>
        </div>

        <div>
          <label for="sizes" class="block text-sm font-medium text-gray-700"
            >Sizes</label
          >
          <Field
            as="select"
            name="sizes"
            id="sizes"
            multiple
            class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
          >
            <option value="1">1</option>
            <option value="2">1</option>
          </Field>
        </div>
        <div>
          <label for="images" class="block text-sm font-medium text-gray-700"
            >Images</label
          >
          <Field name="image" type="file" multiple />
        </div>
        <div class="flex justify-end">
          <button
            type="submit"
            class="px-4 py-2 bg-blue-600 text-white rounded-md shadow-sm hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500"
          >
            Submit
          </button>
        </div>
      </Form>
    </a-modal>

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
import { Form, Field, ErrorMessage } from "vee-validate";

export default defineComponent({
  name: "MyComponent",
  setup() {
    const state = reactive({
      showForm: false,
      mode: false,
      footer: null,
    });
    const handleShowFormSubmit = () => {
      state.showForm = true;
    };

    const handleHideFormSubmit = () => {
      state.showForm = false;
    };
    const handleOk = (val: any) => {
      state.showForm = false;
    };

    return {
      state,
      handleOk,
      handleShowFormSubmit,
      handleHideFormSubmit,
    };
  },
  components: {
    Form,
    Field,
    ErrorMessage,
  },
});
</script>
<style></style>
