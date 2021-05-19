import { Injectable, Optional } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Book } from '../model/book';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class BookService {

  headers= new HttpHeaders()
  .set('content-type', 'application/json')
  .set('Access-Control-Allow-Origin', '*');

  url: string;

  constructor(private http: HttpClient){
    this.url = 'http://localhost:8083/api/books'
  }

  public getAllBooks(): Observable<Book[]>{
    return this.http.get<Book[]>(this.url)
  }

  public addBook(data) : Observable<Book>{
    return this.http.post<Book>('http://localhost:8083/api/user/addBook', data)
  }

  public editBook(data) : Observable<Book>{
    return this.http.put<Book>(this.url+'/edit', data,  {'headers': this.headers})
  }

  public deleteBook(id:bigint){
    const param = "/delete?id=" + id
    return this.http.delete(this.url+param, {"headers": this.headers})
  }

}
