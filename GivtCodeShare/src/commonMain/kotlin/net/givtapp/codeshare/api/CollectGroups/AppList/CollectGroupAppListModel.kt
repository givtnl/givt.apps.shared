package net.givtapp.codeshare.api.CollectGroups.AppList

import kotlinx.serialization.Serializable

@Serializable
class CollectGroupAppListModel(
    val CGS: List<CollectGroupListDetailModel>
)

@Serializable
class CollectGroupListDetailModel(
    val N: String,
    val NS: String,
    val C: Boolean,
    val T: Int,
    val L: List<CollectGroupLocationDetailModel>? = null,
    val Q: List<CollectGroupQrCodeDetailModel>? = null
)

@Serializable
class CollectGroupLocationDetailModel(
    val N: String? = null,
    val LA: Double,
    val LO: Double,
    val I: String,
    val R: Double,
    val DB: String,
    val DE: String
)

@Serializable
class CollectGroupQrCodeDetailModel(
    val N: String? = null,
    val I: String,
    val A: Boolean
)