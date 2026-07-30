/*
  Exercise 9 - Cricket App
  Goal: practise map, filter with arrow functions, array destructuring, the
  spread operator, and swapping views with a boolean flag.
*/

import { useState } from 'react';

const HALF_CENTURY_PLUS = 70;

const squad = [
  { player: 'Virat Kohli', runs: 110 },
  { player: 'Rohit Sharma', runs: 85 },
  { player: 'KL Rahul', runs: 45 },
  { player: 'Rishabh Pant', runs: 68 },
  { player: 'Hardik Pandya', runs: 72 },
  { player: 'Ravindra Jadeja', runs: 55 },
  { player: 'Jasprit Bumrah', runs: 12 },
  { player: 'Mohammed Siraj', runs: 8 },
  { player: 'Kuldeep Yadav', runs: 4 },
  { player: 'Shubman Gill', runs: 95 },
  { player: 'Shreyas Iyer', runs: 62 },
];

const t20Squad = ['Virat Kohli', 'Rohit Sharma', 'Hardik Pandya', 'Jasprit Bumrah'];
const ranjiSquad = ['Sarfaraz Khan', 'Rinku Singh', 'Yash Dayal', 'Abhimanyu Easwaran'];

export function ListofPlayers() {
  const bigScorers = squad.filter((entry) => entry.runs >= HALF_CENTURY_PLUS);

  return (
    <div>
      <h3 className="panel__title">Every player and score</h3>
      <table className="ledger" style={{ maxWidth: '420px', marginBottom: '24px' }}>
        <thead>
          <tr>
            <th>Player</th>
            <th>Runs</th>
          </tr>
        </thead>
        <tbody>
          {squad.map((entry) => (
            <tr key={entry.player}>
              <td>{entry.player}</td>
              <td>{entry.runs}</td>
            </tr>
          ))}
        </tbody>
      </table>

      <h3 className="panel__title" style={{ color: 'var(--thrive)' }}>
        Scored {HALF_CENTURY_PLUS} or more
      </h3>
      <ul>
        {bigScorers.map((entry) => (
          <li key={entry.player}>
            {entry.player} &mdash; {entry.runs} runs
          </li>
        ))}
      </ul>
    </div>
  );
}

export function IndianPlayers() {
  /* Destructure the eleven names, then split them by position in the list. */
  const [
    one,
    two,
    three,
    four,
    five,
    six,
    seven,
    eight,
    nine,
    ten,
    eleven,
  ] = squad.map((entry) => entry.player);

  const oddPositions = [one, three, five, seven, nine, eleven];
  const evenPositions = [two, four, six, eight, ten];

  /* Spread operator joins the two formats into one list. */
  const combinedSquad = [...t20Squad, ...ranjiSquad];

  return (
    <div>
      <h3 className="panel__title">Split by batting position</h3>
      <div className="grid" style={{ marginBottom: '24px' }}>
        <div className="card">
          <h4 className="card__title">Odd positions</h4>
          <ul>
            {oddPositions.map((name) => (
              <li key={name}>{name}</li>
            ))}
          </ul>
        </div>
        <div className="card">
          <h4 className="card__title">Even positions</h4>
          <ul>
            {evenPositions.map((name) => (
              <li key={name}>{name}</li>
            ))}
          </ul>
        </div>
      </div>

      <h3 className="panel__title">T20 and Ranji squads merged</h3>
      <ul>
        {combinedSquad.map((name) => (
          <li key={name}>{name}</li>
        ))}
      </ul>
    </div>
  );
}

function CricketApp() {
  const [showScores, setShowScores] = useState(true);

  return (
    <div className="panel">
      <span className="tag">ES6 features</span>
      <h2 className="panel__title">Cricket App</h2>
      <p className="panel__note">
        One boolean decides which of the two child components renders.
      </p>

      <div className="btn-row">
        <button
          className={showScores ? 'btn' : 'btn btn--ghost'}
          onClick={() => setShowScores(true)}
        >
          Scores view
        </button>
        <button
          className={showScores ? 'btn btn--ghost' : 'btn'}
          onClick={() => setShowScores(false)}
        >
          Squads view
        </button>
      </div>

      {showScores ? <ListofPlayers /> : <IndianPlayers />}
    </div>
  );
}

export default CricketApp;
