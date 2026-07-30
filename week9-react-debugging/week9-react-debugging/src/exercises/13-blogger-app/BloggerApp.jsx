/*
  Exercise 13 - Blogger App
  Goal: show the same three sections through four different conditional
  rendering syntaxes - switch/case, element variable, logical AND, ternary.
*/

import { useState } from 'react';

export function BookDetails() {
  const books = [
    { title: 'The Pragmatic Programmer', author: 'Hunt and Thomas', year: 1999 },
    { title: 'Clean Code', author: 'Robert C. Martin', year: 2008 },
    { title: 'Eloquent JavaScript', author: 'Marijn Haverbeke', year: 2011 },
  ];

  return (
    <div>
      <h3 className="panel__title">Book details</h3>
      <table className="ledger">
        <thead>
          <tr>
            <th>Title</th>
            <th>Author</th>
            <th>Year</th>
          </tr>
        </thead>
        <tbody>
          {books.map((book) => (
            <tr key={book.title}>
              <td>{book.title}</td>
              <td>{book.author}</td>
              <td>{book.year}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export function BlogDetails() {
  const posts = [
    {
      title: 'Reading a React stack trace',
      author: 'Cohort notes',
      date: '18 Jul 2026',
      summary: 'How to walk from a red console line back to the component that threw.',
    },
    {
      title: 'State updates are queued, not instant',
      author: 'Cohort notes',
      date: '24 Jul 2026',
      summary: 'Why the updater function form is safer than reading state directly.',
    },
  ];

  return (
    <div>
      <h3 className="panel__title">Blog details</h3>
      {posts.map((post) => (
        <article className="card" key={post.title} style={{ marginBottom: '12px' }}>
          <h4 className="card__title">{post.title}</h4>
          <p className="card__meta">
            {post.author} &middot; {post.date}
          </p>
          <p style={{ margin: '6px 0 0' }}>{post.summary}</p>
        </article>
      ))}
    </div>
  );
}

export function CourseDetails() {
  const courses = [
    { title: 'Java Full Stack Engineering', trainer: 'Cognizant Academy', length: '7 weeks' },
    { title: 'React SPA and Debugging', trainer: 'Cognizant Academy', length: '2 weeks' },
    { title: 'Cloud Foundations', trainer: 'AWS Academy', length: '20 hours' },
  ];

  return (
    <div>
      <h3 className="panel__title">Course details</h3>
      <div className="grid">
        {courses.map((course) => (
          <div className="card" key={course.title}>
            <h4 className="card__title">{course.title}</h4>
            <p className="card__meta">Trainer: {course.trainer}</p>
            <p className="card__meta">Length: {course.length}</p>
          </div>
        ))}
      </div>
    </div>
  );
}

const sections = [
  { key: 'book', label: 'Books' },
  { key: 'blog', label: 'Blog' },
  { key: 'course', label: 'Courses' },
];

const syntaxes = [
  { key: 'switch', label: 'switch / case' },
  { key: 'variable', label: 'element variable' },
  { key: 'logical', label: 'logical AND' },
  { key: 'ternary', label: 'ternary' },
];

function BloggerApp() {
  const [section, setSection] = useState('book');
  const [syntax, setSyntax] = useState('switch');

  /* Syntax 1 - switch/case inside a helper function. */
  const bySwitch = () => {
    switch (section) {
      case 'book':
        return <BookDetails />;
      case 'blog':
        return <BlogDetails />;
      case 'course':
        return <CourseDetails />;
      default:
        return <p>Pick a section.</p>;
    }
  };

  /* Syntax 2 - assign the element to a variable, then render the variable. */
  let byVariable;
  if (section === 'book') {
    byVariable = <BookDetails />;
  } else if (section === 'blog') {
    byVariable = <BlogDetails />;
  } else {
    byVariable = <CourseDetails />;
  }

  return (
    <div className="panel">
      <span className="tag">Four rendering syntaxes</span>
      <h2 className="panel__title">Blogger App</h2>
      <p className="panel__note">
        Choose a section, then choose the syntax used to decide what appears. The
        output is identical every time, which is the point of the exercise.
      </p>

      <div className="btn-row">
        {sections.map((item) => (
          <button
            key={item.key}
            className={section === item.key ? 'btn' : 'btn btn--ghost'}
            onClick={() => setSection(item.key)}
          >
            {item.label}
          </button>
        ))}
      </div>

      <fieldset
        style={{
          border: '1px solid var(--rule)',
          borderRadius: 'var(--radius)',
          padding: '12px 14px',
          marginBottom: '18px',
        }}
      >
        <legend className="field__label" style={{ padding: '0 6px' }}>
          Rendering syntax
        </legend>
        <div style={{ display: 'flex', flexWrap: 'wrap', gap: '16px' }}>
          {syntaxes.map((item) => (
            <label key={item.key} style={{ display: 'flex', gap: '6px' }}>
              <input
                type="radio"
                name="syntax"
                value={item.key}
                checked={syntax === item.key}
                onChange={() => setSyntax(item.key)}
              />
              <span style={{ fontFamily: 'var(--mono)', fontSize: '13px' }}>
                {item.label}
              </span>
            </label>
          ))}
        </div>
      </fieldset>

      <div style={{ borderTop: '1px dashed var(--rule)', paddingTop: '18px' }}>
        {syntax === 'switch' && bySwitch()}

        {syntax === 'variable' && byVariable}

        {/* Syntax 3 - logical AND, one line per possibility. */}
        {syntax === 'logical' && section === 'book' && <BookDetails />}
        {syntax === 'logical' && section === 'blog' && <BlogDetails />}
        {syntax === 'logical' && section === 'course' && <CourseDetails />}

        {/* Syntax 4 - nested ternary. */}
        {syntax === 'ternary' &&
          (section === 'book' ? (
            <BookDetails />
          ) : section === 'blog' ? (
            <BlogDetails />
          ) : (
            <CourseDetails />
          ))}
      </div>
    </div>
  );
}

export default BloggerApp;
