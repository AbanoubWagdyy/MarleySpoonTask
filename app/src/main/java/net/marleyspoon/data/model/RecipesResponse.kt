package net.marleyspoon.data.model

data class RecipesResponse(
    val exception: Throwable?,
    val items: List<Recipe>?
)