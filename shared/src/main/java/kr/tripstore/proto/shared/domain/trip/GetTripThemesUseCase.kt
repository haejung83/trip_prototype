package kr.tripstore.proto.shared.domain.trip

import android.net.Uri
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kr.tripstore.proto.model.TripLink
import kr.tripstore.proto.model.TripLinkType
import kr.tripstore.proto.model.domain.TripTheme
import kr.tripstore.proto.model.domain.TripThemeDetail
import kr.tripstore.proto.shared.data.trip.TripRepository
import kr.tripstore.proto.shared.di.DefaultCoroutineDispatcher
import kr.tripstore.proto.shared.result.Result
import javax.inject.Inject

class GetTripThemesUseCase @Inject constructor(
    private val tripRepository: TripRepository,
    @DefaultCoroutineDispatcher private val defaultDispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(): Result<List<TripTheme>> = withContext(defaultDispatcher) {
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
                                            parseOpenLinkUrlToTripLink(tripDetail.openLinkUrl)
                                        )
                                    }
                            )
                        }
                )
            }
            is Result.Error -> Result.Error(Exception("GetTripThemesUseCase: Error"))
            is Result.Loading -> Result.Loading
        }
    }

    private fun parseOpenLinkUrlToTripLink(openLink: String): TripLink {
        val parsedUri = Uri.parse(openLink)
        val type = parsedUri.path?.let {
            TripLinkType.getTripLinkTypeFromString(it)
        } ?: TripLinkType.UNKNOWN
        val parameters = parsedUri.queryParameterNames.associateWithTo(
            mutableMapOf(), { parsedUri.getQueryParameter(it) }
        )
        return TripLink(type, parameters)
    }

}