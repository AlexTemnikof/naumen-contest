import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { PersonListComponent } from './person-list/person-list.component';
import { GetAgeByNameComponent } from './get-age-by-name/get-age-by-name.component';
import {PersonService} from "./person.service";

@NgModule({
  declarations: [
    AppComponent,
    PersonListComponent,
    GetAgeByNameComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [PersonService],
  bootstrap: [AppComponent]
})
export class AppModule { }
