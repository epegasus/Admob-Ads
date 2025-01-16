package com.hypersoft.admobads.ads.natives.data.dataSources.local

import com.hypersoft.admobads.ads.natives.data.entities.ItemNativeAd

/**
 * Created by: Sohaib Ahmed
 * Date: 1/15/2025
 *
 * Links:
 * - LinkedIn: https://linkedin.com/in/epegasus
 * - GitHub: https://github.com/epegasus
 */

class AdCache {

    private val adCache = mutableMapOf<String, ItemNativeAd>()

    fun get(adKey: String): ItemNativeAd? {
        return adCache[adKey] // Return cached ad if it exists
    }

    fun getFreeAd(): ItemNativeAd? {
        // Iterate through the cache and find a free ad
        for ((_, itemNativeAd) in adCache) {
            if (!itemNativeAd.impressionReceived) {
                return itemNativeAd
            }
        }
        // Return null if no free ad is found
        return null
    }

    fun put(adKey: String, itemNativeAd: ItemNativeAd) {
        adCache[adKey] = itemNativeAd
    }

    /**
     *  Only delete if impression is received else if ignore
     */

    fun deleteAd(adKey: String) {
        adCache[adKey]?.let {
            if (it.impressionReceived) {
                adCache.remove(adKey)
            }
        }
    }
}