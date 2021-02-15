# ForumSpring
Project Forum in Spring

Simple forum with CRUD operations.

Tools: Spring Framework, Hibernate, Thymeleaf, mySql, IntelliJ Idea

Functions:

For everyone:
- /registration path for register new user
- /login path for sign in to forum

For authenticated (user):
- Home page with Last Activity (last inscription), top 5 new topics (/home)
- Section with topic list(10 for page) (/topic)
- Creating topic option with title and text (/newTopic)
- Searching option for looking for topics by word key (with regular expression) (/search)
- Find user option for looking for users (display information about topic and inscription numbers, display topic/inscription list) (/user)
- Creating inscriptions in topics (/topic/id/inscription)
- Delete/edit own inscriptions
- Editing text in topic (/topic/edit/id)

For administrator (admin):
- Admin section(/admin)
- Displaying all users (/admin/getusers)
- Delete topic/inscription by id
- Displaying in every topic informations about topic and inscription id
- Disable/Enable user

