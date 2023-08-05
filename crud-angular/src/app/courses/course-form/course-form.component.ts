import { CoursesService } from './../services/courses.service';
import { Course } from './../model/course';
import { Component, OnInit } from '@angular/core';
import { UntypedFormBuilder, UntypedFormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Location } from '@angular/common';

@Component({
  selector: 'app-course-form',
  templateUrl: './course-form.component.html',
  styleUrls: ['./course-form.component.scss']
})
export class CourseFormComponent implements OnInit {

  form: UntypedFormGroup;

  constructor(private fb: UntypedFormBuilder,
    private service: CoursesService,
    private snackBar: MatSnackBar,
    private location: Location) {
    this.form = this.fb.group({
      name: [''],
      category: ['']
    });
  }

  ngOnInit(): void {
  }

  onSubmit() {
    this.service.save(this.form.value as Course)
      .subscribe(result => this.onSuccess(), _error => this.onError());
    }

  onCancel() {
    this.location.back();
  }

  private onSuccess() {
    this.snackBar.open('Curso salvo com sucesso','', {duration: 3000});
    this.location.back();
  }

  private onError() {
    this.snackBar.open('Erro ao salvar o curso','', {duration: 3000});
  }

}
