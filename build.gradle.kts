// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    id("com.google.dagger.hilt.android") version "2.48" apply false
    id ("org.jetbrains.kotlin.jvm") version "2.0.21"
    id("com.google.devtools.ksp") version "2.0.21-1.0.27" apply false
    alias(libs.plugins.compose.compiler) apply false
}

buildscript{
    dependencies{
        classpath(libs.hilt.android.gradle.plugin)
    }
}