import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/app/shared/environment';
import { RegisterAdmin } from 'src/app/user';

@Injectable({
  providedIn: 'root'
})
export class AdminRegisterService {

  public user!: RegisterAdmin;
  
  apiUrl = environment.adminregisterurl;
  userRegisterUrl=environment.adminregisterurl;
  constructor(private http: HttpClient) {}

  registerUser(user: RegisterAdmin): Observable<RegisterAdmin> {
    return this.http.post<RegisterAdmin>(this.apiUrl, user);
  }
}
