import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { JsonResult } from '../../app/json-result.model';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map} from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})

export class BackendAPIService {
  
  constructor(private http: HttpClient) {
   
  }
  createCurrency(currency: string): Observable<JsonResult> {
   return this.http.post<JsonResult>(environment.apiURL + 'api/currencies/create-currency', currency).pipe(map((response: JsonResult) => response));
  }

  getAllCurrency():Observable<JsonResult>{
    return this.http.get<JsonResult>(environment.apiURL + 'api/currencies/get-all-currencies').pipe(map((response: JsonResult) => response));

  }

  getCurrency(id: number): Observable<JsonResult> {
    return this.http.get<JsonResult>(environment.apiURL + 'api/currencies/'+ id).pipe(map((response:JsonResult) => response));
  }

  updateCurrency(currency: string):Observable<JsonResult> {
    return this.http.put<JsonResult>(environment.apiURL + 'api/currencies/update-currency',currency).pipe(map((response: JsonResult) => response));
  }

  deleteCurrency(currency: string):Observable<JsonResult> {
    return this.http.delete<JsonResult>(environment.apiURL + 'api/currencies/delete-currency'+currency).pipe(map((response: JsonResult) => response));
  }

}
