plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.devtools.ksp)
    alias(libs.plugins.jetbrains.kotlin.kapt)
    alias(libs.plugins.navigation.safe.args.kotlin)
}

android {
    namespace = "com.example.kotlinfreegamelist"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.kotlinfreegamelist"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        viewBinding = true
        dataBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Jetpack Navigation
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)

    // Jetpack Lifecycle
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.lifecycle.viewmodel.compose)
    implementation(libs.lifecycle.livedata.ktx)

    // Jetpack Legacy
    implementation(libs.legacy.support.v4)

    // Jetpack Room
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    implementation(libs.room.rxjava)
    ksp(libs.room.compiler)
    annotationProcessor(libs.room.compiler)

    // Coroutines
    implementation(libs.coroutines.core)

    // Retrofit
    implementation(libs.retrofit.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.retrofit.adapter.rxjava2)

    // RxJava2
    implementation(libs.rxjava2.rxjava)
    implementation(libs.rxjava2.rxandroid)

}