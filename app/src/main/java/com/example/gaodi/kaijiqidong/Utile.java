/*
package com.nekocode.xuedao.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.nekocode.xuedao.PublicData;
import com.nekocode.xuedao.PublicData.Subscribe;

public class HtmlStorageHelper {
    private String URL = "http://eduproject.sinaapp.com/fetchurl.php/getcontent/";
    private PublicData pd;
    private AQuery aq;
    private SQLiteDatabase mDB;
    private String mDownloadPath;

    public HtmlStorageHelper(Context context) {
        pd = PublicData.getInstance();
        aq = new AQuery(context);
        mDB = context.openOrCreateDatabase("data.db", Context.MODE_PRIVATE, null);
        mDB.execSQL("create table if not exists download_html(_id INTEGER PRIMARY KEY AUTOINCREMENT, content_id TEXT NOT NULL, title TEXT NOT NULL)");
        mDownloadPath = pd.mAppPath + "download/";
        File dir_file = new File(pd.mAppPath + "download/");
        if (!dir_file.exists()) dir_file.mkdir();
    }

    public void saveHtml(final String id, final String title) {
        if (isHtmlSaved(id)) return;
        aq.ajax(URL + id, String.class, new AjaxCallback<String>() {
            @Override
            public void callback(String url, String html, AjaxStatus status) {
                File dir_file = new File(mDownloadPath + id);
                if (!dir_file.exists()) dir_file.mkdir();
                Pattern pattern = Pattern.compile("(?<=src=\")[^\"]+(?=\")");
                Matcher matcher = pattern.matcher(html);
                StringBuffer sb = new StringBuffer();
                while (matcher.find()) {
                    downloadPic(id, matcher.group(0));
                    matcher.appendReplacement(sb, formatPath(matcher.group(0)));
                }
                matcher.appendTail(sb);
                html = sb.toString();
                writeHtml(id, title, html);
            }
        });
    }

    private void downloadPic(String id, String url) {
        File pic_file = new File(mDownloadPath + id + "/" + formatPath(url));
        aq.download(url, pic_file, new AjaxCallback<File>() {
            @Override
            public void callback(String url, final File file, AjaxStatus status) {
            }
        });
    }

    private void writeHtml(String id, String title, String html) {
        File html_file = new File(mDownloadPath + id + "/index.html");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(html_file);
            fos.write(html.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        ContentValues values = new ContentValues();
        values.put("content_id", id);
        values.put("title", title);
        mDB.insert("download_html", "_id", values);
    }

    public boolean isHtmlSaved(String id) {
        File file = new File(mDownloadPath + id);
        if (file.exists()) {
            file = new File(mDownloadPath + id + "/index.html");
            if (file.exists()) return true;
        }
        deleteHtml(id);
        return false;
    }

    public String getTitle(String id) {
        Cursor c = mDB.rawQuery("select * from download_html where content_id=?", new String[]{id});
        if (c.getCount() == 0) return null;
        c.moveToFirst();
        int index1 = c.getColumnIndex("title");
        return c.getString(index1);
    }

    public ArrayList<Subscribe> getHtmlList() {
        Cursor c = mDB.rawQuery("select * from download_html", null);
        ArrayList<Subscribe> list = new ArrayList<Subscribe>();
        if (c.getCount() != 0) {
            c.moveToFirst();
            int index1 = c.getColumnIndex("content_id");
            int index2 = c.getColumnIndex("title");
            while (!c.isAfterLast()) {
                String id = c.getString(index1);
                if (isHtmlSaved(id)) {
                    Subscribe sub = new Subscribe(id, c.getString(index2), Subscribe.FILE_DOWNLOADED);
                    list.add(sub);
                }
                c.moveToNext();
            }
        }
        return list;
    }

    public void deleteHtml(String id) {
        mDB.delete("download_html", "content_id=?", new String[]{id});
        File dir_file = new File(mDownloadPath + id);
        deleteFile(dir_file);
    }

    private void deleteFile(File file) {
        if (file.exists()) { // 判断文件是否存在
            if (file.isFile()) { // 判断是否是文件
                file.delete(); // delete()方法 你应该知道 是删除的意思;
            } else if (file.isDirectory()) { // 否则如果它是一个目录
                File files[] = file.listFiles(); // 声明目录下所有的文件 files[];
                for (int i = 0; i < files.length; i++) { // 遍历目录下所有的文件
                    this.deleteFile(files[i]); // 把每个文件 用这个方法进行迭代
                }
            }
            file.delete();
        } else {   //
        }
    }

    private String formatPath(String path) {
        if (path != null && path.length() > 0) {
            path = path.replace("\\", "_");
            path = path.replace("/", "_");
            path = path.replace(":", "_");
            path = path.replace("*", "_");
            path = path.replace("?", "_");
            path = path.replace("\"", "_");
            path = path.replace("<", "_");
            path = path.replace("|", "_");
            path = path.replace(">", "_");
        }
        return path;
    }
}*/
