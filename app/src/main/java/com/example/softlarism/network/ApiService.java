package com.example.softlarism.network;

import com.example.softlarism.model.Usuarios;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {

    @GET ("api/usuarios")
    Call<List<Usuarios>> obtenerUsuarios();

    @GET("api/usuarios/{id}")
    Call<Usuarios> obtenerUsuarios(@Path("id")Long id);

    @POST("api/usuarios")
    Call<Usuarios> crearUsuarios(@Body Usuarios usuarios);

    @PUT ("api/usuarios/{id}")
    Call<Usuarios> actualizarUsuarios(@Path("id")Long id,@Body Usuarios usuarios);

    @DELETE("api/usuarios/{id}")
    Call<Void> eliminarUsuarios (@Path("id")Long id);
}
