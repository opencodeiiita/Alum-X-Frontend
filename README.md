# Thank you to all the contributors!

<a href="https://github.com/opencodeiiita/Alum-X-Frontend/graphs/contributors">
  <img src = "https://contrib.rocks/image?repo=opencodeiiita/Alum-X-Frontend"/>
</a>

---

# AlumX-Frontend 🎓📱

Welcome to **AlumX Frontend** — the modern, scalable, and intuitive client application for the **AlumX Alumni–Student Networking Platform**.

This repository contains the **Android frontend** built using **Kotlin** and **Jetpack Compose**, designed to deliver a seamless experience for students, alumni, and professors while interacting with the AlumX backend services.

---

## 📚 Table of Contents

- [About AlumX Frontend](#-about-alumx-frontend)
- [Core Features](#-core-features)
- [Tech Stack](#-tech-stack)
- [App Architecture](#-app-architecture)
- [State Management](#-state-management)
- [Networking](#-networking)
- [AI-Powered Features](#-ai-powered-features)
- [Project Structure](#-project-structure)
- [Setup Instructions](#️-setup-instructions)
- [Environment Configuration](#-environment-configuration)
- [Contribution Guidelines](#-contribution-guidelines)
- [Future Enhancements](#-future-enhancements)

---

## 🎯 About AlumX Frontend

AlumX is a **college-focused alumni engagement platform** that enables:

- Students to connect with alumni mentors
- Alumni to share industry experience
- AI-driven discovery of mentors and skills
- In-app resume creation using AI
- Secure, scalable, and clean UI built with modern Android practices

This frontend is designed to be:
- **Composable-first**
- **Backend-driven**
- **Resume & interview ready**
- **Scalable for real-world use**

---

## 🌟 Core Features

### 👤 Authentication & Onboarding
- Login via:
  - College Email
  - Google OAuth
  - LinkedIn OAuth
- Role-based flows:
  - Student
  - Alumni
  - Professor (Proctor)

### 🧑‍🎓 Student Features
- AI-powered alumni search using skills & interests
- Request alumni as mentors
- Blog interaction (like, comment, save)
- Resume builder with AI suggestions
- Skill & interest profiling

### 🧑‍💼 Alumni Features
- Write and publish experience blogs
- Accept or reject mentorship requests
- Maintain professional profile
- Skill tagging for AI matching

### 🧑‍🏫 Professor Features
- Verify alumni and students
- Monitor mentorship quality
- Moderate content

### 📰 Blog Feed
- LinkedIn-style feed
- Likes, comments, timestamps
- Markdown-based blog rendering

### 🤖 AI Integrations
- RAG-based alumni discovery
- Resume bullet enhancement
- Smart skill recommendations

---

## 🎨 Design
https://www.figma.com/board/niVdJzTd0FK75zIqYrNyBx/Untitled?node-id=0-1&t=yM8Q2OiyT645HkWV-1

---

## ⚙️ Tech Stack

### Android
- **Language:** Kotlin
- **UI:** Jetpack Compose (Material 3)
- **Architecture:** MVVM + Clean Architecture
- **Navigation:** Navigation Compose
- **Dependency Injection:** Hilt
- **Async:** Kotlin Coroutines + Flow

### Networking
- Retrofit
- OkHttp
- Gson / Kotlinx Serialization

### Storage
- DataStore (Preferences)
- Room (offline cache)

### AI Integration
- Secure API calls to backend AI services
- Token-based AI requests
- Resume parsing & enhancement APIs

---

## 🧱 App Architecture

```
Presentation Layer (Compose UI)
        ↓
ViewModels (State + Events)
        ↓
Domain Layer (UseCases)
        ↓
Data Layer (Repositories)
        ↓
Remote (Backend APIs)
```

✔ Unidirectional Data Flow  
✔ Testable components  
✔ Scalable architecture  

---

## 🔄 State Management

- **State:** StateFlow
- **Events:** SharedFlow
- **UI:** Stateless Composables
- **Side Effects:** LaunchedEffect

---

## 🌐 Networking

- All API calls are:
  - JWT authenticated
  - Interceptor-based
  - Error-handled centrally

Example:
```kotlin
@GET("/api/users/profile")
suspend fun getProfile(): ProfileResponse
```

---

## 🤖 AI-Powered Features

| Feature | Description |
|------|------------|
| Alumni Search | RAG-based semantic matching |
| Resume Builder | AI-enhanced bullet points |
| Skill Suggestions | AI-powered recommendations |
| Mentor Matching | Similarity scoring |

---

## 📁 Project Structure

```
com.alumx.app
├── data
│   ├── remote
│   ├── local
│   ├── repository
│
├── domain
│   ├── model
│   ├── usecase
│
├── presentation
│   ├── auth
│   ├── home
│   ├── profile
│   ├── blog
│   ├── mentor
│   ├── resume
│
├── di
├── navigation
├── ui
│   ├── theme
│   ├── components
│
└── utils
```

---

## 🛠️ Setup Instructions

### Prerequisites
- Android Studio Hedgehog or later
- JDK 17
- Git

### Steps

```bash
git clone https://github.com/<org>/alumx-frontend.git
cd alumx-frontend
```

Open the project in **Android Studio** and sync Gradle.

---

## 🤝 Contribution Guidelines

- Follow MVVM strictly
- Write reusable composables
- Avoid business logic in UI
- Keep ViewModels lean
- Descriptive commit messages

---

## 🚀 Future Enhancements

- Web frontend (Compose Multiplatform)
- Push notifications (FCM)
- In-app chat between mentors & students
- Analytics dashboard
- Dark mode personalization
