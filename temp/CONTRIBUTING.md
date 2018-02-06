# Contributing

When contributing to this repository, please first discuss the change you wish to make via issue,
email, or any other method with the owners of this repository before making a change. 

Please note we have a code of conduct, please follow it in all your interactions with the project.

## Git Workflow

1. Fork on GitHub (click Fork button)
2. Clone to computer, use SSH URL ($ git clone git@github.com:repo/URL.git)
3. Don't forget to cd into your repo: ($ cd CSJoe/)
4. Set up remote upstream ($ git remote add upstream git://github.com/techiespace/CSJoe.git)
5. Create a branch for new issue ($ git checkout -b 404-new-feature)
6. Develop on issue branch. [Time passes, the main MuseScore repository accumulates new commits]
7. Commit changes to your local issue branch. ($ git add . ; git commit -m 'commit message')
8. Fetch upstream ($ git fetch upstream)
9. Update local master ($ git checkout master; git merge upstream/master)
10. Rebase issue branch ($ git checkout 404-new-feature; git rebase master)
11. Repeat steps 6-11 until dev is complete
12. Push branch to GitHub ($ git push origin 404-new-feature)
13. Start your browser, go to your Github repo, switch to "404-new-feature" branch and press the [Pull Request] button

After having made a Pull Request don't pull/merge anymore, it'll mess up the commit history. If you (have to) rebase, use 'push --force' ($ git push --force) to send it up to your GitHub repository, this will update the PR too. Be careful not to do this while the core team is working on merging in your PR.

## Code of Conduct

### Our Standards

Examples of behavior that contributes to creating a positive environment
include:

* Using welcoming and inclusive language
* Being respectful of differing viewpoints and experiences
* Gracefully accepting constructive criticism
* Focusing on what is best for the community
* Showing empathy towards other community members
