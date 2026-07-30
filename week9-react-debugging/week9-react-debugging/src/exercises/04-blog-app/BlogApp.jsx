/*
  Exercise 4 - Blog App
  Goal: a class component that loads data in componentDidMount and recovers
  from a child render error in componentDidCatch.
*/

import { Component } from 'react';

const LIVE_ENDPOINT = 'https://jsonplaceholder.typicode.com/posts';
const BROKEN_ENDPOINT = 'https://jsonplaceholder.typicode.com/endpoint-that-is-missing';

/* Child of Posts. Throws on purpose so the error boundary has something to catch. */
function PostCard({ post, unstable }) {
  if (unstable) {
    throw new Error(`Cannot render post #${post.id}: headline data is missing.`);
  }

  return (
    <article className="card" style={{ marginBottom: '12px' }}>
      <h3 className="card__title" style={{ textTransform: 'capitalize' }}>
        {post.title}
      </h3>
      <p className="card__meta">{post.body}</p>
    </article>
  );
}

export class Posts extends Component {
  constructor(props) {
    super(props);
    this.state = {
      articles: [],
      busy: true,
      requestError: '',
      renderError: '',
      unstable: false,
    };
  }

  componentDidMount() {
    this.requestArticles(LIVE_ENDPOINT);
  }

  componentDidCatch(error, details) {
    console.error('Error boundary caught a render failure:', error, details);
    this.setState({ renderError: error.message });
  }

  requestArticles(endpoint) {
    this.setState({ busy: true, requestError: '', renderError: '', unstable: false });

    fetch(endpoint)
      .then((response) => {
        if (!response.ok) {
          throw new Error(`Request failed with status ${response.status}`);
        }
        return response.json();
      })
      .then((payload) => {
        this.setState({ articles: payload.slice(0, 6), busy: false });
      })
      .catch((error) => {
        this.setState({ requestError: error.message, busy: false, articles: [] });
      });
  }

  render() {
    const { articles, busy, requestError, renderError, unstable } = this.state;

    return (
      <div className="panel">
        <span className="tag">Lifecycle + fetch</span>
        <h2 className="panel__title">Blog Posts</h2>
        <p className="panel__note">
          Posts arrive from the JSONPlaceholder API. The two failure buttons let
          you watch each error path separately.
        </p>

        <div className="btn-row">
          <button className="btn" onClick={() => this.requestArticles(LIVE_ENDPOINT)}>
            Reload posts
          </button>
          <button
            className="btn btn--danger"
            onClick={() => this.requestArticles(BROKEN_ENDPOINT)}
          >
            Break the request
          </button>
          <button
            className="btn btn--ghost"
            onClick={() => this.setState({ unstable: true })}
          >
            Break the render
          </button>
        </div>

        {renderError && (
          <div className="notice notice--error">
            <strong>componentDidCatch:</strong> {renderError} Press
            &ldquo;Reload posts&rdquo; to recover.
          </div>
        )}

        {requestError && !renderError && (
          <div className="notice notice--error">
            <strong>Request failed:</strong> {requestError}
          </div>
        )}

        {busy && <p className="panel__note">Loading posts&hellip;</p>}

        {!renderError &&
          articles.map((post) => (
            <PostCard key={post.id} post={post} unstable={unstable} />
          ))}
      </div>
    );
  }
}

function BlogApp() {
  return <Posts />;
}

export default BlogApp;
