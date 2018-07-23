package sample.deeplinking.deferred.model

import java.util.Observable

private const val IMAGEURL = "imageUrl"
private const val TAGLINE = "tagline"

class User : Observable() {

    var imageUrl: String = ""
        set(value) {
            field = value
            setChangedAndNotify(IMAGEURL)
        }

    var tagline: String = ""
        set(value) {
            field = value
            setChangedAndNotify(TAGLINE)
        }

    private fun setChangedAndNotify(field: Any) {
        setChanged()
        notifyObservers(field)
    }
}