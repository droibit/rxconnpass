package com.github.droibit.rxconnpass

import com.squareup.moshi.Json
import java.io.Serializable
import java.util.*

/**
 * [API Reference](http://connpass.com/about/api/)
 *
 * @param resultsReturned 含まれる検索結果の件数
 * @param resultsAvailable 検索結果の総件数
 * @param resultsStart 検索の開始位置
 */
data class EventResponse(
        @JvmField @Json(name="results_returned")  val resultsReturned: Int,
        @JvmField @Json(name="results_available") val resultsAvailable: Int,
        @JvmField @Json(name="results_start")     val resultsStart: Int,
        @JvmField @Json(name="events")            val events: List<Event>
): Serializable

/**
 * @param id イベントID
 * @param title タイトル
 * @param catchCopy キャッチ
 * @param description 概要(HTML形式)
 * @param url connpass.com 上のURL
 * @param hashTag Twitterのハッシュタグ
 * @param startedAt イベント開催日時 (ISO-8601形式)
 * @param endedAt イベント終了日時 (ISO-8601形式)
 * @param limit 定員
 * @param type イベント参加タイプ
 * @param series グループ
 * @param address 開催場所
 * @param place 開催会場
 * @param lat 開催会場の緯度
 * @param lon 開催会場の経度
 * @param ownerId 管理者のID
 * @param ownerNickname 管理者のニックネーム
 * @param ownerDisplayName 管理者の表示名
 * @param accepted 参加者数
 * @param waiting 補欠者数
 * @param updatedAt 更新日時 (ISO-8601形式)
 */
data class Event(
        @JvmField @Json(name="event_id")           val id: Int,
        @JvmField @Json(name="title")              val title: String,
        @JvmField @Json(name="catch")              val catchCopy: String = "",
        @JvmField @Transient                       val description: String? = null,
        @JvmField @Json(name="event_url")          val url: String,
        @JvmField @Json(name="hash_tag")           val hashTag: String = "",
        @JvmField @Json(name="started_at")         val startedAt: Date,
        @JvmField @Json(name="ended_at")           val endedAt: Date,
        @JvmField @Json(name="limit")              val limit: Int? = null,
        @JvmField @Json(name="event_type")         val type: EventType,
        @JvmField @Json(name="series")             val series: Series? = null,
        @JvmField @Json(name="address")            val address: String? = null,
        @JvmField @Json(name="place")              val place: String? = null,
        @JvmField @Json(name="lat")                val lat: Double? = null,
        @JvmField @Json(name="lon")                val lon: Double? = null,
        @JvmField @Json(name="owner_id")           val ownerId: Int,
        @JvmField @Json(name="owner_nickname")     val ownerNickname: String,
        @JvmField @Json(name="owner_display_name") val ownerDisplayName: String,
        @JvmField @Json(name="accepted")           val accepted: Int,
        @JvmField @Json(name="waiting")            val waiting: Int,
        @JvmField @Json(name="updated_at")         val updatedAt: Date
): Serializable {

    val overload: Boolean
        get() = limit != null && limit != 0 && accepted >= limit

    fun isFinished(currentDate: Date) = endedAt < currentDate
}

/**
 * @param id グループID
 * @param title グループタイトル
 * @param url グループのconnpass.com 上のURL
 */
data class Series(
        @JvmField @Json(name="id")    val id: Int,
        @JvmField @Json(name="title") val title: String,
        @JvmField @Json(name="url")   val url: String
): Serializable

/**
 * @property participation connpassで参加受付あり
 * @property advertisement 告知のみ
 */
enum class EventType {
    participation,
    advertisement
}