import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-course-form',
  templateUrl: './course-form.component.html',
  styleUrls: ['./course-form.component.scss']
})
export class CourseFormComponent implements OnInit {

  form: FormGroup;

  constructor(private fb: FormBuilder) {
    this.form = this.fb.group({
      name: [''],
      category: ['']
    });
   }

  ngOnInit(): void {
  }

  onSubmit() {
    console.log(this.form.value);
  }

  onCancel() {
    this.form.reset();
  }

}
