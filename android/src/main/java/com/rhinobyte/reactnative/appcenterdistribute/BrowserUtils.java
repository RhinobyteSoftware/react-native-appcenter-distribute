// Copied from: https://github.com/Microsoft/AppCenter-SDK-Android/blob/develop/sdk/appcenter-distribute/src/main/java/com/microsoft/appcenter/distribute/BrowserUtils.java
// Raw: https://raw.githubusercontent.com/Microsoft/AppCenter-SDK-Android/develop/sdk/appcenter-distribute/src/main/java/com/microsoft/appcenter/distribute/BrowserUtils.java

package com.rhinobyte.reactnative.appcenterdistribute;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import com.microsoft.appcenter.utils.AppCenterLog;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static com.rhinobyte.reactnative.appcenterdistribute.DistributeConstants.LOG_TAG;

/**
 * Browser utils.
 */
class BrowserUtils {

    @VisibleForTesting
    BrowserUtils() {

        /* Hide constructor in utils pattern. */
    }


    /**
     * Open a URL in the best browser available.
     *
     * @param url      url to open.
     * @param activity activity from which to open browser.
     */
    static void openBrowser(@NonNull String url, @NonNull Activity activity) {

        /* Open a browser but we don't want a chooser U.I. to pop. */
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        List<ResolveInfo> browsers = activity.getPackageManager().queryIntentActivities(intent, 0);
        if (browsers.isEmpty()) {
            AppCenterLog.error(LOG_TAG, "No browser found on device, abort login.");
        } else {

            /*
             * Check the default browser is not the picker,
             * last thing we want is app to start and suddenly asks user to pick
             * between 2 browsers without explaining why.
             */
            String defaultBrowserPackageName = null;
            String defaultBrowserClassName = null;
            ResolveInfo defaultBrowser = activity.getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
            if (defaultBrowser != null) {
                ActivityInfo activityInfo = defaultBrowser.activityInfo;
                defaultBrowserPackageName = activityInfo.packageName;
                defaultBrowserClassName = activityInfo.name;
                AppCenterLog.debug(LOG_TAG, "Default browser seems to be " + defaultBrowserPackageName + "/" + defaultBrowserClassName);
            }
            String selectedPackageName = null;
            String selectedClassName = null;
            for (ResolveInfo browser : browsers) {
                ActivityInfo activityInfo = browser.activityInfo;
                if (activityInfo.packageName.equals(defaultBrowserPackageName) && activityInfo.name.equals(defaultBrowserClassName)) {
                    selectedPackageName = defaultBrowserPackageName;
                    selectedClassName = defaultBrowserClassName;
                    AppCenterLog.debug(LOG_TAG, "And its not the picker.");
                    break;
                }
            }
            if (defaultBrowser != null && selectedPackageName == null) {
                AppCenterLog.debug(LOG_TAG, "Default browser is actually a picker...");
            }

            /* If no default browser found, pick first one we can find. */
            if (selectedPackageName == null) {
                AppCenterLog.debug(LOG_TAG, "Picking first browser in list.");
                ResolveInfo browser = browsers.iterator().next();
                ActivityInfo activityInfo = browser.activityInfo;
                selectedPackageName = activityInfo.packageName;
                selectedClassName = activityInfo.name;
            }

            /* Launch generic browser. */
            AppCenterLog.debug(LOG_TAG, "Launch browser=" + selectedPackageName + "/" + selectedClassName);
            intent.setClassName(selectedPackageName, selectedClassName);
            activity.startActivity(intent);
        }
    }

    /**
     * Append a query parameter to an existing URI.
     *
     * @param uri         initial uri.
     * @param appendQuery parameter to append.
     * @return uri string with appended query item.
     */
    static String appendUri(@NonNull String uri, @NonNull String appendQuery) throws URISyntaxException {
        URI oldUri = new URI(uri);
        String newQuery = oldUri.getQuery();
        if (newQuery == null) {
            newQuery = appendQuery;
        } else {
            newQuery += "&" + appendQuery;
        }
        URI newUri = new URI(oldUri.getScheme(), oldUri.getAuthority(),
                oldUri.getPath(), newQuery, oldUri.getFragment());
        return newUri.toString();
    }
}
