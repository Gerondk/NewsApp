package plugins

import AppConfig
import gradle.kotlin.dsl.accessors._376146573db9bb6b39a641d04d157273.android
import gradle.kotlin.dsl.accessors._376146573db9bb6b39a641d04d157273.kotlinOptions
import org.gradle.kotlin.dsl.kotlin

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    kotlin("kapt")
}

android {
    compileSdk = AppConfig.compileSDK

    defaultConfig {
        minSdk = AppConfig.minSDK

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = AppConfig.javaVersion
        targetCompatibility = AppConfig.javaVersion
    }
    kotlinOptions {
        jvmTarget = AppConfig.javaTarget
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = AppConfig.composeKotlinCompilerExt
    }
}
