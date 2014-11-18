package net.jmreyes.tutela.api.services;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by juanma on 8/11/14.
 */
public interface UserService {

    @GET("/user/me/role")
    void getRole(Callback<String> callback);
}
