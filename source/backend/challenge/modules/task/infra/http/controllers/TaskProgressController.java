package backend.challenge.modules.task.infra.http.controllers;

import backend.challenge.modules.task.dtos.TaskProgressDTO;
import backend.challenge.modules.task.infra.http.views.TaskProgressView;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.services.*;
import kikaha.urouting.api.*;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.UUID;

@Singleton
@Path("tasks/progress")
public class TaskProgressController {

	private final IUpdateTaskProgressService updateTaskProgressService;

	@Inject
	public TaskProgressController(final IUpdateTaskProgressService updateTaskProgressService) {
		this.updateTaskProgressService = updateTaskProgressService;
	}

	@PUT
	@Path("single/{taskId}")
	public Response updateProgress(@PathParam("taskId") UUID taskId, TaskProgressView taskProgressView) {
		TaskProgressDTO taskProgressDTO = new TaskProgressDTO();
		taskProgressDTO.setId(taskId);
		taskProgressDTO.setProgress(taskProgressView.getProgress());

		return DefaultResponse.ok().entity(updateTaskProgressService.execute(taskProgressDTO));
	}

}
