package com.huantt

import okhttp3.HttpUrl
import okhttp3.Response
import org.json.JSONObject
import spock.lang.Specification

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

    def "get(HttpUrl httpUrl, Map<String, Object> headers)"() {
        setup:
        HttpUrl httpUrl = new UrlBuilder(TESTING_API)
                .addPath(path)
                .addQueryParameters(params)
                .build()
        when:
        Response response = HttpClient.get(httpUrl, headers)
        String responseBody = response.body().string()
        JSONObject responseJson = new JSONObject(responseBody)

        then:
        responseJson.getString('url') == httpUrl.toString()
        responseJson.getJSONObject('args').get('action') == params.get('action')
        responseJson.getJSONObject('headers').get('token') == headers.get('token')

        where:
        path  | params                        | headers                 | expectationURL
        'get' | ['action': 'test get method'] | ['token': 'DEMO_TOKEN'] | 'https://postman-echo.com/get?action=test get method'
    }

    def "get(String url, Map<String, Object> headers)"() {
        setup:
        HttpUrl httpUrl = new UrlBuilder(url).build()

        when:
        Response response = HttpClient.get(url, headers)
        String responseBody = response.body().string()
        JSONObject responseJson = new JSONObject(responseBody)

        then:
        responseJson.getString('url') == url
        responseJson.getJSONObject('headers').get('token') == headers.get('token')
        responseJson.getJSONObject('args').get('action') == httpUrl.queryParameter('action')

        where:
        headers                 | url
        ['token': 'DEMO_TOKEN'] | 'https://postman-echo.com/get?action=test%20get%20method'
    }

    def "post(HttpUrl httpUrl, Map<String, Object> headers, String json)"() {
        setup:
        HttpUrl httpUrl = new UrlBuilder(TESTING_API)
                .addPath(path)
                .addQueryParameters(params)
                .build()
        when:
        Response response = HttpClient.post(httpUrl, headers, jsonBody)
        String responseBody = response.body().string()
        JSONObject responseJson = new JSONObject(responseBody)

        then:
        responseJson.getString('url') == httpUrl.toString()
        responseJson.getJSONObject('args').get('action') == params.get('action')
        responseJson.getJSONObject('headers').get('token') == headers.get('token')
        responseJson.getJSONObject('headers').get('token') == headers.get('token')
        responseJson.getJSONObject('data').get('key') == new JSONObject(jsonBody).get('key')

        where:
        path   | params                         | headers                 | jsonBody            | expectationURL
        'post' | ['action': 'test post method'] | ['token': 'DEMO_TOKEN'] | '{"key" : "value"}' | 'https://postman-echo.com/post?action=test get method'
    }

    def "post(HttpUrl httpUrl, Map<String, Object> headers, Map<String, Object> form)"() {
        setup:
        HttpUrl httpUrl = new UrlBuilder(TESTING_API)
                .addPath(path)
                .addQueryParameters(params)
                .build()
        when:
        Response response = HttpClient.post(httpUrl, headers, form)
        String responseBody = response.body().string()
        JSONObject responseJson = new JSONObject(responseBody)

        then:
        responseJson.getString('url') == httpUrl.toString()
        responseJson.getJSONObject('args').get('action') == params.get('action')
        responseJson.getJSONObject('headers').get('token') == headers.get('token')
        responseJson.getJSONObject('headers').get('token') == headers.get('token')
        responseJson.getJSONObject('form').get('key') == form.get('key')

        where:
        path   | params                         | headers                 | form             | expectationURL
        'post' | ['action': 'test post method'] | ['token': 'DEMO_TOKEN'] | ['key': 'value'] | 'https://postman-echo.com/post?action=test get method'
    }

    def "put(HttpUrl httpUrl, Map<String, Object> headers, String json)"() {
        setup:
        HttpUrl httpUrl = new UrlBuilder(TESTING_API)
                .addPath(path)
                .addQueryParameters(params)
                .build()
        when:
        Response response = HttpClient.put(httpUrl, headers, jsonBody)
        String responseBody = response.body().string()
        JSONObject responseJson = new JSONObject(responseBody)

        then:
        responseJson.getString('url') == httpUrl.toString()
        responseJson.getJSONObject('args').get('action') == params.get('action')
        responseJson.getJSONObject('headers').get('token') == headers.get('token')
        responseJson.getJSONObject('headers').get('token') == headers.get('token')
        responseJson.getJSONObject('data').get('key') == new JSONObject(jsonBody).get('key')

        where:
        path  | params                         | headers                 | jsonBody            | expectationURL
        'put' | ['action': 'test post method'] | ['token': 'DEMO_TOKEN'] | '{"key" : "value"}' | 'https://postman-echo.com/post?action=test get method'
    }

    def "put(HttpUrl httpUrl, Map<String, Object> headers, Map<String, Object> form)"() {
        setup:
        HttpUrl httpUrl = new UrlBuilder(TESTING_API)
                .addPath(path)
                .addQueryParameters(params)
                .build()
        when:
        Response response = HttpClient.put(httpUrl, headers, form)
        String responseBody = response.body().string()
        JSONObject responseJson = new JSONObject(responseBody)

        then:
        responseJson.getString('url') == httpUrl.toString()
        responseJson.getJSONObject('args').get('action') == params.get('action')
        responseJson.getJSONObject('headers').get('token') == headers.get('token')
        responseJson.getJSONObject('headers').get('token') == headers.get('token')
        responseJson.getJSONObject('form').get('key') == form.get('key')

        where:
        path  | params                         | headers                 | form             | expectationURL
        'put' | ['action': 'test post method'] | ['token': 'DEMO_TOKEN'] | ['key': 'value'] | 'https://postman-echo.com/post?action=test get method'
    }

    def "delete(HttpUrl httpUrl, Map<String, Object> headers)"() {
        setup:
        HttpUrl httpUrl = new UrlBuilder(TESTING_API)
                .addPath(path)
                .addQueryParameters(params)
                .build()
        when:
        Response response = HttpClient.delete(httpUrl, headers)
        String responseBody = response.body().string()
        JSONObject responseJson = new JSONObject(responseBody)

        then:
        responseJson.getString('url') == httpUrl.toString()
        responseJson.getJSONObject('args').get('action') == params.get('action')
        responseJson.getJSONObject('headers').get('token') == headers.get('token')

        where:
        path     | params                        | headers                 | expectationURL
        'delete' | ['action': 'test get method'] | ['token': 'DEMO_TOKEN'] | 'https://postman-echo.com/get?action=test get method'
    }
}
