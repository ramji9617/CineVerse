Cineverse Movies is a modern Android app that lets you explore upcoming movies, view detailed information, and save your favorites in a personal watchlist.

The project follows Clean Architecture + MVVM, making it highly scalable, maintainable, and testable.

✨ Features

📅 Upcoming Movies – Browse the latest movie releases.

📖 Movie Details – View release date, description, rating, and more.

⭐ Favorites – Add or remove movies from your watchlist.

🔄 Offline Support – Favorites stored locally using Room Database.

🛠 Tech Stack

Kotlin – Primary language

Hilt (Dependency Injection) – Easy DI setup for scalable code

Retrofit – REST API client for network requests

Room (Local Database) – Store and manage favorites offline

Coroutines + Flow – Asynchronous, reactive programming

ViewModel + LiveData/StateFlow – Lifecycle-aware UI handling

Clean Architecture (MVVM) – Separation of concerns across layers

🏗 Architecture Overview
app/
 ├── data/          # Repositories, API, DB, DTOs, Mappers
 ├── domain/        # UseCases, Entities, Repository Interfaces
 ├── presentation/  # ViewModels, UI (Compose/Views), State Management
 └── di/            # Hilt Modules


✅ Data Layer → Fetches data from network (Retrofit) or local DB (Room)
✅ Domain Layer → Contains business logic (UseCases, Entities)
✅ Presentation Layer → UI + ViewModels, observes data/state

🚀 Getting Started
Prerequisites

Android Studio Giraffe+

Gradle 8.x

JDK 11+

Setup

Clone the repo:

git clone https://github.com/yourusername/upnext-movies.git
cd upnext-movies


Open in Android Studio.

Insert your Movie API key (e.g., TMDb) in local.properties:

API_KEY=your_api_key_here


Run the app on your emulator/device. 🎉

📸 Screenshots

(Add here once you have UI screenshots)

📚 Learnings

Implemented Clean Architecture with layered separation.

Used Hilt for Dependency Injection.

Managed local & remote data sources seamlessly with Room + Retrofit.

Reactive data flow using Kotlin Coroutines & Flow.
