// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
}

// In your root build.gradle file
buildscript {
    dependencies {
        // Add this line to the classpath section
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.5.3")  // Make sure to use the latest version
    }
}
