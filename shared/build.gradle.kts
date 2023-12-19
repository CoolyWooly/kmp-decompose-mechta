plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinSerialization)
}

kotlin {
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true
        }
    }
    
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    sourceSets {
        androidMain.dependencies {
            implementation(libs.ktor.client.okhttp)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
        commonMain.dependencies {
            implementation(libs.decompose)
            implementation(libs.decompose.jetbrains)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.koin.core)
//            implementation(libs.koin.ktor)
//            implementation(libs.koin.test)
//            implementation(libs.koin.android)
            implementation(libs.ktor.client.core)
            implementation(libs.kotlinx.coroutines.core)
        }
    }
}

android {
    namespace = "kz.mechta.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}
