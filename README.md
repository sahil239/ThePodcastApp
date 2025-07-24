# ThePodcastApp

TMDBApp is an Android application that allows users to browse podcasts and view their details using Listen Notes Api. The app provides podcast list and details.
The project is following an MVVM (Model-View-ViewModel) architecture with some principles of Clean Architecture.

## Description

This is an Android app that fetches movie data from Listen Notes's Api. It features:
- A list of podcasts fetched from the API
- Detailed information about each podcast when clicked
- Pagination for loading podcasts in batches

## Technologies Used

- **Kotlin**: Primary programming language for Android development.
- **Jetpack Compose**: For building modern UIs.
- **Retrofit**: For making network calls and parsing JSON responses.
- **Paging 3**: For loading large datasets incrementally.
- **Hilt**: For dependency injection.
- **Coil**: For image loading.
- **Turbine**: For testing coroutines flows.

## Setup & Installation

1. Open the project in **Android Studio**.

2. If you are using a physical device or emulator, make sure to set up the **Android SDK** and **JDK** correctly in Android Studio.

## How to Run the App

1. **Build the project**: Go to `Build > Make Project` in Android Studio to compile the code.

2. **Run the project**: Select an emulator or a connected device, then click `Run` or use `Shift + F10` to launch the app.

3. The app should now launch on your emulator or physical device.
