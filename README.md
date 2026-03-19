# Worth Care

> A unified Android health companion focused on everyday care: medicine adherence, health metrics, workout guidance, and voice-assisted interaction.

<p align="center">
  <img src="https://img.shields.io/badge/Codebase-51_Java_Files-0f172a?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java Files"/>
  <img src="https://img.shields.io/badge/UI-41_Layout_XML-1d4ed8?style=for-the-badge&logo=android&logoColor=white" alt="Layout XML"/>
  <img src="https://img.shields.io/badge/Visual_Assets-230_PNG_JPG-7c3aed?style=for-the-badge&logo=figma&logoColor=white" alt="Visual Assets"/>
  <img src="https://img.shields.io/badge/Reminder_Core-SQLite_%2B_AlarmManager-059669?style=for-the-badge&logo=sqlite&logoColor=white" alt="Reminder Core"/>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/minSdk-23-111827?style=flat-square" alt="minSdk 23"/>
  <img src="https://img.shields.io/badge/targetSdk-28-111827?style=flat-square" alt="targetSdk 28"/>
  <img src="https://img.shields.io/badge/Firebase-Auth_%7C_DB_%7C_Storage-111827?style=flat-square&logo=firebase&logoColor=orange" alt="Firebase stack"/>
  <img src="https://img.shields.io/badge/Voice-SpeechRecognizer_%2B_TTS-111827?style=flat-square" alt="Voice stack"/>
</p>

---

## <img src="https://img.icons8.com/3d-fluency/36/heart-with-pulse.png" width="24" alt="Overview"/> Product Overview

Worth Care is a multi-feature Android healthcare app where users can:

- create an account and maintain a personal health profile
- manage recurring medicine reminders with repeat logic and scheduled alarms
- calculate BMI using metric or imperial units
- browse and search medicine information
- follow guided workout screens with timer-led flow
- interact with a built-in voice companion

The app is organized around a dashboard-style navigation drawer that routes into each health module.

---

## <img src="https://img.icons8.com/3d-fluency/36/checklist.png" width="24" alt="Maturity"/> Feature Maturity Map

| Module | Current State | Notes |
|---|---|---|
| Authentication and Registration | Implemented | Firebase Authentication + user record creation in Realtime Database |
| Main Navigation Shell | Implemented | Drawer-based routing to major health modules |
| Medicine Reminder Engine | Strongly implemented | Create/edit/view, repeat rules, local persistence, alarms, snooze, dismiss, boot restore |
| BMI Calculator | Implemented | Real-time calculation with unit switching |
| Medicine Directory | Implemented | WebView-driven directory experience |
| Medicine Search | Implemented | Firebase prefix search with Recycler results |
| Workout Flow | Implemented | Multi-screen animation-first flow with countdown and workout counter |
| Voice Companion | Implemented | Speech recognition + Text-to-Speech command responses |
| Profile / Notifications / Settings Fragments | Basic level | Connected in nav, currently lightweight placeholders |

---

## <img src="https://img.icons8.com/3d-fluency/36/database.png" width="24" alt="Stack"/> Tech Stack

| Layer | Technologies Used |
|---|---|
| Platform | Android (Java), AndroidX |
| UI | XML, Material Components, ConstraintLayout, RecyclerView, CardView, ButterKnife |
| Navigation | Activity + Fragment hybrid, DrawerLayout, ViewPager tabs |
| Local Data | SQLite via `SQLiteOpenHelper` |
| Cloud Services | Firebase Authentication, Realtime Database, Storage |
| Background Scheduling | AlarmManager + BroadcastReceiver |
| Voice | SpeechRecognizer, RecognizerIntent, TextToSpeech |
| Media and Styling | Glide, custom animations, custom fonts |
| Build Tooling | Gradle, Android Gradle Plugin 3.5.2, Gradle Wrapper 5.4.1 |

---

## <img src="https://img.icons8.com/3d-fluency/36/flow-chart.png" width="24" alt="Architecture"/> Architecture Snapshot

### Entry and Identity

`splash` -> `RegActivity` / `LoginActivity` -> `NavDrawer`

### Reminder Domain Flow

- user creates or edits reminder
- reminder persisted in local SQLite
- exact alarm is scheduled
- receiver triggers notification
- next occurrence is calculated and re-scheduled by repeat strategy
- UI refresh broadcast updates active/history lists

### Modular Health Extensions

- BMI, workout, medicine directory/search, and voice companion are separate feature activities.
- The reminder subsystem is currently the most complete end-to-end domain in the app.

---

## <img src="https://img.icons8.com/3d-fluency/36/rocket.png" width="24" alt="Character"/> Product Character

Worth Care currently reads as a feature-rich healthcare super-app prototype with strong foundations in medicine adherence automation and practical multi-utility wellness tooling.

This README intentionally focuses on app scope, built capability, and technical composition.
