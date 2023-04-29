import { Component } from '@angular/core';
import {PersonService} from "../person.service";
import {Person} from "../person";

@Component({
  selector: 'app-max-age-form',
  templateUrl: './max-age-form.component.html',
  styleUrls: ['./max-age-form.component.css']
})
export class MaxAgeFormComponent {
  result: String | null;

  constructor(private personService: PersonService) {
    this.result = null;
  }

  getPersonWithMaxAge(): void {
    this.personService.getPersonWithMaxAge().subscribe((data: Person) => {
      this.result = "Person with name " + data.name + " and age of " + data.age;
    });
  }
}
