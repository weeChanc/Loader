package weechan.com.loading;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import static weechan.com.loading.LoadingTag.*;

/**
 * @author 214652773@qq.com
 * @user c
 * @create 2018/11/8 21:57
 */

public class LoadingWrapper extends FrameLayout {

    private View mLoadingView, mErrorView, mRetryView;


    private View getViewByState(int state) {
        switch (state) {
            case LOADING_STATE:
                return mLoadingView;
            case ERROR_STATE:
                return mErrorView;
            case RETRY_STATE:
                return mRetryView;
        }
        return null;
    }

    public void activeViewByState(int state) {
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).setVisibility(INVISIBLE);
        }
        View targetView = getViewByState(state);
        if (targetView != null) {
            targetView.setVisibility(VISIBLE);
        }
    }

    public LoadingWrapper(@NonNull Context context, Rect rect, @LayoutRes int loadingView,
                          @LayoutRes int errorView, @LayoutRes int retryView) {
        super(context);

        this.mLoadingView = LayoutInflater.from(getContext()).inflate(loadingView, this, false);
        this.mErrorView = LayoutInflater.from(getContext()).inflate(errorView, this, false);
        this.mRetryView = LayoutInflater.from(getContext()).inflate(retryView, this, false);

        setClipChildren(false);
        init(rect);
    }

    private void init(final Rect border) {


        mLoadingView.setVisibility(INVISIBLE);
        mErrorView.setVisibility(INVISIBLE);
        mRetryView.setVisibility(INVISIBLE);

        addView(mLoadingView);
        addView(mErrorView);
        addView(mRetryView);

        addOnLayoutChangeListener(new OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (top != border.top) {
                    layout(border.left, border.top, border.right, border.bottom);
                    setVisibility(View.VISIBLE);
                }else{
                    mErrorView.layout(0,0,getWidth(),getHeight());
                    mLoadingView.layout(0,0,getWidth(),getHeight());
                    mRetryView.layout(0,0,getWidth(),getHeight());
                }
            }
        });
    }

}
