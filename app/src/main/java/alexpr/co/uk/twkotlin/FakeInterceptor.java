package alexpr.co.uk.twkotlin;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;

import alexpr.co.uk.twkotlin.network.stubs.StubGenerator;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class FakeInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = null;
        if (BuildConfig.DEBUG) {
            String responseString="";
            final URI uri = chain.request().url().uri();
            Log.e("alexp", "reqUri: " + uri);
            switch (uri.getPath()) {
                case "/home/menu": {
                    responseString = new Gson().toJson(StubGenerator.getMockMainMenu());
                }
            }

            response = new Response.Builder()
                    .code(200)
                    .message(responseString)
                    .request(chain.request())
                    .protocol(Protocol.HTTP_1_0)
                    .body(ResponseBody.create(MediaType.parse("application/json"), responseString.getBytes()))
                    .addHeader("content-type", "application/json")
                    .build();
        } else {
//            response = chain.proceed(chain.request());
            response = new Response.Builder()
                    .code(401)
                    .message("The server implementation is not ready yet! Stubs are currently returned.")
                    .request(chain.request())
                    .protocol(Protocol.HTTP_1_0)
                    .body(ResponseBody.create(MediaType.parse("application/json"), "".getBytes()))
                    .addHeader("content-type", "application/json")
                    .build();
        }

        return response;
    }
}