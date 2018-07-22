package sample.deeplinking.deferred.util

import java.util.*

class User : Observable() {

    var imageUrl: String = ""
        set(value) {
            field = value
            setChangedAndNotify("imageUrl")
        }

    var tagline: String = ""
        set(value) {
            field = value
            setChangedAndNotify("tagline")
        }

    private fun setChangedAndNotify(field: Any) {
        setChanged()
        notifyObservers(field)
    }
}