/*
  Exercise 11 - Event Examples
  Goal: handle clicks and form submits - calling two functions from one click,
  passing an argument to a handler, reading the synthetic event, and converting
  rupees to euros on submit.
*/

import { useState } from 'react';

const RUPEES_PER_EURO = 90;

export function CurrencyConverter() {
  const [rupeeText, setRupeeText] = useState('');
  const [euroAmount, setEuroAmount] = useState(null);
  const [problem, setProblem] = useState('');

  const convert = (event) => {
    event.preventDefault();
    const rupees = Number(rupeeText);

    if (rupeeText.trim() === '' || Number.isNaN(rupees) || rupees < 0) {
      setProblem('Enter an amount of 0 or more.');
      setEuroAmount(null);
      return;
    }

    setProblem('');
    setEuroAmount((rupees / RUPEES_PER_EURO).toFixed(2));
  };

  return (
    <div className="card" style={{ maxWidth: '380px' }}>
      <h3 className="card__title">Rupees to euros</h3>

      <form onSubmit={convert}>
        <label className="field">
          <span className="field__label">Amount in INR</span>
          <input
            className="field__input"
            type="number"
            min="0"
            step="1"
            value={rupeeText}
            onChange={(event) => setRupeeText(event.target.value)}
            placeholder="e.g. 4500"
          />
        </label>
        <button className="btn" type="submit">
          Convert
        </button>
      </form>

      {problem && (
        <p className="notice notice--error" style={{ marginTop: '14px' }}>
          {problem}
        </p>
      )}

      {euroAmount !== null && !problem && (
        <p className="readout" style={{ marginTop: '14px', fontSize: '1.4rem' }}>
          &euro; {euroAmount}
        </p>
      )}

      <p className="card__meta">Rate used: 1 euro = {RUPEES_PER_EURO} rupees.</p>
    </div>
  );
}

function EventExamples() {
  const [tally, setTally] = useState(0);
  const [log, setLog] = useState('Nothing clicked yet.');

  const stepUp = () => setTally((current) => current + 1);
  const stepDown = () => setTally((current) => Math.max(0, current - 1));

  const noteTheClick = () => setLog('Two functions ran from a single click.');

  /* One click, two functions. */
  const handleIncrement = () => {
    stepUp();
    noteTheClick();
  };

  /* Handler that receives an argument. */
  const greet = (word) => setLog(`Greeting received: ${word}`);

  /* Handler that reads the React synthetic event. */
  const inspectEvent = (event) => {
    setLog(
      `Synthetic event -> type: ${event.type}, element: <${event.target.tagName.toLowerCase()}>`
    );
    console.log('Synthetic event object:', event);
  };

  return (
    <div className="panel">
      <span className="tag">Event handling</span>
      <h2 className="panel__title">Event Examples</h2>
      <p className="panel__note">
        Every button writes to the message line instead of opening an alert, so
        the result stays visible in a screenshot.
      </p>

      <p className="readout">{tally}</p>

      <div className="btn-row">
        <button className="btn" onClick={handleIncrement}>
          Increment (runs two functions)
        </button>
        <button className="btn btn--ghost" onClick={stepDown}>
          Decrement
        </button>
        <button className="btn btn--ghost" onClick={() => greet('welcome')}>
          Say welcome
        </button>
        <button className="btn btn--ghost" onClick={inspectEvent}>
          Inspect synthetic event
        </button>
      </div>

      <div className="notice">
        <strong>Message:</strong> {log}
      </div>

      <CurrencyConverter />
    </div>
  );
}

export default EventExamples;
