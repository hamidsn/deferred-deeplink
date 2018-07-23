package sample.deeplinking.deferred.ui

import android.databinding.BaseObservable
import android.databinding.Bindable
import sample.deeplinking.deferred.model.User
import java.util.*
import sample.deeplinking.deferred.deferreddeeplink.BR

class UserViewModel(private val user: User) : Observer, BaseObservable() {
    private var IMAGEURL = "imageUrl"
    private var TAGLINE = "tagline"

    /// Register itself as the observer of Model
    init {
        user.addObserver(this)
    }

    /// Notify the UI when change event emitting from Model is received.
    override fun update(p0: Observable?, p1: Any?) {
        if (p1 is String) {
            if (p1 == IMAGEURL) {
                notifyPropertyChanged(BR.imageUrl)
            } else if (p1 == TAGLINE) {
                notifyPropertyChanged(BR.tagline)
            }
        }
    }

    val imageUrl: String
        @Bindable get() {
            return user.imageUrl
        }

    val tagline: String
        @Bindable get() {
            return "$TAGLINE + :  ${user.tagline}"
        }


}
