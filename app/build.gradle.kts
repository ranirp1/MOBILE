plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    namespace = "cn.shef.msc5.todo"
    compileSdk = 34

    defaultConfig {
        applicationId = "cn.shef.msc5.todo"
        minSdk = 30
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")

    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.7.0")
//    implementation("androidx.fragment:fragment-ktx:1.7.0")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.ui:ui-text-android:1.5.4")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    val nav_version = "2.5.3"
    implementation("androidx.navigation:navigation-compose:$nav_version")
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")

    val material3_version = "1.1.2"
    implementation("androidx.compose.material3:material3:$material3_version")

    implementation("androidx.compose.material:material")
    implementation("androidx.compose.material:material-icons-extended")
//    val swiperefreshlayout_version = "1.1.0"
//    implementation("androidx.swiperefreshlayout:swiperefreshlayout:$swiperefreshlayout_version")

    val room_version = "2.6.0"
    implementation("androidx.room:room-common:$room_version")
    implementation("androidx.room:room-ktx:$room_version")
    kapt("androidx.room:room-compiler:$room_version")

    val google_gms_location = "21.0.1"
    val google_gms_map = "18.1.0"
    val google_map_compose = "2.8.0"
    // google maps
    implementation("com.google.android.gms:play-services-location:$google_gms_location")
    implementation("com.google.android.gms:play-services-maps:$google_gms_map")
    // google maps for compose
    implementation("com.google.maps.android:maps-compose:$google_map_compose")

    val google_map_ktx = "3.2.1"
    // KTX for the Maps SDK for Android
    implementation("com.google.maps.android:maps-ktx:$google_map_ktx")
    // KTX for the Maps SDK for Android Utility Library
    implementation("com.google.maps.android:maps-utils-ktx:$google_map_ktx")


//    val camerax_version = "1.1.0"
//    // camera
//    implementation("androidx.camera:camera-core:${camerax_version}")
//    implementation("androidx.camera:camera-camera2:${camerax_version}")
//    implementation("androidx.camera:camera-lifecycle:${camerax_version}")
//    implementation("androidx.camera:camera-video:${camerax_version}")
//
//    implementation("androidx.camera:camera-view:${camerax_version}")
//    implementation("androidx.camera:camera-extensions:${camerax_version}")

    // https://developer.android.com/jetpack/androidx/releases/core
    // To use the Animator APIs
//    val animation_version = "1.0.0-beta01"
//    implementation("androidx.core:core-animation:$animation_version")
    // Optional - APIs for SplashScreen, including compatibility helpers on devices prior Android 12
//    val splashscreen_version = "1.1.0-alpha02"
//    implementation("androidx.core:core-splashscreen:$splashscreen_version")
}