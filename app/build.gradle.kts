plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "id.antasari.p04.wargahub"
    compileSdk = 36

    defaultConfig {
        applicationId = "id.antasari.p04.wargahub"
        minSdk = 26
        targetSdk = 34
        versionCode = 2
        versionName = "1.1-Pengayaan"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures { 
        viewBinding = true 
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    
    // Praktikum P04 dependencies
    implementation("androidx.recyclerview:recyclerview:1.4.0")
    implementation("androidx.fragment:fragment-ktx:1.8.9")
    implementation("androidx.navigation:navigation-fragment-ktx:2.9.5")
    implementation("androidx.navigation:navigation-ui-ktx:2.9.5")
}
