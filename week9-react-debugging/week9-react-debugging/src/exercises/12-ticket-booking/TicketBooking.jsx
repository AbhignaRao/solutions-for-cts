/*
  Exercise 12 - Ticket Booking App
  Goal: render two different screens from one component depending on whether
  the visitor is signed in. Guests see the flight list, members see the form.
*/

import { useState } from 'react';

const flights = [
  { code: 'AI-101', airline: 'Air India', from: 'Chennai', to: 'Delhi', departs: '06:00', fare: 5500 },
  { code: '6E-204', airline: 'IndiGo', from: 'Chennai', to: 'Mumbai', departs: '09:30', fare: 4200 },
  { code: 'UK-812', airline: 'Vistara', from: 'Chennai', to: 'Bengaluru', departs: '14:15', fare: 3100 },
  { code: 'QP-451', airline: 'Akasa Air', from: 'Chennai', to: 'Hyderabad', departs: '19:45', fare: 2900 },
];

function GuestScreen() {
  return (
    <div>
      <h3 className="panel__title">Today&rsquo;s departures</h3>
      <p className="panel__note">Sign in to reserve a seat on any of these.</p>
      <table className="ledger">
        <thead>
          <tr>
            <th>Flight</th>
            <th>Route</th>
            <th>Departs</th>
            <th>Fare</th>
          </tr>
        </thead>
        <tbody>
          {flights.map((flight) => (
            <tr key={flight.code}>
              <td>
                {flight.airline} ({flight.code})
              </td>
              <td>
                {flight.from} &rarr; {flight.to}
              </td>
              <td>{flight.departs}</td>
              <td>Rs. {flight.fare.toLocaleString('en-IN')}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

function BookingScreen() {
  const [flightCode, setFlightCode] = useState(flights[0].code);
  const [travelDate, setTravelDate] = useState('2026-08-14');
  const [seats, setSeats] = useState(1);
  const [receipt, setReceipt] = useState('');

  const chosen = flights.find((flight) => flight.code === flightCode);
  const payable = chosen.fare * seats;

  const submitBooking = (event) => {
    event.preventDefault();
    setReceipt(
      `${seats} seat(s) held on ${chosen.airline} ${chosen.code} for ${travelDate}. Total Rs. ${payable.toLocaleString('en-IN')}.`
    );
  };

  return (
    <div>
      <h3 className="panel__title">Reserve a seat</h3>

      <form onSubmit={submitBooking}>
        <label className="field">
          <span className="field__label">Flight</span>
          <select
            className="field__input"
            value={flightCode}
            onChange={(event) => {
              setFlightCode(event.target.value);
              setReceipt('');
            }}
          >
            {flights.map((flight) => (
              <option key={flight.code} value={flight.code}>
                {flight.airline} {flight.code} &mdash; {flight.from} to {flight.to}
              </option>
            ))}
          </select>
        </label>

        <label className="field">
          <span className="field__label">Travel date</span>
          <input
            className="field__input"
            type="date"
            value={travelDate}
            onChange={(event) => setTravelDate(event.target.value)}
          />
        </label>

        <label className="field">
          <span className="field__label">Seats</span>
          <input
            className="field__input"
            type="number"
            min="1"
            max="9"
            value={seats}
            onChange={(event) => setSeats(Number(event.target.value) || 1)}
          />
        </label>

        <p className="panel__note">
          Amount payable: Rs. {payable.toLocaleString('en-IN')}
        </p>

        <button className="btn" type="submit">
          Confirm booking
        </button>
      </form>

      {receipt && (
        <div className="notice" style={{ marginTop: '16px' }}>
          {receipt}
        </div>
      )}
    </div>
  );
}

function TicketBooking() {
  const [signedIn, setSignedIn] = useState(false);

  return (
    <div className="panel">
      <span className="tag">Conditional rendering</span>

      <div
        style={{
          display: 'flex',
          justifyContent: 'space-between',
          alignItems: 'center',
          gap: '12px',
          borderBottom: '1px solid var(--rule)',
          paddingBottom: '12px',
          marginBottom: '18px',
        }}
      >
        <h2 className="panel__title" style={{ margin: 0 }}>
          {signedIn ? 'Member area' : 'Guest area'}
        </h2>
        <button className="btn" onClick={() => setSignedIn(!signedIn)}>
          {signedIn ? 'Sign out' : 'Sign in'}
        </button>
      </div>

      {signedIn ? <BookingScreen /> : <GuestScreen />}
    </div>
  );
}

export default TicketBooking;
