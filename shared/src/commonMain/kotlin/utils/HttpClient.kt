package utils

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.request
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import core.domain.Result

suspend inline fun <reified T> HttpClient.fetch(
    block: HttpRequestBuilder.() -> Unit
): Result<T> = try {
    val response = request(block)
    if (response.status == HttpStatusCode.OK)
        Result.Success(response.body())
    else
        Result.Error("${response.status}: ${response.bodyAsText()}")
} catch (e: SocketTimeoutException) {
    Result.Error("Ошибка подключения")
} catch (e: Exception) {
    Result.Error("${e.message}")
}