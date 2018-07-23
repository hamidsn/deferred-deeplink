# deferred-deeplink
Shows how deferred deeplinking works natively on Android


Sample app is available in Google Play.

https://play.google.com/store/apps/details?id=sample.deeplinking.deferred.deferreddeeplink

You can test its functionality by bewlow sample URLs:

https://www.iranatlas.info/droid/deferred-deeplink.htm?color=yellow

https://www.iranatlas.info/droid/deferred-deeplink.htm?color=black

https://www.iranatlas.info/droid/deferred-deeplink.htm?color=blue

https://www.iranatlas.info/droid/deferred-deeplink.htm?color=orange

https://www.iranatlas.info/droid/deferred-deeplink.htm?color=red

https://www.iranatlas.info/droid/deferred-deeplink.htm?color=green

If you have the app on your device, clicking on the links will use direct deep link functionality and show the color to you.

BUT

When you don't have the app installed on your device, clicking on one of above links will use deferred deep linking functionality, will download the app and show the right color you chose.

The color will pass from URL to Google Play. After finishing installation, Play will send a broadcast to the app with the selected color. InstalReferrerReceiver will reveice the broadcast and pass the color to your Activity. Now you can handle the color and show the right UI.

You can check the Java Script code to create intent URL inside:

view-source:https://www.iranatlas.info/droid/deferred-deeplink.htm

Further reading:

https://www.linkedin.com/pulse/android-deferred-deep-link-hamid-sedghi-n/?published=t
