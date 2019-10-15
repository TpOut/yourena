

int calc_array_size(char arrays[]){
  return sizeof(arrays) / sizeof(arrays[0]);
}

int is_strs_equals(char *str1, char *str2){
  return strcmp(str1, str2) == 0;
}
