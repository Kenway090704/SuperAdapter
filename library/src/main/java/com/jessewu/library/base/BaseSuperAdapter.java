package com.jessewu.library.base;
/*
 * ===========================
 * BaseSuperAdapter     2017/08/29
 *      
 * Created by JesseWu
 * ===========================
 */

import android.support.v7.widget.RecyclerView;

import com.jessewu.library.builder.ExpandHeaderBuilder;
import com.jessewu.library.builder.FooterBuilder;
import com.jessewu.library.builder.HeaderBuilder;
import com.jessewu.library.builder.MultiItemViewBuilder;
import com.jessewu.library.paging.LoadDataListener;
import com.jessewu.library.view.ViewHolder;

import java.util.HashSet;
import java.util.Set;

public abstract class BaseSuperAdapter extends RecyclerView.Adapter<ViewHolder> {


    // 唯一头部item类型
    protected static final int TYPE_HEAD_SINGLE = 1000;

    // 底部item类型
    protected static final int TYPE_FOOT = 1001;

    // 无用类型
    protected static final int TYPE_IGNORE = 1002;

    // 没有数据时提示视图item类型
    protected static final int TYPE_EMPTY = 1003;

    // 正在获取数据中
    protected static final int LOADING = 2000;

    // 获取数据失败
    protected static final int LOADING_FAILURE = 2001;

    // 没有更多的数据
    protected static final int LOADING_NO_MORE = 2002;

    // 唯一头部布局id
    protected int mSingleItemViewLayoutId;

    // 没有数据时显示的提示视图布局文件id
    protected int mEmptyLayoutId = TYPE_IGNORE;

    // 特殊布局计数器，用来记录特殊布局数量
    protected Set<String> mSpecialViewBuilder = new HashSet<>();

    // 多类型item 构建器
    protected MultiItemViewBuilder mMultiItemViewBuilder;

    // 分类列表构造器
    protected ExpandHeaderBuilder mExpandHeaderBuilder;

    // 唯一头部视图构建器
    protected HeaderBuilder mHeaderBuilder;

    // 底部视图构建器
    protected FooterBuilder mFooterBuilder;

    // 加载更多数据监听器
    protected LoadDataListener mLoadDataListener;


    /**
     * 获取里列表中特殊控件的数量
     */
    protected int getSpecialBuilderNum(){
        return mSpecialViewBuilder.size();
    }



    /**
     * 是否为多类型item
     */
    protected boolean isMultiItemView(){
        return mMultiItemViewBuilder != null;
    }

    /**
     * 是否为分类头部列表
     */
    protected boolean isExpandHeaderView(){
        return this.mExpandHeaderBuilder != null;
    }

    /**
     * 是否存在header
     */
    protected boolean hasHeaderView(){
        return mHeaderBuilder != null;
    }

    /**
     * 是否存在footer
     */
    protected boolean hasFooterView(){
        return mFooterBuilder != null;
    }

    /**
     * 是否需要加载更多数据
     */
    protected boolean shouldLoadMoreData(){
        return hasFooterView() && mLoadDataListener != null;
    }

    /**
     * 是否设置过空视图
     */
    protected boolean hasEmptyView(){
        return mEmptyLayoutId != TYPE_IGNORE;
    }

    /**
     * 重新效验position
     *
     * 当添加了特殊View后会对列表中itemView的position造成错乱，需要重新效验position
     */
    protected int checkPosition(int position){

        int newPosition = position;

        if (hasHeaderView() && position != 0){
           newPosition = position - 1;
        }
        return newPosition;
    }

    /**
     * 底部构造器未加载异常
     */
    public static class FooterBuilderNotLoadedException extends RuntimeException {
        public FooterBuilderNotLoadedException() {
            super("The FooterBuilder is not loaded");
        }
    }

}
