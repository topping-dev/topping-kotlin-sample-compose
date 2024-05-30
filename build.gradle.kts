plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.library").version("7.4.2").apply(false)
    kotlin("multiplatform").version("1.9.22").apply(false)
    kotlin("plugin.serialization").version("1.9.22").apply(false)
    id("dev.topping.kotlin.gradle").version("0.5.2").apply(false)
    id("org.jetbrains.compose").version("1.5.12").apply(false)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}