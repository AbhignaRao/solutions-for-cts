/*
  Exercise 2 - Student App
  Goal: build three class components and render them from one parent component.
*/

import { Component } from 'react';

export class Home extends Component {
  render() {
    return (
      <div className="card">
        <h3 className="card__title">Home</h3>
        <p className="card__meta">
          Landing area of the Student Management Portal.
        </p>
      </div>
    );
  }
}

export class About extends Component {
  render() {
    return (
      <div className="card">
        <h3 className="card__title">About</h3>
        <p className="card__meta">
          The portal keeps enrolment, attendance and result records in one place.
        </p>
      </div>
    );
  }
}

export class Contact extends Component {
  render() {
    return (
      <div className="card">
        <h3 className="card__title">Contact</h3>
        <p className="card__meta">
          Reach the registrar desk on extension 4021 or at desk@campus.edu.
        </p>
      </div>
    );
  }
}

class StudentApp extends Component {
  render() {
    return (
      <div className="panel">
        <span className="tag">Class components</span>
        <h2 className="panel__title">Student Management Portal</h2>
        <p className="panel__note">
          Three separate class components rendered side by side by a fourth one.
        </p>
        <div className="grid">
          <Home />
          <About />
          <Contact />
        </div>
      </div>
    );
  }
}

export default StudentApp;
