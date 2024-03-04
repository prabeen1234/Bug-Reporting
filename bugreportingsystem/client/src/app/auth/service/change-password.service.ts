import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { ChangePassword } from 'src/app/user';

@Injectable({
  providedIn: 'root'
})
export class ChangePasswordService {

  private apiUrl ='http://localhost:8080/api/v1/changePassword';
  constructor(private http:HttpClient){}
  changePassword(user:ChangePassword):Observable<ChangePassword>{
    return this.http.post<ChangePassword>(this.apiUrl, user);
  }
}
