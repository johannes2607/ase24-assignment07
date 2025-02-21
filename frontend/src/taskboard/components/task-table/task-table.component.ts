import {Component, OnInit} from '@angular/core';
import {
  MatTableDataSource
} from "@angular/material/table";
import {TaskDto} from '../../client/model/taskDto';
import {TasksService} from '../../services/tasks.service';
import {CdkDragDrop, CdkDropList, CdkDrag, moveItemInArray, transferArrayItem} from '@angular/cdk/drag-drop';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'taskboard-task-table',
  imports: [
    CdkDropList,
    CdkDrag,
    FormsModule
  ],
  templateUrl: './task-table.component.html',
  styleUrl: './task-table.component.css'
})
export class TaskTableComponent implements OnInit {
  dataSource = new MatTableDataSource<TaskDto>([]);
  todo: TaskDto[] = [];
  doing: TaskDto[] = [];
  done: TaskDto[] = [];
  searchString: string = "";

  constructor(private tasksService: TasksService) {
    this.tasksService = tasksService;
  }

  ngOnInit() {
    this.tasksService.getTasks().then(tasks => {
      this.dataSource.data = tasks;
      this.todo = tasks.filter(task => task.status === 'TODO');
      this.doing = tasks.filter(task => task.status === 'DOING');
      this.done = tasks.filter(task => task.status === 'DONE');
    });
  }

  drop(event: CdkDragDrop<TaskDto[]>) {
    if (event.previousContainer === event.container) {
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    } else {
      transferArrayItem(
        event.previousContainer.data,
        event.container.data,
        event.previousIndex,
        event.currentIndex,
      );

      let movedTask = event.container.data[event.currentIndex];

      if (event.container.id === 'todoList') {
        movedTask.status = 'TODO';
      } else if (event.container.id === 'doingList') {
        movedTask.status = 'DOING';
      } else if (event.container.id === 'doneList') {
        movedTask.status = 'DONE';
      }

      this.tasksService.updateTask(movedTask.id, movedTask).then(() => {});
    }
  }

  filteredTasks(tasks: any[]) {
    if (!this.searchString) {
      return tasks;
    }

    return tasks.filter(task =>
      task.title.toLowerCase().includes(this.searchString.toLowerCase()) ||
      task.description.toLowerCase().includes(this.searchString.toLowerCase()) ||
      (task.assignee?.name && task.assignee.name.toLowerCase().includes(this.searchString.toLowerCase()))
    );
  }
}
