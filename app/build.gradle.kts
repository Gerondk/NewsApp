@file:Suppress("UnstableApiUsage")

import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties


plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("com.google.gms.google-services")
    id("com.jaredsburrows.license")
    id("com.google.firebase.crashlytics")
}

licenseReport {
    generateJsonReport = true
    generateHtmlReport = true
    generateCsvReport = false
    copyHtmlReportToAssets = true
}

android {
    namespace = "com.gkp.newsapp"
    compileSdk = AppConfig.compileSDK

    defaultConfig {
        applicationId = "com.gkp.newsapp"
        minSdk = AppConfig.minSDK
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        val key = gradleLocalProperties(rootDir, rootProject.providers)
            .getProperty("NEWSAPP_API_KEY")

        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "newsApiKey", "\"${key}\"")
        }
        debug {
            buildConfigField("String", "newsApiKey", "\"${key}\"")
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
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = AppConfig.composeKotlinCompilerExt
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

// check ktlint before build
tasks.getByPath("preBuild").dependsOn("ktlintCheck")

dependencies {

    implementation("androidx.core:core-splashscreen:1.0.1")

    implementation(Dependencies.androidCoreKtx)
    implementation(Dependencies.androidLifecycleKtx)

    // compose
    compose()

    // hilt
    hilt()

    // Modules
    coreModule()
    homeModule()
    bookmarksModule()

    testDependencies()

    // Import the BoM for the Firebase platform
    implementation(platform("com.google.firebase:firebase-bom:32.8.0"))

    // Add the dependencies for the Crashlytics and Analytics libraries
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation("com.google.firebase:firebase-crashlytics")
    implementation("com.google.firebase:firebase-analytics")
}
