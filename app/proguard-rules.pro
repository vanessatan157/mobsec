# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# Keep the entry point to your app (e.g., MainActivity)
-keep class com.example.myapplication.MainActivity

# Keep Firebase classes and methods
-keep class com.google.firebase.** { *; }
-keep class com.firebase.** { *; }

# Keep Glide classes and methods if you're using Glide for image loading
-keep class com.bumptech.glide.** { *; }

# Keep your model classes if you have any
-keep class com.example.myapplication.model.** { *; }

# Keep any other classes or methods that you want to preserve
# For example, if you have custom views or utilities
-keep class com.example.myapplication.views.** { *; }

# Specify any additional libraries or packages that need to be preserved
-keep class androidx.** { *; }

# Add any other rules specific to your project
