import com.android.build.api.dsl.BuildFeatures
import com.android.build.api.dsl.BuildType
import com.android.build.api.dsl.DefaultConfig
import com.android.build.api.dsl.ProductFlavor
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion

enum class Feature {
    DataBinding,
    ViewBinding,
    Compose
}

sealed class GradlePluginIds(open val value: String) {
    object Kotlin : GradlePluginIds("kotlin-android")
    object App : GradlePluginIds("com.android.application")
    object Lib : GradlePluginIds("com.android.library")
    object Parcelize : GradlePluginIds("kotlin-parcelize")
    object Publish : GradlePluginIds("maven-publish")
    data class Other(override val value: String) : GradlePluginIds(value)

    companion object {
        fun from(isAppModule: Boolean, vararg plugins: GradlePluginIds): List<String> =
            plugins.toMutableList().apply {
                val moduleTypePlugin = if (isAppModule) App else Lib
                if (!contains(moduleTypePlugin)) {
                    add(moduleTypePlugin)
                }
                if (!contains(Kotlin)) {
                    add(Kotlin)
                }
            }.map { it.value }
    }
}

fun BaseAppModuleExtension.appModuleConfig() {
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

fun LibraryExtension.libModuleConfig() {
    compileSdk = SdkConfig.compile
    defaultConfig {
        minSdk = SdkConfig.min
        compileSdk = SdkConfig.compile
        targetSdk = SdkConfig.target
        testInstrumentationRunner = SdkConfig.instrumentationRunner
        vectorDrawables { useSupportLibrary = true }
        consumerProguardFiles("consumer-rules.pro")
    }
}

fun KotlinJvmOptions.jvmTarget(target: String = "1.8") {
    jvmTarget = target
}

fun <BF : BuildFeatures, BT : BuildType, DC : DefaultConfig, PF : ProductFlavor> CommonExtension<BF, BT, DC, PF>.applyCompileOptions() {
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

fun <BF : BuildFeatures, BT : BuildType, DC : DefaultConfig, PF : ProductFlavor> CommonExtension<BF, BT, DC, PF>.commonBuildTypes() {
    buildTypes {
        this.release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        this.debug {

        }
    }
}

fun <BF : BuildFeatures, BT : BuildType, DC : DefaultConfig, PF : ProductFlavor> CommonExtension<BF, BT, DC, PF>.withFeatures(
    vararg features: Feature
) {
    buildFeatures {
        features.forEach {
            when (it) {
                Feature.DataBinding -> {
                    if (this is com.android.build.api.dsl.LibraryBuildFeatures) {
                        dataBinding = true
                    } else {
                        dataBinding { isEnabled = true }
                    }
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

fun <BF : BuildFeatures, BT : BuildType, DC : DefaultConfig, PF : ProductFlavor> CommonExtension<BF, BT, DC, PF>.exclude(
    isMetaExcluded: Boolean = true,
    vararg values: String
) {
    packagingOptions {
        resources {
            values.toMutableList().apply {
                if (isMetaExcluded && !contains("META-INF")) {
                    add("/META-INF")
                }
            }
            excludes.addAll(values.toList())
        }
    }
}
