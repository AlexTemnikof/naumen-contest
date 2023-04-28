import { Component } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {PersonService} from "../person.service";

@Component({
  selector: 'app-frequency-form',
  templateUrl: './frequency-form.component.html',
  styleUrls: ['./frequency-form.component.css']
})

export class FrequencyFormComponent {
  requestCount: Map<String, Number> | undefined;

  constructor(private personService: PersonService) {}

  getRequestCount(): void {
    this.personService.getFrequency()
      .subscribe(data => {
        this.requestCount = data;
      });
  }
}
