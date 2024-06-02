package com.nashss.se.taskmaster.activity.result;

import com.nashss.se.taskmaster.models.TaskModel;

public class CreateTaskResult {
    private final TaskModel taskModel;

    private CreateTaskResult(TaskModel taskModel) {
        this.taskModel = taskModel;
    }

    public TaskModel getTaskModel() {
        return taskModel;
    }

    @Override
    public String toString() {
        return "CreateTaskResult{" +
        "TaskModel='" + taskModel + '\'' +
        "}";
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private TaskModel taskModel;

        public Builder withTaskModel(TaskModel taskModel) {
            this.taskModel = taskModel;
            return this;
        }

        public CreateTaskResult build() {
            return new CreateTaskResult(taskModel);
        }
    }
}
