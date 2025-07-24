package dev.sahildesai.thepodcastapp.domain.usecase

fun interface IToggleFavoriteUseCase: suspend (String, Boolean) -> Unit