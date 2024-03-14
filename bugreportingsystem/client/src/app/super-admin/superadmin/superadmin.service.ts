import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { RegisterUser } from '../../user';
import { environment } from '../../shared/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class SuperadminService {
  apiUrl = environment.adminregisterurl;

  constructor(private http: HttpClient) {}

  registerAdminBySuperAdmin(user: RegisterUser): Observable<RegisterUser> {
    return this.http.post<RegisterUser>(this.apiUrl, user);
  }
}
