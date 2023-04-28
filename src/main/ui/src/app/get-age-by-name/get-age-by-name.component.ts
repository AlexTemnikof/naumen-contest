import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {PersonService} from "../person.service";

@Component({
  selector: 'app-get-age-by-name',
  templateUrl: './get-age-by-name.component.html',
  styleUrls: ['./get-age-by-name.component.css']
})
export class GetAgeByNameComponent implements OnInit {

  ages!: Number[];
  nameForm!: FormGroup;

  constructor(private formBuilder: FormBuilder, private personService: PersonService) { }

  ngOnInit() {
    this.nameForm = this.formBuilder.group({
      name: ['', Validators.required]
    });
  }

  onSubmit() {
    const name = this.nameForm.controls['name'].value;
    this.personService.findAge(name).subscribe((data: Number[]) => {
      this.ages = data;
    });
  }

}
