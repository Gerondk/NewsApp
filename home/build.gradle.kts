plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("io.gitlab.arturbosch.detekt").version("1.22.0")
    id("kotlin-parcelize")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.gkp.home"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
}

dependencies {

    implementation(Dependencies.androidCoreKtx)

    implementation(Dependencies.androidCoreKtx)
    implementation(Dependencies.androidLifecycleKtx)
    implementation(Dependencies.androidActivityCompose)
    implementation(platform(Dependencies.composeBom))
    implementation(Dependencies.composeUi)
    implementation(Dependencies.composeUiGraphics)
    implementation(Dependencies.composeUiToolingPreview)
    implementation(Dependencies.composeMaterial3)
    implementation(Dependencies.composeNavigation)
    implementation(Dependencies.composeRuntineLifecycle)
    implementation(Dependencies.composeViewModel)
    implementation(Dependencies.composeCoreIcons)
    implementation(Dependencies.composeExtendedIcons)
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    //retrofit
    implementation(Dependencies.okhHttp3)
    implementation(Dependencies.okhHttp3Interceptor)
    implementation(Dependencies.gson)
    implementation(Dependencies.gsonSquare)
    implementation(Dependencies.retrofit)

    //coroutine
    implementation(Dependencies.androidCoroutine)

    // hilt
    implementation(Dependencies.hiltAndroid)
    kapt(Dependencies.hiltCompiler)

    //coil
    implementation(Dependencies.composeCoil)

    implementation(project(Modules.core))

    //room
    implementation(Dependencies.roomRuntime)
    annotationProcessor(Dependencies.roomCompiler)
    ksp(Dependencies.roomCompiler)
    implementation(Dependencies.roomCorountine)


    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}