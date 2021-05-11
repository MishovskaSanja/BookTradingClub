import { Injectable, Optional } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Book } from '../model/book';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class BookService {

  url: string;

  constructor(private http: HttpClient){
    this.url = 'http://localhost:8083/api/books'
  }

  public getAllBooks(): Observable<Book[]>{
    return this.http.get<Book[]>(this.url)
  }

  //TODO: addBook not working
  public addBook(){
    return ;
  }
}
