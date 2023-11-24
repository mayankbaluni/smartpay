package com.smart.pay.views.seatview;

import android.content.Context;
import android.util.TypedValue;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Collection;

/**
 * 工具类。
 *
 * @author wuzhen
 * @since 2017-04-20
 */
final class Utils {

    static int dp2px(@NonNull Context context, float dpValue) {
        float value = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dpValue,
                context.getResources().getDisplayMetrics());
        return (int) (value + 0.5f);
    }

    static int size(@Nullable Collection<?> collection) {
        if (collection == null) {
            return 0;
        }
        return collection.size();
    }

    static boolean isEmpty(@Nullable Collection<?> collection) {
        return size(collection) == 0;
    }
}
