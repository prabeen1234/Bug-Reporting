import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginUser } from 'src/app/user';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private apiUrl ='http://localhost:8080/api/v1/auth/signin';
  constructor(private http:HttpClient){}
  loginUser(user:LoginUser):Observable<LoginUser>{
    return this.http.post<LoginUser>(this.apiUrl, user);
  }}
