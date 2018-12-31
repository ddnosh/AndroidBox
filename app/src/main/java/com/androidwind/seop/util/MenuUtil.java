package com.androidwind.seop.util;

import android.content.Context;
import android.content.res.AssetManager;

import com.androidwind.seop.bean.MenuBean;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class MenuUtil {

    public static List<MenuBean> position;

    public static List<MenuBean> getPositions(Context context, String fileName) {
        if (position == null) {
            initPositions(context, fileName);
        }
        return position;
    }

    private static void initPositions(Context context, String fileName) {
        String industryString = readAssetsTXT(context, fileName);
        String[] strings = industryString.split(";");
        position = new ArrayList<MenuBean>();
        for (int i = 0; i < strings.length; i++) {
            String[] items = strings[i].split(",");
            MenuBean tmp = new MenuBean();
            tmp.currentId = Integer.parseInt(items[0].trim());
            tmp.name = items[1];
            tmp.upperId = Integer.parseInt(items[2].trim());
            position.add(tmp);
        }
    }

    public static String readAssetsTXT(Context context, String fName) {
        try {
            AssetManager assetManager = context.getAssets();
            InputStream is = assetManager.open(fName);
            byte[] bytes = new byte[1024];
            int length;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((length = is.read(bytes)) != -1) {
                baos.write(bytes, 0, length);
            }
            return new String(baos.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}
