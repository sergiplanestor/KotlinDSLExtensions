object DependencyVersion {
    const val androidxCore = "1.7.0"
    const val androidxRuntime = "2.4.0"
    const val compose = "1.0.5"
    const val composeMaterial3 = "1.0.0-alpha03"
    const val composeActivity = "1.4.0"
}

object Dependency {

    const val androidxCore = "androidx.core:core-ktx:${DependencyVersion.androidxCore}"
    const val androidxRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${DependencyVersion.androidxRuntime}"
    const val androidxComposeUi = "androidx.compose.ui:ui:${DependencyVersion.compose}"
    const val androidxComposeMaterial = "androidx.compose.material3:material3:${DependencyVersion.composeMaterial3}"
    const val androidxComposeUiPreview = "androidx.compose.ui:ui-tooling-preview:${DependencyVersion.compose}"
    const val androidxComposeActivity = "androidx.activity:activity-compose:${DependencyVersion.composeActivity}"
}

object TestVersion {
    const val junit = "4.13.2"
    const val androidxJUnit = "1.1.3"
    const val androidxEspressoCore = "3.4.0"
    const val androidxComposeJUnit = DependencyVersion.compose
    const val androidxComposeTooling = DependencyVersion.compose
}

object Test {
    const val junit = "junit:junit:${TestVersion.junit}"
    const val androidxJUnit = "androidx.test.ext:junit:${TestVersion.androidxJUnit}"
    const val androidxEspressoCore = "androidx.test.espresso:espresso-core:${TestVersion.androidxEspressoCore}"
    const val androidxComposeJUnit = "androidx.compose.ui:ui-test-junit4:${TestVersion.androidxComposeJUnit}"
    const val androidxComposeTooling = "androidx.compose.ui:ui-tooling:${TestVersion.androidxComposeTooling}"
}