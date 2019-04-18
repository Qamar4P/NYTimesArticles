package dev.qamar.nytimesarticles.data.model;

/**
 * Created by Qamar4P on 18/4/2019.
 * Islamabad, Pakistan.
 */
public class ApiResponse<T>{
    String status;
    T results;

    public String getStatus() {
        return status;
    }

    public T getResults() {
        return results;
    }
}
