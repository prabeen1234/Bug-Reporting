import { Injectable } from '@angular/core';
import { environment } from '../shared/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BugDto } from '../user';

@Injectable({
  providedIn: 'root',
})
export class BugService {
  private apiUrl = environment.userbug;
  constructor(private http: HttpClient) {}

  addUserBug(bugDto: BugDto, photo: File, video: File): Observable<any> {
    debugger;
    const formData: FormData = new FormData();
    formData.append('photo', photo);
    formData.append('video', video);
    formData.append(
      'bugDto',
      new Blob([JSON.stringify(bugDto)], { type: 'application/json' })
    );
    return this.http.post<any>(this.apiUrl, formData);
  }
}
