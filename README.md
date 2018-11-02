# 2018PowerUp-Offseason
=========================

Welcome! We're glad you've decided to help build SaltedFishballs' sweet robots! In order to keep the robot running well, we have a few light standards for editing code in the repository.

## Getting an Assignment

Todo

## Formulating a Change

We encourage using branches so that you can work on a change, make it visible to the group for review and feedback, and merge once the idea is fully baked. This makes it easy for everyone to work on different tasks without having to work about impacting others or the stability of master.

You can make a branch either on your local clone (and push later) or on the github interface (and pull the new branch now). Change to the branch with `git checkout -b <branch_name>`. Please make you branch names with the `<username>_<quick_change_description>` format so that we can find them easily.

Once you have tested/debugged your change and want it to be considered for merge into master, use it to submit a pull request.

## Pull Requests (Review)

The master branch should be protected[1]. Changes must come from a pull request. In order to merge your pull request, you must:

* get the approval of a subsystem lead (even though github will not enforce this)
* if you are the system lead get the approval of another system collaborator
* pass system tests

The approver will leave a comment like "ready for merge" if they approve, but they will not approve until any other commented issues are resolved. They may approve and leave a "(nit)" comment in the case of extra spaces or misspellings; the writer is responsible for resolving these before merge but does not need new approval.

The goal of our branches/pull request policy is to keep ownership of the code with the writer while controlling the quality of what is in master. It is the writer's responsiblity to sheppard their code into master. This means they should not feel bad about bothering the necessary reviewers if they have not responded in more than a day.

## Subsystem Leads

* Subsystem1 (`Elevator`): SomeAwesomePerson
* Subsystem2 (`Intake`): AnotherAwesomePerson
* etc.

## Notes

[1] Never ever do `git push -f`! This will cause a mergepocalypse, dropping everything from our master branch!
