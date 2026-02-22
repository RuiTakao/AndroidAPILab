plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)

    // Hilt
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.takaobrog.iftest"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.takaobrog.iftest"
        minSdk = 36
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.takaobrog.iftest.HiltTestRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // Hilt
    implementation("com.google.dagger:hilt-android:2.57.1")
    kapt("com.google.dagger:hilt-android-compiler:2.57.1")

    testImplementation(libs.junit)
    testImplementation("androidx.arch.core:core-testing:2.2.0")

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation("androidx.arch.core:core-testing:2.2.0")

    // Hilt testing
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.57.1")
    kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.57.1")

    // Mockito（finalクラスをモックできるようにする）
    testImplementation("org.mockito:mockito-inline:5.2.0")

    // Kotlin用のMockito拡張（whenever等）
    testImplementation("org.mockito.kotlin:mockito-kotlin:5.2.1")
}