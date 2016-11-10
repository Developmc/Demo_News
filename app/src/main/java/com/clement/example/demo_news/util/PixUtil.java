package com.clement.example.demo_news.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

/**单位转换类
 * Created by clement on 16/11/9.
 */

public class PixUtil {
    public static float convertDpToPixel(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return px;
    }
}
