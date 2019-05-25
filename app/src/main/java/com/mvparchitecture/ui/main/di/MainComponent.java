package com.mvparchitecture.ui.main.di;

import com.mvparchitecture.ui.main.MainActivity;

import dagger.Component;

@Component(modules = MainModule.class)
public interface MainComponent {
    void injectMainActivity(MainActivity activity);
}
