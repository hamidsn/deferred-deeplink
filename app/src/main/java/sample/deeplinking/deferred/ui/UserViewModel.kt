package sample.deeplinking.deferred.ui

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.view.View
import sample.deeplinking.deferred.util.User
import java.util.*
import sample.deeplinking.deferred.deferreddeeplink.BR

class UserViewModel(private val user: User) : Observer, BaseObservable() {

    /// Register itself as the observer of Model
    init {
        user.addObserver(this)
    }

    /// Notify the UI when change event emitting from Model is received.
    override fun update(p0: Observable?, p1: Any?) {
        if (p1 is String) {
            if (p1 == "imageUrl") {
                notifyPropertyChanged(BR.imageUrl)
            } else if (p1 == "tagLine") {
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
            return "Tagline: " + user.tagline
        }


}
