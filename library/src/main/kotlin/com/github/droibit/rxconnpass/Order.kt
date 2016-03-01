package com.github.droibit.rxconnpass

enum class Order(val index: Int) {
    UPDATED(0), // 更新日時順
    EVENT(1), // 開催日時順
    NEW(2); // 新着順

    override fun toString() = "$index"
}