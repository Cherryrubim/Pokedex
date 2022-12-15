/*
 * Copyright (c) 2022. Designed and developed by Cherryrubim
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cherryrubim.pokedex.util

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState

fun LazyGridState.isLastItemVisible(): Boolean {
    layoutInfo.visibleItemsInfo.lastOrNull()?.key
    val check =
        layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1 && layoutInfo.totalItemsCount > 1 // <- Bug?, layoutInfo start with a element.
    return check
}

fun <Key> LazyGridState.isLastItemVisibleByKey(key: Key?): Boolean {
    val lastItemKey = layoutInfo.visibleItemsInfo.lastOrNull()?.key
    val isLastItem = lastItemKey == key
    return isLastItem && layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1
}

fun LazyListState.isLastItemVisible(): Boolean{

    val check = layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1 && layoutInfo.totalItemsCount > 1 // <- Bug?, layoutInfo start with a element.
/*  Log.i("LazyListState", "Check lastItemVisibility: $check")
    Log.i("LazyListState", "LayoutInfo Visible Last Index: ${layoutInfo.visibleItemsInfo.lastOrNull()?.index}")
    Log.i("LazyListState", "LayoutInfo totalItems: ${ layoutInfo.totalItemsCount}")
    Log.i("LazyListState", "LayoutInfo Last Item Key: ${ layoutInfo.visibleItemsInfo.lastOrNull()?.key}")*/
    return check
}