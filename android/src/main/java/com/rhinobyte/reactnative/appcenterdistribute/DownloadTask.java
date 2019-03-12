// Copied from: https://github.com/Microsoft/AppCenter-SDK-Android/blob/develop/sdk/appcenter-distribute/src/main/java/com/microsoft/appcenter/distribute/DownloadTask.java
// Raw: https://raw.githubusercontent.com/Microsoft/AppCenter-SDK-Android/develop/sdk/appcenter-distribute/src/main/java/com/microsoft/appcenter/distribute/DownloadTask.java

package com.rhinobyte.reactnative.appcenterdistribute;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;

import com.microsoft.appcenter.utils.AppCenterLog;

import static android.content.Context.DOWNLOAD_SERVICE;
import static com.rhinobyte.reactnative.appcenterdistribute.DistributeConstants.LOG_TAG;

/**
 * The download manager API triggers strict mode exception in U.I. thread.
 */
class DownloadTask extends AsyncTask<Void, Void, Void> {

    /**
     * Context.
     */
    @SuppressLint("StaticFieldLeak")
    private final Context mContext;

    /**
     * Release details to check.
     */
    private final ReleaseDetails mReleaseDetails;

    /**
     * Init.
     *
     * @param context        context.
     * @param releaseDetails release details associated to this check.
     */
    DownloadTask(Context context, ReleaseDetails releaseDetails) {
        mContext = context;
        mReleaseDetails = releaseDetails;
    }

    @Override
    protected Void doInBackground(Void[] params) {

        /* Download file. */
        Uri downloadUrl = mReleaseDetails.getDownloadUrl();
        AppCenterLog.debug(LOG_TAG, "Start downloading new release, url=" + downloadUrl);
        DownloadManager downloadManager = (DownloadManager) mContext.getSystemService(DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(downloadUrl);

        /* Hide mandatory download to prevent canceling via notification cancel or download U.I. delete. */
        if (mReleaseDetails.isMandatoryUpdate()) {
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_HIDDEN);
            request.setVisibleInDownloadsUi(false);
        }
        long enqueueTime = System.currentTimeMillis();

        @SuppressWarnings("ConstantConditions")
        long downloadRequestId = downloadManager.enqueue(request);
        CustomizedDistributeService.getInstance().storeDownloadRequestId(downloadManager, this, downloadRequestId, enqueueTime);
        return null;
    }
}
