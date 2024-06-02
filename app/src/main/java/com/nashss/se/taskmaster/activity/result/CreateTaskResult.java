package com.nashss.se.taskmaster.activity.result;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nashss.se.taskmaster.models.TaskModel;

public class CreateTaskResult {
    private final Logger log = LogManager.getLogger();
    private final TaskModel taskModel;

    private CreateTaskResult(TaskModel taskModel) {
        log.info("TaskModel: {}", taskModel);
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
