/*
  Week 9 - React SPA & Application Debugging
  Dashboard shell. Each handbook exercise lives in its own folder under
  src/exercises and is mounted here one at a time.
*/

import { useState } from 'react';
import './styles/shell.css';

import FirstReact from './exercises/01-first-react/FirstReact';
import StudentApp from './exercises/02-student-app/StudentApp';
import ScoreCalculator from './exercises/03-score-calculator/ScoreCalculator';
import BlogApp from './exercises/04-blog-app/BlogApp';
import CohortDetails from './exercises/05-cohort-details/CohortDetails';
import CricketApp from './exercises/09-cricket-app/CricketApp';
import OfficeRental from './exercises/10-office-rental/OfficeRental';
import EventExamples from './exercises/11-event-examples/EventExamples';
import TicketBooking from './exercises/12-ticket-booking/TicketBooking';
import BloggerApp from './exercises/13-blogger-app/BloggerApp';

/* Edit these three lines once and they appear in the header of every screenshot. */
const OWNER = {
  name: 'Abhigna Rao Lingala',
  email: 'lingalaabhignarao@gmail.com',
repo: 'github.com/AbhignaRao/solutions-for-cts',
};

const EXERCISES = [
  {
    id: '01',
    label: 'My First React',
    brief: 'Render a main heading from a component built on a Vite React setup.',
    Screen: FirstReact,
  },
  {
    id: '02',
    label: 'Student App',
    brief: 'Three class components rendered together by a parent class component.',
    Screen: StudentApp,
  },
  {
    id: '03',
    label: 'Score Calculator',
    brief: 'A functional component takes props and calculates an average percentage.',
    Screen: ScoreCalculator,
  },
  {
    id: '04',
    label: 'Blog App',
    brief: 'componentDidMount loads posts over fetch; componentDidCatch handles a render failure.',
    Screen: BlogApp,
  },
  {
    id: '05',
    label: 'Cohort Details',
    brief: 'CSS Modules style the cards and colour each title by cohort status.',
    Screen: CohortDetails,
  },
  {
    id: '09',
    label: 'Cricket App',
    brief: 'map, filter, destructuring and the spread operator, switched by a boolean flag.',
    Screen: CricketApp,
  },
  {
    id: '10',
    label: 'Office Space Rental',
    brief: 'JSX in variables, an image element, and a rent colour set by an inline style.',
    Screen: OfficeRental,
  },
  {
    id: '11',
    label: 'Event Examples',
    brief: 'Two functions from one click, handler arguments, synthetic events, and a form submit.',
    Screen: EventExamples,
  },
  {
    id: '12',
    label: 'Ticket Booking',
    brief: 'Guests see the flight list; signed-in users see the booking form.',
    Screen: TicketBooking,
  },
  {
    id: '13',
    label: 'Blogger App',
    brief: 'The same three sections rendered through four conditional rendering syntaxes.',
    Screen: BloggerApp,
  },
];

function App() {
  const [openId, setOpenId] = useState('01');

  const current = EXERCISES.find((item) => item.id === openId) ?? EXERCISES[0];
  const CurrentScreen = current.Screen;

  return (
    <div className="shell">
      <header className="masthead">
        <div>
          <p className="masthead__eyebrow">
            Digital Nurture 5.0 &middot; Java FSE React
          </p>
          <h1 className="masthead__title">
            Week 9 &mdash; React SPA &amp; Application Debugging
          </h1>
        </div>
        <div className="masthead__owner">
          <span>{OWNER.name}</span>
          <span>{OWNER.email}</span>
          <span>{OWNER.repo}</span>
        </div>
      </header>

      <div className="workspace">
        <nav className="rail" aria-label="Exercise list">
          <p className="rail__caption">Hands-on exercises</p>
          <ul className="rail__list">
            {EXERCISES.map((item) => {
              const active = item.id === openId;
              return (
                <li key={item.id}>
                  <button
                    type="button"
                    className={
                      active ? 'rail__button rail__button--active' : 'rail__button'
                    }
                    aria-current={active ? 'true' : undefined}
                    onClick={() => setOpenId(item.id)}
                  >
                    <span className="rail__id">{item.id}</span>
                    <span className="rail__label">{item.label}</span>
                  </button>
                </li>
              );
            })}
          </ul>
        </nav>

        <main className="stage">
          <div className="stage__head">
            <p className="stage__number">Exercise {current.id}</p>
            <h2 className="stage__title">{current.label}</h2>
            <p className="stage__brief">{current.brief}</p>
          </div>

          <CurrentScreen />
        </main>
      </div>

      <footer className="footnote">
        Ten hands-on exercises from Module 9 and Module 10 of the Deep Skilling
        handbook. Numbers 6 to 8 are not React exercises in this module.
      </footer>
    </div>
  );
}

export default App;
