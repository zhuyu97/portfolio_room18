import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AllStockService {

  constructor(private http : HttpClient) { }

  getAllStock(){
    return this.http.get<any>("http://localhost:8091/api/stocks/getAll");
  }
}
