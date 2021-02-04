import com.novoda.gradle.release.PublishExtension

plugins {
    id(BuildPlugins.androidLibrary)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinAndroidExtensions)
    id(BuildPlugins.dagger)
    id(BuildPlugins.kapt)
    id(BuildPlugins.jacocoAndroid)
}

jacoco {
    toolVersion = Versions.jacoco
}

android {
    compileSdkVersion(AndroidSdk.compileSdkVersion)

    android.buildFeatures.dataBinding = true
    android.buildFeatures.viewBinding = true

    defaultConfig {
        minSdkVersion(AndroidSdk.minSdkVersion)
        targetSdkVersion(AndroidSdk.targetSdkVersion)
        vectorDrawables.useSupportLibrary = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    testOptions {
        animationsDisabled = true
        unitTests.apply {
            isReturnDefaultValues = true
            isIncludeAndroidResources = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildTypes {
        getByName("debug") {
            isDebuggable = true
        }

        getByName("release") {
            isMinifyEnabled = true
        }
    }
}

configure<PublishExtension> {
    userOrg = "androidstudy"
    groupId = "com.androidstudy"
    artifactId = "daraja"
    publishVersion = "1.0.2"
    desc = "Android MPESA SDK Library - Dubbed Daraja"
    repoName = "android-mpesa-api"
    website = "https://github.com/jumaallan/android-mpesa-api"
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Libraries.kotlinStdLib)
    implementation(Libraries.coreKtx)

    // Network - Retrofit, OKHTTP, chucker
    implementation(Libraries.retrofit)
    implementation(Libraries.gson)
    implementation(Libraries.ohttp)
    implementation(Libraries.loggingInterceptor)
    debugImplementation(Libraries.chunkerDebug)
    releaseImplementation(Libraries.chunkerRelease)

    // debug
    implementation(Libraries.timber)
}