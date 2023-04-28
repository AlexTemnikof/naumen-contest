import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PersonListComponent } from './person-list/person-list.component';
import {GetAgeByNameComponent} from "./get-age-by-name/get-age-by-name.component";
import {FrequencyFormComponent} from "./frequency-form/frequency-form.component";

const routes: Routes = [
  { path: 'persons', component: PersonListComponent },
  { path: 'age', component: GetAgeByNameComponent },
  { path: 'frequency', component: FrequencyFormComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
