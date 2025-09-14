import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}
android {
    namespace = "com.project.movieapp"
    compileSdk = 36

    val localProperties = Properties()
    val localPropertiesFile = File(rootDir , "secret.properties")
    if (localPropertiesFile.exists() && localPropertiesFile.isFile) {
        localPropertiesFile.inputStream().use{
            localProperties.load(it)
        }
    }

    defaultConfig {
        applicationId = "com.project.movieapp"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        val apiKey = localProperties.getProperty("API_KEY") ?: ""
        buildConfigField("String", "API_KEY", "\"$apiKey\"")

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
    buildFeatures {
        compose = true
        buildConfig = true
        resValues = true
    }
}

dependencies {

    val room_version = "2.7.2"
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    implementation("com.google.dagger:hilt-android:2.57.1")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    ksp("com.google.dagger:hilt-android-compiler:2.57.1")

    //room dependency
    implementation("androidx.room:room-runtime:$room_version")
    ksp("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-ktx:$room_version")

    implementation("io.coil-kt:coil-compose:2.7.0")

    //lottie dependency
    implementation("com.airbnb.android:lottie-compose:6.6.7")

    implementation("androidx.paging:paging-compose:3.4.0-alpha03")

    implementation("androidx.paging:paging-runtime:3.3.6")

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation("androidx.navigation3:navigation3-runtime:1.0.0-alpha08")
    implementation("androidx.navigation3:navigation3-ui:1.0.0-alpha08")


        implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.9.0")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:3.0.0")


// Gson converter for Retrofit
    implementation("com.squareup.retrofit2:converter-gson:3.0.0")

// Gson itself (optional, usually included transitively)
    implementation("com.google.code.gson:gson:2.13.1")
    }
