package com.bing.lan.comm.api;

import com.bing.lan.comm.config.AppConfig;
import com.bing.lan.comm.utils.AppUtil;
import com.bing.lan.comm.utils.FileUtils;
import com.bing.lan.comm.utils.LogUtil;
import com.bing.lan.comm.utils.NetworkUtil;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

public class ApiManager {

    private static final int DEFAULT_CONN_TIMEOUT = 5;
    private static final int WRITE_TIMEOUT = 20;
    private static final int READ_TIMEOUT = 20;
    private static ApiManager instance;
    private static ApiService sApiService;
    protected final LogUtil log = LogUtil.getLogUtil(getClass(), LogUtil.LOG_VERBOSE);
    private Retrofit mRetrofit;

    private ApiManager() {
        OkHttpClient httpClient = getClientBuilder().build();
//        Gson gson = getGsonBuilder().create();

        // create unique instance
        mRetrofit = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .client(httpClient)
//                .addConverterFactory(GsonConverterFactory.create(gson))
                .addConverterFactory(FastJsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    private static ApiManager getInstance() {
        if (instance == null) {
            instance = new ApiManager();
        }
        return instance;
    }

    public static ApiService getApiService() {
        if (sApiService == null) {
            sApiService = getInstance().mRetrofit.create(ApiService.class);
        }
        // sApiService = getInstance().mRetrofit.create(ApiService.class);
        return sApiService;
    }

    private OkHttpClient.Builder getClientBuilder() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        // logging
        if (AppConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }

        // http cache
        // File cacheFile = new File(AppUtil.getCacheDir() + File.pathSeparator + "httpCache");
        File cacheFile = new File(FileUtils.getHttpDir());
        builder.cache(new Cache(cacheFile, 10 * 1024 * 1024));// 10MB

        // cache control
        CacheInterceptor interceptor = new CacheInterceptor();
        builder.addNetworkInterceptor(interceptor);
        builder.addInterceptor(interceptor);

        // time out
        builder.connectTimeout(DEFAULT_CONN_TIMEOUT, TimeUnit.SECONDS);
        builder.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS);
        builder.writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS);

        // retry mechanism
        builder.retryOnConnectionFailure(true);
        return builder;
    }

    private GsonBuilder getGsonBuilder() {
        return new GsonBuilder()
                .setDateFormat("yyyyMMdd")
                .disableInnerClassSerialization()
                .generateNonExecutableJson()
                .disableHtmlEscaping()
                .setLenient()//com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed JSON at line 1 column 4 path $[0].
                .setPrettyPrinting();
    }

    private class CacheInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            // get request
            Request request = chain.request();

            // build request of force cache
            Request cacheRequest = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();

            // cache mechanism
            Response.Builder builder;
            if (!NetworkUtil.isNetworkAvailable(AppUtil.getAppContext())) {

                int maxStale = 60 * 60 * 24 * 28;
                builder = chain.proceed(cacheRequest)
                        .newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale);
            } else {

                int maxAge = 60 * 10;
                builder = chain.proceed(request)
                        .newBuilder()
                        .header("Cache-Control", "public, max-age=" + maxAge);
            }

            return builder.removeHeader("Pragma").build();

            // // cache mechanism
            // if (NetworkUtil.isNetworkAvailable(AppUtil.getAppContext())) {
            //     int maxAge = 60 * 10;
            //     response.newBuilder()
            //             .header("Cache-Control", "public, max-age=" + maxAge)
            //             .removeHeader("Pragma")
            //             .build();
            // } else {
            //     int maxStale = 60 * 60 * 24 * 28;
            //     response.newBuilder()
            //             .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
            //             .removeHeader("Pragma")
            //             .build();
            // }
            // return response;
        }
    }
}
