package net.jmreyes.tutela.api.services;

import net.jmreyes.tutela.model.User;
import retrofit.Callback;
import retrofit.http.GET;

import java.util.List;

/**
 * Created by juanma on 8/11/14.
 */
public interface UserService {

    @GET("/user/me/role")
    void getRole(Callback<String> callback);
}
