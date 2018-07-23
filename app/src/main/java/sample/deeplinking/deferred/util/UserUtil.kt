package sample.deeplinking.deferred.util

import android.content.Context
import android.text.TextUtils
import sample.deeplinking.deferred.deferreddeeplink.R
import sample.deeplinking.deferred.model.User
import sample.deeplinking.deferred.model.UserInfoModel
import java.util.*

object UserUtil {
    private const val APPLINK_COLOR = "color"

    fun setupUser(context: Context, deepLinkingParams: HashMap<String, String>?): User {
        val user = User()
        if (deepLinkingParams == null || deepLinkingParams.isEmpty()) {
            user.imageUrl = context.resources.getString(R.string.url_default)
            user.tagline = context.resources.getString(R.string.color_default)

        } else {
            val selectedColor = deepLinkingParams[APPLINK_COLOR].toString()
            user.tagline = selectedColor
            user.imageUrl = context.resources.getString(getUrlResource(selectedColor))
        }

        return user
    }

    private fun getUrlResource(selectedColor: String): Int {
        var urlResource = R.string.url_default
        when {
            UserInfoModel.BLACK.name.equals(selectedColor, true) -> urlResource = UserInfoModel.BLACK.url
            UserInfoModel.BLUE.name.equals(selectedColor, true) -> urlResource = UserInfoModel.BLUE.url
            UserInfoModel.GREEN.name.equals(selectedColor, true) -> urlResource = UserInfoModel.GREEN.url
            UserInfoModel.ORANGE.name.equals(selectedColor, true) -> urlResource = UserInfoModel.ORANGE.url
            UserInfoModel.RED.name.equals(selectedColor, true) -> urlResource = UserInfoModel.RED.url
            UserInfoModel.YELLOW.name.equals(selectedColor, true) -> urlResource = UserInfoModel.YELLOW.url
        }

        return urlResource
    }

}