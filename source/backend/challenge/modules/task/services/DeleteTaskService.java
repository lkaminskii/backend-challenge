package backend.challenge.modules.task.services;

import backend.challenge.modules.task.repositories.ITaskRepository;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.UUID;

@Singleton
public class DeleteTaskService implements IDeleteTaskService {

	private final ITaskRepository taskRepository;

	@Inject
	public DeleteTaskService(final ITaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	@Override
	public void execute(UUID taskId) {
		if (!taskRepository.existsById(taskId)) {
			throw new RuntimeException("The id provided does not exist");
		}
		taskRepository.delete(taskId);
	}

}
