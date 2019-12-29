package com.example.kampusku.ApiHelper;

import com.example.kampusku.Daftar.GetDaftar;
import com.example.kampusku.Fakultas.GetFakultas;
import com.example.kampusku.Kampus.GetDetail;
import com.example.kampusku.Kampus.GetKampus;
import com.example.kampusku.User.ValueUser;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface BaseApiHelper {

    @FormUrlEncoded
    @POST("login")
    Call<ResponseBody> loginRequest(@Field("email") String email,
                                    @Field("password") String password);

    // Fungsi ini untuk memanggil API http://10.0.2.2/mahasiswa/register.php
    @FormUrlEncoded
    @POST("register")
    Call<ResponseBody> registerRequest(@Field("name") String nama,
                                       @Field("email") String email,
                                       @Field("password") String password,
                                       @Field("c_password") String c_password);


    @FormUrlEncoded
    @POST("edituser/{id}")
    Call<ResponseBody> editUser(@Path("id") int id ,
                                @Field("name") String nama,
                                @Field("email") String email);

    @GET("kampus")
    Call<GetKampus> getKampus();

    @GET("getKampus")
    Call<GetKampus> datakampus();

    @GET("search/{lokasi}")
    Call<GetKampus> SearchKampus(@Path("lokasi") String lokasi);

    @GET("kampus/search/{nama_univ}")
    Call<GetKampus> getKampus(@Path("nama_univ") String nama_univ);

    @FormUrlEncoded
    @POST("kampus")
    Call<ResponseBody> kampusRequest(@Field("nama_univ") String nama_univ,
                                         @Field("tentang") String tentang,
                                         @Field("lokasi") String lokasi);

    @GET("fakultas")
    Call<GetFakultas> getFakultas();


    @FormUrlEncoded
    @POST("kampus/{id}")
    Call<ResponseBody> kampusUpdate(@Path("id") int id ,
                                    @Field("nama_univ") String nama_univ,
                                     @Field("tentang") String tentang,
                                     @Field("lokasi") String lokasi);


    @POST("deluniv/{id}")
    Call<ResponseBody> kampusDelete(@Path("id") int id);

    @GET("kampus/{id}")
    Call<GetDetail> getDetail(@Path("id") int id);

    @GET("getuser/{id}")
    Call<ValueUser> viewUser(@Path("id") int id);

    @FormUrlEncoded
    @POST("prodi")
    Call<ResponseBody> InsertProdi(@Field("nama_prodi") String nama_univ,
                                    @Field("tentang") String tentang,
                                    @Field("biaya") int biaya,
                                    @Field("id_fakultas") int id_fakultas,
                                    @Field("id_univ") int id_univ);

    @FormUrlEncoded
    @POST("prodi/{id}")
    Call<ResponseBody> UpdateProdi(@Path("id") int id ,
                                   @Field("nama_prodi") String nama_univ,
                                   @Field("tentang") String tentang,
                                   @Field("biaya") int biaya,
                                   @Field("id_fakultas") int id_fakultas,
                                   @Field("id_univ") int id_univ);

    @POST("prodi/{id}")
    Call<ResponseBody> DeleteProdi(@Path("id") int id);

    @GET("getDaftar/{id}")
    Call<GetDaftar> GetDaftar(@Path("id") int id);

    @GET("daftar")
    Call<GetDaftar> daftar();

    @FormUrlEncoded
    @POST("daftar")
    Call<ResponseBody> Daftar(@Field("nama_univ") String nama_univ,
                                   @Field("fee") int fee,
                                   @Field("id_user") int id_user,
                                   @Field("id_prodi") int id_prodi);

    @POST("deldaftar/{id}")
    Call<ResponseBody> DeleteDaftar(@Path("id") int id);

    @Multipart
    @POST("gambar")
    Call<ResponseBody> uploadFile(@Part MultipartBody.Part file,
                                  @Part("id_univ") int id_univ);

    @Multipart
    @POST("edituser/{id}")
    Call<ResponseBody> updateuser(@Path("id") int id,
                                  @Part MultipartBody.Part file,
                                  @Part("name") RequestBody nama,
                                  @Part("email") RequestBody email);

    @FormUrlEncoded
    @POST("fakultas")
    Call<ResponseBody> requestFakultas(@Field("nama_fakultas") String nama_fakultas);

    @FormUrlEncoded
    @POST("fakultas/{id}")
    Call<ResponseBody> updateFakultas(  @Path("id") int id,
                                        @Field("nama_fakultas") String nama_fakultas);

    @POST("delfak/{id}")
    Call<ResponseBody> DeleteFakultas(@Path("id") int id);

}
