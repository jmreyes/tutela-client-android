package net.jmreyes.tutela.api.services;

import net.jmreyes.tutela.model.extra.LoginResponse;
import retrofit.Callback;
import retrofit.http.*;

/**
 * Created by juanma on 2/11/14.
 */
public interface AuthService {
    @FormUrlEncoded
    @POST("/oauth/token?grant_type=password") void getAccessToken(
            @Header("Authorization") String authHeader,
            @Field("username") String email,
            @Field("password") String password,
            Callback<LoginResponse> loginResponseCallback);
}
