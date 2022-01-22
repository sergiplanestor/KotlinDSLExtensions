package TODO("Include your package")

import org.gradle.api.JavaVersion

fun com.android.build.gradle.internal.dsl.BaseAppModuleExtension.appModuleConfig() {
    compileSdk = SdkConfig.compile
    buildToolsVersion = SdkConfig.buildTools
    defaultConfig {
        applicationId = SdkConfig.appId
        minSdk = SdkConfig.min
        compileSdk = SdkConfig.compile
        targetSdk = SdkConfig.target
        versionCode = BuildVersion.code
        versionName = BuildVersion.name
        testInstrumentationRunner = SdkConfig.instrumentationRunner
        vectorDrawables { useSupportLibrary = true }
    }
}

fun com.android.build.gradle.internal.dsl.BaseAppModuleExtension.commonBuildTypes() {
    buildTypes {
        release {
            // TODO: To review
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
          // TODO: To implement
        }
    }
}

fun com.android.build.gradle.internal.dsl.BaseAppModuleExtension.applyCompileOptions() {
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

fun com.android.build.gradle.internal.dsl.BaseAppModuleExtension.withFeatures(vararg features: Feature) {
    buildFeatures {
        features.forEach {
            when (it) {
                Feature.DataBinding -> {
                    dataBinding = true
                }
                Feature.ViewBinding -> {
                    viewBinding = true
                }
                Feature.Compose -> {
                    compose = true
                }
            }
        }
    }
}
