package com.bing.lan.comm.api;

import com.bing.lan.jdmall.bean.BannerResultBean;
import com.bing.lan.jdmall.bean.LoginResultBean;
import com.bing.lan.jdmall.bean.SecKillResultBean;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

public interface ApiService {


    String BASE_URL = "http://mall.520it.com/";


    /*访问全路径API并返回原生数据*/
    @GET
    Observable<ResponseBody> getRaw(@Url String url);

    @GET("{url}")
    Observable<ResponseBody> executeGet(
            @Path("url") String url,
            @QueryMap Map<String, String> map
    );


//    @POST("login")
//    Observable<LoginResultBean> getLoginResult(@Body LoginParamsBean loginParamsBean);

    @FormUrlEncoded
    @POST("login")
    Observable<LoginResultBean> login(@Field("username") String username,
                                      @Field("pwd") String pwd);

    @GET("banner")
    Observable<BannerResultBean> loadBanner(@Query("adKind") int adKind);

    @GET("seckill")
    Observable<SecKillResultBean> loadSecKill( );

    // @GET("home")
    // Call<ResponseBody> getUrl(@Query("index") long index);
    // //http://192.168.196.2:8080/GooglePlayServer/home?index=0
    //
    // @GET("home")
    // Call<HomeInfoBean> getUrl1(@Query("index") long index);
    //
    // @GET("{home}")
    // Observable<HomeInfoBean> getHomeInfo(@Path("home") String home, @Query("index") int index);
    // //http://192.168.196.2:8080/GooglePlayServer/home?index=0


    // /*获得指定id的长评论*/
    // @GET("4/story/{id}/long-comments/{userId}")
    // Observable<CommentListBean> getLongCommentMore(@Path("id") long id, @Path("userId") long userId);
    // //http://news-at.zhihu.com/api/4/story/4232852/long-comments

    // /*获得指定栏目之前的新闻*/
    // @GET("4/section/{id}/before/{timestamp}")
    // Observable<StoriesBeforeBean> getSectionNewsBefore(@Path("id") long id, @Path("timestamp") long date);
    // //http://news-at.zhihu.com/api/4/section/#{section id}/before/#{timestamp}
}
