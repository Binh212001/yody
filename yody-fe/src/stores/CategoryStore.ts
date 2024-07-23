import { defineStore } from "pinia";
import sizeApi from "@/api/Size";
import categoryApi from "@/api/Category";

interface Category {
  name: string;
  id?: Number;
  slug: string;
}

export const useCategoryStore = defineStore("categories", {
  state: () => ({
    category: [],
    male: {
      ao: [],
      quan: [],
      sport: [],
      orther: [],
    },
    female: {
      ao: [],
      quan: [],
      sport: [],
      orther: [],
    },
    baby: {
      ao: [],
      quan: [],
    },
  }),

  actions: {
    async fetchAllCategory() {
      try {
        const { data } = await categoryApi.getAll();
        this.category = data;
        const male = data.filter((item: Category) => item.slug.includes("nam"));
        const aoNam = male.filter((item: Category) => item.slug.includes("ao"));
        const quanNam = male.filter((item: Category) =>
          item.slug.includes("quan")
        );
        const sportNam = male.filter((item: Category) =>
          item.slug.includes("the-thao")
        );
        const ortherNam = male.filter(
          (item: Category) =>
            !item.slug.includes("quan") &&
            !item.slug.includes("the-thao") &&
            !item.slug.includes("ao")
        );
        this.male.ao = aoNam;
        this.male.quan = quanNam;
        this.male.sport = sportNam;
        this.male.orther = ortherNam;
        const female = data.filter((item: Category) =>
          item.slug.includes("nu")
        );
        const aoNu = female.filter((item: Category) =>
          item.slug.includes("ao")
        );
        const quanNu = female.filter((item: Category) =>
          item.slug.includes("quan")
        );
        const sportNu = female.filter((item: Category) =>
          item.slug.includes("the-thao")
        );
        const ortherNu = female.filter(
          (item: Category) =>
            !item.slug.includes("quan") &&
            !item.slug.includes("the-thao") &&
            !item.slug.includes("ao")
        );
        this.female.ao = aoNu;
        this.female.quan = quanNu;
        this.female.sport = sportNu;
        this.female.orther = ortherNu;
        const baby = data.filter((item: Category) =>
          item.slug.includes("tre-em")
        );
        const ao = baby.filter((item: Category) => item.slug.includes("ao"));
        const quan = baby.filter((item: Category) =>
          item.slug.includes("quan")
        );
        this.baby.ao = ao;
        this.baby.quan = quan;
      } catch (error) {
        console.log("🚀 ~ fetchUserPreferences ~ error:", error);
      }
    },
  },
});
