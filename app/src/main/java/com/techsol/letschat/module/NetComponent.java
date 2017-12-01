package com.techsol.letschat.module;


import com.techsol.letschat.ui.TabActivity;

import dagger.Component;

@Component(modules = {AppModule.class, NetworkModule.class})
public interface NetComponent {
    void inject(TabActivity activity);
}
