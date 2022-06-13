import { CoursesService } from './../services/courses.service';
import { Course } from './../model/course';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-course-form',
  templateUrl: './course-form.component.html',
  styleUrls: ['./course-form.component.scss']
})
export class CourseFormComponent implements OnInit {

  form: FormGroup;

  constructor(private fb: FormBuilder,
    private service: CoursesService,
    private snackBar: MatSnackBar) {
    this.form = this.fb.group({
      name: [''],
      category: ['']
    });
  }

  ngOnInit(): void {
  }

  onSubmit() {
    this.service.save(this.form.value as Course)
      .subscribe(result => console.log(result), _error => this.onError());
    }

  onCancel() {
    this.form.reset();
  }

  private onError() {
    this.snackBar.open('Erro ao salvar o curso','', {duration: 3000});
  }

}
