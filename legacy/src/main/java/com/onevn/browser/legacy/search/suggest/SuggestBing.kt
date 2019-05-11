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

package com.onevn.browser.legacy.search.suggest

import com.squareup.moshi.JsonReader
import java.io.IOException
import java.net.URL
import java.net.URLEncoder
import java.util.*

class SuggestBing : ISuggest {

    @Throws(IOException::class)
    override fun getUrl(query: String): URL {
        return URL(SUGGEST_URL.replace("{{LANG}}", Locale.getDefault().language).replace("{{TERMS}}", URLEncoder.encode(query, "UTF-8")))
    }

    @Throws(IOException::class)
    override fun getSuggestions(reader: JsonReader): MutableList<Suggestion> {
        val list = ArrayList<Suggestion>()

        if (reader.peek() == JsonReader.Token.BEGIN_ARRAY) {
            reader.beginArray()
            while (reader.hasNext()) {
                if (reader.peek() == JsonReader.Token.BEGIN_ARRAY) {
                    reader.beginArray()
                    while (reader.hasNext()) {
                        list.add(Suggestion(reader.nextString()))
                    }
                    reader.endArray()
                } else {
                    reader.skipValue()
                }
            }
            reader.endArray()
        }

        return list
    }

    companion object {
        private const val SUGGEST_URL = "https://api.bing.com/osjson.aspx?FORM=OPERAS&Market={{LANG}}&Query={{TERMS}}"
    }
}