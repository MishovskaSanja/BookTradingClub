import { Injectable, Optional } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Book } from '../model/book';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class BookService {

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      Authorization: 'my-auth-token'
    })
  };

  url: string;

  constructor(private http: HttpClient){
    this.url = 'http://localhost:8080/api/books'
  }

  public getAllBooks(): Observable<Book[]>{
    return this.http.get<Book[]>(this.url)
  }

  public addBook(data) : Observable<Book>{
    return this.http.post<Book>('http://localhost:8080/api/user/addBook', data)
  }
}
