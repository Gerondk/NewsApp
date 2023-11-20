package plugins

import AppConfig
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

// ktlintCheck before Build
tasks.getByPath("preBuild").dependsOn("ktlintCheck")

// log the  test result of each test in the console.
tasks.withType(Test::class) {
    testLogging {
        showStandardStreams = true
        setEvents(listOf("skipped", "passed", "failed"))
        setExceptionFormat("full")
    }
}
