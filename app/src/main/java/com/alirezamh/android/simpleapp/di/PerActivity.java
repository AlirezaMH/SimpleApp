package com.alirezamh.android.simpleapp.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Author:      Alireza Mahmoodi
 * Created:     8/19/2018
 * Email:       mahmoodi.dev@gmail.com
 * Website:     alirezamh.com
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
