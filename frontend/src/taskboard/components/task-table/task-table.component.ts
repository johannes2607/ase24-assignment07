import {Component, OnInit} from '@angular/core';
import {
  MatTableDataSource
} from "@angular/material/table";
import {TaskDto} from '../../client/model/taskDto';
import {TasksService} from '../../services/tasks.service';
import {CdkDragDrop, CdkDropList, CdkDrag, moveItemInArray, transferArrayItem} from '@angular/cdk/drag-drop';

@Component({
  selector: 'taskboard-task-table',
  imports: [
    CdkDropList,
    CdkDrag
  ],
  templateUrl: './task-table.component.html',
  styleUrl: './task-table.component.css'
})
export class TaskTableComponent implements OnInit {
  displayedColumns: string[] = ['title', 'description', 'status', 'assignee'];
  dataSource = new MatTableDataSource<TaskDto>([]);
  todo = ['Get to work', 'Pick up groceries', 'Go home', 'Fall asleep'];
  done = ['Get up', 'Brush teeth', 'Take a shower', 'Check e-mail', 'Walk dog'];

  constructor(private tasksService: TasksService) {
    this.tasksService = tasksService;
  }

  ngOnInit() {
    this.tasksService.getTasks().then(tasks => {
      this.dataSource.data = tasks;
    });
  }

  drop(event: CdkDragDrop<string[]>) {
    if (event.previousContainer === event.container) {
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    } else {
      transferArrayItem(
        event.previousContainer.data,
        event.container.data,
        event.previousIndex,
        event.currentIndex,
      );
    }
  }
}
