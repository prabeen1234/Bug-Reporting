import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { environment } from 'src/app/shared/environment';
import { ChangePassword } from 'src/app/user';

@Injectable({
  providedIn: 'root'
})
export class ChangePasswordService {

  private apiUrl =environment.changepasswordurl;
  constructor(private http:HttpClient){}
  changePassword(user:ChangePassword):Observable<ChangePassword>{
    return this.http.post<ChangePassword>(this.apiUrl, user);
  }
}
