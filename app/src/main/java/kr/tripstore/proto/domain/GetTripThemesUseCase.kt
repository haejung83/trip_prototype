package kr.tripstore.proto.domain

import kr.tripstore.proto.data.Result
import kr.tripstore.proto.data.source.TripRepository

class GetTripThemesUseCase(
    private val tripRepository: TripRepository
) {

    suspend operator fun invoke(): Result<List<TripTheme>> =
        when (val tripPackagePage = tripRepository.getTripPackagePage()) {
            is Result.Success -> {
                Result.Success(
                    tripPackagePage.data.tripPackages
                        .filter { it.title.isNotEmpty() && it.tripDetails.isNotEmpty() }
                        .map { tripPackage ->
                            TripTheme(
                                tripPackage.id,
                                tripPackage.title,
                                tripPackage.tripDetails
                                    .filter { it.title.isNotEmpty() && it.imageUrl.isNotEmpty() && it.openLinkUrl.isNotEmpty() }
                                    .map { tripDetail ->
                                        TripThemeDetail(
                                            tripDetail.id,
                                            tripDetail.title,
                                            tripDetail.imageUrl,
                                            tripDetail.openLinkUrl
                                        )
                                    }
                            )
                        }
                )
            }
            is Result.Error -> Result.Error(tripPackagePage.exception)
            is Result.Loading -> Result.Loading
        }

}