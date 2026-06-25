export interface FiltersState {
  readonly searchTerm: string;
}

export const initialFiltersState: FiltersState = {
  searchTerm: '',
};
