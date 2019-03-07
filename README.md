
# React Native AppCenter Distribute

The App Center SDK can has a variety of features. See the (App Center SDK documentation)[https://docs.microsoft.com/en-us/appcenter/sdk/] for more details.

Unfortunately, Microsoft does not support the Distribute features of the sdk in their react-native bridge code projects. This library aims to fill that void for scenarios where the code push sdk is not appropriate or for scenarios where both the code push sdk features and the Distribute SDK features are desired.

See the (App Center Distribute documentation)[https://docs.microsoft.com/en-us/appcenter/sdk/distribute/android] for more details.



**NOTE**: See https://github.com/Microsoft/AppCenter-SDK-React-Native/issues/225 regarding Microsoft's plans regarding support for this. They usually try to push consumers to their code push service which allows updates to the bundled resources but does not support updates to the native binaries. If you do not need the update feature for native binaries then using only the code push sdk might be a better alternative for your project.
