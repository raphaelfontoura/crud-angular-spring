import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { NonNullableFormBuilder } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Course } from './../model/course';
import { CoursesService } from './../services/courses.service';

@Component({
  selector: 'app-course-form',
  templateUrl: './course-form.component.html',
  styleUrls: ['./course-form.component.scss']
})
export class CourseFormComponent implements OnInit {

  form = this.formBuilder.group({
    name: [''],
    category: ['']
  });

  constructor(private formBuilder: NonNullableFormBuilder,
    private service: CoursesService,
    private snackBar: MatSnackBar,
    private location: Location) {
    // this.form
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
