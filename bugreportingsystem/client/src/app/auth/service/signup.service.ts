import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RegisterUser } from 'src/app/user';
import { environment } from 'src/app/shared/environment';

@Injectable({
  providedIn: 'root',
})
export class SignupService {
  public user!: RegisterUser;

  apiUrl = environment.registerurl;
  constructor(private http: HttpClient) {}

  registerUser(user: RegisterUser): Observable<RegisterUser> {
    return this.http.post<RegisterUser>(this.apiUrl, user);
  }
}
