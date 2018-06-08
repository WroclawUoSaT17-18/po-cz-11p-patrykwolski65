package com.exchange.extchange.network;


import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface NBPService {

    @GET("api/exchangerates/tables/a/?format=json")
    Single<List<CoursesTableResponse>> getCoursesForTableA();

}
