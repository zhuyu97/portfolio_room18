import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ASS_URL } from 'app.constants';

@Injectable({
  providedIn: 'root'
})
export class AllassetsvalueService {

  constructor(private http:HttpClient) { }

  getAllAssetsValue() {
    return this.http.get< any >(`${ASS_URL }/api/holdAssets/getAllAssetsValue`)
  }
}
