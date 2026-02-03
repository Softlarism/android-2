package com.example.softlarism.network;

import com.example.softlarism.model.Comunidad;
import com.example.softlarism.model.Eventos;
import com.example.softlarism.model.LoginRequest;
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

    @GET ("Softlarism/api/Usuarios")
    Call<List<Usuarios>> obtenerUsuarios();

    @GET("Softlarims/api/Usuarios/{id}")
    Call<Usuarios> obtenerUsuarios(@Path("id")Long id);

    @POST("Softlarism/api/Usuarios")
    Call<Usuarios> crearUsuarios(@Body Usuarios usuarios);

    @PUT ("Softlarism/api/Usuarios/{id}")
    Call<Usuarios> actualizarUsuarios(@Path("id")Long id,@Body Usuarios usuarios);

    @DELETE("Softlarism/api/Usuarios/{id}")
    Call<Void> eliminarUsuarios (@Path("id")Long id);

    @POST("Softlarism/api/Usuarios/registro")
    Call<Usuarios> registrarUsuarios (@Body Usuarios usuarios);

    @POST("Softlarims/api/Usuarios/login")
    Call<Usuarios> login (@Body LoginRequest loginRequest);

    //Comunidad xd

    @GET ("Softlarism/api/comunidad")
    Call<List<Comunidad>> obtenerComunidades();

    @GET("Softlarism/api/comunidad/{id}")
    Call<Comunidad> obtenerComunidad(@Path("id")Integer id);

    @POST("Softlarism/api/comunidad")
    Call<Comunidad> crearComunidad (@Body Comunidad comunidad);

    @PUT("Softlarism/api/comunidad/{id}")
    Call<Comunidad> actualizarComunidad(@Path("id") Integer id,@Body Comunidad comunidad);

    @DELETE ("Softlarism/api/comunidad/{id}")
    Call<Void> eliminarComunidad(@Path("id")Integer id);

    //Eventos carajo

    @GET ("Softlarism/api/eventos")
    Call<List<Eventos>> obtenerEventos();

    @GET ("Softlarism/api/eventos/{id}")
    Call<Eventos> obtenerEventos (@Path("id") Integer id);

    @POST("Softlarism/api/eventos")
    Call<Eventos> crearEventos (@Body Eventos eventos);

    @PUT("Softlarism/api/eventos/{id}")
    Call<Eventos> actualizarEventos (@Path("id") Integer id, @Body Eventos eventos);

    @DELETE ("Softlarism/api/eventos/{id}")
    Call<Void> eliminarEventos(@Path("id")Integer id);
}
