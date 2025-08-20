import { Course } from "./course";

export interface CoursePage {
  content: Course[];
  totalElements: number;
  totalPages: number;
  size: number;
  number: number;
  sort: string[];
  first: boolean;
  last: boolean;
  numberOfElements: number;
}
