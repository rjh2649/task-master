# TaskMaster

Individuals with Attention Deficit Hyperactivity Disorder (ADHD) often struggle with managing their daily tasks and maintaining focus on priorities. Despite recognizing the importance of task management, many find it challenging to effectively organize their time and activities, leading to stress, procrastination, and feelings of overwhelm. This project hopes to address these challenges by featuring priority-based task scheduling, allowing users to create personalized schedules aligned with their goals. Users earn points as they complete tasks, which can be exchanged for pre-selected recreational activities/break time.

As someone with ADHD, I find myself struggling to get things done in timely fashion, no matter how much I want to get them done. With TaskMaster, I'm hoping to create something that works for me, as well as anyone else who struggles with executive dysfunction.

# Use Cases

1. As a user, I'd like to create tasks and assign priority levels (eg. high, medium, low; 1-5).
  a. Create a new task
  b. Assign priority
  c. Set time for task
  d. Add task details/notes
2. As a user, I'd like the app to generate a daily schedule based on my prioritized tasks.
   a. Create a schedule
   b. View schedule
   c. Adjust tasks within schedule
   d. Integration with Google Calendar
3. As a user, I'd like to earn points for completing tasks, which I can exchange for recreational activities or break time to stay motivated.
   a. Mark tasks as completed
   b. Earn points upon completion
   c. View available rewards
   d. Redeem points for rewards
4. As a user, I'd like a focus mode feature that allows me to work on single tasks in timed sessions (e.g. 15 minutes on, 5 minutes off)
   a. Start a focus session
   b. Set duration
   c. Take scheduled breaks
5. As a user, I'd like to track my mood and energy levels throughout the day in order ot understand how these factors impact my productivity and adjust my schedule accordingly.
   a. Log mood and energy levels
   b. View past logs alongside prior task completion
6. As a user, I'd like to receive reminders for upcoming tasks that I've set. (maybe more of a stretch? will investigate)
7. As a user, I'd like to view my task completion history to track my progress over time, helping me see improvements and stay motivated.
  a. View completed tasks
  b. Track points earned
  c. Maybe also provide graphs/charts
8. As a user, I'd like to categorize my tasks with custom tags, so that I can organize them better and filter tasks based on categories.
  a. Create and assign tags to tasks
  b. Filter tasks by tags
  c. Edit and delete tasks
9. As a user, I'd like to share tasks with other users to either collaborate or provide mutual support. (This is probably more of a stretch, but not a bad idea either)

# Rough Class Diagram
![jLbBRzms4BxpLwW-zO44xTK8Z6nifosmpM7j77gK9OOMhCUMfBn8ZVxtajI37CwGAmUjBmiDFtBc6tAeR-JtIfTIZrtBs7Sr_zBql3ZSNj_lFlswlsNggUcFfIoxecfBfOeNLfY_3sNr9EGmzlNrq0xotRjzsJtKvSUnhtGpz5TNnRuHZ_fFthR3QPeno9hB5Ff3QnPqk9sKmyd1PkC_](https://github.com/rjh2649/task-master/assets/146969105/9a68ba9d-bbad-4a86-950b-b1bf010c81d3)

# DynamoDB Tables

1. Users Table
Table Name: Users

Purpose: To store user information and authentication details.

Primary Key:

    UserID (String) - Partition key

Attributes:

    UserID (String): Unique identifier for the user.
    Email (String): User's email address.
    PasswordHash (String): Hashed password for authentication.
    Points (Number): Total points earned by the user.
    RegisteredAt (String): Timestamp of when the user registered.

2. Tasks Table
Table Name: Tasks

Purpose: To store information about user tasks.

Primary Key:

    TaskID (String) - Partition key

Secondary Index:

    UserID-CompletionStatus-index - To query tasks by user and completion status.

Attributes:

    TaskID (String): Unique identifier for the task.
    UserID (String): Identifier of the user to whom the task belongs.
    Title (String): Title of the task.
    Description (String): Detailed description of the task.
    Priority (String): Priority level of the task (e.g., High, Medium, Low).
    DueDate (String): Due date of the task.
    CompletionStatus (String): Status of the task (e.g., Pending, Completed).
    CreatedAt (String): Timestamp of when the task was created.
    CompletedAt (String): Timestamp of when the task was completed.
    Points (Number): Points awarded upon completion of the task.

3. Rewards Table
Table Name: Rewards

Purpose: To store information about available rewards and activities.

Primary Key:

    RewardID (String) - Partition key

Attributes:

    RewardID (String): Unique identifier for the reward.
    Name (String): Name of the reward.
    Description (String): Description of the reward.
    PointsRequired (Number): Points required to redeem the reward.

4. UserRewards Table
Table Name: UserRewards

Purpose: To track rewards redeemed by users.

Primary Key:

    UserRewardID (String) - Partition key

Attributes:

    UserRewardID (String): Unique identifier for the user-reward relationship.
    UserID (String): Identifier of the user.
    RewardID (String): Identifier of the reward.
    RedeemedAt (String): Timestamp of when the reward was redeemed.

5. Tags Table
Table Name: Tags

Purpose: To store tags for categorizing tasks.

Primary Key:

    TagID (String) - Partition key

Attributes:

    TagID (String): Unique identifier for the tag.
    UserID (String): Identifier of the user who created the tag.
    Name (String): Name of the tag.

6. TaskTags Table
Table Name: TaskTags

Purpose: To associate tags with tasks.

Primary Key:

    TaskTagID (String) - Partition key

Attributes:

    TaskTagID (String): Unique identifier for the task-tag relationship.
    TaskID (String): Identifier of the task.
    TagID (String): Identifier of the tag.

7. FocusSessions Table
Table Name: FocusSessions

Purpose: To store information about user's focus sessions.

Primary Key:

    SessionID (String) - Partition key

Attributes:

    SessionID (String): Unique identifier for the focus session.
    UserID (String): Identifier of the user.
    TaskID (String): Identifier of the task.
    StartTime (String): Timestamp of when the session started.
    EndTime (String): Timestamp of when the session ended.
    Duration (Number): Duration of the session in minutes.

8. MoodLogs Table
Table Name: MoodLogs

Purpose: To store user's mood and energy level logs.

Primary Key:

    MoodLogID (String) - Partition key

Attributes:

    MoodLogID (String): Unique identifier for the mood log.
    UserID (String): Identifier of the user.
    Mood (String): Mood level of the user.
    EnergyLevel (String): Energy level of the user.
    LoggedAt (String): Timestamp of when the mood and energy level were logged.

# API endpoints

1. Create Task

Name: Create Task
Description: Creates a new task for the user.
HTTP Method: POST
Path: /tasks
Request Body:


  "userID": "string",
  "title": "string",
  "description": "string",
  "priority": "string",
  "dueDate": "string",
  "points": "number"


Response Body:


  "taskID": "string",
  "userID": "string",
  "title": "string",
  "description": "string",
  "priority": "string",
  "dueDate": "string",
  "completionStatus": "string",
  "createdAt": "string",
  "points": "number"


Errors:

    400 Bad Request: Invalid input.

2. Update Task

Name: Update Task
Description: Updates an existing task.
HTTP Method: PUT
Path: /tasks/taskID
Path Parameters:

    taskID: ID of the task to update.

Request Body:


  "title": "string",
  "description": "string",
  "priority": "string",
  "dueDate": "string",
  "completionStatus": "string"


Response Body:


  "taskID": "string",
  "userID": "string",
  "title": "string",
  "description": "string",
  "priority": "string",
  "dueDate": "string",
  "completionStatus": "string",
  "createdAt": "string",
  "completedAt": "string",
  "points": "number"


Errors:

    400 Bad Request: Invalid input.
    404 Not Found: Task not found.

3. Delete Task

Name: Delete Task
Description: Deletes an existing task.
HTTP Method: DELETE
Path: /tasks/taskID
Path Parameters:

    taskID: ID of the task to delete.

Response Body:


  "status": "string"


Errors:

    404 Not Found: Task not found.

4. Complete Task

Name: Complete Task
Description: Marks a task as completed.
HTTP Method: POST
Path: /tasks/taskID/complete
Path Parameters:

    taskID: ID of the task to complete.

Response Body:


  "taskID": "string",
  "userID": "string",
  "title": "string",
  "description": "string",
  "priority": "string",
  "dueDate": "string",
  "completionStatus": "string",
  "createdAt": "string",
  "completedAt": "string",
  "points": "number"


Errors:

    404 Not Found: Task not found.
    400 Bad Request: Task already completed.

5. Create Tag

Name: Create Tag
Description: Creates a new tag for the user.
HTTP Method: POST
Path: /tags
Request Body:


  "userID": "string",
  "name": "string"


Response Body:


  "tagID": "string",
  "userID": "string",
  "name": "string"


Errors:

    400 Bad Request: Invalid input.

6. Assign Tag to Task

Name: Assign Tag to Task
Description: Assigns a tag to a task.
HTTP Method: POST
Path: /tasks/taskID/tags
Path Parameters:

    taskID: ID of the task to assign the tag to.

Request Body:


  "tagID": "string"


Response Body:


  "taskTagID": "string",
  "taskID": "string",
  "tagID": "string"


Errors:

    404 Not Found: Task or tag not found.
    400 Bad Request: Tag already assigned to the task.

7. Remove Tag from Task

Name: Remove Tag from Task
Description: Removes a tag from a task.
HTTP Method: DELETE
Path: /tasks/taskID/tags/taskTagID
Path Parameters:

    taskID: ID of the task.
    taskTagID: ID of the task-tag association to remove.

Response Body:

json


  "status": "string"


Errors:

    404 Not Found: Task or tag not found.

8. Create Reward

Name: Create Reward
Description: Creates a new reward.
HTTP Method: POST
Path: /rewards
Request Body:


  "name": "string",
  "description": "string",
  "pointsRequired": "number"


Response Body:


  "rewardID": "string",
  "name": "string",
  "description": "string",
  "pointsRequired": "number"


Errors:

    400 Bad Request: Invalid input.

9. Redeem Reward

Name: Redeem Reward
Description: Redeems a reward for the user.
HTTP Method: POST
Path: /rewards/redeem
Request Body:


  "userID": "string",
  "rewardID": "string"


Response Body:


  "userRewardID": "string",
  "userID": "string",
  "rewardID": "string",
  "redeemedAt": "string"


Errors:

    404 Not Found: Reward not found.
    400 Bad Request: Insufficient points.

10. Start Focus Session

Name: Start Focus Session
Description: Starts a focus session for a task.
HTTP Method: POST
Path: /focus-sessions/start
Request Body:


  "userID": "string",
  "taskID": "string",
  "startTime": "string"


Response Body:


  "sessionID": "string",
  "userID": "string",
  "taskID": "string",
  "startTime": "string",
  "duration": "number"


Errors:

    400 Bad Request: Invalid input.

11. End Focus Session

Name: End Focus Session
Description: Ends a focus session for a task.
HTTP Method: POST
Path: /focus-sessions/end
Request Body:


  "sessionID": "string",
  "endTime": "string"


Response Body:


  "sessionID": "string",
  "userID": "string",
  "taskID": "string",
  "startTime": "string",
  "endTime": "string",
  "duration": "number"


Errors:

    404 Not Found: Session not found.

12. Log Mood

Name: Log Mood
Description: Logs the user's mood and energy level.
HTTP Method: POST
Path: /mood-logs
Request Body:


  "userID": "string",
  "mood": "string",
  "energyLevel": "string",
  "loggedAt": "string"


Response Body:


  "moodLogID": "string",
  "userID": "string",
  "mood": "string",
  "energyLevel": "string",
  "loggedAt": "string"

