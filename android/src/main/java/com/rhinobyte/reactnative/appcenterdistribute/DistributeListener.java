// Copied from: https://github.com/Microsoft/AppCenter-SDK-Android/blob/develop/sdk/appcenter-distribute/src/main/java/com/microsoft/appcenter/distribute/DistributeListener.java
// Raw: https://raw.githubusercontent.com/Microsoft/AppCenter-SDK-Android/develop/sdk/appcenter-distribute/src/main/java/com/microsoft/appcenter/distribute/DistributeListener.java

package com.rhinobyte.reactnative.appcenterdistribute;

import android.app.Activity;

/**
 * Listener for the Distribute allowing customization.
 */
@SuppressWarnings("unused")
public interface DistributeListener {

    /**
     * Called from UI thread whenever a new release is available to download and install.
     * <p>
     * If user does not action the release (either postpone or update), this callback
     * will repeat for every activity change for the same release.
     * <p>
     * If you are showing your own U.I. for the new release, return <code>true</code> to this method
     * and when call {@link Distribute#notifyUpdateAction(int)} when the user action the U.I.
     *
     * @param activity       current activity.
     * @param releaseDetails release details for the update.
     * @return the custom dialog whose visibility will be managed for you if not null.
     */
    boolean onReleaseAvailable(Activity activity, ReleaseDetails releaseDetails);
}
