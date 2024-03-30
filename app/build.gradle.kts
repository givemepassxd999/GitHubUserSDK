plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "github.user.sdk"
    compileSdk = 34

    defaultConfig {
        applicationId = "github.user.sdk"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    ext {
        set("paging_version", "3.3.0-alpha05")
        set("kotlin_version", "3.3.0-alpha04")
        set("retrofit_version", "2.9.0")
        set("retrofit_log_version", "4.9.3")
        set("lifecycle_version", "2.7.0")
        set("timber_version", "5.0.1")
        set("glide_version", "4.16.0")
        set("hilt_version", "2.49")
        set("coil_version", "2.6.0")
    }

    // coil
    implementation("io.coil-kt:coil-compose:${ext.get("coil_version")}")

    // hilt injection
    implementation("com.google.dagger:hilt-android:${ext.get("hilt_version")}")
    ksp("com.google.dagger:hilt-android-compiler:2.49")

    // Glide
    implementation("com.github.bumptech.glide:glide:${ext.get("glide_version")}")

    // Timber
    implementation("com.jakewharton.timber:timber:${ext.get("timber_version")}")

    // retrofit
    implementation("com.squareup.retrofit2:retrofit:${ext.get("retrofit_version")}")
    implementation("com.squareup.retrofit2:converter-gson:${ext.get("retrofit_version")}")
    implementation("com.squareup.retrofit2:retrofit-mock:${ext.get("retrofit_version")}")
    implementation("com.squareup.okhttp3:logging-interceptor:${ext.get("retrofit_log_version")}")

    // lifecycle
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:${ext.get("lifecycle_version")}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${ext.get("lifecycle_version")}")

    // paging
    implementation("androidx.paging:paging-runtime-ktx:${ext.get("paging_version")}")

    // architecture components
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.activity:activity-ktx:1.8.2")
    implementation("androidx.fragment:fragment-ktx:1.6.2")

    //Material Design 3
    implementation("androidx.compose.material3:material3:1.2.1")
    implementation("com.google.android.material:material:1.11.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}