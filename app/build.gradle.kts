plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)

//    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.shoppingapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.shoppingapp"
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures{
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
    implementation(libs.firebase.database)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.1")

    implementation ("androidx.activity:activity-ktx:1.9.0")

    implementation ("com.github.bumptech.glide:glide:4.16.0")

    implementation ("com.google.code.gson:gson:2.11.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.12.0")

    implementation("com.tbuonomo:dotsindicator:5.0")



        // Import the BoM for the Firebase platform
        implementation(platform("com.google.firebase:firebase-bom:33.1.0"))

    implementation ("androidx.viewpager2:viewpager2:1.0.0")

        // Add the dependency for the Firebase Authentication library
        // When using the BoM, you don't specify versions in Firebase library dependencies
        implementation("com.google.firebase:firebase-auth")

        // Also add the dependency for the Google Play services library and specify its version
        implementation("com.google.android.gms:play-services-auth:21.2.0")

    implementation("com.google.firebase:firebase-analytics")


        implementation ("com.airbnb.android:lottie:6.4.1")

    implementation(libs.firebase.database)


}