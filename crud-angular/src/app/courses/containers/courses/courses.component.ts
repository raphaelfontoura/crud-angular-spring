import { Course } from './../../model/course';
import { ErrorDialogComponent } from '../../../shared/components/error-dialog/error-dialog.component';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Observable, of } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';

import { CoursesService } from '../../services/courses.service';
import { ActivatedRoute, Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ConfirmationDialogComponent } from 'src/app/shared/components/confirmation-dialog/confirmation-dialog.component';
import { CoursePage } from '../../model/course-page';
import { MatPaginator, PageEvent } from '@angular/material/paginator';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.scss']
})
export class CoursesComponent implements OnInit {

  courses$: Observable<CoursePage> | null = null;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  pageIndex: number = 0;
  pageSize: number = 10;

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

  loadCourses(pageEvent: PageEvent = {length: 0, pageIndex: 0, pageSize: 10}) {
    this.courses$ = this.coursesService.list(pageEvent.pageIndex, pageEvent.pageSize)
    .pipe(
      tap(() => {
        this.pageIndex = pageEvent.pageIndex;
        this.pageSize = pageEvent.pageSize;
      }),
      catchError(err => {
        this.onError('Erro ao carregar cursos.');
        return of({
          content: [],
          totalElements: 0,
          totalPages: 0,
          size: 0,
          number: 0,
          sort: [],
          first: true,
          last: true,
          numberOfElements: 0
        } as CoursePage);
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
