# Android Drawing App 🎨

This is a simple Android app that lets users draw shapes on the screen by dragging their finger.  
The app supports **Line, Rectangle, and Circle** drawing with a real-time preview before committing the shape.

---

## ✨ Features
- Draw shapes by touch:
  - **Line** (drag from start point to end point)
  - **Rectangle** (drag to define opposite corners)
  - **Circle** (drag to define radius)
- **Live preview** while dragging
- **Commit shapes** permanently to the canvas on release
- Change **color** and **stroke width**

---

## 🛠️ Tech Stack
- **Language**: Java
- **Framework**: Android SDK
- **UI**: ImageView + Canvas + Bitmap

---
## 📸 Screenshots

### Drawing a Circle
<img src="screenshots/circle.png" width="250"/>

### Drawing a Rectangle
<img src="screenshots/rectangle.png" width="250"/>

### Live Preview
<img src="screenshots/preview.gif" width="250"/>


## 🚀 How It Works
1. **Touch Down** – starting point is saved (`startX`, `startY`).  
2. **Touch Move** – preview shape is drawn on a temporary canvas (`previewCanvas`).  
3. **Touch Up** – final shape is committed to the main canvas (`canvas`).  
4. Preview is refreshed after each commit so it always matches the main drawing.

---


