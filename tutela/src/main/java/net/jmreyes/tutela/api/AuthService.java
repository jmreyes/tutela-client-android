package net.jmreyes.tutela.api;

import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by juanma on 2/11/14.
 */
public interface AuthService {
    @FormUrlEncoded
    @POST("/oauth/token?grant_type=password") String getAccessToken(
            @Field("username") String email,
            @Field("password") String password,
            @Field("client_id") String clientId);
}
