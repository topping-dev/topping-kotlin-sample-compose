pluginManagement {
    repositories {
        //mavenLocal()
        maven("https://github.com/topping-dev/topping-compose-release/raw/maven2")
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        google()
        gradlePluginPortal()
        mavenCentral()
    }
    
}

dependencyResolutionManagement {
    repositories {
        //mavenLocal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://github.com/topping-dev/topping-compose-release/raw/maven2")
    }
}

rootProject.name = "topping-kotlin-sample-compose"
include(":androidApp")
include(":shared")

