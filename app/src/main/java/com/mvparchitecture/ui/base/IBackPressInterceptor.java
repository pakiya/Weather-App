package com.mvparchitecture.ui.base;

public interface IBackPressInterceptor {
    void onBackPressed(BackCallBack backCallBack);

    abstract class BackCallBack {

        public void backHandled() {
            backCompleted();
        }

        public abstract void backCompleted();
    }
}
