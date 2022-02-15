package net.givtapp.codeshare.api.CollectGroups.AppList

import kotlinx.serialization.Serializable

@Serializable
class CollectGroupAppListModel(
    val CGS: List<CollectGroupListDetailModel>
)

@Serializable
class CollectGroupListDetailModel(
    val N: String, //Name
    val NS: String, //Namespace
    val C: Boolean, //Celebrations
    val T: Int, //Type
    val L: List<CollectGroupLocationDetailModel>? = null, //Locations
    val Q: List<CollectGroupQrCodeDetailModel>? = null //QRcodes
)

@Serializable
class CollectGroupLocationDetailModel(
    val N: String? = null, //Name
    val LA: Double, //Latitude
    val LO: Double, //Longitude
    val I: String, //Instance
    val R: Int, //Radius
    val DB: String, //DateBegin
    val DE: String //DateEnd
)

@Serializable
class CollectGroupQrCodeDetailModel(
    val N: String? = null, //Name
    val I: String, //Instance
    val A: Boolean //Active
)