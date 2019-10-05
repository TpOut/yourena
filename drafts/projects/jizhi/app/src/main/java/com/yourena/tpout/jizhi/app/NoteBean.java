package com.yourena.tpout.jizhi.app;

import android.os.Parcel;
import android.os.Parcelable;

import com.yourena.tpout.jizhi.app.util.DateUtil;

import org.litepal.crud.DataSupport;

/**
 * Created by 41675 on 2018/2/18.
 */

public class NoteBean extends DataSupport implements Parcelable {

    private String bookName = "";
    private int pageNum = -1;
    private String summary = "";
    private String feel = "";
    private String createDate = "";
    private String modifyDate = "";

    @Override
    public String toString() {
        return "NoteBean{" +
                "bookName : " + bookName +
                "pageNum : " + pageNum +
                "summary : " + summary +
                "feel : " + feel +
                "createDate : " + createDate +
                "modifyDate : " + modifyDate +
                "}";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.bookName);
        dest.writeInt(this.pageNum);
        dest.writeString(this.summary);
        dest.writeString(this.feel);
        dest.writeString(this.createDate);
        dest.writeString(this.modifyDate);
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getFeel() {
        return feel;
    }

    public void setFeel(String feel) {
        this.feel = feel;
    }

    public String getCreateDate() {
        return createDate;
    }

    private void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void updateCurDateToModifyDate() {
        setModifyDate(DateUtil.getCurDateOfString(DateUtil.FORMAT_YMDHM));
    }

    private void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public NoteBean() {
        String curDateOfString = DateUtil.getCurDateOfString(DateUtil.FORMAT_YMDHM);
        setCreateDate(curDateOfString);
        setModifyDate(curDateOfString);
    }

    protected NoteBean(Parcel in) {
        this.bookName = in.readString();
        this.pageNum = in.readInt();
        this.summary = in.readString();
        this.feel = in.readString();
        this.createDate = in.readString();
        this.modifyDate = in.readString();
    }

    public static final Parcelable.Creator<NoteBean> CREATOR = new Parcelable.Creator<NoteBean>() {
        @Override
        public NoteBean createFromParcel(Parcel source) {
            return new NoteBean(source);
        }

        @Override
        public NoteBean[] newArray(int size) {
            return new NoteBean[size];
        }
    };
}
