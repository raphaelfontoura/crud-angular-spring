import { Course } from './../../model/course';
import { ErrorDialogComponent } from '../../../shared/components/error-dialog/error-dialog.component';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { CoursesService } from '../../services/courses.service';
import { ActivatedRoute, Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ConfirmationDialogComponent } from 'src/app/shared/components/confirmation-dialog/confirmation-dialog.component';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.scss']
})
export class CoursesComponent implements OnInit {

  courses$: Observable<Course[]> = of([]);

  constructor(
    private coursesService: CoursesService,
    public dialog: MatDialog,
    private router: Router,
    private route: ActivatedRoute,
    private snackBar: MatSnackBar
    ) {
    // this.courses = [];
    // this.coursesService = new CoursesService();
    this.loadCourses();
  }

  ngOnInit(): void {

  }

  loadCourses() {
    this.courses$ = this.coursesService.list()
    .pipe(
      catchError(err => {
        this.onError('Erro ao carregar cursos.');
        return of([])
      })
    );
  }

  onError(errorMsg: string) {
    this.dialog.open(ErrorDialogComponent, {
      data: errorMsg
    });
  }

  onAdd() {
    this.router.navigate(['new'], {relativeTo: this.route});
  }

  onEdit(course: Course) {
    this.router.navigate(['edit', course._id], {relativeTo: this.route});
  }

  onRemove(course: Course) {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      data: "Tem certeza que deseja remover esse curso?"
    });

    dialogRef.afterClosed().subscribe( (result: boolean) => {
      if (result) this.removeCourse(course);
    })

  }

  private removeCourse(course: Course) {
    this.coursesService.remove(course._id).subscribe(
      () => {
        this.loadCourses();
        this.snackBar.open("Curso removido com sucesso", "X", {
          duration: 5000,
          verticalPosition: "top",
          horizontalPosition: "center"
        });
      },
      error => this.onError("Erro ao tentar remover curso.")
    );
  }

}
