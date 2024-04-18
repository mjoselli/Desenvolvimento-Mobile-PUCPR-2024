package com.example.mywebapplication

import retrofit2.http.GET

interface ChuckNorrisAPI {
    @GET("jokes/random")
    suspend fun getRandomJoke(): Joke
}