# About this SDK
This SDK will encapsulate all logic related to GitHub users, such as fetching lists, 
searching by username, and obtaining detailed user profiles.


## Installation

### step 1: Add the app-debug.aar to your project's libs folder.

### step 2: Add the following to your build.gradle file:

```
dependencies {
    implementation(files("libs/app-debug.aar"))
}
```
### step 3: modify the build.gradle file to include the following:
* compileSdk = 34 and targetSdk = 34

```
android {
    namespace = "github.user.aardemo"
    compileSdk = 34

    defaultConfig {
        applicationId = "github.user.aardemo"
        minSdk = 26
        targetSdk = 34
    }
    //...
}
```

### step 4: Add the following to your settings.gradle file:

``` 
dependencies {
    ext {
        set("koin_version", "3.1.6")
        set("timber_version", "5.0.1")
        set("retrofit_version", "2.9.0")
        set("retrofit_log_version", "4.9.3")
        set("coil_version", "2.6.0")
    }
    // Koin Core features
    implementation("io.insert-koin:koin-core:${ext.get("koin_version")}")
    // Koin main features for Android
    implementation("io.insert-koin:koin-android:${ext.get("koin_version")}")
    // Java Compatibility
    implementation("io.insert-koin:koin-android-compat:${ext.get("koin_version")}")

    // Timber
    implementation("com.jakewharton.timber:timber:${ext.get("timber_version")}")

    // retrofit
    implementation("com.squareup.retrofit2:retrofit:${ext.get("retrofit_version")}")
    implementation("com.squareup.retrofit2:converter-gson:${ext.get("retrofit_version")}")
    implementation("com.squareup.retrofit2:retrofit-mock:${ext.get("retrofit_version")}")
    implementation("com.squareup.okhttp3:logging-interceptor:${ext.get("retrofit_log_version")}")

    // coil
    implementation("io.coil-kt:coil-compose:${ext.get("coil_version")}")
    
    //...
}
```
### step 5: Add the following to your AndroidManifest.xml file:

```
<application
        android:name="github.user.sdk.ui.MainApplication"
        //...
```

# Architecture
The SDK is built using the MVVM architecture pattern.

# Tools
- Koin: Dependency Injection
- Retrofit: Network calls
- Coil: Image loading
- Timber: Logging
- JUnit: Unit testing
- Compose: Composable UI


# License
```
Copyright 2024 

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

```
