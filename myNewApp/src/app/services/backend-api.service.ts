import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import {  map, Observable } from 'rxjs';
import { JsonResult } from '../../app/json-result.model';
import { HttpClient } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})

export class BackendAPIService {
  
  constructor(private http: HttpClient) {
   
  }
  createCurrency(currency: string): Observable<JsonResult> {
    // tslint:disable-next-line: max-line-length
   return this.http.post(environment.apiURL + 'api/currencies/create-currency', currency).pipe(map((response: JsonResult) => response));

  }

  getAllCurrency():Observable<JsonResult>{
    return this.http.get(environment.apiURL + 'api/currencies/get-all-currencies').pipe(map((response: JsonResult) => response));

  }

  getCurrency(id: number): Observable<JsonResult> {
    // tslint:disable-next-line: max-line-length
    return this.http.get(environment.apiURL + 'api/currencies/'+ id).pipe(map((response: JsonResult) => response));
  }

  updateCurrency(currency: string):Observable<JsonResult> {
    // tslint:disable-next-line: max-line-length
    return this.http.put(environment.apiURL + 'api/currencies/update-currency',currency).pipe(map((response: JsonResult) => response));
  }

  deleteCurrency(currency: string):Observable<JsonResult> {
    // tslint:disable-next-line: max-line-length
    return this.http.delete(environment.apiURL + 'api/currencies/delete-currency'+currency).pipe(map((response: JsonResult) => response));
  }

}
