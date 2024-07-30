pluginManagement {
    repositories {
        //mavenLocal()
        maven("https://github.com/topping-dev/topping-compose-release/raw/maven2")
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    
}

dependencyResolutionManagement {
    repositories {
        //mavenLocal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://github.com/topping-dev/topping-compose-release/raw/maven2")
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "topping-kotlin-sample-compose"
include(":androidApp")
include(":shared")

