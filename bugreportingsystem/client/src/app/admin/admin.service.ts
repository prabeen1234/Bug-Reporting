import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AdminBugDto, BugDto } from '../user';
import { environment } from '../shared/environment';

@Injectable({
  providedIn: 'root'
})
export class AdminService {
  private getBugUrl=environment.getBug;

  constructor(private http:HttpClient) { }

  getBugOfUser():Observable<AdminBugDto[]>{
  
  return this.http.get<AdminBugDto[]>(this.getBugUrl)

  }
}
