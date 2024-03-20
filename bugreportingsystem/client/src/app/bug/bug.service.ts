import { Injectable } from '@angular/core';
import { environment } from '../shared/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BugDto } from '../user';
import { Token } from '@angular/compiler';

@Injectable({
  providedIn: 'root',
})
export class BugService {
  private apiUrl = environment.userbug;

  constructor(private http: HttpClient) {}

  addUserBug(bugDto: BugDto, photo: File, video: File): Observable<any> {
    const userToken = localStorage.getItem('token');

    if (!userToken) {
      throw new Error('No token available. User is not logged in.');
    }

    const formData: FormData = new FormData();
    formData.append('photo', photo);
    formData.append('video', video);
    formData.append(
      'bugDto',
      new Blob([JSON.stringify(bugDto)], { type: 'application/json' })
    );

    const headers = new HttpHeaders({
      'Authorization': 'Bearer ${userToken}'
    });

    return this.http.post<any>(this.apiUrl, formData, { headers });
  }
}