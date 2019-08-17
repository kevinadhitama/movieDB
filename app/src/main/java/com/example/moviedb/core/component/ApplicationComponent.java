package com.example.moviedb.core.component;

import com.example.moviedb.core.module.AppProviderModule;
import com.example.moviedb.page.landing.LandingActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by kevin.adhitama on 2019-08-15.
 */
@Singleton
@Component(modules = {AppProviderModule.class})
public interface ApplicationComponent {

    void inject(LandingActivity landingActivity);

}
