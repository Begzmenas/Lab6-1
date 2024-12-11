plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.lab2"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.lab2"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true  // Correct testOptions setting
    }
}

dependencies {
    // Core dependencies
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)

    // Unit tests dependencies
    testImplementation(libs.junit)
    testImplementation(libs.androidx.core)
    testImplementation(libs.espresso.core)
    testImplementation(libs.ext.junit)

    // Instrumentation/UI test dependencies
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.androidx.test.core)
    androidTestImplementation(libs.androidx.test.rules)
    androidTestImplementation(libs.androidx.test.runner)
    androidTestImplementation(libs.hamcrest.library)

    // No unnecessary explicit version dependencies were added
}