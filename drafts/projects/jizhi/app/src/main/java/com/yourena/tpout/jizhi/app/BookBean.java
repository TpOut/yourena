package com.yourena.tpout.jizhi.app;

import com.yourena.tpout.jizhi.R;
import com.yourena.tpout.jizhi.app.util.LogUtil;
import com.yourena.tpout.jizhi.app.util.ResUtil;

import org.litepal.crud.DataSupport;

/**
 * Created by 41675 on 2018/2/18.
 */

public class BookBean extends DataSupport {

    private static String TAG = BookBean.class.getSimpleName();

    private String name = "";
    private int color = ResUtil.getColor(R.color.color_white);
    private String createDate = "";
    private String modifyDate = "";

    public BookBean(String name, int color, String createDate, String modifyDate) {
        this.name = name;
        this.color = color;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    }

    public static int getRandomColor() {
        int[] colors = new int[]{ResUtil.getColor(R.color.color_book_black), ResUtil.getColor(R.color.color_book_green),
                ResUtil.getColor(R.color.color_book_blue), ResUtil.getColor(R.color.color_book_red), ResUtil.getColor(R.color.color_book_yellow)};
        int index = (int) (Math.random() * colors.length);
        LogUtil.d(TAG, "---getRandomColor---index : " + index);
        return colors[index];
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Override
    public String toString() {
        return "BookBean {" +
                "name : " + name +
//                "notes : " + mNotes +
//                "modifyDate : " + modifyDate +
//                "createDate : " + createDate +
                "}";
    }
}
