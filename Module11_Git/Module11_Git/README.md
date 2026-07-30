# Module 11 — Version Control with Git

Hands-on labs **GIT HOL 1 to HOL 5** from the *Digital Nurture 5.0 Deep Skilling
Handbook*.

This module has no application to run. The deliverable is the set of Git
operations carried out at the command line, the files they produce, and the
terminal evidence of each one.

---

## Developer Details

| | |
|---|---|
| **Name** | Abhigna Rao Lingala |
| **Email** | lingalaabhignarao@gmail.com |
| **Repository** | https://github.com/AbhignaRao/solutions-for-cts |
| **Track** | Java Full Stack Engineer (React) — Deep Skilling Cohort |

---

## Labs Completed

### GIT HOL 1 — Installation and Configuration
Verified the Git client version, set `user.name` and `user.email` globally, read
the configuration back with `git config --list`, registered a default editor for
commit messages, and added a shell alias to launch the editor from Git Bash.

### GIT HOL 2 — Ignore Rules
Wrote a `.gitignore` excluding `*.log` and the `log/` directory, created matching
files and folders to test against, and confirmed with `git status` and
`git check-ignore -v` that Git skips them while still reporting other untracked
files.

### GIT HOL 3 — Branching and Merging
Created `GitNewBranch`, listed branches, switched to it, committed `info.txt`
there, returned to `main`, compared the two with `git diff`, merged the branch as
a fast-forward, inspected the decorated log graph, and deleted the merged branch.

### GIT HOL 4 — Conflict Resolution
Created `hello.xml` independently on `GitWork` and on `main`, observed the
divergence in the log graph, triggered a content conflict on merge, resolved the
file by hand by removing the conflict markers, committed the resolution, added
`*.orig` to `.gitignore`, and deleted the branch.

### GIT HOL 5 — Remote Repository Operations
Inspected the configured remote with `git remote -v`, pulled remote changes to
sync, pushed local commits to GitHub, and confirmed the commit appeared on the
repository page.

Full command sequences with expected output are in **[commands.md](commands.md)**.

---

## Files in this folder

| File | Produced by |
|---|---|
| `commands.md` | Command walkthrough for all five labs |
| `.gitignore` | HOL 2 ignore rules, extended in HOL 4 |
| `info.txt` | Committed on `GitNewBranch` in HOL 3 |
| `hello.xml` | Conflicted then resolved in HOL 4 |
| `screenshots/` | Terminal evidence for each lab |

---

## Screenshots

| Lab | Evidence | Screenshot |
|---|---|---|
| HOL 1 | Version and config output | ![HOL 1](screenshots/HOL1_Config.png) |
| HOL 2 | `git status` with log files ignored | ![HOL 2](screenshots/HOL2_GitIgnore.png) |
| HOL 3 | Merge result and log graph | ![HOL 3](screenshots/HOL3_BranchMerge.png) |
| HOL 4 | `CONFLICT` message | ![HOL 4 conflict](screenshots/HOL4_Conflict.png) |
| HOL 4 | Resolved history after commit | ![HOL 4 resolved](screenshots/HOL4_Resolved.png) |
| HOL 5 | Successful push output | ![HOL 5](screenshots/HOL5_Push.png) |

---

## Note on the conflict labs

HOL 3 and HOL 4 deliberately create branches, a merge conflict, and branch
deletions. Those were carried out in a separate scratch repository rather than in
this one, so that the submission repository keeps a clean, readable history. The
resulting files and terminal captures were copied here afterwards.
