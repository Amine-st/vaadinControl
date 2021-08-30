package com.example.application.data.service;

import com.google.gson.Gson;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class ZkService<T> {


    public  String get(String uri) throws Exception {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }



    public void post(String postUrl1, T person1) throws Exception {
        String       postUrl       = postUrl1;
        Gson         gson          = new Gson();
        CloseableHttpClient httpClient    = HttpClientBuilder.create().build();
        HttpPost post          = new HttpPost(postUrl);
        StringEntity postingString = new StringEntity(gson.toJson(person1));
        post.setEntity(postingString);
        post.setHeader("Content-type", "application/json");
        CloseableHttpResponse response =  httpClient.execute(post);
    }

    public static void main(String[] args) throws Exception {

    }
}
