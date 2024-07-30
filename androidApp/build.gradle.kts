import org.jetbrains.kotlin.js.dce.InputResource

plugins {
    id("com.android.application")
    kotlin("android")
    //id("dev.topping.kotlin.gradle")
}

apply(from = "./kotlinprocessor.gradle")

dependencies {
    implementation (project(":shared"))
    implementation ("androidx.constraintlayout:constraintlayout:2.0.2")
    implementation ("androidx.appcompat:appcompat:1.3.0")
    implementation ("androidx.legacy:legacy-support-v4:1.0.0")
    implementation ("androidx.recyclerview:recyclerview:1.2.1")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.3.1")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-savedstate:2.3.1")
    implementation ("androidx.lifecycle:lifecycle-common-java8:2.3.1")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.3")
    implementation ("androidx.navigation:navigation-fragment-ktx:2.3.5")
    implementation ("androidx.navigation:navigation-ui-ktx:2.3.5")
    implementation ("androidx.navigation:navigation-dynamic-features-fragment:2.3.5")
    implementation ("com.google.android.material:material:1.4.0")
    implementation ("org.jetbrains.kotlin:kotlin-stdlib:1.5.10")
    implementation ("org.jetbrains.kotlin:kotlin-reflect:1.5.10")
    implementation ("androidx.core:core-ktx:1.3.2")
    implementation ("androidx.fragment:fragment:1.3.5")
    implementation ("androidx.fragment:fragment-ktx:1.3.5")
    implementation ("dev.topping:toppingandroid") {
        version {
            strictly("0.6.1")
        }
    }
    implementation("androidx.compose.ui:ui") {
        version {
            strictly("1.6.0-alpha06-topping02")
        }
    }
    implementation("androidx.compose.ui:ui-navigation:1.6.0-alpha06-topping02")
    implementation("androidx.compose.ui:ui-lifecycle:1.6.0-alpha06-topping02")
    implementation("androidx.compose.material3:material3") {
        version {
            strictly("1.2.0-alpha08-topping02")
        }
    }
}

android {
    compileSdk = 34
    defaultConfig {
        applicationId = "dev.topping.kotlin.androidApp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}