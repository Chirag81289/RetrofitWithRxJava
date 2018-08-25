package com.example.indianic.retrofitwithrxjava;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * This interface will describe service methods. This interface will be use by retrofit object from Wrapper class of Service.
 */
public interface ServiceInterface {

    //Dummy URL
    //  https://jsonplaceholder.typicode.com/todos/1

    String HEADER_CONTENT_TYPE_APP_JSON = "Content-Type: application/json";
    String HEADER_REQ_FORMAT_JSON = "RequestFormat: json";

    /* Parameters */
    String ID = "id";


    /* ============================================================================================================= */
    /* Method end points */
//    String TODOS = "todos";
    String TODOS = "todos/1";

    /* ============================================================================================================= */

    /**
     * Get Method
     * If url is like, "https://jsonplaceholder.typicode.com/todos/1"
     * then use below method
     *
     * @return Model
     */
    @Headers({HEADER_CONTENT_TYPE_APP_JSON, HEADER_REQ_FORMAT_JSON})
    @GET(TODOS)
    Observable<UserModel> getUserDetail();

//    /**
//     * Get Method
//     * If url is like, "https://jsonplaceholder.typicode.com/todos?id=5"
//     * then use below method
//     * @param id id
//     * @return Model
//     */
//    @Headers({HEADER_CONTENT_TYPE_APP_JSON, HEADER_REQ_FORMAT_JSON})
//    @GET(TODOS)
//    Observable<UserModel> getLanguageList(
//            @Query(ID) String id);
//
//
//    // Post Method
//    @Headers({HEADER_CONTENT_TYPE_APP_JSON, HEADER_REQ_FORMAT_JSON})
//    @POST(GENERATE_KEY)
//    Observable<GenerateKeyModel> generateKey(
//            @Query(LANG_ID) String langId,
//            @Query(DEVICE_ID) String deviceId,
//            @Query(DEVICE_TYPE) String deviceType,
//            @Query(NM_DEBUG) String nmDebug);
//
//
//
//
//    // Multipart for upload image and video
//    @Multipart
//    @POST(CREATE_ORGANIZATION_GROUP_FRONT)
//    Observable<CreateGroupModel> createGroup(
//            @Part MultipartBody.Part doc,
//            @Part(USER_ID) RequestBody userId);

}