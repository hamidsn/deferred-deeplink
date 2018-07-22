package sample.deeplinking.deferred.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.util.Log
import com.google.android.gms.analytics.CampaignTrackingReceiver
import sample.deeplinking.deferred.ui.MainActivity
import java.io.UnsupportedEncodingException
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

/**
 * URL to download the app should be as below,
 * note at the end of url we have &referrer=[droid]%3D[SOME-DEEPLINKING-NAMES]
 * Example: https://play.google.com/store/apps/details?id=au.com.bilah.bilah&referrer=[droid]/page-key?number%3D123456789[OPTIONAL:%26utm_campaign%3D[somecampaign]]
 * We expect receiving encoded url with encoded chars, for example  %26 instead of "&" or %3D instead of "=" otherwise referrer doesn't work properly
 * App will decode the url:
 * A sequence of the form "%xy" will be treated as representing a byte where xy is the two-digit hexadecimal representation of the 8 bits...
 *
 * @see: https://goo.gl/48KDjE for decoding
 * MUST HAVE:
 * @see: https://goo.gl/EyzLQL for creating an encoded deeplink include campaign
 */

class InstallReferrerReceiver : BroadcastReceiver() {
    private val TAG = javaClass.name
    private val APP_LINK = "droid"
    private val REFERRER = "referrer"
    private val RECEIVER_ACTION = "com.android.vending.INSTALL_REFERRER"

    override fun onReceive(context: Context, intent: Intent) {
        if (TextUtils.equals(intent.action, RECEIVER_ACTION)) {
            val referrer = intent.getStringExtra(REFERRER)
            if (!TextUtils.isEmpty(referrer)) {
                try {
                    val decodedReferrer = URLDecoder.decode(referrer, StandardCharsets.UTF_8.name())
                    if (!TextUtils.isEmpty(decodedReferrer)) {
                        if (decodedReferrer.contains(APP_LINK)) {
                            val target = Intent(context, MainActivity::class.java)
                            target.action = Intent.ACTION_VIEW
                            target.putExtra(DEFERRED_DEEPLINK, decodedReferrer)
                            Log.d(TAG, String.format("Deferred deep-link referrer: %s", decodedReferrer))
                            target.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                            context.startActivity(target)
                        }
                        //todo send Analytics campaign tracker - decodedReferrer;
                    }

                } catch (e: UnsupportedEncodingException) {
                    Log.e(TAG, "Unable to decode referrer. Deferred deep-link url is not correct: ", e)
                }

            }
            CampaignTrackingReceiver().onReceive(context, intent)
        }
    }

    companion object {
        val DEFERRED_DEEPLINK = "deferred_deeplink"
    }
}
