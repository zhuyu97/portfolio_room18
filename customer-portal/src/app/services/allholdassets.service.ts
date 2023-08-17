import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ASS_URL } from 'app.constants';
import { AllHoldAssets } from '../model/AllHoldAssets';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AllholdassetsService {

  constructor(private http:HttpClient) {}

  getAllAssetsValue(): Observable<any>{
    return this.http.get(`${ASS_URL }/api/holdAssets/getAll`)
  }
}
