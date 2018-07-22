package sample.deeplinking.deferred.util

import android.content.Context
import android.text.TextUtils
import sample.deeplinking.deferred.deferreddeeplink.R
import sample.deeplinking.deferred.model.User
import sample.deeplinking.deferred.model.UserInfoModel
import java.util.*

object UserUtil {
    private const val APPLINK_COLOR = "color"

    fun setupUser(context: Context): User {
        var user = User()
        user.imageUrl = context.resources.getString(R.string.url_default)
        user.tagline = context.resources.getString(R.string.color_default)

        return user
    }

    fun setupUser(context: Context, deepLinkingParams: HashMap<String, String>): User {
        var user = User()
        if (!isEmpty(deepLinkingParams)) {
            val selectedColor = deepLinkingParams[APPLINK_COLOR].toString()
            user.tagline = selectedColor
            user.imageUrl = context.resources.getString(getUrlResource(selectedColor))
        } else {

        }

        return user
    }

    private fun getUrlResource(selectedColor: String): Int {
        var urlResource = R.string.url_default
        when {
            TextUtils.equals(selectedColor.toLowerCase(), UserInfoModel.BLACK.name.toLowerCase()) -> urlResource = UserInfoModel.BLACK.url
            TextUtils.equals(selectedColor.toLowerCase(), UserInfoModel.BLUE.name.toLowerCase()) -> urlResource = UserInfoModel.BLUE.url
            TextUtils.equals(selectedColor.toLowerCase(), UserInfoModel.GREEN.name.toLowerCase()) -> urlResource = UserInfoModel.GREEN.url
            TextUtils.equals(selectedColor.toLowerCase(), UserInfoModel.ORANGE.name.toLowerCase()) -> urlResource = UserInfoModel.ORANGE.url
            TextUtils.equals(selectedColor.toLowerCase(), UserInfoModel.RED.name.toLowerCase()) -> urlResource = UserInfoModel.RED.url
            TextUtils.equals(selectedColor.toLowerCase(), UserInfoModel.YELLOW.name.toLowerCase()) -> urlResource = UserInfoModel.YELLOW.url
        }

        return urlResource
    }

    private fun isEmpty(map: Map<*, *>?): Boolean {
        return map == null || map.isEmpty()
    }
}