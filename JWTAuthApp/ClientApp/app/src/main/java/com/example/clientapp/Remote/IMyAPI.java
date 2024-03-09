package com.example.clientapp.Remote;

import com.example.clientapp.Model.tblUser;

import io.reactivex.Observable;

import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IMyAPI {
    @POST("register")
    Observable<String> registerUser(@Body tblUser user);

    @POST("login")
    Observable<String> loginUser(@Body tblUser user);
}