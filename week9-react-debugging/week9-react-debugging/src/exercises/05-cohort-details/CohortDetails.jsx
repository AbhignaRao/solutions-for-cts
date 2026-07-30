/*
  Exercise 5 - Cohort Details
  Goal: style a dashboard with CSS Modules and colour each cohort title by
  status - green while it is running, blue otherwise.
*/

import styles from './CohortDetails.module.css';

const cohorts = [
  {
    code: 'DN5-JFS-01',
    title: 'Java Full Stack Engineer',
    programme: 'Digital Nurture 5.0',
    starts: '02 Jun 2026',
    ends: '20 Jul 2026',
    status: 'completed',
  },
  {
    code: 'DN5-RCT-04',
    title: 'React Front End Track',
    programme: 'Digital Nurture 5.0',
    starts: '15 Jul 2026',
    ends: '05 Sep 2026',
    status: 'ongoing',
  },
  {
    code: 'DN5-CLD-02',
    title: 'Cloud and DevOps Track',
    programme: 'Deep Skilling Academy',
    starts: '01 Aug 2026',
    ends: '15 Sep 2026',
    status: 'ongoing',
  },
  {
    code: 'DN5-DAT-07',
    title: 'Data Engineering Track',
    programme: 'Deep Skilling Academy',
    starts: '05 Oct 2026',
    ends: '28 Nov 2026',
    status: 'scheduled',
  },
];

function CohortDetails() {
  return (
    <div className="panel">
      <span className="tag">CSS Modules</span>
      <h2 className="panel__title">Academy Cohorts</h2>
      <p className="panel__note">
        Titles use a green class while a cohort is ongoing and a blue class for
        every other status.
      </p>

      <div className={styles.board}>
        {cohorts.map((cohort) => {
          const statusClass =
            cohort.status === 'ongoing' ? styles.running : styles.other;

          return (
            <section className={styles.box} key={cohort.code}>
              <h3 className={`${styles.boxTitle} ${statusClass}`}>
                {cohort.title}
              </h3>
              <dl>
                <dt>Cohort code</dt>
                <dd>{cohort.code}</dd>
                <dt>Programme</dt>
                <dd>{cohort.programme}</dd>
                <dt>Runs</dt>
                <dd>
                  {cohort.starts} &ndash; {cohort.ends}
                </dd>
                <dt>Status</dt>
                <dd className={statusClass}>{cohort.status}</dd>
              </dl>
            </section>
          );
        })}
      </div>
    </div>
  );
}

export default CohortDetails;
