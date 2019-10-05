package com.yourena.tpout.jizhi.act.edit;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.yourena.tpout.jizhi.R;
import com.yourena.tpout.jizhi.app.NoteBean;
import com.yourena.tpout.jizhi.app.base.BaseAct;
import com.yourena.tpout.jizhi.app.util.DateUtil;
import com.yourena.tpout.jizhi.app.util.LogUtil;
import com.yourena.tpout.jizhi.app.util.SpUtil;
import com.yourena.tpout.jizhi.app.util.ToastUtil;
import com.yourena.tpout.jizhi.frag.BookFrag;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by 41675 on 2018/2/18.
 */

public class EditAct extends BaseAct implements BookFrag.OnSelectListener, EasyPermissions.PermissionCallbacks {

    private static final String TAG_BOOK_FRAG = "TAG_BOOK_FRAG";

    private TextView textView;
    private String userName;
    private NoteBean noteBean;

    private static final String KEY_NOTE_BEAN = "KEY_NOTE_BEAN";

    public static void startEditAct(Context context, @Nullable NoteBean noteBean) {
        Intent intent = new Intent(context, EditAct.class);
        if (null != noteBean) {
            intent.putExtra(KEY_NOTE_BEAN, noteBean);
        }
        context.startActivity(intent);
    }

    public static void startEditActForResult(Context context, @Nullable NoteBean noteBean) {
        Intent intent = new Intent(context, EditAct.class);
        if (null != noteBean) {
            intent.putExtra(KEY_NOTE_BEAN, noteBean);
        }
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_edit);

        //init view
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textView = findViewById(R.id.tv_sign);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new BookFrag().show(getSupportFragmentManager(), TAG_BOOK_FRAG);
            }
        });

        Typeface fontFace = Typeface.createFromAsset(getAssets(),
                "fonts/o.TTF");
        ((EditText) findViewById(R.id.tv_summary)).setTypeface(fontFace);
        ((EditText) findViewById(R.id.tv_feel)).setTypeface(fontFace);
        ((TextView) findViewById(R.id.tv_sign)).setTypeface(fontFace);

        ((EditText) findViewById(R.id.et_page_num)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (null == s || 0 == s.length()) {
                    noteBean.setPageNum(-1);
                } else {
                    noteBean.setPageNum(Integer.valueOf(s.toString()));
                }
                buildSignText();
            }
        });

        findViewById(R.id.tv_feel_title).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFeelLayoutVisible(View.GONE);
                Snackbar.make(textView, "是否误点击？", Snackbar.LENGTH_SHORT)
                        .setAction("撤销隐藏", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setFeelLayoutVisible(View.VISIBLE);
                            }
                        }).show();

            }
        });

        //init data
        userName = SpUtil.getInstance().getString(SpUtil.KEY_USER_NAME);

        NoteBean bean = getIntent().<NoteBean>getParcelableExtra(KEY_NOTE_BEAN);
        if (null != bean) {
            noteBean = bean;
        } else {
            noteBean = new NoteBean();
        }
        buildSignText();


    }

    //感想布局
    private void setFeelLayoutVisible(int visible) {
        findViewById(R.id.tv_feel).setVisibility(visible);
        findViewById(R.id.tv_feel_title).setVisibility(visible);
    }

    private void buildSignText() {
        String nail = noteBean.getPageNum() == -1 ? "  " : ("");
        String numberString = noteBean.getPageNum() == -1 ? "" : ("第" + noteBean.getPageNum() + "页");
        SpannableStringBuilder style = new SpannableStringBuilder(userName + " 摘自《" +
                noteBean.getBookName() + "》" + numberString + "\n"
                + noteBean.getModifyDate() + nail);
        style.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_green)),
                userName.length() + 4, userName.length() + 4 + noteBean.getBookName().length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(style);
    }

    @Override
    public void onSelectListener(String bookName) {
        noteBean.setBookName(bookName);
        buildSignText();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is
        // present.
        getMenuInflater().inflate(R.menu.edit, menu);
        return true;
    }


    private static final int RC_WRITE_EXTERNAL_STORAGE = 1;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        saveViewToImage();
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        ToastUtil.showShort("拒绝权限将导致保存图片功能无法使用");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_save_pic) {
            LogUtil.d(TAG, "---onOptionsItemSelected---action_save_pic---");

            String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
            if (EasyPermissions.hasPermissions(this, perms)) {
                saveViewToImage();
            } else {
                // Do not have permissions, request them now
                EasyPermissions.requestPermissions(this, "请求存储权限！",
                        RC_WRITE_EXTERNAL_STORAGE, perms);
            }
            return true;
        } else if (id == R.id.action_save_text) {
            String s = textView.getText().toString();
            int bookNameStart = s.indexOf("《");
            int bookNameEnd = s.indexOf("》");
            noteBean.setBookName(s.substring(bookNameStart, bookNameEnd));
            noteBean.updateCurDateToModifyDate();
            noteBean.setSummary(((EditText) findViewById(R.id.tv_summary)).getText().toString());
            noteBean.setFeel(((EditText) findViewById(R.id.tv_feel)).getText().toString());
            LogUtil.d(TAG, "---onOptionsItemSelected---action_save_text---noteBean : " + noteBean);
            noteBean.save();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void saveViewToImage() {
        View view = findViewById(R.id.cl_pic_content);
        ((EditText) findViewById(R.id.tv_summary)).setCursorVisible(false);
        ((EditText) findViewById(R.id.tv_feel)).setCursorVisible(false);

        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);

//        view.setDrawingCacheEnabled(true);
//        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
//        view.setDrawingCacheEnabled(false);

        saveBitmap(bitmap, noteBean.getBookName());
        ((EditText) findViewById(R.id.tv_summary)).setCursorVisible(true);
        ((EditText) findViewById(R.id.tv_feel)).setCursorVisible(true);
    }

    private void saveBitmap(Bitmap bitmap, String bitName) {
        //获取内部存储状态
        String state = Environment.getExternalStorageState();
        //如果状态不是mounted，无法读写
        if (!state.equals(Environment.MEDIA_MOUNTED)) {
            ToastUtil.showShort("外部存储未挂载，无法存储！");
            return;
        }

        String dir = Environment.getExternalStorageDirectory().getAbsolutePath();

        File file = new File(Environment.getExternalStorageDirectory().getPath() + "/" + bitName + DateUtil.getCurDateOfString(DateUtil.FORMAT_YMDHMS) + ".png");
        if (file.exists()) {
            file.delete();
        }
        FileOutputStream out;
        try {
            out = new FileOutputStream(file);
            if (bitmap.compress(Bitmap.CompressFormat.PNG, 90, out)) {
                LogUtil.d(TAG, "---compress---");
                out.flush();
                out.close();
                //保存图片后发送广播通知更新数据库
                Uri uri = Uri.fromFile(file);
                sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
                ToastUtil.showShort("图片生成成功，请到相册查看！");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
