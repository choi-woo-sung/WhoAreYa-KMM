package goal.hyunwoo.whoareya.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.http.ContentType
import io.ktor.http.URLBuilder
import io.ktor.http.contentType
import io.ktor.http.encodedPath
import io.ktor.http.takeFrom
import io.ktor.serialization.kotlinx.json.json

interface KtorApi {
    val client: HttpClient

    fun HttpRequestBuilder.apiUrl(path: String, param : URLBuilder.() -> Unit)

    fun HttpRequestBuilder.json()
}

class ClothKtorApiImpl() : KtorApi {
    val prodUrl = "https://api.odcloud.kr"

    override val client =
        HttpClient {
            install(ContentNegotiation) {
                json()
            }
        }

    override fun HttpRequestBuilder.apiUrl(path: String, param : URLBuilder.() -> Unit) {
        url {
            takeFrom(prodUrl)
            encodedPath = path
            param()
        }
    }

    override fun HttpRequestBuilder.json() {
        contentType(ContentType.Application.Json)
    }
}


class NaverKtorApiImpl() : KtorApi {
    val prodUrl = "https://naveropenapi.apigw.ntruss.com"

    override val client =
        HttpClient {
            install(ContentNegotiation) {
                json()
            }
        }

    override fun HttpRequestBuilder.apiUrl(path: String, param : URLBuilder.() -> Unit) {
        url {
            takeFrom(prodUrl)
            encodedPath = path
            param()
        }
    }

    override fun HttpRequestBuilder.json() {
        contentType(ContentType.Application.Json)
    }
}
