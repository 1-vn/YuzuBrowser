/*
 * Copyright (C) 2019 DiepDT
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

package com.onevn.browser.legacy.action.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

import com.onevn.browser.legacy.action.Action
import com.onevn.browser.legacy.action.ActionList
import com.onevn.browser.legacy.action.ActionNameArray

class ActionListViewAdapter(context: Context, objects: ActionList, array: ActionNameArray?) : ArrayAdapter<Action>(context, 0, objects) {
    private val mActionNameArray = array ?: ActionNameArray(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, null)

        getItem(position)?.let {
            view.findViewById<TextView>(android.R.id.text1).text = it.toString(mActionNameArray)
        }
        return view
    }
}
