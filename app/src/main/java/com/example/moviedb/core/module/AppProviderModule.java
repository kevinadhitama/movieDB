package com.example.moviedb.core.module;

import com.example.moviedb.core.provider.ApiProvider;
import com.example.moviedb.core.provider.ApiProviderImpl;
import com.example.moviedb.core.provider.ApiRoute;
import com.example.moviedb.core.provider.NavigationProvider;
import com.example.moviedb.core.provider.NavigationProviderImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by kevin.adhitama on 2019-08-15.
 */
@Module
public class AppProviderModule {

    @Singleton
    @Provides
    ApiProvider providesApiRepository() {
        return new ApiProviderImpl();
    }

    @Singleton
    @Provides
    ApiRoute providesApiRoute() {
        return new ApiRoute();
    }

    @Singleton
    @Provides
    NavigationProvider providesNavigationProvider() {
        return new NavigationProviderImpl();
    }
}
