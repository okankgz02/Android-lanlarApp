package com.example.mk.otogalerim.RestApi;

/**
 * Created by mk on 07.01.2018.
 */

public class BaseManager {


    protected RestApi getRestApi()
    {
        RestApiClient restApiClient = new RestApiClient(BaseUrl.URL);
        return restApiClient.getRestApi();
    }
}
