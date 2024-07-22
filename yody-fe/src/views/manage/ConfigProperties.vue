<template lang="">
  <div>
    <h3 class="text-xl font-medium">Cấu hình {{ property }}</h3>
    <div class="mt-7">
      <button
        type="button"
        @click="handleShowFormSubmit"
        class="px-4 py-2 bg-indigo-600 text-white rounded-md shadow-sm hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
      >
        Thêm
      </button>
    </div>
    <Form class="space-y-4" v-if="state.showForm" @submit="onSubmit">
      <div class="mt-4" v-if="state.mode">
        <label for="id" class="block text-sm font-medium text-gray-700"
          >Id</label
        >
        <Field
          type="text"
          id="id"
          name="id"
          class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
        />
      </div>
      <div>
        <label for="name" class="block text-sm font-medium text-gray-700"
          >Name</label
        >
        <Field
          type="text"
          id="name"
          name="name"
          :value="state.mode ? '123' : ''"
          :rules="validateName"
          :placeholder="property == 'color' ? 'red, green, blue, ...' : ''"
          class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
        />
        <ErrorMessage name="name" class="text-red-400" />
      </div>
      <div>
        <button
          type="submit"
          class="px-4 py-2 mr-3 bg-indigo-600 text-white rounded-md shadow-sm hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
        >
          Lưu
        </button>
        <button
          @click="handlehideFormSubmit"
          type="button"
          class="px-4 py-2 bg-red-400 text-white rounded-md shadow-sm hover:bg-red-500 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
        >
          Đóng
        </button>
      </div>
    </Form>
    <div>
      <table class="min-w-full divide-y divide-gray-200">
        <thead class="bg-gray-50">
          <tr>
            <th
              class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider"
            >
              ID
            </th>
            <th
              class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider"
            >
              NAME
            </th>
          </tr>
        </thead>
        <tbody class="bg-white divide-y divide-gray-200">
          <tr
            v-if="property == 'size'"
            v-for="s in sizeStore.size"
            :key="s.id"
            :class="{ 'bg-gray-100': s.id % 2 === 0 }"
          >
            <td
              class="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-500"
            >
              {{ s.id }}
            </td>
            <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
              {{ s.name }}
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>
<script lang="ts">
import { defineComponent, onMounted, reactive } from "vue";
import { useRoute } from "vue-router";
import { Form, Field, ErrorMessage } from "vee-validate";
import categoryApi from "@/api/Category";
import sizeApi from "@/api/Size";
import colorApi from "@/api/Color";
import { useSizeStore } from "@/stores/SizeStore";
import { useCategoryStore } from "@/stores/CategoryStore";

export default defineComponent({
  name: "Configuration",
  setup() {
    const state = reactive({
      showForm: false,
    });
    const sizeStore = useSizeStore();
    const categoryStore = useCategoryStore();
    sizeStore.fetchAllsize();
    const handleShowFormSubmit = () => {
      state.showForm = true;
    };

    const handleHideFormSubmit = () => {
      state.showForm = false;
    };

    onMounted(() => {
      sizeStore.fetchAllsize().catch((error) => {
        console.error("Error fetching sizes on mount:", error);
      });
      categoryStore.fetchAllCategory().catch((error) => {
        console.error("Error fetching sizes on mount:", error);
      });
    });

    const route = useRoute();
    const onSubmit = async (values: any) => {
      const property = route.params.property;

      try {
        switch (property) {
          case "size":
            await sizeApi.create(values);
            break;
          case "category":
            await categoryApi.create(values);
            break;
          case "color":
            await colorApi.create(values);
            break;
          default:
            console.error("Unknown property type");
        }
      } catch (error) {
        console.error("Error submitting form:", error);
      }
    };

    const validateName = (value: string) => {
      return value.trim().length === 0 ? "This field is required" : true;
    };

    // Return properties and methods to be used in template
    return {
      state,
      handleShowFormSubmit,
      handleHideFormSubmit,
      onSubmit,
      validateName,
      property: route.params.property,
      sizeStore,
    };
  },
  components: {
    Form,
    Field,
    ErrorMessage,
  },
});
</script>

<style lang=""></style>
