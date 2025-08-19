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

### Drawing a Rectangle
<img src="screenshots/circle<img width="510" height="881" alt="Rectang" src="https://github.com/user-attachments/assets/88d8567a-8a11-4ce1-a1ad-46d34366722d" />
.png" width="250"/>

### Drawing a Circle
<img src="screenshots/rectangle.png" width="<img width="395" height="828" alt="Circle" src="https://github.com/user-attachments/assets/71fc06e3-a709-4b4a-886a-fa872f0671af" />
250"/>

### Drawing a Line
<img width="448" height="887" alt="Line" src="https://github.com/user-attachments/assets/b269f262-2cc1-4205-8d57-1075c80924dd" />

### Live Preview
<img src="screenshots/preview.gif

https://github.com/user-attachments/assets/7ef1a572-d90b-44c4-a417-83c076b0de9a

" width="250"/>


## 🚀 How It Works
1. **Touch Down** – starting point is saved (`startX`, `startY`).  
2. **Touch Move** – preview shape is drawn on a temporary canvas (`previewCanvas`).  
3. **Touch Up** – final shape is committed to the main canvas (`canvas`).  
4. Preview is refreshed after each commit so it always matches the main drawing.

---


