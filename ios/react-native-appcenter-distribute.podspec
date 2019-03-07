require 'json'

package = JSON.parse(File.read(File.join(__dir__, '../', 'package.json')))

Pod::Spec.new do |s|
  s.name              = package['name']
  s.version           = package['version']
  s.summary           = package['description']
  s.license           = package['license']
  s.homepage          = package['homepage']
  s.documentation_url = "https://github.com/RhinobyteSoftware/react-native-appcenter-distribute"

  s.author            = { 'Ryan Thomas' => 'Ryan.Thomas@rhinobyte.com' }

  s.source            = { :git => "https://github.com/RhinobyteSoftware/react-native-appcenter-distribute.git" }
  s.source_files      = "ReactNativeAppCenterDistribute/**/*.{h,m}"
  s.platform          = :ios, '9.0'
  s.requires_arc      = true

  s.vendored_frameworks = 'AppCenterReactNativeShared/AppCenterReactNativeShared.framework'

  s.dependency 'AppCenterReactNativeShared'
  s.dependency 'AppCenter/Distribute'
  s.dependency 'React'
end
