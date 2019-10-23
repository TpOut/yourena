char默认既不是signed， 也不是unsigned，具体由C++实现决定。

当字符集无法使用8位字节表示的时候，char可能会被定义为一个16字节或更长；

也可能由底层（underlying）类型实现（上层对应的wchar_t）动态长度，在一些系统可能是unsigned short, 在另一个系统则是int

char16_t 和 char32_t 也有底层类型，随系统而异



cin和cout把输入输出看作是char流

所以需要wcin 和wcout

wchar_t bob = L'P';

wcout << L"tall" << endl;



char16_t ch1 = u'q';

char32_t ch2 = U'\U0000222B';



wchar_t title[] = L"Chief";

char16_t name[] = u"Felonia";

char32_t car[] = U"Humber";

UTF-8 的字符使用 u8前缀