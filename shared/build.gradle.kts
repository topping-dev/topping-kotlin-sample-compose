plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization")
    kotlin("native.cocoapods")
    id("org.jetbrains.compose")
}
group = "dev.topping.kotlin"
version = "1.0"

//https://stackoverflow.com/questions/63267897/building-for-ios-simulator-but-the-linked-framework-framework-was-built

kotlin {
    androidTarget()

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        /*specRepos {
            url("https://github.com/Deadknight/dk-specs.git")
        }*/
        summary = "Topping Engine kotlin sample"
        homepage = "https://github.com/topping-dev/topping-kotlin-sample"
        ios.deploymentTarget = "13.0"
        framework {
            baseName = "shared"
            isStatic = false
        }
        pod("Topping") {
            version = "0.6.1"
            extraOpts += listOf("-compiler-option", "-fmodules")
            //linkOnly = true
        }
        podfile = project.file("../iosApp/Podfile")
    }

    sourceSets {
        all {
            languageSettings.optIn("kotlin.RequiresOptIn")
            languageSettings.optIn("kotlinx.cinterop.ExperimentalForeignApi")
            languageSettings.optIn("kotlinx.cinterop.BetaInteropApi")
            languageSettings.optIn("kotlin.experimental.ExperimentalNativeApi")
            languageSettings.optIn("org.jetbrains.kotlin.backend.konan.InternalKotlinNativeApi")
        }
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3")
                implementation(kotlin("reflect"))
                implementation("dev.topping:toppingkotlin:0.6.1")

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
        }
        //commonMain.kotlin.srcDir("../androidApp/build/generated/toppingviewbinding")
        commonMain.kotlin.srcDir("../androidApp/build/generated/toppingresource")
        val androidMain by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
    }
}
android {
    namespace = "dev.topping.kotlin"
    compileSdk = 34
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 24
        targetSdk = 34
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.7"
    }
    sourceSets {
        getByName("main") {
            java.srcDirs("src/androidMain/kotlin")
            res.srcDirs("src/androidMain/res")
        }
        getByName("test") {
            java.srcDirs("src/androidTest/kotlin")
            res.srcDirs("src/androidTest/res")
        }
    }
}

task<Exec>("iosAppPodInstall") {
    workingDir("${project.rootDir}/iosApp")
    commandLine("pod", "install")
}