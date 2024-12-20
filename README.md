# Movie App

## Overview

A functional movie browsing application built using Kotlin and the MVVM architecture. The app integrates with the TMDB API to fetch and display movie data. The architecture is modular and clean, ensuring scalability and maintainability.

---

## Features

### 1. Home Screen

- Displays trending movies with posters (max 5) in a horizontal scroll.
- Categorizes movies into sections:
  - Now Playing
  - Popular
  - Top-Rated
  - Upcoming
- Movies are displayed in a grid format, and clicking on a movie navigates to the details screen.

### 2. Movie Details Screen

- Displays detailed information about the movie, including:
  - Banner
  - Poster
  - Title
  - Description
  - Release Year
  - Duration
  - Genre
- Tabs for additional details:
  - **About Movie**: Full description.
  - **Cast**: Displays cast members with profile pictures and names.

### 3. Video Player Integration

- Trailer player in landscape mode with:
  - Used Android YouTube Player for embedding YouTube videos, providing a lightweight and customizable player for trailers.
  - Full-screen toggle support

---

## Technologies Used

### Languages and Frameworks
- Kotlin
- Android Jetpack Components (ViewModel, LiveData)

### Libraries

- Retrofit: For API integration
- Glide: For image loading
- Gson: For JSON parsing
- Android YouTube Player (`com.pierfrancescosoffritti.androidyoutubeplayer:core`): For play youtube trailer
- ViewPager2 and TabLayout: For tabs
- Facebook shimmer: For Shimmer effect while data loading
- CircularImageView: For circular imageview
- RoundImageView: For Rounded Imageview

### Architecture

- **MVVM (Model-View-ViewModel):**
  - Ensures separation of concerns and testability.

---

## Setup Instructions

1. Clone the repository:
   ```bash
   git clone https://github.com/sahajvithani/MovieApp.git
   ```
2. Open the project in Android Studio.

3. Sync the project to download dependencies.
4. Build and run the project on an Android device or emulator.

---

## Contact

If you have any questions or suggestions, feel free to reach out:

- **Name**: Sahaj Vithani
- **Email**: vithani.sahaj@gmail.com
- **LinkedIn**: https://www.linkedin.com/in/sahajvithani
- **GitHub**: https://github.com/sahajvithani

