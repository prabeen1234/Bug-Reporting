import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { ChangePassword } from 'src/app/user';
import { environment } from 'src/shared/environment';

@Injectable({
  providedIn: 'root'
})
export class ChangePasswordService {

  private apiUrl = environment.changepasswordurl;
  constructor(private http:HttpClient){}
  changePassword(user:ChangePassword):Observable<ChangePassword>{
    return this.http.post<ChangePassword>(this.apiUrl, user);
  }
}
