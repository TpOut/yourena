#include <stdio.h>

int main() {

    extern void find_char(char **strings, char value);
    extern void find_char_(char *strings, char value);

    char *str1 = "A string";
    char *str2 = "Another";
    char *str3 = "Third";
    char *str4 = "Last";

    char *strings[] = {str1, str2, str3, str4, NULL};

    printf("%s\n", strings[1]);

    find_char_(str1, 'o');
    find_char(strings, 'o');

    return 0;
}

void find_char_(char *strings, char value) {
    while (*strings != '\0') {
        if (*strings++ == value) {
            printf("%s\n", "get it !");
        }
    }
}

void find_char(char **strings, char value) {
    char *string;
    while ((string = *strings++) != NULL) {
        while (*string != '\0') {
            if (*string++ == value) {
                printf("%s\n", "get it !");
            }
        }
    }

}
