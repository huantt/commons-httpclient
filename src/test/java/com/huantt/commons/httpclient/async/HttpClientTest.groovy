package com.huantt.commons.httpclient.async

import com.huantt.commons.httpclient.UrlBuilder
import okhttp3.Call
import okhttp3.Callback
import okhttp3.HttpUrl
import okhttp3.Response
import org.json.JSONObject
import spock.lang.Specification

import java.util.concurrent.CountDownLatch

/**
 * @author huantt on 10/29/18
 */
class HttpClientTest extends Specification {

    /**
     * Postman Echo is a testing API, it will response request information in response body:
     * - args: Query parameters
     * - headers: request headers
     * - url: request URL
     * Documentation: https://docs.postman-echo.com/
     * */
    String TESTING_API = 'https://postman-echo.com'
    String DEFAULT_VALUE = "MEOZ"

    def "get(HttpUrl httpUrl, Map<String, Object> headers, Callback callback)"() {
        setup:
        CountDownLatch waiter = new CountDownLatch(1)

        String responseUrl = DEFAULT_VALUE
        String responseToken = DEFAULT_VALUE
        String actionParam = DEFAULT_VALUE

        HttpUrl httpUrl = new UrlBuilder(TESTING_API)
                .addPath(path)
                .addQueryParameters(params)
                .build()

        when:
        HttpClient.get(httpUrl, headers, new Callback() {
            @Override
            void onFailure(Call call, IOException e) {
                throw e
            }

            @Override
            void onResponse(Call call, Response response) throws IOException {
                JSONObject responseJson = new JSONObject(response.body().string())
                responseUrl = responseJson.getString('url')
                responseToken = responseJson.getJSONObject('headers').get('token')
                actionParam = responseJson.getJSONObject('args').get('action')
                waiter.countDown()
            }
        })

        then:
        // Before request completed
        responseUrl == DEFAULT_VALUE
        responseToken == DEFAULT_VALUE
        actionParam == DEFAULT_VALUE

        // Do something while requesting
        waiter.await()

        // After request completed
        responseToken == headers['token']
        responseUrl == expectationURL
        actionParam == params['action']

        where:
        path  | params                        | headers                 | expectationURL
        'get' | ['action': 'test get method'] | ['token': 'DEMO_TOKEN'] | 'https://postman-echo.com/get?action=test%20get%20method'
    }
}
