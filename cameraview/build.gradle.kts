import io.deepmedia.tools.publisher.common.License
import io.deepmedia.tools.publisher.common.Release
import io.deepmedia.tools.publisher.common.GithubScm

plugins {
    id("com.android.library")
    //id("kotlin-android") //https://issuetracker.google.com/issues/234865137
    id("kotlin-android")
    id("io.deepmedia.tools.publisher")
    id("jacoco")
}

android {
    compileSdk = property("compileSdkVersion") as Int
    defaultConfig {
        minSdk = property("minSdkVersion") as Int
        targetSdk = property("targetSdkVersion") as Int
    }
    namespace = "com.otaliastudios.cameraview"
    buildTypes["debug"].isTestCoverageEnabled = true
    buildTypes["release"].isMinifyEnabled = false
    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
}

dependencies {
    api("androidx.exifinterface:exifinterface:1.3.7")
    api("androidx.lifecycle:lifecycle-common:2.6.2")
    api("com.google.android.gms:play-services-tasks:18.1.0")
    implementation("androidx.annotation:annotation:1.7.1")
    implementation("com.otaliastudios.opengl:egloo:0.6.1")
}

// Publishing

publisher {
    project.description = "A well documented, high-level Android interface that makes capturing " +
            "pictures and videos easy, addressing all of the common issues and needs. " +
            "Real-time filters, gestures, watermarks, frame processing, RAW, output of any size."
    project.artifact = "cameraview"
    project.group = "com.otaliastudios"
    project.url = "https://github.com/natario1/CameraView"
    project.scm = GithubScm("natario1", "CameraView")
    project.addLicense(License.APACHE_2_0)
    project.addDeveloper("natario1", "mat.iavarone@gmail.com")
    release.sources = Release.SOURCES_AUTO
    //release.docs = Release.DOCS_AUTO
    release.version = "2.7.2"

    directory()

    sonatype {
        auth.user = "SONATYPE_USER"
        auth.password = "SONATYPE_PASSWORD"
        signing.key = "SIGNING_KEY"
        signing.password = "SIGNING_PASSWORD"
    }

    sonatype("snapshot") {
        repository = io.deepmedia.tools.publisher.sonatype.Sonatype.OSSRH_SNAPSHOT_1
        release.version = "latest-SNAPSHOT"
        auth.user = "SONATYPE_USER"
        auth.password = "SONATYPE_PASSWORD"
        signing.key = "SIGNING_KEY"
        signing.password = "SIGNING_PASSWORD"
    }
}