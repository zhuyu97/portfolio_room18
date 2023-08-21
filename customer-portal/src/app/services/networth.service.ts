import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { NET_URL } from 'app.constants';

@Injectable({
  providedIn: 'root'
})
export class NetworthService {

  constructor(private http:HttpClient) {}

  getNetWorth(): Observable<any>{
    return this.http.get(`${NET_URL }/api/networth/getAll`)
  }
}
