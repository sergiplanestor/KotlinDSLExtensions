object DependencyVersion {
    // Example: 
    // private const val androidxCore = "1.7.0" or `by lazy { "1.7.0" }`
}

object Dependency {
    // Example: 
    // const val androidxCore = "androidx.core:core-ktx:${DependencyVersion.androidxCore}"
    // or `by lazy { "androidx.core:core-ktx:${DependencyVersion.androidxCore}" }`
}

object TestVersion {
    // Example: 
    // private const val junit = "4.13.2" or `by lazy { "4.13.2" }`
}

object Test {
    // Example: 
    // const val junit = "junit:junit:${TestVersion.junit}"
    // or `by lazy { "junit:junit:${TestVersion.junit}" }`
}
