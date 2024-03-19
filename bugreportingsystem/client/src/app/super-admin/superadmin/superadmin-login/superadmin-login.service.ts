import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/app/shared/environment';
import { LoginSuperAdmin } from 'src/app/superaddmin';

@Injectable({
  providedIn: 'root'
})
export class SuperadminLoginService {

  private apiUrl = environment.loginurl;
  constructor(private http: HttpClient) {}
  
  loginSuperAdmin(sadmin: LoginSuperAdmin): Observable<LoginSuperAdmin> {
    return this.http.post<LoginSuperAdmin>(this.apiUrl, sadmin);
  }}
