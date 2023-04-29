import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {Observable} from "rxjs";
import {Person} from "./person";


@Injectable()
export class PersonService {

  private apiUrl: string;

  constructor(private http: HttpClient) {
    this.apiUrl = 'http://localhost:8080/api';
  }

  public findAll(): Observable<Person[]> {
    const url = `${this.apiUrl}/persons`;
    return this.http.get<Person[]>(url);
  }

  public findAge(name: String): Observable<Number[]> {
    const url = `${this.apiUrl}/age?name=${name}`;
    return this.http.get<Number[]>(url);
  }

  public getFrequency(): Observable<Map<String, Number>>{
    const url = `${this.apiUrl}/frequency`;
    return this.http.get<Map<String, Number>>(url);
  }

  public getPersonWithMaxAge(): Observable<Person> {
    const url = `${this.apiUrl}/maxage`;
    return this.http.get<Person>(url);
  }

}
