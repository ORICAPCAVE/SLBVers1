plugins {
    alias(libs.plugins.androidApplication)
    id("com.google.gms.google-services")
    alias(libs.plugins.googleFirebaseCrashlytics)

}

android {
    namespace = "com.slbvers1new.slbvers1"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.youcntbel.slbvers1"
        minSdk = 24
        targetSdk = 36
        versionCode = 30
        versionName = "4.2"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {

            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true

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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    // Import the BoM for the Firebase platform
    implementation(platform("com.google.firebase:firebase-bom:33.1.0"))

    // Add the dependency for the Firebase Authentication library
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation("com.google.firebase:firebase-auth")

    // Also add the dependency for the Google Play services library and specify its version
    //implementation("com.google.android.gms:play-services-auth:21.1.0")
    // viewModel and LiveData


    // Android Navigation Architecture
    implementation("com.google.firebase:firebase-database")
    implementation("com.google.firebase:firebase-analytics")

    //implementation("com.google.firebase:firebase-auth:23.0.0")
    //implementation("com.google.firebase:firebase-auth-ktx:23.0.0")
    //implementation ("androidx.fragment:fragment:fragment_version")
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.storage)
    implementation(libs.firebase.crashlytics)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)



}