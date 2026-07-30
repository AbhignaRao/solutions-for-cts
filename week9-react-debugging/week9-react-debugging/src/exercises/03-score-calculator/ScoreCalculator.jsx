/*
  Exercise 3 - Score Calculator
  Goal: a functional component that receives props, computes an average
  percentage, and takes its look from an external stylesheet.
*/

import './scoreboard.css';

function toPercentLabel(fraction) {
  return `${(fraction * 100).toFixed(2)}%`;
}

function averagePercentage(total, goal) {
  if (!goal) {
    return '0.00%';
  }
  return toPercentLabel(total / goal);
}

export function CalculateScore({ Name, School, total, goal }) {
  return (
    <div className="report">
      <p className="report__caption">Student result summary</p>

      <div className="report__row">
        <span className="report__key">Name</span>
        <span className="report__value">{Name}</span>
      </div>

      <div className="report__row">
        <span className="report__key">School</span>
        <span className="report__value">{School}</span>
      </div>

      <div className="report__row">
        <span className="report__key">Marks scored</span>
        <span className="report__value">{total}</span>
      </div>

      <div className="report__row">
        <span className="report__key">Marks available</span>
        <span className="report__value">{goal}</span>
      </div>

      <div className="report__row report__row--result">
        <span className="report__key">Average percentage</span>
        <span className="report__result">{averagePercentage(total, goal)}</span>
      </div>
    </div>
  );
}

function ScoreCalculatorDemo() {
  return (
    <div className="panel">
      <span className="tag">Props + external CSS</span>
      <h2 className="panel__title">Score Calculator</h2>
      <p className="panel__note">
        Four props go in, one calculated percentage comes out. Change the values
        below in the source file and the panel updates on save.
      </p>
      <CalculateScore
        Name="Steeve"
        School="DNV Public School"
        total={284}
        goal={300}
      />
    </div>
  );
}

export default ScoreCalculatorDemo;
