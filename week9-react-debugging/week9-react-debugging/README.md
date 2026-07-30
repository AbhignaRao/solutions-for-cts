# Week 9 — React SPA & Application Debugging

Hands-on exercises for **Module 9 (React SPA)** and **Module 10 (Application Debugging)** of the
Digital Nurture 5.0 Deep Skilling handbook, Java Full Stack Engineer (React) track.

All ten exercises live in one Vite application. The left rail mounts one exercise
at a time so each can be demonstrated and screenshotted on its own.

## Running it

```bash
npm install
npm run dev
```

The dev server starts on <http://localhost:5173>.

```bash
npm run build     # production build into dist/
npm run preview   # serve the built output
```

Requires Node.js 20.19 or newer.

## Exercises

| # | Exercise | Concept demonstrated |
|---|----------|----------------------|
| 01 | My First React | Vite + React setup, rendering a heading |
| 02 | Student App | Class components composed by a parent |
| 03 | Score Calculator | Props, derived values, external stylesheet |
| 04 | Blog App | `componentDidMount`, `componentDidCatch`, `fetch` |
| 05 | Cohort Details | CSS Modules, status-driven colours |
| 09 | Cricket App | `map`, `filter`, destructuring, spread, flag rendering |
| 10 | Office Space Rental | JSX in variables, images, conditional inline styles |
| 11 | Event Examples | Multiple handlers, handler arguments, synthetic events, forms |
| 12 | Ticket Booking | Conditional rendering on authentication state |
| 13 | Blogger App | `switch`, element variable, logical AND, ternary |

Exercises 6 to 8 of the handbook are not React exercises in this module.

## Layout

```
src/
├── main.jsx                     React entry point
├── App.jsx                      Dashboard shell + exercise registry
├── index.css                    Design tokens and base element styles
├── styles/
│   └── shell.css                Dashboard layout + shared UI classes
└── exercises/
    ├── 01-first-react/
    ├── 02-student-app/
    ├── 03-score-calculator/     component + plain external CSS
    ├── 04-blog-app/
    ├── 05-cohort-details/       component + CSS Module
    ├── 09-cricket-app/
    ├── 10-office-rental/        component + SVG asset
    ├── 11-event-examples/
    ├── 12-ticket-booking/
    └── 13-blogger-app/
```

Each exercise folder holds everything that exercise needs, so a folder can be
read, reviewed or removed on its own.

## Debugging notes (Module 10)

Exercise 04 has two buttons that fail on purpose:

- **Break the request** points `fetch` at a missing endpoint. The 404 is thrown,
  caught by `.catch`, and shown as a request error. Watch it in the browser
  Network tab.
- **Break the render** makes a child component throw during render. The parent's
  `componentDidCatch` catches it and shows a recovery message. React also logs
  the component stack to the console.

An error boundary only catches errors thrown by its **children**, never by
itself — that is why the throwing code sits in `PostCard` rather than in `Posts`.

## Author

Abhigna Rao Lingala · lingalaabhignarao@gmail.com
SRM University AP.