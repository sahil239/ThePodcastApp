package dev.sahildesai.thepodcastapp.domain.usecase

import kotlinx.coroutines.flow.Flow

fun interface IGetFavoritePodcastIdsUseCase: () ->  Flow<List<String>>