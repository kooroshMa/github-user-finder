package com.example.githubuserfinder.app

import android.app.Application
import android.graphics.Bitmap
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.request.CachePolicy
import coil.util.DebugLogger
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application(), ImageLoaderFactory {

    override fun newImageLoader(): ImageLoader {
        val diskCacheSizeBytes = 1024 * 1024 * 20

        return ImageLoader(this).newBuilder()
            .crossfade(true)
            .bitmapConfig(Bitmap.Config.ARGB_8888)
            .memoryCache {
                MemoryCache.Builder(this)
                    .maxSizePercent(0.125)
                    .build()
            }
            .diskCachePolicy(CachePolicy.ENABLED)
            .diskCache {
                DiskCache.Builder()
                    .maxSizeBytes(diskCacheSizeBytes.toLong())
                    .directory(cacheDir.resolve("image_cache"))
                    .build()
            }
            .logger(DebugLogger())
            .build()
    }
}