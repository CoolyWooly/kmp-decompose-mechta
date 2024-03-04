package core.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    @SerialName("result") val result: Boolean,
    @SerialName("errors") val errors: List<String>?,
    @SerialName("data") val data: T,
)