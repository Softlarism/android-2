package com.example.softlarism.network;

import com.example.softlarism.model.Comunidad;
import com.example.softlarism.model.Eventos;
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

    //Comunidad xd

    @GET ("api/comunidad")
    Call<List<Comunidad>> obtenerComunidades();

    @GET("api/comunidad/{id}")
    Call<Comunidad> obtenerComunidad(@Path("id")Integer id);

    @POST("api/comunidad")
    Call<Comunidad> crearComunidad (@Body Comunidad comunidad);

    @PUT("api/comunidad/{id}")
    Call<Comunidad> actualizarComunidad(@Path("id") Integer id,@Body Comunidad comunidad);

    @DELETE ("api/comunidad/{id}")
    Call<Void> eliminarComunidad(@Path("id")Integer id);

    //Eventos carajo

    @GET ("api/eventos")
    Call<List<Eventos>> obtenerEventos();

    @GET ("api/eventos/{id}")
    Call<Eventos> obtenerEventos (@Path("id") Integer id);

    @POST("api/eventos")
    Call<Eventos> crearEventos (@Body Eventos eventos);

    @PUT("api/eventos/{id}")
    Call<Eventos> actualizarEventos (@Path("id") Integer id, @Body Eventos eventos);

    @DELETE ("api/eventos/{id}")
    Call<Void> eliminarEventos(@Path("id")Integer id);
}
