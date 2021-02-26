package com.mkpatir.showcase.ui.home.collections

import com.mkpatir.showcase.api.models.CollectionModel

data class CollectionViewState(
    private val item: CollectionModel
) {

    fun getTitle(): String = item.title.orEmpty()

    fun getDefinition(): String = item.definition.orEmpty()

    fun getImageUrl(): String = item.coverPhoto?.mediumPhoto?.url.orEmpty()

}