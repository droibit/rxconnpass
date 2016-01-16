package com.droibit.github.rxconnpass

import com.droibit.github.rxconnpass.internal.ISO8601
import com.squareup.moshi.Json
import java.util.*

/**
 * [API Reference](http://connpass.com/about/api/)
 *
 * @param resultsReturned 含まれる検索結果の件数
 * @param resultsAvailable 検索結果の総件数
 * @param resultsStart 検索の開始位置
 */
public data class EventResponse(
        @Json(name="results_returned") val resultsReturned: Int,
        @Json(name="results_available") val resultsAvailable: Int,
        @Json(name="results_start") val resultsStart: Int,
        val events: Array<Event>
)

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
public data class Event(
        @Json(name="event_id") val id: Int,
        val title: String,
        @Json(name="catch") val catchCopy: String,
        @Transient val description: String? = null,
        @Json(name="event_url") val url: String,
        @Json(name="hash_tag") val hashTag: String = "",
        @ISO8601 @Json(name="started_at") val startedAt: Date,
        @ISO8601 @Json(name="ended_at") val endedAt: Date,
        val limit: Int?,
        @Json(name="event_type") val type: EventType,
        val series: Series,
        val address: String,
        val place: String,
        val lat: Double?,
        val lon: Double?,
        @Json(name="owner_id") val ownerId: Int,
        @Json(name="owner_nickname") val ownerNickname: String,
        @Json(name="owner_display_name") val ownerDisplayName: String,
        val accepted: Int,
        val waiting: Int,
        @ISO8601 @Json(name="updated_at") val updatedAt: Date
)

/**
 * @param id グループID
 * @param title グループタイトル
 * @param url グループのconnpass.com 上のURL
 */
public data class Series(
        val id: Int,
        val title: String,
        val url: String
)

/**
 * @property participation connpassで参加受付あり
 * @property advertisement 告知のみ
 */
public enum class EventType {
    participation,
    advertisement
}