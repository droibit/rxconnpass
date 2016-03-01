@file:JvmName("MockResponse")
package com.github.droibit.rxconnpass

import com.github.droibit.rxconnpass.ResponseAdapters.iso8601Format


internal const val mockNoEventResponse = """
{"results_returned": 0, "events": [], "results_start": 1, "results_available": 0}
"""
internal val mockNoEvent = EventResponse(
        resultsReturned = 0,
        resultsStart = 1,
        resultsAvailable = 0,
        events = emptyList()
)

internal const val mockPartiallyMissingResponse = """
{"results_returned": 1, "events": [{"event_url": "http://connpass.com/event/5691/", "event_type": "participation", "owner_nickname": "droibiter", "series": null, "updated_at": "2014-03-22T21:31:46+09:00", "lat": null, "started_at": "2014-03-28T00:00:00+09:00", "hash_tag": "", "title": "\u30c6\u30b9\u30c8\uff12", "event_id": 5691, "lon": null, "waiting": 0, "limit": null, "owner_id": 9742, "owner_display_name": "Shinya Kumagai", "description": "", "address": null, "catch": "", "accepted": 1, "ended_at": "2014-03-28T02:00:00+09:00", "place": null}], "results_start": 1, "results_available": 1}
"""
internal val mockPartiallyMissingEvent = EventResponse(
        resultsReturned = 1,
        resultsStart = 1,
        resultsAvailable = 1,
        events = arrayListOf(
                Event(
                        id = 5691,
                        title = "テスト２",
                        url = "http://connpass.com/event/5691/",
                        startedAt = iso8601Format.parse("2014-03-28T00:00:00+09:00"),
                        endedAt = iso8601Format.parse("2014-03-28T02:00:00+09:00"),
                        type = EventType.participation,
                        ownerId = 9742,
                        ownerNickname = "droibiter",
                        ownerDisplayName = "Shinya Kumagai",
                        accepted = 1,
                        waiting = 0,
                        updatedAt = iso8601Format.parse("2014-03-22T21:31:46+09:00")
                )
        )
)

internal val mockSingleEvent = EventResponse(
        resultsReturned = 1,
        resultsStart = 1,
        resultsAvailable = 683,
        events = arrayListOf(
                Event(
                        id = 24503,
                        title = "でじぽろ #3",
                        catchcopy = "webっぽいことやるっぽい",
                        url = "http://digiporo.connpass.com/event/24503/",
                        hashTag = "digiporo",
                        startedAt = iso8601Format.parse("2016-01-15T18:00:00+09:00"),
                        endedAt = iso8601Format.parse("2016-01-15T22:00:00+09:00"),
                        limit = 24,
                        type = EventType.participation,
                        series = Series(
                                id = 1383,
                                title = "でじぽろ",
                                url = "http://digiporo.connpass.com/"
                        ),
                        address = "札幌市中央区北1条東4丁目1番地1　サッポロファクトリー1条館　3階 ",
                        place = "株式会社インフィニットループ会議室",
                        lat = 43.064386400000,
                        lon = 141.363206900000,
                        ownerId = 50460,
                        ownerNickname = "nasa9084",
                        ownerDisplayName = "nasa9084",
                        accepted = 9,
                        waiting = 0,
                        updatedAt = iso8601Format.parse("2016-01-15T01:00:04+09:00")
                )
        )

)
internal const val mockSingleEventResponse = """
{"results_returned": 1, "events": [{"event_url": "http://digiporo.connpass.com/event/24503/", "event_type": "participation", "owner_nickname": "nasa9084", "series": {"url": "http://digiporo.connpass.com/", "id": 1383, "title": "\u3067\u3058\u307d\u308d"}, "updated_at": "2016-01-15T01:00:04+09:00", "lat": "43.064386400000", "started_at": "2016-01-15T18:00:00+09:00", "hash_tag": "digiporo", "title": "\u3067\u3058\u307d\u308d #3", "event_id": 24503, "lon": "141.363206900000", "waiting": 0, "limit": 24, "owner_id": 50460, "owner_display_name": "nasa9084", "description": "", "address": "\u672d\u5e4c\u5e02\u4e2d\u592e\u533a\u53171\u6761\u67714\u4e01\u76ee1\u756a\u57301\u3000\u30b5\u30c3\u30dd\u30ed\u30d5\u30a1\u30af\u30c8\u30ea\u30fc1\u6761\u9928\u30003\u968e ", "catch": "web\u3063\u307d\u3044\u3053\u3068\u3084\u308b\u3063\u307d\u3044", "accepted": 9, "ended_at": "2016-01-15T22:00:00+09:00", "place": "\u682a\u5f0f\u4f1a\u793e\u30a4\u30f3\u30d5\u30a3\u30cb\u30c3\u30c8\u30eb\u30fc\u30d7\u4f1a\u8b70\u5ba4"}], "results_start": 1, "results_available": 683}

"""

// 3 events
internal const val mockMultiEventResponse = """
{"results_returned": 3, "events": [{"event_url": "http://digiporo.connpass.com/event/24503/", "event_type": "participation", "owner_nickname": "nasa9084", "series": {"url": "http://digiporo.connpass.com/", "id": 1383, "title": "\u3067\u3058\u307d\u308d"}, "updated_at": "2016-01-15T01:00:04+09:00", "lat": "43.064386400000", "started_at": "2016-01-15T18:00:00+09:00", "hash_tag": "digiporo", "title": "\u3067\u3058\u307d\u308d #3", "event_id": 24503, "lon": "141.363206900000", "waiting": 0, "limit": 24, "owner_id": 50460, "owner_display_name": "nasa9084", "description": "", "address": "\u672d\u5e4c\u5e02\u4e2d\u592e\u533a\u53171\u6761\u67714\u4e01\u76ee1\u756a\u57301\u3000\u30b5\u30c3\u30dd\u30ed\u30d5\u30a1\u30af\u30c8\u30ea\u30fc1\u6761\u9928\u30003\u968e ", "catch": "web\u3063\u307d\u3044\u3053\u3068\u3084\u308b\u3063\u307d\u3044", "accepted": 9, "ended_at": "2016-01-15T22:00:00+09:00", "place": "\u682a\u5f0f\u4f1a\u793e\u30a4\u30f3\u30d5\u30a3\u30cb\u30c3\u30c8\u30eb\u30fc\u30d7\u4f1a\u8b70\u5ba4"}, {"event_url": "http://techcircle.connpass.com/event/23365/", "event_type": "participation", "owner_nickname": "shiraco", "series": {"url": "http://techcircle.connpass.com/", "id": 954, "title": "Tech-Circle"}, "updated_at": "2016-01-14T17:44:24+09:00", "lat": "35.695876800000", "started_at": "2015-12-10T18:30:00+09:00", "hash_tag": "techcircleja", "title": "Tech-Circle Chainer\u3067\u81ea\u7136\u8a00\u8a9e\u51e6\u7406\u3092\u884c\u3044\u7ffb\u8a33\u306b\u6311\u6226(\u30cf\u30f3\u30ba\u30aa\u30f3\uff09", "event_id": 23365, "lon": "139.690339800000", "waiting": 21, "limit": 46, "owner_id": 2268, "owner_display_name": "shiraco", "description": "", "address": "\u6771\u4eac\u90fd\u65b0\u5bbf\u533a\u897f\u65b0\u5bbf8-17-1\u3000 \u4f4f\u53cb\u4e0d\u52d5\u7523\u65b0\u5bbf\u30b0\u30e9\u30f3\u30c9\u30bf\u30ef\u30fc 14\uff26", "catch": "Tech-Circle #11 Chainer Handson", "accepted": 45, "ended_at": "2015-12-10T21:30:00+09:00", "place": "TIS\u682a\u5f0f\u4f1a\u793e \u6771\u4eac\u672c\u793e"}, {"event_url": "http://tokyosharingeconomy.connpass.com/event/25214/", "event_type": "participation", "owner_nickname": "hsekine", "series": {"url": "http://tokyosharingeconomy.connpass.com/", "id": 1749, "title": "Tokyo Sharing Economy Startups"}, "updated_at": "2016-01-14T15:47:31+09:00", "lat": "35.672853900000", "started_at": "2016-01-22T18:30:00+09:00", "hash_tag": "", "title": "Sharing Economy Tech Meetup", "event_id": 25214, "lon": "139.709821900000", "waiting": 0, "limit": 30, "owner_id": 68167, "owner_display_name": "hsekine", "description": "", "address": "\u3012150-0001 \u6771\u4eac\u90fd\u6e0b\u8c37\u533a\u795e\u5bae\u524d2\u4e01\u76ee18-21", "catch": "\u30b7\u30a7\u30a2\u30ea\u30f3\u30b0\u30a8\u30b3\u30ce\u30df\u30fc\u95a2\u9023\u306e\u30b9\u30bf\u30fc\u30c8\u30a2\u30c3\u30d7\u3092\u4e2d\u5fc3\u3068\u3057\u305f\u30a8\u30f3\u30b8\u30cb\u30a2\u5411\u3051\u306e\u30a4\u30d9\u30f3\u30c8\uff08\u30df\u30fc\u30c8\u30a2\u30c3\u30d7\uff09\u3067\u3059\u3002", "accepted": 0, "ended_at": "2016-01-22T22:00:00+09:00", "place": "kurkku home"}], "results_start": 1, "results_available": 683}
"""
