package com.yongxing.activity.demo01.utils;

/**
 * Created by Administrator on 2018-01-24.
 */

public interface ItemTouchHelperAdapter {
    void onItemMove(int formPosition, int toPosition);
    void onItemDelete(int position);
}
