import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BuyStockService {
  private buystockUrl = 'http://localhost:8094/api/transactionRecord/buyStock';
  constructor(private http: HttpClient) { }

  saveData(data: any): Observable<any> {
    const url = `${this.buystockUrl}/save`;
    return this.http.post(url, data);
  }
}
