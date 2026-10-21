package `in`.kmp.demoapp.utils

import platform.UIKit.*
import platform.darwin.*

class IosToastManager : ToastManager {
    override fun show(message: String) {
        dispatch_async(dispatch_get_main_queue()) {
            var top = UIApplication.sharedApplication.keyWindow?.rootViewController
                ?: return@dispatch_async
            while (top.presentedViewController != null) top = top.presentedViewController!!

            val alert = UIAlertController.alertControllerWithTitle(
                title = null, message = message,
                preferredStyle = UIAlertControllerStyleAlert
            )
            top.presentViewController(alert, true, null)

            dispatch_after(dispatch_time(DISPATCH_TIME_NOW, 2_000_000_000L), dispatch_get_main_queue()) {
                alert.dismissViewControllerAnimated(true, null)
            }
        }
    }
}