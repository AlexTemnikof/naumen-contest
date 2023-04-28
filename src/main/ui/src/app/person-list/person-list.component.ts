import { Component, OnInit } from '@angular/core';
import {Person} from "../person";
import {PersonService} from "../person.service";


@Component({
  selector: 'app-user-list',
  templateUrl: './person-list.component.html',
  styleUrls: ['./person-list.component.css']
})
export class PersonListComponent implements OnInit {

  persons: Person[];

  constructor(private userService: PersonService) {
    this.persons = [];
  }

  ngOnInit() {
    this.userService.findAll().subscribe((data: Person[]) => {
      this.persons = data;
    });
  }
}
