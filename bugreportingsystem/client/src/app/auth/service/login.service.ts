import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginUser } from 'src/app/user';
import { environment } from 'src/app/shared/environment';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  private apiUrl = environment.loginurl;
  constructor(private http: HttpClient) {}
  
  loginUser(user: LoginUser): Observable<LoginUser> {
    return this.http.post<LoginUser>(this.apiUrl, user);
  }
}
