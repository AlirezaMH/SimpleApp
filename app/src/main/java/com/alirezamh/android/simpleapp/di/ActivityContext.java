package com.alirezamh.android.simpleapp.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Author:      Alireza Mahmoodi
 * Created:     8/19/2018
 * Email:       mahmoodi.dev@gmail.com
 * Website:     alirezamh.com
 */

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityContext {
}