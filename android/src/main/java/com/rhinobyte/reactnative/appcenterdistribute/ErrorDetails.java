// Copied from: https://github.com/Microsoft/AppCenter-SDK-Android/blob/develop/sdk/appcenter-distribute/src/main/java/com/microsoft/appcenter/distribute/ErrorDetails.java
// Raw: https://raw.githubusercontent.com/Microsoft/AppCenter-SDK-Android/develop/sdk/appcenter-distribute/src/main/java/com/microsoft/appcenter/distribute/ErrorDetails.java

package com.rhinobyte.reactnative.appcenterdistribute;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Release details JSON schema.
 */
class ErrorDetails {

    /**
     * Error code when all releases have been deleted.
     */
    static final String NO_RELEASES_FOR_USER_CODE = "no_releases_for_user";

    /**
     * Code property.
     */
    private static final String CODE = "code";

    /**
     * Error code.
     */
    private String code;

    /**
     * Parse a JSON string describing error details.
     *
     * @param json a string.
     * @return parsed error details.
     * @throws JSONException if JSON is invalid.
     */
    static ErrorDetails parse(String json) throws JSONException {
        JSONObject object = new JSONObject(json);
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.code = object.getString(CODE);
        return errorDetails;
    }

    /**
     * Get error code.
     *
     * @return error code
     */
    String getCode() {
        return code;
    }
}
