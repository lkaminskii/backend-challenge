package backend.challenge.modules.task.services;

import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.ITaskRepository;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.UUID;


@Singleton
public class RetrieveTaskByIdService implements IRetrieveTaskByIdService{

    private final ITaskRepository taskRepository;

    @Inject
    public RetrieveTaskByIdService(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    @Override
    public Task execute(UUID taskId) {
        if (!taskRepository.existsById(taskId)) {
            throw new RuntimeException("The id provided does not exist");
        }
        return taskRepository.index(taskId);
    }
}
