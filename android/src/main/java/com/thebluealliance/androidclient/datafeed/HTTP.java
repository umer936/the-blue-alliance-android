package com.thebluealliance.androidclient.datafeed;

import android.util.Log;

import com.thebluealliance.androidclient.Constants;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;


public class HTTP {

    public static HttpResponse getResponse(String url) {
        return getResponse(url, null);
    }

    public static HttpResponse getResponse(String url, String lastUpdated) {
        // HTTP
        try {
            HttpClient httpclient = new DefaultHttpClient(); // for port 80 requests!
            HttpGet httpget = new HttpGet(url);
            httpget.addHeader("X-TBA-App-Id", Constants.getApiHeader());
            if (lastUpdated != null) {
                httpget.addHeader("If-Modified-Since", lastUpdated);
            }
            return httpclient.execute(httpget);
        } catch (Exception e) {
            Log.w(Constants.LOG_TAG, "Exception while fetching " + url);
            e.printStackTrace();
            return null;
        }
    }

    public static String dataFromResponse(HttpResponse response) {
        InputStream is;
        String result = "";
        // Read response to string
        if(response != null) {
            try {
                HttpEntity entity = response.getEntity();

                is = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf-8"), 8);
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                is.close();
                result = sb.toString();
                return result;
            } catch (Exception e) {
                Log.w(Constants.LOG_TAG, "Exception while fetching data from " + response.toString());
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public static String GET(String url) {

        HttpResponse response = getResponse(url);
        if (response == null) return null;

        return dataFromResponse(response);
    }

    public static String GET(String url, Map<String, String> headers){

        // HTTP
        HttpResponse response = null;
        try {
            HttpClient httpclient = new DefaultHttpClient(); // for port 80 requests!
            HttpGet httpget = new HttpGet(url);
            if(headers != null) {
                for (Map.Entry<String, String> header : headers.entrySet()) {
                    httpget.addHeader(header.getKey(), header.getValue());
                }
            }
            response = httpclient.execute(httpget);
        } catch (Exception e) {
            Log.w(Constants.LOG_TAG, "Exception while fetching " + url);
            e.printStackTrace();
            return null;
        }

        InputStream is;
        String result = "";
        // Read response to string
        if(response != null) {
            try {
                HttpEntity entity = response.getEntity();

                is = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf-8"), 8);
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                is.close();
                result = sb.toString();
                return result;
            } catch (Exception e) {
                Log.w(Constants.LOG_TAG, "Exception while fetching data from " + response.toString());
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public static void POST(String serverUrl, Map<String, String> params) throws IOException {
        URL url;
        try {
            url = new URL(serverUrl);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("invalid url: " + serverUrl);
        }
        StringBuilder bodyBuilder = new StringBuilder();
        Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();
        // constructs the POST body using the parameters
        while (iterator.hasNext()) {
            Map.Entry<String, String> param = iterator.next();
            bodyBuilder.append(param.getKey()).append('=')
                    .append(param.getValue());
            if (iterator.hasNext()) {
                bodyBuilder.append('&');
            }
        }
        String body = bodyBuilder.toString();
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setChunkedStreamingMode(0);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded;charset=UTF-8");
            conn.setRequestProperty("Content-Length",
                    Integer.toString(body.length()));
            // post the request
            OutputStream out = conn.getOutputStream();
            out.write(body.getBytes());
            out.close();
            // handle the response
            int status = conn.getResponseCode();
            if (status != 200) {
                throw new IOException("Post failed with error code " + status);
            }
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }
}