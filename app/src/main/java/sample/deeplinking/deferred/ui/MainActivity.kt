package sample.deeplinking.deferred.ui

import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import sample.deeplinking.deferred.deferreddeeplink.BR
import sample.deeplinking.deferred.deferreddeeplink.R
import sample.deeplinking.deferred.receiver.InstallReferrerReceiver.Companion.DEFERRED_DEEPLINK
import sample.deeplinking.deferred.model.User
import sample.deeplinking.deferred.util.UserUtil
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        landing()
    }

    private fun landing() {
        val action = intent.action
        var user: User = UserUtil.setupUser(this)

        if (Intent.ACTION_MAIN == action) run {
            // user launches the app from app icon or widget
            // do your normal logic here

        } else if (Intent.ACTION_VIEW == action) {
            // deferred deep link is running the app
            // customise the color of gif
            val uri = getUri()
            if (uri != null) run {
                user = UserUtil.setupUser(this, getDeepLinkParams(uri))
            }
        }

        val userViewModel = UserViewModel(user)
        /// Data-Binding
        val binding = DataBindingUtil.setContentView<ViewDataBinding>(this, R.layout.activity_main)
        binding.setVariable(BR.user, userViewModel)

    }

    private fun getUri(): Uri? {
        val uri = intent.data
        return uri ?: if (intent.hasExtra(DEFERRED_DEEPLINK))
            Uri.parse(intent.extras.getString(DEFERRED_DEEPLINK))
        else
            null
    }

    private fun getDeepLinkParams(uri: Uri?): HashMap<String, String> {
        val deepLinkingParams = HashMap<String, String>()
        if (uri != null) {
            val paramNames = uri.queryParameterNames
            for (name in paramNames) {
                deepLinkingParams[name] = uri.getQueryParameter(name)
            }
        }
        return deepLinkingParams
    }


}



