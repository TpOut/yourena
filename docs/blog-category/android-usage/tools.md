```
    /**
     * 中文验证
     */
    private static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }
```
不写dimen 单位  
```
<resources>
    <!--没有单位的dimens-->
    <!--代码中引用请查看getNoUnitDimens()方法-->
    <item name="text_line_spacing" format="float" type="dimen">1.2</item>
</resources>

    /**
     * 引用没有单位的dimens
     */
    private void getNoUnitDimens() {
        TypedValue outValue = new TypedValue();
        mAppContext.getResources().getValue(R.dimen.text_line_spacing, outValue, true);
        float value = outValue.getFloat();
    }
```

