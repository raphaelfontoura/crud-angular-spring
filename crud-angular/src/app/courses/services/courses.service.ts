import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Course } from './../model/course';
import { delay, first, take, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CoursesService {

  private readonly API = '../../../assets/courses.json'; // http://localhost:8080/api/courses

  constructor(private httpClient: HttpClient) { }

  list() {
    return this.httpClient.get<Course[]>(this.API)
    .pipe(
      // take(1),
      first(),
      // delay(5),
      tap(courses => console.log(courses))
    );
  }

  save(record:Course) {
    return this.httpClient.post<Course>(this.API, record).pipe(first());
  }

}
