# MotionTraction
Motion is a movie recommendation applet built to demonstrate knowledge of the required skills for the Android Engineer Position at Traction Apps.
### CI Status
![Confirm APK build workflow](https://github.com/judahben149/MotionTraction/actions/workflows/build_apk_workflow.yml/badge.svg)

![Run Unit Tests workflow](https://github.com/judahben149/MotionTraction/actions/workflows/run_tests.yml/badge.svg)

# App Requirements
| Mandatory | Nice haves |
| --------- | ---------- |
| Kotlin  ✅ | Dependency Injection Framework  ✅ |
| Clean Code ✅ | Good UI ✅ |
| Offline Cache ✅ | Integrated CI ✅ |
| RX Java ✅ | |
| Testing ✅ | |
| Clean Architectural Pattern ✅ | |
| Users can filter by Favorites ✅ | |

# Try out App
You can click [here](https://appetize.io/embed/vhut5chpstywutl3ecdw57awm4?device=pixel7pro&osVersion=13.0&scale=50) to test Motion or download an APK from the releases section

# Make a Local build 🛠️
1. Clone the repository ```https://github.com/judahben149/MotionTraction.git```
2. Open in Android Studio
3. Sync the project
4. Add in TMDB API key into your local.properties file
5. Here's a test one you can use ```TMDB_API_KEY="0ca7e67bfaa50afa304af4a9bb6dbc93"```
6. The Project should build and run successfully
7. To run tests, open the terminal and run this ```./gradlew test```

# External Libraries used
* Lifecycle (for ViewModel and LiveData)
* Retrofit and OkHttp
* Gson
* Room
* RX Java & RX Android
* Paging 3
* Dagger-Hilt
* Glide
* Epoxy Recycler View
* Lottie Animations
* Timber
* Shimmer Layout
* Swipe Refresh Layout
* Mockito
* Truth Assertion library
* Splash Screen API

# App Screenshots

### Movie List
<img src="https://github.com/judahben149/MotionTraction/assets/71103838/adb64538-6117-4d92-8450-077f49a88b45" width="200" alt="Movie List screen - Light Mode">
<img src="https://github.com/judahben149/MotionTraction/assets/71103838/4c654f1b-fbe7-44bf-96f6-666a863e6e47" width="200" alt="Movie List screen - Dark Mode">

### Movie Detail
<img src="https://github.com/judahben149/MotionTraction/assets/71103838/e6fc9a47-36cd-44d0-b180-5996d7bdfd9d" width="200" alt="Movie Detail screen - Light Mode">
<img src="https://github.com/judahben149/MotionTraction/assets/71103838/2a8299b3-9c49-476b-b212-063b8196a6f2" width="200" alt="Movie List screen - Dark Mode">

### Filtered by favorite Movies
<img src="https://github.com/judahben149/MotionTraction/assets/71103838/528ef276-f639-45a8-87d0-7a406f9929b0" width="200" alt="Filtered by Favorite movies - Light Mode">
<img src="https://github.com/judahben149/MotionTraction/assets/71103838/7fc0a677-9392-4e8e-93f9-279c9e03809e" width="200" alt="Filtered by Favorite movies - Dark Mode">


# App Flow

### Light Mode
https://github.com/judahben149/MotionTraction/assets/71103838/37430df6-5a76-4f16-ade7-54dc89b23e7e

### Dark Mode
https://github.com/judahben149/MotionTraction/assets/71103838/19a2f078-3506-4a81-bf21-1e736486adb7


# About the app

* 100% Kotlin - Motion is built with Kotlin from top to bottom. This approach leverages on some of the benefits of Kotlin over Java
  
* Asynchronous Programming with RX Java - The app leverages the power of RX Java to handle asynchronous operations efficiently, freeing up the main thread to handle UI operations. This ensures a smooth and responsive user experience.

* Caching with Paging Remote Mediator - To optimize data retrieval especially when the device is offline, the app employs the Paging 3 library's RX Remote Mediator for caching.

* Effective Caching Strategy - The app employs a well-defined caching strategy to strike a balance between data freshness and storage efficiency. Cached data is intelligently managed and updated to provide users with the latest movie recommendations.

* Offline Support - Movies which have been viewed are saved and can be viewed again when the device is offline. Favorite movies are also saved.

* Separation of data - Data types at various layers are completely isolated only having mappers to convert between the various types. Kotlin extension functions were leveraged efficiently.

* Proper Error Handling - Comprehensive error handling has been implemented to gracefully handle various scenarios, such as network errors, data inconsistencies, and user interactions. This ensures that users receive clear and informative error messages when needed.

* Dependency Injection using Dagger-Hilt - The app follows the best practices of dependency injection by utilizing Dagger-Hilt. This powerful framework simplifies and centralizes the management of dependencies, promoting maintainable and testable code.

* Epoxy RecyclerView for Enhanced UI - The user interface is built using Epoxy, a highly flexible and efficient library for creating complex RecyclerView layouts. Epoxy streamlines the process of building dynamic UI components, resulting in a visually appealing and interactive app interface.

* Feedback for loading state - User experiance is enhanced by displaying shimmer in the bounds of the expected view area as opposed to progress bars. This is beneficial for user retention due to shorter perceived loading times.

* Tests - Units in the various layers are well tested. Mockito is used for mocking and Google's Truth assertion library is used for making assertions.










