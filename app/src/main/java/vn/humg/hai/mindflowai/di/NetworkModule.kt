package vn.humg.hai.mindflowai.di

import com.google.ai.client.generativeai.GenerativeModel
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun provideGenerativeModel(): GenerativeModel {
        // TODO: Thay "YOUR_API_KEY" bằng API Key thực tế của bạn
        return GenerativeModel(
            modelName = "gemini-1.5-flash", // Hoặc "gemini-1.5-pro"
            apiKey = "AIzaSyBP7KyUZmE2GvZP1nFqOAQzgxQgtPvtKOk"
        )
    }
}
