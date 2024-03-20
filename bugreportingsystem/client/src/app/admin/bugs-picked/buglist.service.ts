import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/app/shared/environment';

@Injectable({
  providedIn: 'root'
})
export class BuglistService {
  private apiUrl = environment.getBug; 

  constructor(private http: HttpClient) { }

  getBugOfUser(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/admin/buglist`); 
  }
}
