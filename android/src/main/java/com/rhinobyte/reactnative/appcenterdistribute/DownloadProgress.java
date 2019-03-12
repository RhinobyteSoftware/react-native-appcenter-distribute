// Copied from: https://github.com/Microsoft/AppCenter-SDK-Android/blob/develop/sdk/appcenter-distribute/src/main/java/com/microsoft/appcenter/distribute/DownloadProgress.java
// Raw: https://raw.githubusercontent.com/Microsoft/AppCenter-SDK-Android/develop/sdk/appcenter-distribute/src/main/java/com/microsoft/appcenter/distribute/DownloadProgress.java

package com.rhinobyte.reactnative.appcenterdistribute;

/**
 * Class to hold current download progress status.
 */
class DownloadProgress {

    /**
     * Number of bytes downloaded so far.
     */
    private final long mCurrentSize;

    /**
     * Expected file size.
     */
    private final long mTotalSize;

    /**
     * Init.
     */
    DownloadProgress(long currentSize, long totalSize) {
        mCurrentSize = currentSize;
        mTotalSize = totalSize;
    }

    /**
     * @return Number of bytes downloaded so far.
     */
    long getCurrentSize() {
        return mCurrentSize;
    }

    /**
     * @return Expected file size.
     */
    long getTotalSize() {
        return mTotalSize;
    }
}
