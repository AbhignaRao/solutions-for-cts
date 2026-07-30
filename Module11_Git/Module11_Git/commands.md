# Module 11 — Version Control with Git

Command-by-command walkthrough of **GIT HOL 1 to HOL 5** from the Digital Nurture 5.0
Deep Skilling Handbook.

| | |
|---|---|
| **Name** | Abhigna Rao Lingala |
| **Email** | lingalaabhignarao@gmail.com |
| **Repository** | https://github.com/AbhignaRao/solutions-for-cts |

---

## Before you start — where to run these

HOL 3 and HOL 4 create branches, force a merge conflict, and delete branches.
**Do not run them inside `solutions-for-cts`.** A deliberate conflict in your
submission repo leaves a messy history that you then have to explain.

Use a throwaway repo instead:

```bash
cd ~
mkdir git-practice
cd git-practice
git init
```

Run HOL 2 to HOL 4 there. Copy the finished files and your screenshots into
`Module11_Git/` afterwards.

HOL 5 needs a real remote, so run that part in a repo that is actually connected
to GitHub.

---

## GIT HOL 1 — Installation and configuration

### Confirm Git is installed

```bash
git --version
```

Prints something like `git version 2.45.1`. If the command is not found, install
Git for Windows from git-scm.com and reopen the terminal.

### Set your identity

Every commit records a name and an email. Set them once, globally:

```bash
git config --global user.name "Abhigna Rao Lingala"
git config --global user.email "lingalaabhignarao@gmail.com"
```

These must match the account that owns the repository, or your commits will show
up on GitHub as belonging to nobody.

### Read the configuration back

```bash
git config --list
```

Look for the `user.name` and `user.email` lines you just set. To check one value
on its own:

```bash
git config user.name
git config --global --list
```

### Set the default editor

Git opens an editor when a commit message is not supplied inline. The handbook
uses Notepad++:

```bash
git config --global core.editor "'C:/Program Files/Notepad++/notepad++.exe' -multiInst -notabbar -nosession -noPlugin"
```

If you installed the 32-bit build, the path is
`C:/Program Files (x86)/Notepad++/notepad++.exe` instead.

Since you already work in VS Code, this is the equivalent:

```bash
git config --global core.editor "code --wait"
```

The `--wait` flag matters. Without it Git carries on before you have saved the
message, and the commit is aborted as empty.

### Add a shell alias

In Git Bash, open your profile:

```bash
notepad ~/.bashrc
```

Add one line, then save and close:

```bash
alias npp="'C:/Program Files/Notepad++/notepad++.exe'"
```

Reload the profile and test the alias:

```bash
source ~/.bashrc
npp sample.txt
```

Notepad++ should open with an empty `sample.txt`.

**Screenshot for this HOL:** one terminal showing `git --version` followed by
`git config --list` with your name and email visible.

---

## GIT HOL 2 — Ignore rules

Git tracks everything in the working directory unless told otherwise. A
`.gitignore` file lists the patterns to skip.

### Write the ignore file

In the repository root, create `.gitignore` containing:

```text
*.log
log/
```

`*.log` matches any file with that extension at any depth. `log/` matches a
directory of that name and everything inside it.

### Create files to test against

```bash
mkdir log
touch application.log
touch log/debug.log
touch notes.txt
```

On PowerShell, `touch` does not exist — use `New-Item application.log` instead,
or run these in Git Bash.

### Check what Git sees

```bash
git status
```

`notes.txt` and `.gitignore` appear as untracked. `application.log` and the
whole `log/` directory do not. That absence is the result.

To prove it directly, ask Git which rule is hiding a file:

```bash
git check-ignore -v application.log
```

It prints the file, the rule that matched, and the line number in `.gitignore`.

### One thing to know

`.gitignore` only affects **untracked** files. If a file was committed before
the rule existed, Git keeps tracking it. Remove it from the index first:

```bash
git rm --cached application.log
```

**Screenshot for this HOL:** `git status` showing the log files absent, with the
`.gitignore` contents visible either above it or side by side.

---

## GIT HOL 3 — Branching and merging

### Start from a clean state

```bash
git status
```

It should say there is nothing to commit. Commit or stash anything outstanding
before branching.

### Create a branch

```bash
git branch GitNewBranch
```

This creates the branch but leaves you where you are.

### List branches

```bash
git branch -a
```

The `*` marks the branch you are on — still `main` at this point. `-a` includes
remote-tracking branches.

### Switch to it

```bash
git checkout GitNewBranch
```

Newer Git also offers `git switch GitNewBranch`, which does the same thing with
a clearer name.

### Commit something on the branch

```bash
echo "Created on GitNewBranch during HOL 3." > info.txt
git add info.txt
git commit -m "Add info.txt on GitNewBranch"
```

### Go back to main and compare

```bash
git checkout main
git diff main GitNewBranch
```

The diff shows `info.txt` as added, because it exists on one side only. Note
that `info.txt` has disappeared from your folder — that is branches working
correctly, not a lost file.

### Merge

```bash
git merge GitNewBranch
```

Because `main` has not moved since the branch was created, Git reports a
**fast-forward**: it slides the `main` pointer up rather than creating a merge
commit. `info.txt` reappears.

### Look at the history

```bash
git log --oneline --graph --decorate --all
```

`--oneline` compresses each commit to a line, `--graph` draws the branch
structure, `--decorate` shows branch names, `--all` includes every branch.

### Delete the merged branch

```bash
git branch -d GitNewBranch
```

Lowercase `-d` refuses to delete a branch whose commits are not yet merged
somewhere. That refusal is a safety net — reach for `-D` only when you are
certain you want to discard the work.

**Screenshot for this HOL:** the log graph after the merge, with the merge
result and the branch deletion confirmation visible.

---

## GIT HOL 4 — Merge conflicts

A conflict happens when the same lines of the same file change on both branches.
Git cannot choose between them, so it stops and asks you.

### Create a branch and commit a file on it

```bash
git checkout -b GitWork
```

`-b` creates and switches in one step.

```bash
echo "<greeting>Hello from GitWork</greeting>" > hello.xml
git add hello.xml
git commit -m "Add hello.xml on GitWork"
```

### Create the same file differently on main

```bash
git checkout main
echo "<greeting>Hello from main</greeting>" > hello.xml
git add hello.xml
git commit -m "Add hello.xml on main"
```

Both branches now have a `hello.xml`, with different content, added
independently. That is the setup for a conflict.

### See the divergence

```bash
git log --oneline --graph --decorate --all
```

The graph splits into two lines that do not rejoin.

### Trigger the conflict

```bash
git merge GitWork
```

Git reports:

```text
Auto-merging hello.xml
CONFLICT (content): Merge conflict in hello.xml
Automatic merge failed; fix conflicts and then commit the result.
```

`git status` now lists `hello.xml` under "Unmerged paths".

### Resolve it

Open `hello.xml`. Git has written both versions into the file with markers:

```text
<<<<<<< HEAD
<greeting>Hello from main</greeting>
=======
<greeting>Hello from GitWork</greeting>
>>>>>>> GitWork
```

Everything between `<<<<<<<` and `=======` is the current branch. Everything
between `=======` and `>>>>>>>` is the branch being merged in.

Resolving means editing the file into whatever you actually want and **deleting
all three marker lines**. Keeping one side, keeping the other, or writing
something new are all valid. For this lab, combine them:

```xml
<greeting>Hello from main and GitWork, merged</greeting>
```

Leftover marker characters are the classic mistake here — they are not syntax
Git strips for you, they are plain text in your file.

### Commit the resolution

```bash
git add hello.xml
git commit -m "Resolve merge conflict in hello.xml"
```

Staging the file is how you tell Git the conflict is settled. Running
`git commit` with no message opens your editor with a pre-filled merge message.

To abandon a merge and return to the state before it:

```bash
git merge --abort
```

### Ignore merge tool leftovers

Some merge tools leave `.orig` backup files behind:

```bash
echo "*.orig" >> .gitignore
git add .gitignore
git commit -m "Ignore merge backup files"
```

Note `>>` appends. A single `>` would overwrite the whole file.

### Clean up

```bash
git branch -d GitWork
git log --oneline --graph --decorate
```

The graph now shows the two lines rejoining at the merge commit.

**Screenshot for this HOL:** two captures — the `CONFLICT` message from the
failed merge, and the log graph after the resolution commit.

---

## GIT HOL 5 — Remote repositories

Run this part in a repo connected to GitHub.

### Check the remote

```bash
git remote -v
```

Prints the fetch and push URLs for `origin`. No output means no remote is
configured:

```bash
git remote add origin https://github.com/AbhignaRao/solutions-for-cts.git
```

### Pull before you push

```bash
git pull origin main
```

This fetches remote commits and merges them into your branch. Doing it first
avoids a rejected push when the remote has moved ahead — for instance after you
uploaded files through the GitHub web interface.

### Push

```bash
git push origin main
```

The first push of a new branch needs `-u` to set up tracking, after which plain
`git push` is enough:

```bash
git push -u origin main
```

### If the push is rejected

```text
! [rejected]  main -> main (fetch first)
```

The remote has commits you do not. Pull, resolve anything that conflicts, then
push again. Do not reach for `--force` to get past this — it overwrites the
remote history, including other people's commits.

### Confirm on GitHub

Open the repository in a browser and check that your latest commit message and
timestamp appear at the top of the file list.

**Screenshot for this HOL:** the terminal output of a successful push, and the
GitHub repository page showing that commit.

---

## Quick reference

| Task | Command |
|---|---|
| Check version | `git --version` |
| Set identity | `git config --global user.name "..."` |
| List config | `git config --list` |
| Working tree status | `git status` |
| Why is this ignored | `git check-ignore -v <file>` |
| Stop tracking a file | `git rm --cached <file>` |
| List branches | `git branch -a` |
| Create and switch | `git checkout -b <name>` |
| Compare branches | `git diff <a> <b>` |
| Merge in a branch | `git merge <name>` |
| Abandon a merge | `git merge --abort` |
| History graph | `git log --oneline --graph --decorate --all` |
| Delete merged branch | `git branch -d <name>` |
| Show remotes | `git remote -v` |
| Fetch and merge | `git pull origin main` |
| Upload commits | `git push origin main` |
