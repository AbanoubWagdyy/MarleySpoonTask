package net.marleyspoon.data.model

data class RecipeDetailsResponse(
    val exception: Throwable?,
    val recipeDetails: RecipeDetails?
)