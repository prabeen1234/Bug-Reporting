import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/app/shared/environment';
import { LoginSuperAdmin } from 'src/app/superaddmin';
import { LoginUser } from 'src/app/user';

@Injectable({
  providedIn: 'root'
})
export class SuperadminLoginService {

  private apiUrl = environment.loginurl;
  constructor(private http: HttpClient) {}
  
  loginUser(user: LoginUser): Observable<LoginUser> {
    return this.http.post<LoginUser>(this.apiUrl, user);
  }
}
