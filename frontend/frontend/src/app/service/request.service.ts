import { Injectable, Optional } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Request } from '../model/request';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RequestService {

   headers= new HttpHeaders()
  .set('content-type', 'application/json')
  .set('Access-Control-Allow-Origin', '*');

  url: string;

  constructor(private http: HttpClient){
    this.url = 'http://localhost:8083/api/requests'
  }

  public getAllRequests(): Observable<Request[]>{
    return this.http.get<Request[]>(this.url)
  }

  public getAllTrades(): Observable<Request[]>{
    return this.http.get<Request[]>(this.url+'/accepted')
  }

  //TODO()
  public makeRequest(data) : Observable<Request>{
    return this.http.post<Request>(this.url+'/post',  {"headers": this.headers})
  }

  public acceptRequest(id: bigint) : Observable<Request>{
    const param = "/accept?id=" + id
    return this.http.post<Request>(this.url+param, {"headers": this.headers} )
  }



}
