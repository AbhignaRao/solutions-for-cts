/*
  Exercise 1 - My First React
  Goal: render a main heading from a React component created with Vite.
*/

function FirstReact() {
  return (
    <div className="panel">
      <span className="tag">Vite + React</span>
      <h1 style={{ fontSize: '2.1rem', color: 'var(--petrol)' }}>
        welcome to the first session of React
      </h1>
      <p className="panel__note">
        This heading is produced by a single function component. Vite serves it
        through <code>index.html</code> &rarr; <code>main.jsx</code> &rarr;{' '}
        <code>App.jsx</code>.
      </p>
    </div>
  );
}

export default FirstReact;
