/*
 * Copyright (C) 2017-2019 DiepDT
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.onevn.browser.webview

import android.content.Context
import android.os.Bundle
import com.squareup.moshi.Moshi
import com.onevn.browser.core.settings.WebViewPrefs


class WebViewFactory(private val moshi: Moshi, private val prefs: WebViewPrefs) {

    val mode: Int
        get() {
            return if (prefs.fastBack) {
                when (prefs.fastBackCacheSize) {
                    0 -> MODE_CACHE
                    1 -> MODE_NORMAL
                    else -> MODE_LIMIT_CACHE
                }
            } else {
                MODE_NORMAL
            }
        }

    fun create(context: Context, @WebViewType mode: Int): CustomWebView {
        return when (mode) {
            MODE_NORMAL -> SwipeWebView(context)
            MODE_CACHE -> CacheWebView(context)
            MODE_LIMIT_CACHE -> LimitCacheWebView(context, moshi, prefs)
            else -> SwipeWebView(context)
        }
    }

    @WebViewType
    fun getMode(bundle: Bundle): Int {
        return when {
            CacheWebView.isBundleCacheWebView(bundle) -> MODE_CACHE
            LimitCacheWebView.isBundleFastBackWebView(bundle) -> MODE_LIMIT_CACHE
            else -> MODE_NORMAL
        }
    }

    companion object {
        const val MODE_NORMAL = 1
        const val MODE_CACHE = 2
        const val MODE_LIMIT_CACHE = 3
    }
}
