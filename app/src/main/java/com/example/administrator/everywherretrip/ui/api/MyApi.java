package com.example.administrator.everywherretrip.ui.api;



import com.example.administrator.everywherretrip.base.Constants;
import com.example.administrator.everywherretrip.bean.AboutBean;
import com.example.administrator.everywherretrip.bean.AppraiseBean;
import com.example.administrator.everywherretrip.bean.AttenBeanDelete;
import com.example.administrator.everywherretrip.bean.AttenBeanInsert;
import com.example.administrator.everywherretrip.bean.AttenList;
import com.example.administrator.everywherretrip.bean.BalanceBean;
import com.example.administrator.everywherretrip.bean.CollectionList;
import com.example.administrator.everywherretrip.bean.CollectionNo;
import com.example.administrator.everywherretrip.bean.CollectionOk;
import com.example.administrator.everywherretrip.bean.DemoBean;
import com.example.administrator.everywherretrip.bean.DynamicBean;
import com.example.administrator.everywherretrip.bean.LoginInfo;
import com.example.administrator.everywherretrip.bean.MainBean;
import com.example.administrator.everywherretrip.bean.MessageBean;
import com.example.administrator.everywherretrip.bean.ProjectlistBean;
import com.example.administrator.everywherretrip.bean.StayBean;
import com.example.administrator.everywherretrip.bean.WithPath;
import com.example.administrator.everywherretrip.bean.WithState;
import com.example.administrator.everywherretrip.util.SpUtil;
import com.umeng.commonsdk.debug.D;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MyApi {

    //token
    String param = (String) SpUtil.getParam(Constants.TOKEN, "");
    public static final int SUCCESS_CODE = 0;
    String sBaseUrl = "http://yun918.cn/study/public/index.php/";
    String mainUrl = "https://api.banmi.com/";

    /**
     * 获取验证码
     *
     * @return
     */
    @GET("verify")
    Observable<DemoBean> getVerifyCode();

    /**
     * 主页数据
     *
     *
     * @return
     */
    @GET("api/3.0/content/routesbundles?page=1")
  //  @Headers("banmi-app-token:JVy0IvZamK7f7FBZLKFtoniiixKMlnnJ6dWZ6NlsY4HGsxcAA9qvFo8yacHCKHE8YAcd0UF9L59nEm7zk9AUixee0Hl8EeWA880c0ikZBW0KEYuxQy5Z9NP3BNoBi3o3Q0g")
    Observable<MainBean> getMain(@Header("banmi-app-token") String token);


    /**
     * 微博登录
     *
     * @param oAuthToken 就是三方里面的uid
     * @return
     */
    @FormUrlEncoded
    @POST("api/3.0/account/login/oauth")
    Observable<LoginInfo> postWeiboLogin(@Field("oAuthToken") String oAuthToken);

    /**
     * 伴米数据
     *
     *
     * @param page
     * @return
     */
   // @Headers("banmi-app-token:4mjA0j6Ii2GgPEJ66IMCE7PqLXkBPfQla9nZDSyUV3udn2LqzO6QOJgypbt8BII3Ol34eQGgboGuvLZHzPC9dahXT7eQdKYRrCdeGdRnYiOJI99lWDwAqnuHImD75pgUCEw")
    @GET("api/3.0/banmi")
    Observable<StayBean> getData(@Header("banmi-app-token") String token,@Query("page") int page);

    /**
     * 修改个人信息
     * @param map
     * @return
     */
    @POST("api/3.0/account/updateInfo")
    @FormUrlEncoded
    Observable<ResponseBody> upDateInfo(@FieldMap HashMap<String, String> map, @Header("banmi-app-token") String token);


    /**
     * 获取个人信息
     * @param token
     * @return
     */
    @POST("api/3.0/account/balance")
    Observable<MessageBean> newDateInfo(@Header("banmi-app-token") String token);


    /*
    * 主页详情
    * */
    @GET("api/3.0/content/routes/{routeId}")
    Observable<AboutBean> getAbout(@Path("routeId") int routeId,@Header("banmi-app-token") String token);

    /**
     * 账户余额
     *
     * @param token
     * @return
     */
    @GET("api/3.0/account/balance")
    Observable<BalanceBean> getBan(@Header("banmi-app-token") String token);

    /**
     * 修改个人信息
     *
     * @param userName
     * @param description
     * @param gender
     * @param photo
     * @param token
     * @return
     */
    @POST("api/3.0/account/updateInfo")
    @FormUrlEncoded
    Observable<ResponseBody> upDateInfo(@Field("userName") String userName,
                                        @Field("description") String description,
                                        @Field("gender") String gender,
                                        @Field("photo") String photo,
                                        @Header("banmi-app-token") String token);



    /**
     * 收藏数据
     *
     * @param token
     * @param id
     * @return
     */
    @POST("api/3.0/content/routes/{routeId}/like")
    Observable<CollectionOk> setCollectionOk(@Header("banmi-app-token") String token, @Path("routeId") int id);

    /**
     * 取消收藏
     *
     * @param token
     * @param id
     * @return
     */
    @POST("api/3.0/content/routes/{routeId}/dislike")
    Observable<CollectionNo> setCollectionNo(@Header("banmi-app-token") String token, @Path("routeId") int id);

    /**
     * 获取已收藏的数据
     *
     * @param token
     * @return
     */
    @GET("api/3.0/account/collectedRoutes?")
    Observable<CollectionList> setCollectionList(@Header("banmi-app-token") String token, @Query("page") int page);

    /**
     * 关注
     *
     * @param token
     * @param banmiId
     * @return
     */
    @POST("api/3.0/banmi/{banmiId}/follow")
    Observable<AttenBeanInsert> setInsert(@Header("banmi-app-token") String token, @Path("banmiId") int banmiId);

    /**
     * 取消关注
     *
     * @param token
     * @param banmiId
     * @return
     */
    @POST("api/3.0/banmi/{banmiId}/unfollow")
    Observable<AttenBeanDelete> setDelete(@Header("banmi-app-token") String token, @Path("banmiId") int banmiId);

    /**
     * 获取关注列表
     *
     * @param token
     * @param page
     * @return
     */
    @GET("api/3.0/account/followedBanmi?")
    Observable<AttenList> setList(@Header("banmi-app-token") String token, @Query("page") int page);

    /**
     * 伴米动态
     * @param token
     * @param page
     * @param banmiId
     * @return
     */
    @GET("api/3.0/banmi/{banmiId}?")
    Observable<DynamicBean> getState(@Header("banmi-app-token") String token, @Path("banmiId") int banmiId, @Query("page") int page);

    /**
     * 伴米线路
     * @param token
     * @param page
     * @param banmiId
     * @return
     */
    @GET("api/3.0/banmi/{banmiId}/routes?")
    Observable<WithPath> setPath(@Header("banmi-app-token") String token, @Path("banmiId") int banmiId, @Query("page") int page);

/*
* 获取专题列表
* */
    @GET("api/3.0/content/bundles")
    Observable<ProjectlistBean> getproject(@Header("banmi-app-token") String token);

/*
* 主页线路全部评价
* */
    @GET("api/3.0/content/routes/{routeId}/reviews")
    Observable<AppraiseBean> getAppraise(@Header("banmi-app-token") String token, @Path("routeId") int banmiId, @Query("page") int page);
}
