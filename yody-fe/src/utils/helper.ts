export const createSlug = (text: string): string => {
  // Convert to lowercase
  let slug = text.toLowerCase();

  // Remove accents and diacritical marks
  slug = slug.normalize("NFD").replace(/[\u0300-\u036f]/g, "");

  // Replace spaces and special characters with hyphens
  slug = slug
    .replace(/[^a-z0-9\s-]/g, "") // Remove non-alphanumeric characters except spaces and hyphens
    .replace(/\s+/g, "-") // Replace spaces with hyphens
    .replace(/-+/g, "-"); // Remove multiple hyphens

  // Trim hyphens from start and end
  slug = slug.replace(/^-+|-+$/g, "");

  return slug;
};
