plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id ("kotlin-kapt")
}

android {
    namespace = "ir.abyx.daneshjooyar"
    compileSdk = 34

    defaultConfig {
        applicationId = "ir.abyx.daneshjooyar"
        minSdk = 25
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

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    //lottie animation
    implementation(libs.lottie)

    //Justify Text View
    implementation(libs.justifiedtextview)

    //security
    implementation(libs.androidx.security.crypto)

    //expo
    implementation(libs.google.exoplayer)


    //room
//    implementation ("androidx.room:room-ktx:2.5.1")
//    implementation ("androidx.room:room-runtime:2.5.1")
//    implementation ("com.google.code.gson:gson:2.8.7")
//    kapt ("androidx.room:room-compiler:2.5.1")


//    implementation("androidx.room:room-ktx:2.6.1")
//    implementation("androidx.room:room-runtime:2.6.1")
//    kapt("androidx.room:room-compiler:2.6.1")


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}