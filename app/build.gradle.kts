plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("com.google.gms.google-services")
    kotlin("android")


}


android {
    namespace = "com.example.dua"
    compileSdk = 34




    defaultConfig {
        applicationId = "com.example.dua"
        minSdk = 31
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.vectordrawable)
    implementation (libs.androidx.compose.material3.material3)
    implementation(libs.google.firebase.auth.ktx)
    implementation(libs.firebase.firestore.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.runtime.livedata)
    implementation(libs.androidx.media3.common)
    implementation(libs.androidx.material3.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(libs.postgresql)
    implementation(libs.androidx.navigation.compose)
    implementation (libs.androidx.room.runtime)
    implementation (libs.androidx.room.ktx)
    implementation (libs.kotlinx.coroutines.android.v160)
    implementation(libs.androidx.room.runtime)
    implementation (libs.androidx.material.icons.extended)
    implementation (libs.accompanist.navigation.animation)
    implementation (libs.accompanist.insets)
    implementation(libs.coil)
    implementation(libs.coil.gif)
    implementation (libs.coil.compose.v222)
    implementation (libs.squareup.retrofit)
    implementation (libs.converter.gson)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.runtime.livedata.v120)
    implementation(libs.androidx.lifecycle.livedata.ktx.v250)
    implementation(libs.androidx.runtime.livedata.v110beta03)
    implementation(libs.androidx.runtime.livedata.v168)
    implementation (libs.androidx.runtime.livedata.v105)
    implementation (libs.converter.moshi)
    implementation (libs.moshi.kotlin)

























}
apply(plugin = "com.google.gms.google-services")
apply (plugin = "com.android.application")


